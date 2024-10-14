package src.net.jadiefication.GUI;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.API.Border;
import src.net.jadiefication.survival.Survival;

public class TeamWarpsGui implements InventoryHolder {

    private final Inventory inventory;

    public TeamWarpsGui() {
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 9, Component.text("Team Warps"));
        Border.setInventoryBorder(inventory);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
