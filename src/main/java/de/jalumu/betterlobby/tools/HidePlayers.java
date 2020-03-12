package de.jalumu.betterlobby.tools;

import java.util.ArrayList;

import org.bukkit.Bukkit;
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
	}

	public static void showVipPlayers(Player player){
		player.sendMessage("WIP");
	}

	public static void hideAllPlayers(Player player){
		for (Player players : Bukkit.getOnlinePlayers()){
			player.hidePlayer(players);
		}
	}


}