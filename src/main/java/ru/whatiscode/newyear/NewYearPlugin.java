package ru.whatiscode.newyear;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class NewYearPlugin extends JavaPlugin {

    public static NewYearPlugin instance;

    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }
}