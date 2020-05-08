package de.jalumu.betterlobby.util;

import de.jalumu.betterlobby.BetterLobby;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    public static String parse(String text){
       if (text != null){
           return ChatColor.translateAlternateColorCodes('&',text);
       }else {
           return null;
       }
    }

    public static String parse(String text, Player player){
        if (BetterLobby.isPlaceHolderApiEnabled()) {
            return parse(PlaceholderAPI.setPlaceholders(player, text));
        }else {
            return parse(text);
        }
    }

    public static List<String> parse(List<String> list){
        List<String> parsed = new ArrayList<>();
        for (String s : list){
            parsed.add(parse(s));
        }
        return parsed;
    }

    public static List<String> parse(List<String> list,Player player){
        if (BetterLobby.isPlaceHolderApiEnabled()) {
            return parse(PlaceholderAPI.setPlaceholders(player, list));
        }else {
            return parse(list);
        }
    }

    public static ItemStack parse(ItemStack itemStack){
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            if (meta.getDisplayName() != null) {
                meta.setDisplayName(parse(meta.getDisplayName()));
            }
            if (meta.getLore() != null) {
                meta.setLore(parse(meta.getLore()));
            }
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }

    public static ItemStack parse(ItemStack itemStack,Player player){
        if (BetterLobby.isPlaceHolderApiEnabled()) {
            ItemMeta meta = itemStack.getItemMeta();
            if (meta != null) {
                if (meta.getDisplayName() != null) {
                    meta.setDisplayName(parse(meta.getDisplayName(), player));
                }
                if (meta.getLore() != null) {
                    meta.setLore(parse(meta.getLore(), player));
                }
                itemStack.setItemMeta(meta);
            }
            return itemStack;
        }else {
            return parse(itemStack);
        }
    }

}
