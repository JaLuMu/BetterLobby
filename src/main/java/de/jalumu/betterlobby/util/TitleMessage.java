package de.jalumu.betterlobby.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * The {@link TitleMessage} helps us, to manage titles
 *
 * @author Fruxz
 * @version 1.0
 */

public class TitleMessage {

    private String title;
    private String subtitle;

    public TitleMessage(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public TitleMessage(ChatColor titleColor, ChatColor subtitlecolor, String title, String subtitle) {
        this.title = titleColor + title;
        this.subtitle = subtitlecolor + subtitle;
    }

    public void send(Player player) {
        player.sendTitle(title, subtitle);
    }

    public void sendEveryone() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player);
        }
    }

    /**
     * @deprecated with {@link #sendEveryone()}
     */
    public void broadcast() {
        sendEveryone();
    }

    /**
     * @deprecated Not recommended to use
     * @param player defines the target
     */
    public void cancle(Player player) {
        player.resetTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
