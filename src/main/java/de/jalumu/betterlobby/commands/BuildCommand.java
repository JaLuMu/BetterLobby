package de.jalumu.betterlobby.commands;

import de.jalumu.betterlobby.manager.BuildManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player)sender;
            if (BuildManager.canBuild(player)){
                BuildManager.allowBuilding(player,false);
            }else {
                BuildManager.allowBuilding(player,true);
            }
        }else {
            sender.sendMessage("You are not a Player");
        }

        return true;
    }
}
