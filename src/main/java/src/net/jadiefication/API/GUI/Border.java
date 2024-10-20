package src.net.jadiefication.API.GUI;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Border {

    public static void setInventoryBorder(Inventory inventory) {

        ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.displayName(Component.text(" "));
        border.setItemMeta(borderMeta);

        int size = inventory.getSize();
        int height = size / 9;

        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, border);
        }

        // Bottom border
        for (int i = size - 9; i < size; i++) {
            inventory.setItem(i, border);
        }

        // Left and right borders
        for (int i = 1; i < height - 1; i++) {
            inventory.setItem(i * 9, border); // Left border
            inventory.setItem(i * 9 + 8, border); // Right border
        }
    }

}
