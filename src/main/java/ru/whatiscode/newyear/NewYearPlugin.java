package ru.whatiscode.newyear;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.plugin.java.JavaPlugin;
import ru.whatiscode.newyear.presents.enums.Gifts;
import ru.whatiscode.newyear.presents.interfaces.impl.GiftManagerImpl;
import ru.whatiscode.newyear.presents.listeners.ClaimGiftListener;
import ru.whatiscode.newyear.snow.command.SnowCommand;
import ru.whatiscode.newyear.snow.interfaces.impl.SnowManagerImpl;
import ru.whatiscode.newyear.snow.tasks.SnowTask;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NewYearPlugin extends JavaPlugin {

    public static NewYearPlugin instance;
    @NonFinal SnowManagerImpl snowManager;

    @NonFinal
    GiftManagerImpl giftManager;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;

        giftManager = new GiftManagerImpl();
        snowManager = new SnowManagerImpl();

//        new SnowTask(snowManager).runTaskTimer(this, 0, 5);

        getServer().getPluginManager().registerEvents(new ClaimGiftListener(giftManager), this);

        for (Gifts gifts : Gifts.values()) {
            setupGift(gifts.getLocation());
            giftManager.startParticleEffect(gifts);
        }

        getCommand("snow").setExecutor(new SnowCommand(snowManager));
    }

    @Override
    public void onDisable() {

        for (Gifts gifts : Gifts.values()) {
            giftManager.stopParticleEffect(gifts);
        }
    }

    private void setupGift(Location location) {
        Block block = location.getBlock();
        block.setType(Material.SKULL);
        Skull skull = (Skull) block.getState();
        skull.setSkullType(SkullType.PLAYER);
        skull.update();
    }
}