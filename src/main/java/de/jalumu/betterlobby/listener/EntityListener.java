package de.jalumu.betterlobby.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class EntityListener implements Listener {
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() != EntityType.PLAYER) {
            event.getRightClicked().setPassenger(event.getPlayer());
        }
    }
}
