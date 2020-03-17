package de.jalumu.betterlobby.commands;

import de.jalumu.betterlobby.manager.FightManager;
import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DeathmatchCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getPermission() == null || sender.hasPermission(command.getPermission())) {
            if (args.length == 0) {
                
                FightManager.setDeathMatch(!FightManager.isDeathMatch());

            } else
                new Transmission("Please use " + ChatColor.RED + command.getUsage().replaceAll("<command>", command.getName())).send(sender);
        } else
            new Transmission(ChatColor.RED + "You have no rights for this, you need an authorization!").send(sender);
        return true;

    }
}
