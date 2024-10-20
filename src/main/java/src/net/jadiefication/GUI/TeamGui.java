package src.net.jadiefication.GUI;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.survival.Survival;

import static src.net.jadiefication.API.GUI.Border.setInventoryBorder;

public class TeamGui implements InventoryHolder {

    private final Inventory inventory;

    public TeamGui() {
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 9, Component.text("Team Menu"));
        setInventoryBorder(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
