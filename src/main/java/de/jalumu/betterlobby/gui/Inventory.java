package de.jalumu.betterlobby.gui;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Bukkit;
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

	@Override
	public void defaultConfiguration() {
		BetterLobby.getConfiguration().addDefault("inventory.teleporter.enabled",true);
		BetterLobby.getConfiguration().addDefault("inventory.teleporter.slotId",0);

		ItemStack teleporter = ItemHelper.getItemStack(Material.COMPASS, "&6&lTeleporter", 1, Arrays.asList("&4Teleports you to another location"));
		BetterLobby.getConfiguration().addDefault("inventory.teleporter.item",teleporter);


		BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.enabled",true);
		BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.slotId",1);

		ItemStack playerVisibility = ItemHelper.getItemStack(Material.BLAZE_ROD, "&6&lHide Players", 1, Arrays.asList("&4Show or Hide Players"));
		BetterLobby.getConfiguration().addDefault("inventory.playerVisibilityMenu.item",playerVisibility);

		BetterLobby.getConfiguration().addDefault("inventory.lobbySwitcher.enabled",true);
		BetterLobby.getConfiguration().addDefault("inventory.lobbySwitcher.slotId",7);

		ItemStack lobbySwitcher = ItemHelper.getItemStack(Material.NETHER_STAR, "&6&lLobbySwitcher", 1, Arrays.asList("&4Switch Lobby Server"));
		BetterLobby.getConfiguration().addDefault("inventory.lobbySwitcher.item",lobbySwitcher);

		BetterLobby.getConfiguration().addDefault("inventory.friends.enabled",true);
		BetterLobby.getConfiguration().addDefault("inventory.friends.slotId",8);
		BetterLobby.getConfiguration().addDefault("inventory.friends.name","&6&lFriends");
	}


	public static void openTeleporter(Player p) {
		Teleporter.open(p);
	}

	public static void openPlayerVisibilityMenu(Player p) {
		PlayerVisibilityMenu.open(p);
	}

	public static void openLobbySwitcher(Player p) {
		if (BetterLobby.isCloudnetApiEnabled()) {
			LobbySwitcher.open(p);
		}else {
			p.sendMessage("disabled");
		}
	}

	public static void openFriends(Player p) {
		Friends.open(p);
	}
	
	public static void createBar(Player p) {

		if (BetterLobby.getConfiguration().getBoolean("inventory.teleporter.enabled")) {
			if (teleporter == null){
				teleporter = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.teleporter.item"));
				ItemHelper.registerClickEvent(teleporter,player -> openTeleporter(player));
			}
			p.getInventory().setItem(BetterLobby.getConfiguration().getInt("inventory.teleporter.slotId"), teleporter);
		}



		if (BetterLobby.getConfiguration().getBoolean("inventory.playerVisibilityMenu.enabled")) {
			if (playerVisibility == null){
				playerVisibility = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.playerVisibilityMenu.item"));
				ItemHelper.registerClickEvent(playerVisibility,player -> openPlayerVisibilityMenu(player));
			}
			p.getInventory().setItem(BetterLobby.getConfiguration().getInt("inventory.playerVisibilityMenu.slotId"), playerVisibility);
		}

		if (BetterLobby.getConfiguration().getBoolean("inventory.lobbySwitcher.enabled")) {
			if (lobbySwitcher == null){
				lobbySwitcher = TextUtil.parse(BetterLobby.getConfiguration().getItemStack("inventory.lobbySwitcher.item"));
				ItemHelper.registerClickEvent(lobbySwitcher,player -> openLobbySwitcher(player));
			}
			p.getInventory().setItem(BetterLobby.getConfiguration().getInt("inventory.lobbySwitcher.slotId"), lobbySwitcher);
		}

		if (BetterLobby.getConfiguration().getBoolean("inventory.friends.enabled")) {

			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

			SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

			skullMeta.setOwner(p.getName());
			skullMeta.setDisplayName(TextUtil.parse(BetterLobby.getConfiguration().getString("inventory.friends.name")));
			skull.setItemMeta(skullMeta);

			p.getInventory().setItem(BetterLobby.getConfiguration().getInt("inventory.friends.slotId"), skull);
		}

		p.updateInventory();

	}

}
