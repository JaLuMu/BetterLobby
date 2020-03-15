package de.jalumu.betterlobby.gui;

import de.jalumu.betterlobby.BetterLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ClickEvents {

    public static void teleportEvent(Player player, String target){
        if (BetterLobby.getConfiguration().contains("location." + target)){
            player.teleport((Location) BetterLobby.getConfiguration().get("location." + target));
        }else {
            player.sendMessage("Location " + target + " not found");
        }
    }

    public static void commandEvent(Player player, String target) {
        Bukkit.dispatchCommand(player,target);
    }


}
