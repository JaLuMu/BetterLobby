package de.jalumu.betterlobby.listener;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.manager.BuildManager;
import de.jalumu.betterlobby.gui.Inventory;
import de.jalumu.betterlobby.manager.FightManager;
import de.jalumu.betterlobby.manager.HologramMannager;
import de.jalumu.betterlobby.manager.ScoreboardManager;
import de.jalumu.betterlobby.util.TextUtil;
import de.jalumu.betterlobby.util.Title;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class JoinLeaveListener implements Listener, Configurable {

	private int healthScale = 0;

	@Override
	public void defaultConfiguration() {
		BetterLobby.getConfiguration().addDefault("join.teleportToSpawn",false);
		BetterLobby.getConfiguration().addDefault("join.clearInventory",true);
		BetterLobby.getConfiguration().addDefault("join.title.enabled",true);
		BetterLobby.getConfiguration().addDefault("join.title.time.fadeIn",0);
		BetterLobby.getConfiguration().addDefault("join.title.time.stay",5);
		BetterLobby.getConfiguration().addDefault("join.title.time.fadeOut",3);
		BetterLobby.getConfiguration().addDefault("join.title.message.mainTitle","example.de");
		BetterLobby.getConfiguration().addDefault("join.title.message.subTitle","Your favorite Minecraft Network");
		BetterLobby.getConfiguration().addDefault("location.spawn",Bukkit.getWorlds().get(0).getSpawnLocation());

		BetterLobby.getConfiguration().addDefault("misc.healthScale",20);

	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		if (healthScale == 0){
			healthScale = BetterLobby.getConfiguration().getInt("misc.healthScale");
		}

		player.setHealthScale(healthScale);
		player.setHealthScaled(true);
		player.setMaxHealth(healthScale);
		player.setHealth(healthScale);
		player.setFoodLevel(20);

		if (BetterLobby.getConfiguration().getBoolean("join.teleportToSpawn")){
			player.teleport((Location) BetterLobby.getConfiguration().get("location.spawn"));
		}

		if (BetterLobby.getConfiguration().getBoolean("join.title.enabled")){
			int fadeIn = BetterLobby.getConfiguration().getInt("join.title.time.fadeIn");
			int stay = BetterLobby.getConfiguration().getInt("join.title.time.stay");
			int fadeOut = BetterLobby.getConfiguration().getInt("join.title.time.fadeOut");
			String mainTitle = TextUtil.parse(BetterLobby.getConfiguration().getString("join.title.message.mainTitle"));
			String subTitle = TextUtil.parse(BetterLobby.getConfiguration().getString("join.title.message.subTitle"));
			Title.send(player,mainTitle,subTitle,fadeIn,stay,fadeOut);
		}

		if (BetterLobby.getConfiguration().getBoolean("join.clearInventory")) {
			player.getInventory().clear();
		}

		Inventory.createBar(player);
		ScoreboardManager.updateScoreboard(player);
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		BuildManager.allowBuilding(player,false);
		FightManager.allowFight(player,false);
	}


}