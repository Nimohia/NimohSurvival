package src.net.jadiefication.GUI;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.API.Border;
import src.net.jadiefication.survival.Survival;

public class HomeGui implements InventoryHolder {

    private final Inventory inventory;

    public HomeGui() {
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 36, Component.text("Home Menu"));
        Border.setInventoryBorder(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
