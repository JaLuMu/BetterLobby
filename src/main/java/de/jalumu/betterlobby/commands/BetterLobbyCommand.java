package de.jalumu.betterlobby.commands;

import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BetterLobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        new Transmission("BetterLobby programmed with " + ChatColor.RED + "❤" + ChatColor.GRAY + " and " + ChatColor.AQUA + "GitHub" + ChatColor.DARK_GRAY + " | " + ChatColor.GRAY + "https://github.com/JaLuMu/BetterLobby").send(sender);
        return true;
    }
}
