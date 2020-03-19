package de.jalumu.betterlobby.commands;

import static org.bukkit.ChatColor.*;
import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * This class adds a minecraft-bukkit command
 *
 * This command shows plugin-infos
 * @version 1.0
 */

public class BetterLobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        new Transmission().appendMessagePrefix().appendSpace()
                .color(GRAY).appendText("BetterLobby programmed with").appendSpace()
                .color(RED).appendText("❤").appendSpace()
                .color(GRAY).appendText("and").appendSpace()
                .color(AQUA).appendText("GitHub").newLine()
                .color(GRAY).appendText("https://github.com/JaLuMu/BetterLobby").send(sender);
        return true;
    }
}
