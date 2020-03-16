package de.jalumu.betterlobby.manager;

import de.jalumu.betterlobby.gui.Inventory;
import de.jalumu.betterlobby.util.TextUtil;
import de.jalumu.betterlobby.util.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FightManager {

    private static List<Player> fightable = new ArrayList<>();

    private static boolean deathMatch = false;

    public static boolean canFight(Player player){
        if (deathMatch){
            return true;
        }
        return fightable.contains(player);
    }

    public static void allowFight(Player player,boolean allowed){
        if (allowed && !fightable.contains(player)){
            fightable.add(player);
            HologramMannager.showFightHologram(player,true);
        }else if (!allowed && fightable.contains(player)){
            fightable.remove(player);
            HologramMannager.showFightHologram(player,false);
        }
    }

    public static void setDeathMatch(boolean deathMatch) {
        FightManager.deathMatch = deathMatch;
        if (deathMatch){
            for (Player players : Bukkit.getOnlinePlayers()){
                players.getInventory().clear();
                players.setHealthScale(1);
                players.setHealthScaled(true);
                players.setMaxHealth(1);
                players.setHealth(1);
                players.setFoodLevel(1);
                Title.send(players, TextUtil.parse("&4DeathMatch"),TextUtil.parse("&4Every Player has 1 Life"),3,5,1);
                allowFight(players,true);
            }
        }else {
            for (Player players : Bukkit.getOnlinePlayers()){
                players.kickPlayer("Deathmatch is over");
                allowFight(players,false);
            }
        }
    }

    public static boolean isDeathMatch() {
        return deathMatch;
    }
}
