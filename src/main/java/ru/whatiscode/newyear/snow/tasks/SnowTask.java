package ru.whatiscode.newyear.snow.tasks;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.whatiscode.newyear.snow.interfaces.impl.SnowManagerImpl;

@RequiredArgsConstructor
public class SnowTask  {

    private final SnowManagerImpl snowManager;

/*    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (snowManager.isEnable(player)) {
                snowManager.playSnow(player, 5);
            }
        }
    }*/
}
