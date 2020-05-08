package de.jalumu.betterlobby.commands;

import static org.bukkit.ChatColor.*;
import de.jalumu.betterlobby.manager.BuildManager;
import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            BuildManager.toggleBuildMode((Player)sender);
        }else {
            new Transmission().appendMessagePrefix().appendSpace()
                    .color(RED).appendText("You are not a player").send(sender);
        }

        return true;
    }
}
