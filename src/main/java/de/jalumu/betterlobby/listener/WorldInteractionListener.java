package de.jalumu.betterlobby.listener;

import de.jalumu.betterlobby.manager.BuildManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;


public class WorldInteractionListener implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!BuildManager.canBuild(event.getPlayer())){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (!BuildManager.canBuild(event.getPlayer())){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		event.getDrops().clear();
		event.getEntity().spigot().respawn();
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		if (!BuildManager.canBuild(event.getPlayer())){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {
		if (!BuildManager.canBuild(event.getPlayer())){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		if (!event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)){
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player){
			Player player = (Player)event.getDamager();
			if (!BuildManager.canBuild(player)){
				event.setCancelled(true);
			}
		}else {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByBlockEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		event.setCancelled(true);
	}

}
