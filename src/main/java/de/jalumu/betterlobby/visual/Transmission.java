package de.jalumu.betterlobby.visual;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Transmission {

    private String message;

    public Transmission(String message) {
        this.message = message;
    }

    public String getTransmissionContent() {
        return ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY + message;
    }

    public void send(CommandSender sender) {
        sender.sendMessage(getTransmissionContent());
    }

    public void broadcast() {
        Bukkit.broadcastMessage(getTransmissionContent());
    }

    public void sendConsole() {
        Bukkit.getConsoleSender().sendMessage(getTransmissionContent());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
