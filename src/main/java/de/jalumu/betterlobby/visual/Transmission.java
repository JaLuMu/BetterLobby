package de.jalumu.betterlobby.visual;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * The {@link Transmission} class helps, to manage messages and send them to different kind of receivers
 */

public class Transmission {

    private String message;


    /**
     * @param message Defines the message to be sent to the destinations and processed before
     */
    public Transmission(String message) {
        this.message = message;
    }

    /**
     * @return the processed message which is sent
     */
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
