package de.jalumu.betterlobby;

import de.jalumu.betterlobby.commands.*;
import de.jalumu.betterlobby.configuration.Configurable;
import de.jalumu.betterlobby.gui.*;
import de.jalumu.betterlobby.listener.*;
import de.jalumu.betterlobby.manager.HologramMannager;
import de.jalumu.betterlobby.manager.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.event.Listener;

public class Registry {

    private BetterLobby betterLobby;

    public Registry(BetterLobby betterLobby){
        this.betterLobby = betterLobby;
    }

    public void registerConfigurationSerializations(){
        registerConfigSerialization(InventoryItem.class);
    }

    public void registerConfigurations(){
        registerConfig(new Inventory());
        registerConfig(new Teleporter());
        registerConfig(new PlayerVisibilityMenu());
        registerConfig(new LobbySwitcher(betterLobby));
    }

    public void registerListeners(){
        registerListener(new JoinLeaveListener());
        registerListener(new MovementListener());
        registerListener(new InventoryListener());
        registerListener(new WorldInteractionListener());
        registerListener(new WeatherListener());
        registerListener(new EntityListener());
    }

    public void registerCommands(){
        registerCommand("betterlobby", new BetterLobbyCommand());
        registerCommand("build", new BuildCommand());
        registerCommand("setSpawn",new SetSpawnCommand());
        registerCommand("spawn", new SpawnCommand());
        registerCommand("push", new PushCommand());
        registerCommand("fly", new FlyCommand());
        registerCommand("heal", new HealCommand());
        registerCommand("deathmatch", new DeathmatchCommand());
    }

    public void registerOtherStuff(){
        Bukkit.getMessenger().registerOutgoingPluginChannel(betterLobby, "BungeeCord");
        registerConfig(new ScoreboardManager(betterLobby));
        new HologramMannager(betterLobby);
    }

    private void registerCommand(String name, CommandExecutor executor){
        betterLobby.getCommand(name).setExecutor(executor);
        registerConfig(executor);
    }

    private void registerListener(Listener listener){
        Bukkit.getPluginManager().registerEvents(listener,betterLobby);
        registerConfig(listener);
    }

    private void registerConfigSerialization(Class<? extends ConfigurationSerializable> c){
        ConfigurationSerialization.registerClass(c);
    }

    private void registerConfig(Object o) {
        if (o instanceof Configurable) {
            ((Configurable) o).defaultConfiguration();
        }
    }

}
