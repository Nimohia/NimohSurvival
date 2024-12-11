package src.net.jadiefication.Core.Block;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class CustomBlockEntity {

    public static Map<Map<ItemStack, ItemMeta>, String> blockItems = new HashMap<>();

    public static void addBlockEntity(ItemStack item, String command) {
        Map<ItemStack, ItemMeta> map = Map.of(item, item.getItemMeta());
        blockItems.put(map, command);
    }

    public static void renderBlockEntity(String name) {

    }
}
