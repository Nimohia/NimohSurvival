package src.net.jadiefication.GUI;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.survival.Survival;

import java.net.URL;
import java.util.List;

import static src.net.jadiefication.Core.GUI.Border.setInventoryBorder;
import static src.net.jadiefication.Core.GUI.Heads.createHead;
import static src.net.jadiefication.Core.GUI.Heads.createUrl;

public class WarpGui implements InventoryHolder {

    private final Inventory inventory;

    public WarpGui() {
        URL url = createUrl("http://textures.minecraft.net/texture/be9b6d7adac520a707221a053af045dc8d5b6feb13108d0851005428165267b0");
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 27, Component.text("Warp Menu"));
        setInventoryBorder(inventory);
        this.inventory.setItem(12, createHead(createHead(url), Component.text("§6§lWarp to Spawn"), List.of(Component.text("§7Click to warp to spawn"))));
        this.inventory.setItem(13, createHead(createHead(url), Component.text("§6§lWarp to Farmer"), List.of(Component.text("§7Click to warp to the Farmer"))));
        this.inventory.setItem(14, createHead(createHead(url), Component.text("§6§lWarp to Fisherman"), List.of(Component.text("§7Click to warp to the Fisherman"))));
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
