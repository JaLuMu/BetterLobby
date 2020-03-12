package de.jalumu.betterlobby.manager;

import de.jalumu.betterlobby.gui.Inventory;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuildManager {

    private static List<UUID> canBuild = new ArrayList<>();

    public static boolean canBuild(Player player){
        return canBuild.contains(player.getUniqueId());
    }

    public static void allowBuilding(Player player,boolean allow){
        if (allow && !canBuild(player)){
            canBuild.add(player.getUniqueId());
            player.getInventory().clear();
            player.setGameMode(GameMode.CREATIVE);
        }else if(!allow && canBuild(player)){
            canBuild.remove(player.getUniqueId());
            player.setGameMode(GameMode.ADVENTURE);
            Inventory.createBar(player);
        }
    }

}
