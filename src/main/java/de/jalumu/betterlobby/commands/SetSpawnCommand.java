package de.jalumu.betterlobby.commands;

import de.jalumu.betterlobby.BetterLobby;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            BetterLobby.getConfiguration().set("location.spawn",player.getLocation());
            BetterLobby.getConfiguration().set("join.teleportToSpawn",true);
            BetterLobby.saveConfiguration();

        }else {
            sender.sendMessage("You are not a Player");
            return true;
        }

        return false;
    }
}
