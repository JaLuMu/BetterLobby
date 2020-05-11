package de.jalumu.betterlobby.gui;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.HashMap;

public class LobbySwitcher implements Configurable {

    private static BetterLobby betterLobby;


    @Override
    public void defaultConfiguration() {
        BetterLobby.getConfiguration().addDefault("inventory.lobbySwitcher.title","&bLobby Switcher");
    }

    private static final String group = CloudAPI.getInstance().getGroup();

    public LobbySwitcher(BetterLobby betterLobby){
        this.betterLobby = betterLobby;
    }

    protected static void open(Player player){
      Inventory inventory = Bukkit.createInventory(player, 9,TextUtil.parse(BetterLobby.getConfiguration().getString("inventory.lobbySwitcher.title")));

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

    protected static void handleClickEvent(InventoryClickEvent event){
       if (event.getCurrentItem() != null){
           String lobby = event.getCurrentItem().getItemMeta().getDisplayName().substring(2);
           ByteArrayDataOutput out = ByteStreams.newDataOutput();
           out.writeUTF("Connect");
           out.writeUTF(lobby);
           ((Player)event.getWhoClicked()).sendPluginMessage(betterLobby,"BungeeCord", out.toByteArray());
       }
    }
}
