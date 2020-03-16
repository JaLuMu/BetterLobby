package de.jalumu.betterlobby.manager;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FightManager {

    private static List<Player> fightable = new ArrayList<>();

    public static boolean canFight(Player player){
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

}
