package ru.whatiscode.newyear.snow.interfaces.impl;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ru.whatiscode.newyear.NewYearPlugin;
import ru.whatiscode.newyear.snow.enums.SnowStatus;
import ru.whatiscode.newyear.snow.interfaces.ISnow;

import java.util.HashMap;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SnowManagerImpl implements ISnow {

    Map<Player, SnowStatus> SNOW_PLAYERS_MAP = new HashMap<>();

    @Override
    public void setStatus(Player player, SnowStatus snowStatus) {
        SNOW_PLAYERS_MAP.put(player, snowStatus);

        if (snowStatus == SnowStatus.ENABLE) {
            playSnow(player);
        }
    }

    @Override
    public boolean isEnable(Player player) {
        return getStatus(player) == SnowStatus.ENABLE;
    }

    @Override
    public void playSnow(Player player) {
        Bukkit.getScheduler().runTaskTimer(NewYearPlugin.instance, new Runnable() {
            final int radius = 20;
            final int height = 15;
            final int particle = 50;

            @Override
            public void run() {
                if (!player.isOnline() || getStatus(player) != SnowStatus.ENABLE) {
                    Bukkit.getScheduler().cancelTask(this.hashCode());
                    return;
                }

                Location playerLocation = player.getLocation();

                for (int i = 0; i < particle; i++) {
                    double x = (Math.random() - 0.5) * 2 * radius;
                    double y = (Math.random() - 0.5) * 2 * radius;
                    double z = Math.random() * height;

                    Location snowLocation = playerLocation.clone().add(x, y, z);

                    player.getWorld().playEffect(snowLocation, Effect.FIREWORKS_SPARK, 0);
                }
            }
        }, 0L, 2L);
    }

    @Override
    public SnowStatus getStatus(Player player) {
        return SNOW_PLAYERS_MAP.getOrDefault(player, SnowStatus.DISABLE);
    }
}
