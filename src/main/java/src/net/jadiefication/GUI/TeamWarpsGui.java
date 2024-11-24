package src.net.jadiefication.GUI;

import com.booksaw.betterTeams.Team;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.GUI.Heads;
import src.net.jadiefication.survival.Survival;

import java.net.URL;
import java.util.List;

import static src.net.jadiefication.Core.GUI.Border.setInventoryBorder;
import static src.net.jadiefication.Core.GUI.Heads.createHead;
import static src.net.jadiefication.Core.GUI.Heads.createUrl;

public class TeamWarpsGui implements InventoryHolder {

    private final Inventory inventory;

    public TeamWarpsGui(Player player) {
        URL url = createUrl("http://textures.minecraft.net/texture/9f30ffbc0110efa34e030860da18c8a1d6b223de0f00d9e4c5d0cfa7ecfafa48");
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 27, Component.text("Team Warps"));
        setInventoryBorder(inventory);
        Team team = Team.getTeam(player);
        if (team.getTeamHome() != null) {
            ItemStack item = createHead(Heads.createHead(url), Component.text("§6§lTeam Home"), List.of(Component.text("§7Click to warp to team home")));
            this.inventory.setItem(11, item);
        } else {
            ItemStack item = createHead(Heads.createComingSoonHead(), Component.text("§6§lTeam Home"), List.of(Component.text("§7Team home not set.")));
            this.inventory.setItem(11, item);
        }
        if (!team.getWarps().get().isEmpty()) {
            for (int i = 0; i < team.getWarps().get().size(); i++) {
                ItemStack item = createHead(createHead(url),
                        Component.text("§6§l" + team.getWarps().get((String) team.getWarps().getConvertedList().toArray()[i])),
                        List.of(Component.text("§7Click to warp to " + team.getWarps().get((String) team.getWarps().getConvertedList().toArray()[i]))));
                this.inventory.setItem(13 + i, item);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                ItemStack item = createHead(Heads.createComingSoonHead(), Component.text("§6§lTeam Warp"), List.of(Component.text("§7No warp set."), Component.text("Set one by using /team setwarp <name>.")));
                this.inventory.setItem(13 + i, item);
            }
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
