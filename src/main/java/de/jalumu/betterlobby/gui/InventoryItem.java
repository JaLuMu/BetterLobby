package de.jalumu.betterlobby.gui;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class InventoryItem implements ConfigurationSerializable {

    private int index = -1;
    private ItemStack itemStack = null;
    private ClickAction clickAction;
    private String target;


    public InventoryItem(int index, ItemStack itemStack,ClickAction clickAction,String target){
        this.index = index;
        this.itemStack = itemStack;
        this.clickAction = clickAction;
        if (target == null){
            this.target = "";
        }else {
            this.target = target;
        }

    }

    public InventoryItem(Map<String,Object> map){
        index = (Integer) map.get("index");
        itemStack = (ItemStack) map.get("itemStack");
        clickAction = ClickAction.valueOf(map.get("clickAction").toString());
        target = (String) map.get("target");
    }

    public int getIndex() {
        return index;
    }


    public ItemStack getItemStack() {
        return itemStack;
    }

    public ClickAction getClickAction() {
        return clickAction;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String,Object> map = new HashMap<>();
        map.put("index",index);
        map.put("itemStack",itemStack);
        map.put("clickAction",clickAction.toString());
        map.put("target",target);
        return map;
    }
}
