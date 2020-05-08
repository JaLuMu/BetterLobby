package de.jalumu.betterlobby.tools;

import java.util.ArrayList;

import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class HidePlayers{

	static ArrayList<String> HideShow = new ArrayList<>();


	public static void showAllPlayers(Player player){
		for (Player players : Bukkit.getOnlinePlayers()){
			player.showPlayer(players);
		}
		new Transmission().appendMessagePrefix().appendSpace()
				.color(ChatColor.GRAY).appendText("All Players are").appendSpace()
				.color(ChatColor.GREEN).appendText("Visible")
				.send(player);
	}

	public static void showVipPlayers(Player player){
		new Transmission().appendMessagePrefix().appendSpace()
				.color(ChatColor.GRAY).appendText("This option will be available in the future")
				.send(player);
	}

	public static void hideAllPlayers(Player player){
		for (Player players : Bukkit.getOnlinePlayers()){
			player.hidePlayer(players);
		}
		new Transmission().appendMessagePrefix().appendSpace()
				.color(ChatColor.GRAY).appendText("All Players are").appendSpace()
				.color(ChatColor.RED).appendText("Invisible")
				.send(player);
	}


}