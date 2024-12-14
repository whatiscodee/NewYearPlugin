package ru.whatiscode.newyear.snow.command;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.whatiscode.newyear.snow.enums.SnowStatus;
import ru.whatiscode.newyear.snow.interfaces.impl.SnowManagerImpl;

@RequiredArgsConstructor
public class SnowCommand implements CommandExecutor {

    private final SnowManagerImpl snowManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("ты не плеер");
            return true;
        }

        val player = (Player) sender;

        val currentStatus = snowManager.getStatus(player);

        val newStatus = (currentStatus == SnowStatus.ENABLE) ? SnowStatus.DISABLE : SnowStatus.ENABLE;

        snowManager.setStatus(player, newStatus);
        return true;
    }
}
