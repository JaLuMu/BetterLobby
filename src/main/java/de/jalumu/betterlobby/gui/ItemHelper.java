package de.jalumu.betterlobby.gui;


import de.jalumu.betterlobby.util.TextUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class ItemHelper {

    private static HashMap<ItemStack,ClickEvent> inventoryEvents = new HashMap<>();
    private static HashMap<Integer,ClickEvent> hotbarEvents = new HashMap<>();

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
        if (!inventoryEvents.containsKey(itemStack)){
            inventoryEvents.put(itemStack,clickEvent);
        }
    }

    protected static void registerHotbarClickEvent(int index, ClickEvent clickEvent){
        hotbarEvents.put(index,clickEvent);
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

    public static void handleInventory(InventoryClickEvent event) {
        if (event.getCurrentItem() != null && inventoryEvents.containsKey(event.getCurrentItem())){
            inventoryEvents.get(event.getCurrentItem()).onClick((Player) event.getWhoClicked());
        }
    }

    public static void handleInteraction(PlayerInteractEvent event){
        if (hotbarEvents.containsKey(event.getPlayer().getInventory().getHeldItemSlot())){
            hotbarEvents.get(event.getPlayer().getInventory().getHeldItemSlot()).onClick(event.getPlayer());
        }
        if (event.getItem() != null && inventoryEvents.containsKey(event.getItem())){
            inventoryEvents.get(event.getItem()).onClick((Player) event.getPlayer());
        }
    }
}
