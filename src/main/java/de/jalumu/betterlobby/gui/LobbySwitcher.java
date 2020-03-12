package de.jalumu.betterlobby.gui;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.HashMap;

public class LobbySwitcher {

    private static final String group = CloudAPI.getInstance().getGroup();

    protected static void open(Player player){
      Inventory inventory = Bukkit.createInventory(player, 9);

        HashMap<String,ServerInfo> hashMap = new HashMap<>();

        for (ServerInfo info : CloudAPI.getInstance().getServers(group)){
           String id = info.getServiceId().getServerId();
           hashMap.put(id,info);
        }

        Object[] sort =  hashMap.keySet().toArray();

        Arrays.sort(sort);

        for (Object id : sort){
            String name = (String)id;
            ServerInfo info = hashMap.get(name);

            Material material = null;

            if (name.equals(CloudAPI.getInstance().getServerId())){
                material = Material.REDSTONE;
            }else {
                material = Material.GLOWSTONE_DUST;
            }

            inventory.addItem(ItemHelper.getItemStack(material,"&b" + name,1,
                    Arrays.asList("&2&lOnline","&f" + info.getOnlineCount() + "&7/&f" + info.getMaxPlayers())));
        }

        player.openInventory(inventory);
    }


}
