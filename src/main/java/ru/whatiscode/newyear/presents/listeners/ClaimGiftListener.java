package ru.whatiscode.newyear.presents.listeners;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import ru.whatiscode.newyear.presents.enums.Gifts;
import ru.whatiscode.newyear.presents.interfaces.impl.GiftManagerImpl;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ClaimGiftListener implements Listener {

    GiftManagerImpl giftManager;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;

        val block = event.getClickedBlock();

        if (block == null || block.getType() != Material.SKULL) return;

        val player = event.getPlayer();

        for (Gifts gifts : Gifts.values()) {
            if (block.getLocation().equals(gifts.getLocation())) {
                if (giftManager.isGiftClaim(player, gifts)) {
                    player.sendMessage(ChatColor.RED + "Этот подарок уже собран!");
                } else {
                    giftManager.claim(player, gifts);
                }
                event.setCancelled(true);
                return;
            }
        }
    }
}
