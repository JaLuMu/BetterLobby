package de.jalumu.betterlobby.listener;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.gui.ItemHelper;
import de.jalumu.betterlobby.manager.BuildManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class InventoryListener implements Listener, Configurable {

    @Override
    public void defaultConfiguration() {
        BetterLobby.getConfiguration().addDefault("inventory.hotbar.sound.enabled",true);
        BetterLobby.getConfiguration().addDefault("inventory.hotbar.sound.sound",Sound.CLICK.toString());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
       if (event.getItem() != null){
           ItemHelper.handle(event.getItem(),event.getPlayer());
       }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ItemHelper.handle(event.getCurrentItem(), player);
        if (!BuildManager.canBuild(player)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onHotbarSwitch(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        if (BetterLobby.getConfiguration().getBoolean("inventory.hotbar.sound.enabled") && !BuildManager.canBuild(player)) {
            player.playSound(
                    player.getLocation(),
                    Sound.valueOf(BetterLobby.getConfiguration().getString("inventory.hotbar.sound.sound")),
                    3, 2);
        }
    }


}
