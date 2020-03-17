package de.jalumu.betterlobby.manager;

import de.jalumu.betterlobby.util.TitleMessage;
import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FightManager {

    private static List<Player> fightable = new ArrayList<>();

    private static boolean deathMatch = false;

    public static boolean canFight(Player player) {
        if (deathMatch) {
            return true;
        }
        return fightable.contains(player);
    }

    public static void allowFight(Player player, boolean allowed) {
        if (allowed && !fightable.contains(player)) {
            fightable.add(player);
            HologramMannager.showFightHologram(player, true);
        } else if (!allowed && fightable.contains(player)) {
            fightable.remove(player);
            HologramMannager.showFightHologram(player, false);
        }
    }

    public static boolean isDeathMatch() {
        return deathMatch;
    }

    public static void setDeathMatch(boolean deathMatch) {
        FightManager.deathMatch = deathMatch;
        if (deathMatch) {
            TitleMessage titleMessage = new TitleMessage(ChatColor.DARK_RED, ChatColor.RED, "DeathMatch", "Each player has only one life from now on!");
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.getInventory().clear();
                players.setHealthScale(1);
                players.setHealthScaled(true);
                players.setMaxHealth(1);
                players.setHealth(1);
                players.setFoodLevel(1);
                allowFight(players, true);
            }

            // Fruxz | Now uses the new TitleMessage system
            titleMessage.sendEveryone();

        } else {
            for (Player players : Bukkit.getOnlinePlayers()) {
                // Fruxz | Now uses the Transmission (coloring) System
                players.kickPlayer(new Transmission(ChatColor.RED + "The DeathMatch is now finished!").getTransmissionContent());
                allowFight(players, false);
            }
        }
    }
}
