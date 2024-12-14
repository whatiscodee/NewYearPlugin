package ru.whatiscode.newyear.snow.interfaces;

import org.bukkit.entity.Player;
import ru.whatiscode.newyear.snow.enums.SnowStatus;

public interface ISnow {

    void setStatus(Player player, SnowStatus status);

    boolean isEnable(Player player);

    void playSnow(Player player);

    SnowStatus getStatus(Player player);
}
