package de.jalumu.betterlobby.gui;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.tools.HidePlayers;
import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class PlayerVisibilityMenu implements Configurable {

    private static String title;

    private static ItemStack showAll;
    private static ItemStack showVIP;
    private static ItemStack hideAll;

    @Override
    public void defaultConfiguration() {

        BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.title","&c&lHide Players");

        ItemStack showAll = ItemHelper.getItemStack(Material.EMERALD, "&2Show all", 1,Arrays.asList());
        BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.content.showAll",showAll);

        ItemStack showVIP = ItemHelper.getItemStack(Material.GOLD_INGOT, "&6Show VIP", 1,Arrays.asList());
        BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.content.showVIP",showVIP);

        ItemStack hideAll = ItemHelper.getItemStack(Material.REDSTONE, "&4Hide All", 1,Arrays.asList());
        BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.content.hideAll",hideAll);
    }

    protected static void open(Player player){

        if (title == null){
            title = TextUtil.parse(BetterLobby.getConfiguration().getString("inventory.playerVisibilityMenu.title"));
        }

        Inventory inventory = Bukkit.createInventory(player, InventoryType.HOPPER,title);

        if (showAll == null){
            showAll = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.playerVisibilityMenu.content.showAll"));
            ItemHelper.registerClickEvent(showAll,p -> HidePlayers.showAllPlayers(p));
        }
        inventory.setItem(4,showAll);

        if (showVIP == null){
            showVIP = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.playerVisibilityMenu.content.showVIP"));
            ItemHelper.registerClickEvent(showVIP,p -> HidePlayers.showVipPlayers(p));
        }
        inventory.setItem(2,showVIP);

        if (hideAll == null){
            hideAll = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.playerVisibilityMenu.content.hideAll"));
            ItemHelper.registerClickEvent(hideAll,p -> HidePlayers.hideAllPlayers(p));
        }
        inventory.setItem(0,hideAll);



        player.openInventory(inventory);
    }



}
