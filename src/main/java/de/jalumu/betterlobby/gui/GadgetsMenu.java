package de.jalumu.betterlobby.gui;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class GadgetsMenu implements Configurable {

    private static String title;

    @Override
    public void defaultConfiguration() {
        BetterLobby.getConfiguration().addDefault("inventory.gadgets.title","&bGadgets");
    }

    protected static void open(Player player){

        if (title == null){
            title = TextUtil.parse(BetterLobby.getConfiguration().getString("inventory.teleporter.title"));
        }

        Inventory inventory =  Bukkit.createInventory(player,9,title);
        player.openInventory(inventory);
    }

    protected static void handleClickEvent(InventoryClickEvent event) {
    }
}
