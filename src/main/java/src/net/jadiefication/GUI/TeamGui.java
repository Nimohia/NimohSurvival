package src.net.jadiefication.GUI;

import com.booksaw.betterTeams.Team;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.survival.Survival;

import static src.net.jadiefication.Core.GUI.Border.setInventoryBorder;

public class TeamGui implements InventoryHolder {

    private final Inventory inventory;

    public TeamGui(Player player) {
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 9, Component.text("Team Menu"));
        setInventoryBorder(inventory);
        Team team = Team.getTeam(player);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
