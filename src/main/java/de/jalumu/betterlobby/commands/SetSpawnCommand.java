package de.jalumu.betterlobby.commands;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This is a command, that set a new spawn-point location
 *
 * Edited/Revised by Fruxz
 * @version 2.0
 */

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();

            BetterLobby.getConfiguration().set("location.spawn", location);
            BetterLobby.getConfiguration().set("join.teleportToSpawn", true);
            BetterLobby.saveConfiguration();
            player.getWorld().setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());

            new Transmission(ChatColor.GREEN + "The new spawn point could be set successfully!").send(player);

        } else
            new Transmission(ChatColor.RED + "You are not a player!").send(sender);

        return false;
    }
}
