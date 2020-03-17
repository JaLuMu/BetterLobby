package de.jalumu.betterlobby.util;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Title {


    /**
     * Documentation by Fruxz
     * @param player defines the target player
     * @param title defines the headline
     * @param subtitle defines the subline
     * @param fadeInTime defines the time which the popup spend
     * @param showTime defines the time which the hold spend
     * @param fadeOutTime defines the time which the popout spend
     */
    public static void send(Player player, String title, String subtitle, int fadeInTime, int showTime, int fadeOutTime) {
        try {
            Object chatTitle = Reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + title + "\"}");
            Constructor<?> titleConstructor = Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(
                    Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object packet = titleConstructor.newInstance(
                    Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null), chatTitle,
                    fadeInTime, showTime, fadeOutTime);

            Object chatsTitle = Reflection.getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class)
                    .invoke(null, "{\"text\": \"" + subtitle + "\"}");
            Constructor<?> timingTitleConstructor = Reflection.getNMSClass("PacketPlayOutTitle").getConstructor(
                    Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], Reflection.getNMSClass("IChatBaseComponent"),
                    int.class, int.class, int.class);
            Object timingPacket = timingTitleConstructor.newInstance(
                    Reflection.getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null), chatsTitle,
                    fadeInTime, showTime, fadeOutTime);

            Reflection.sendPacket(player, packet);
            Reflection.sendPacket(player, timingPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
