package src.net.jadiefication.Core.Item;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class CustomItem {

    public static ItemStack createCustomItem(ItemStack item, Component name, List<Component> lore, int meta) {
        ItemStack customItem = new ItemStack(item);
        customItem.editMeta((itemMeta -> {
            itemMeta.displayName(name);
            itemMeta.lore(lore);
            itemMeta.setCustomModelData(meta);
        }));
        return customItem;
    }
}
