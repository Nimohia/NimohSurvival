package src.net.jadiefication.GUI;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.GUI.Heads;
import src.net.jadiefication.survival.Survival;

import java.net.URL;
import java.util.List;

import static src.net.jadiefication.Core.GUI.Border.setInventoryBorder;
import static src.net.jadiefication.Core.Item.CustomItem.createItemWithData;

public class BluemapGui implements InventoryHolder {

    private Inventory inventory;

    public BluemapGui() {
        URL url = Heads.createUrl("http://textures.minecraft.net/texture/98daa1e3ed94ff3e33e1d4c6e43f024c47d78a57ba4d38e75e7c9264106");
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 27, Component.text("Bluemap"));
        setInventoryBorder(inventory);
        ItemStack itemStack = new ItemStack(Material.COMPARATOR);
        ItemStack item = createItemWithData(itemStack, Component.text("§6§lBluemap visibility"), List.of(Component.text("Toggle bluemap visibility")));
        this.inventory.setItem(12, item);
        this.inventory.setItem(14, Heads.createHead(Heads.createHead(url), Component.text("§6§lBluemap"), List.of(Component.text("§7Click to open bluemap"))));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
