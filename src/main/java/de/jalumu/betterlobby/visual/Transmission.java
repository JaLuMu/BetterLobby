package de.jalumu.betterlobby.visual;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Transmission {

    private String message;

    public Transmission(String message) {
        this.message = message;
    }

    public void send(CommandSender sender) {
        sender.sendMessage(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY + message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
