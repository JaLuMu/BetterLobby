package de.jalumu.betterlobby.gui;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class Inventory implements Configurable {

    private static ItemStack teleporter;

    private static ItemStack playerVisibility;

    private static ItemStack lobbySwitcher;

    private static ItemStack sword;

    private static InventoryItem friends;

    public static void openTeleporter(Player p) {
        Teleporter.open(p);
    }

    public static void openPlayerVisibilityMenu(Player p) {
        PlayerVisibilityMenu.open(p);
    }

    public static void openLobbySwitcher(Player p) {
        if (BetterLobby.isCloudnetApiEnabled()) {
            LobbySwitcher.open(p);
        } else {
            p.sendMessage("disabled");
        }
    }

    public static void createBar(Player p) {

        if (BetterLobby.getConfiguration().getBoolean("inventory.teleporter.enabled")) {
            if (teleporter == null) {
                teleporter = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.teleporter.item"));
                ItemHelper.registerClickEvent(teleporter, player -> openTeleporter(player));
            }
            p.getInventory().setItem(BetterLobby.getConfiguration().getInt("inventory.teleporter.slotId"), teleporter);
        }


        if (BetterLobby.getConfiguration().getBoolean("inventory.playerVisibilityMenu.enabled")) {
            if (playerVisibility == null) {
                playerVisibility = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.playerVisibilityMenu.item"));
                ItemHelper.registerClickEvent(playerVisibility, player -> openPlayerVisibilityMenu(player));
            }
            p.getInventory().setItem(BetterLobby.getConfiguration().getInt("inventory.playerVisibilityMenu.slotId"), playerVisibility);
        }

        if (BetterLobby.getConfiguration().getBoolean("inventory.lobbySwitcher.enabled")) {
            if (lobbySwitcher == null) {
                lobbySwitcher = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.lobbySwitcher.item"));
                ItemHelper.registerHotbarClickEvent(BetterLobby.getConfiguration().getInt("inventory.lobbySwitcher.slotId"), player -> openLobbySwitcher(player));
            }
            p.getInventory().setItem(BetterLobby.getConfiguration().getInt("inventory.lobbySwitcher.slotId"), lobbySwitcher);
        }


        if (BetterLobby.getConfiguration().getBoolean("inventory.friends.enabled")) {

            if (friends == null) {
                friends = (InventoryItem) BetterLobby.getConfiguration().get("inventory.friends.item");
                ItemHelper.registerHotbarClickEvent(friends.getIndex(), new ClickEvent() {
                    @Override
                    public void onClick(Player player) {
                        if (friends.getClickAction() == ClickAction.COMMAND) {
                            ClickEvents.commandEvent(player, friends.getTarget());
                        } else if (friends.getClickAction() == ClickAction.TELEPORT) {
                            ClickEvents.teleportEvent(player, friends.getTarget());
                        }
                    }
                });
            }
            if (friends.getItemStack().getType() == Material.SKULL_ITEM) {
                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

                skullMeta.setOwner(p.getName());
                skullMeta.setDisplayName(TextUtil.parse(friends.getItemStack().getItemMeta().getDisplayName()));
                skullMeta.setLore(TextUtil.parse(friends.getItemStack().getItemMeta().getLore()));
                skull.setItemMeta(skullMeta);
                p.getInventory().setItem(friends.getIndex(), skull);
            } else {
                p.getInventory().setItem(friends.getIndex(), friends.getItemStack());
            }

        }

        if (BetterLobby.getConfiguration().getBoolean("inventory.sword.enabled")) {
            if (sword == null) {
                sword = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.sword.item"));
            }
            p.getInventory().setItem(BetterLobby.getConfiguration().getInt("inventory.sword.slotId"), sword);
        }

        p.updateInventory();

    }

    @Override
    public void defaultConfiguration() {
        BetterLobby.getConfiguration().addDefault("inventory.teleporter.enabled", true);
        BetterLobby.getConfiguration().addDefault("inventory.teleporter.slotId", 0);

        ItemStack teleporter = ItemHelper.getItemStack(Material.COMPASS, "&6&lTeleporter", 1, Arrays.asList("&4Teleports you to another location"));
        BetterLobby.getConfiguration().addDefault("inventory.teleporter.item", teleporter);

        BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.enabled", true);
        BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.slotId", 1);

        ItemStack playerVisibility = ItemHelper.getItemStack(Material.BLAZE_ROD, "&6&lHide Players", 1, Arrays.asList("&4Show or Hide Players"));
        BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.item", playerVisibility);

        BetterLobby.getConfiguration().addDefault("inventory.lobbySwitcher.enabled", true);
        BetterLobby.getConfiguration().addDefault("inventory.lobbySwitcher.slotId", 7);

        ItemStack lobbySwitcher = ItemHelper.getItemStack(Material.NETHER_STAR, "&6&lLobbySwitcher", 1, Arrays.asList("&4Switch Lobby Server"));
        BetterLobby.getConfiguration().addDefault("inventory.lobbySwitcher.item", lobbySwitcher);

        BetterLobby.getConfiguration().addDefault("inventory.friends.enabled", true);
        InventoryItem friends = new InventoryItem(8, ItemHelper.getItemStack(Material.SKULL_ITEM, "&6&lFriends", 1, Arrays.asList("&4Show or Hide Players")), ClickAction.COMMAND, "friends");
        BetterLobby.getConfiguration().addDefault("inventory.friends.item", friends);

        BetterLobby.getConfiguration().addDefault("inventory.sword.enabled", true);
        BetterLobby.getConfiguration().addDefault("inventory.sword.slotId", 3);
        ItemStack sword = ItemHelper.getItemStack(Material.IRON_SWORD, "&6&lLets Fight", 1, Arrays.asList("&4Fight with other Players"));
        BetterLobby.getConfiguration().addDefault("inventory.sword.item", sword);
    }

}
