package de.jalumu.betterlobby.commands;

import de.jalumu.betterlobby.BetterLobby;
import de.jalumu.betterlobby.visual.Transmission;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getPermission() == null || sender.hasPermission(command.getPermission())) {
            if (args.length == 0) {
                FileConfiguration configuration = BetterLobby.getConfiguration();

                if (sender instanceof Player) {
                    Player currentPlayer = ((Player) sender).getPlayer();
                    assert currentPlayer != null;

                    if (configuration.get("location.spawn") != null && configuration.get("location.spawn") instanceof Location) {
                        Location location = (Location) configuration.get("location.spawn");

                        currentPlayer.teleport(location);
                        currentPlayer.getWorld().playSound(currentPlayer.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 2);
                        currentPlayer.getWorld().playEffect(currentPlayer.getLocation(), Effect.MOBSPAWNER_FLAMES, null, 3);

                        new Transmission("You are now at the Spawn-Point!").send(currentPlayer);
                    } else
                        new Transmission(ChatColor.RED + "There is currently no Spawn-Point!").send(currentPlayer);

                } else
                    new Transmission(ChatColor.RED + "You are not a player").send(sender);
            } else
                new Transmission("Please use " + ChatColor.RED + command.getUsage().replaceAll("<command>", command.getName())).send(sender);
        } else
            new Transmission(ChatColor.RED + "You have no rights for this, you need an authorization!").send(sender);
        return true;
    }
}
