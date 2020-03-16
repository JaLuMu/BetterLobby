package de.jalumu.betterlobby.manager;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import de.jalumu.betterlobby.BetterLobby;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;


public class HologramMannager {

    private static BetterLobby betterLobby;

    private static HashMap<Player,Hologram> fightHolograms = new HashMap<>();

    public HologramMannager(BetterLobby betterLobby){
        this.betterLobby = betterLobby;
        if (BetterLobby.isHolographicDisplaysEnabled()) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(betterLobby, new Runnable() {
                @Override
                public void run() {
                    updateHolograms();
                }
            }, 0, 0);
        }
    }

    protected static void showFightHologram(Player player, boolean b){
        if (BetterLobby.isHolographicDisplaysEnabled()) {
            if (b == true && !fightHolograms.keySet().contains(player)) {
                Location location = player.getLocation().add(0, 3, 0);
                Hologram hologram = HologramsAPI.createHologram(betterLobby, location);
                hologram.appendItemLine(new ItemStack(Material.IRON_SWORD));
                hologram.getVisibilityManager().hideTo(player);
                fightHolograms.put(player, hologram);
            } else if (b == false && fightHolograms.keySet().contains(player)) {
                Hologram hologram = fightHolograms.get(player);
                hologram.delete();
                fightHolograms.remove(player);
            }
        }
    }

    private void updateHolograms(){
        for (Player player : fightHolograms.keySet()){
            updateFightHologram(player);
        }
    }

    private void updateFightHologram(Player player){
        Hologram hologram = fightHolograms.get(player);
        Location location = player.getLocation().add(0,3,0);
        hologram.teleport(location);
    }



}
