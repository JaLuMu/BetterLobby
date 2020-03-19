package de.jalumu.betterlobby.visual;

import de.jalumu.betterlobby.BetterLobby;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * The {@link Transmission} class helps, to manage messages and send them to different kind of receivers
 */

public class Transmission {

    private StringBuilder message;


    public Transmission(){
        this.message = new StringBuilder();
    }

    /**
     * @param message Defines the message to be sent to the destinations and processed before
     */
    public Transmission(String message) {
        this.message = new StringBuilder();
        this.appendMessagePrefix().appendSpace().appendText(message);
    }

    public Transmission appendPluginPrefix() {
        this.resetColor().appendText(BetterLobby.getPluginPrefix()).resetColor();
        return this;
    }

    public Transmission appendMessagePrefix() {
        this.resetColor().appendText(BetterLobby.getMessagePrefix()).resetColor();
        return this;
    }

    public Transmission appendSpace() {
        message.append(" ");
        return this;
    }

    public Transmission newLine() {
        message.append("\n");
        return this;
    }

    public Transmission resetColor() {
        message.append(ChatColor.RESET);
        return this;
    }

    public Transmission color(ChatColor color) {
        message.append(color);
        return this;
    }

    public Transmission appendText(String text){
        message.append(text);
        return this;
    }

    /**
     * @return the processed message which is sent
     */
    public String getTransmissionContent() {
        return message.toString();
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

}
