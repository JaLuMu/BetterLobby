package de.jalumu.betterlobby.gui;


import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Teleporter implements Configurable {

	private static String title;

	private static List<InventoryItem> items;

	@Override
	public void defaultConfiguration() {

		BetterLobby.getConfiguration().addDefault("inventory.teleporter.title","&c&lTeleporter");

		List<InventoryItem> items = new ArrayList<>();

		InventoryItem demoItem1 = new InventoryItem(0,ItemHelper.getItemStack(Material.PAPER,"This is an example Item",1, Arrays.asList("with ItemMeta")),ClickAction.TELEPORT,"spawn");
		items.add(demoItem1);

		InventoryItem demoItem2 = new InventoryItem(4,ItemHelper.getItemStack(Material.GLOWSTONE,"Test"),ClickAction.NOTHING,null);
		items.add(demoItem2);

		InventoryItem demoItem3 = new InventoryItem(8,ItemHelper.getItemStack(Material.BREAD,"Hunger Games"),ClickAction.COMMAND,"give @p paper");
		items.add(demoItem3);

		BetterLobby.getConfiguration().addDefault("inventory.teleporter.content",items);

	}

	
	protected static void open(Player player){

		if (items == null){
			items = (List<InventoryItem>) BetterLobby.getConfiguration().getList("inventory.teleporter.content");

			for (InventoryItem item : items){
				if (item.getClickAction() == ClickAction.TELEPORT){
					ItemHelper.registerClickEvent(item.getItemStack(), player1 -> ClickEvents.teleportEvent(player1,item.getTarget()));
				}else if (item.getClickAction() == ClickAction.COMMAND){
					ItemHelper.registerClickEvent(item.getItemStack(), player1 -> ClickEvents.commandEvent(player1,item.getTarget()));
				}
			}

		}

		if (title == null){
			title = TextUtil.parse(BetterLobby.getConfiguration().getString("inventory.teleporter.title"));
		}

		Inventory inventory = Bukkit.createInventory(player, 45,title);


		for (InventoryItem item : items){
			inventory.setItem(item.getIndex(),item.getItemStack());
		}

		player.openInventory(inventory);
	}





}
