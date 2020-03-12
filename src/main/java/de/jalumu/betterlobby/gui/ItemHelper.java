package de.jalumu.betterlobby.gui;


import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class ItemHelper {

    private static HashMap<ItemStack,ClickEvent> events = new HashMap<>();

    protected static ItemStack getItemStack(Material material, String name, int amount, List<String> loore){
        ItemStack stack = new ItemStack(material,amount);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(TextUtil.parse(name));
        meta.setLore(TextUtil.parse(loore));
        stack.setItemMeta(meta);
        return stack;
    }

    protected static ItemStack getItemStack(Material material, String name, int amount, List<String> loore,ClickEvent clickEvent){
        ItemStack stack = getItemStack(material, name, amount, loore);
        registerClickEvent(stack,clickEvent);
        return stack;
    }

    protected static ItemStack getItemStack(Material material, String name){
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(TextUtil.parse(name));
        stack.setItemMeta(meta);
        return stack;
    }

    protected static ItemStack getItemStack(Material material, String name,ClickEvent clickEvent){
        ItemStack stack = getItemStack(material,name);
        registerClickEvent(stack,clickEvent);
        return stack;
    }

    protected static void registerClickEvent(ItemStack itemStack, ClickEvent clickEvent){
        if (!events.containsKey(itemStack)){
            events.put(itemStack,clickEvent);
        }
    }

    protected static ItemStack setName(ItemStack itemStack,String name){
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(TextUtil.parse(name));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    protected static ItemStack setLore(ItemStack itemStack,List<String> lore){
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(TextUtil.parse(lore));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static void handle(ItemStack currentItem, Player player) {
        if (currentItem != null && events.containsKey(currentItem)){
            events.get(currentItem).onClick(player);
        }
    }
}
