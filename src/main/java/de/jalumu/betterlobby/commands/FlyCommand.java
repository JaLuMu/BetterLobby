package de.jalumu.betterlobby.commands;

import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class adds a minecraft-bukkit command
 *
 * This command allows or disallows player to fly
 * @version 1.0
 */

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getPermission() == null || sender.hasPermission(command.getPermission())) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player currentPlayer = ((Player) sender).getPlayer();
                    assert currentPlayer != null;

                    currentPlayer.setAllowFlight(!currentPlayer.getAllowFlight());

                    if (currentPlayer.getAllowFlight()) {
                        new Transmission("You're flight mode is now " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + "!").send(currentPlayer);
                    } else
                        new Transmission("You're flight mode is now " + ChatColor.RED + "disabled" + ChatColor.GRAY + "!").send(currentPlayer);
                } else
                    new Transmission(ChatColor.RED + "You are not a player!").send(sender);
            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    target.setAllowFlight(!target.getAllowFlight());

                    if (target.getAllowFlight()) {
                        new Transmission("You changed the flight mode of " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " to " + ChatColor.GREEN + "enabled" + ChatColor.GRAY).send(sender);
                        new Transmission("Your flight mode has now changed by " + ChatColor.GOLD + sender.getName() + ChatColor.GRAY + "!").send(target);
                    } else {
                        new Transmission("You changed the flight mode of " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " to " + ChatColor.RED + "disabled" + ChatColor.GRAY).send(sender);
                        new Transmission("Your flight mode has now changed by " + ChatColor.GOLD + sender.getName() + ChatColor.GRAY + "!").send(target);
                    }
                } else
                    new Transmission(ChatColor.GRAY + "This player is currently " + ChatColor.RED + "not available" + ChatColor.GRAY + "!");
            } else
                new Transmission("Please use " + ChatColor.RED + command.getUsage().replaceAll("<command>", command.getName())).send(sender);
        } else
            new Transmission(ChatColor.RED + "You have no rights for this, you need an authorization!").send(sender);
        return true;
    }

    public static TabCompleter tabCompleter = new TabCompleter() {
        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
            if (args.length == 1) {
                ArrayList<String> before = new ArrayList<>();
                ArrayList<String> v = new ArrayList<>();

                for (Player player : Bukkit.getOnlinePlayers()) {
                    before.add(player.getName());
                }

                for (String entry : before) {
                    if (args[0].isEmpty() || args[0].equalsIgnoreCase(" ") || entry.toUpperCase().contains(args[0].toUpperCase())) {
                        v.add(entry);
                    }
                }

                return v;
            }
            return Collections.singletonList(" ");
        }
    };
}
