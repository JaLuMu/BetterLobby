package de.jalumu.betterlobby.listener;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.manager.BuildManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class MovementListener implements Listener, Configurable {

	@Override
	public void defaultConfiguration() {
		BetterLobby.getConfiguration().addDefault("world.jumppad.enabled",true);
		BetterLobby.getConfiguration().addDefault("world.jumppad.multiply",3d);
		BetterLobby.getConfiguration().addDefault("world.jumppad.height",0.3d);
	}


	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		if(BetterLobby.getConfiguration().getBoolean("world.jumppad.enabled")) {

			if(player.getLocation().getBlock().getType() == Material.STONE_PLATE && !BuildManager.canBuild(player)) {
				org.bukkit.util.Vector v = player.getLocation().getDirection()
						.setY(BetterLobby.getConfiguration().getDouble("world.jumppad.height"))
						.multiply(BetterLobby.getConfiguration().getDouble("world.jumppad.multiply"));
				player.setVelocity(v);
				player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 3, 1);
			}

		}

		if (player.getLocation().getY() <= 0){
			player.teleport((Location) BetterLobby.getConfiguration().get("location.spawn"));
		}

	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event){
		event.setCancelled(true);
	}


}