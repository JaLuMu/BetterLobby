package de.jalumu.betterlobby.manager;

import de.jalumu.betterlobby.gui.Inventory;
import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.ChatColor.*;

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
            new Transmission().appendMessagePrefix().appendSpace()
                    .color(GRAY).appendText("Build mode").appendSpace()
                    .color(GREEN).appendText("enabled").send(player);
        }else if(!allow && canBuild(player)){
            canBuild.remove(player.getUniqueId());
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            Inventory.createBar(player);
            new Transmission().appendMessagePrefix().appendSpace()
                    .color(GRAY).appendText("Build mode").appendSpace()
                    .color(RED).appendText("disabled").send(player);
        }
    }

    public static void toggleBuildMode(Player player){
        allowBuilding(player,!canBuild(player));
    }

}
