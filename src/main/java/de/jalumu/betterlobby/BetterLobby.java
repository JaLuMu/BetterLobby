package de.jalumu.betterlobby;

import de.jalumu.betterlobby.metrics.Metrics;
import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public class BetterLobby extends JavaPlugin {


    private static boolean placeHolderApiEnabled = false;

    private static boolean cloudnetApiEnabled = false;

    private static boolean holographicDisplaysEnabled = false;

    private static Plugin plugin = null;

    private static FileConfiguration configuration;

    private static String pluginPrefix;

    private static String messagePrefix;

    private final int pluginId = 6744;

    private Registry registry;

    @Override
    public void onLoad() {
        for (Player players : Bukkit.getOnlinePlayers()){
            players.kickPlayer("Server Reload");
        }
    }

    @Override
    public void onEnable() {
        plugin = this;

        registry = new Registry(this);

        registry.registerConfigurationSerializations();

        configuration = getConfig();

        getConfiguration().addDefault("plugin.prefix","&eLobby");
        getConfiguration().addDefault("plugin.messagePrefix","&b>>");

        pluginPrefix = TextUtil.parse(getConfiguration().getString("plugin.prefix"));
        messagePrefix = TextUtil.parse(getConfiguration().getString("plugin.messagePrefix"));

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            getLogger().info("PlaceholderApi found");
            placeHolderApiEnabled = true;
        }else {
            getLogger().warning("PlaceholderApi not found. Install PlaceholderApi to use Placeholder");
            placeHolderApiEnabled = false;
        }

        if (Bukkit.getPluginManager().getPlugin("HolographicDisplays") != null){
            getLogger().info("HolographicDisplays found");
            holographicDisplaysEnabled = true;
        }else {
            getLogger().info("HolographicDisplays not found. Holograms disabled");
            holographicDisplaysEnabled = false;
        }

        if (Bukkit.getPluginManager().getPlugin("CloudNetAPI") != null){
            getLogger().info("CloudNetAPI found");
            cloudnetApiEnabled = true;
        }else {
            getLogger().info("CloudNetAPI not found. LobbySwitcher disabled");
            placeHolderApiEnabled = false;
        }

        registry.registerConfigurations();

        registry.registerListeners();

        registry.registerCommands();

        registry.registerOtherStuff();

        saveConfiguration();

        Metrics metrics = new Metrics(plugin,pluginId);

    }

    @Override
    public void onDisable() {
        plugin = null;
    }


    public static boolean isPlaceHolderApiEnabled() {
        return placeHolderApiEnabled;
    }

    public static boolean isHolographicDisplaysEnabled() {
        return holographicDisplaysEnabled;
    }

    public static boolean isCloudnetApiEnabled() {
        return cloudnetApiEnabled;
    }

    public static FileConfiguration getConfiguration() {
        return configuration;
    }

    public static void saveConfiguration(){
        configuration.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static String getPluginPrefix() {
        return pluginPrefix;
    }

    public static String getMessagePrefix() {
        return messagePrefix;
    }
}
