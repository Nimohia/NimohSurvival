package src.net.jadiefication.GUI;

import com.booksaw.betterTeams.PlayerRank;
import com.booksaw.betterTeams.Team;
import com.booksaw.betterTeams.TeamPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.GUI.Heads;
import src.net.jadiefication.survival.Survival;

import java.net.URL;
import java.util.List;
import java.util.Map;

import static src.net.jadiefication.Core.GUI.Border.setInventoryBorder;

public class TeamGui implements InventoryHolder {

    private final Inventory inventory;

    public TeamGui(Player player) {
        ItemStack border = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta borderMeta = border.getItemMeta();
        borderMeta.displayName(Component.text(" "));
        border.setItemMeta(borderMeta);
        List<URL> urls = List.of(Heads.createUrl("http://textures.minecraft.net/texture/c268301d5541bc5118107a906a61bc0d985ec967d6f3cb14eba29d5880d6af11"),
                Heads.createUrl("http://textures.minecraft.net/texture/1e17b5ea4e28b324514fdfd6e92c670b534b3bf084753b90f5e68b8e96e0c2da"),
                Heads.createUrl("http://textures.minecraft.net/texture/a6cc486c2be1cb9dfcb2e53dd9a3e9a883bfadb27cb956f1896d602b4067"),
                Heads.createUrl("http://textures.minecraft.net/texture/6a5361b52daf4f1c5c5480a39faaa10897595fa5763f757bdda3956588fec678"),
                Heads.createUrl("http://textures.minecraft.net/texture/beb588b21a6f98ad1ff4e085c552dcb050efc9cab427f46048f18fc803475f7"),
                Heads.createUrl("http://textures.minecraft.net/texture/874f8367a31df6fccf51871d0a610709ac58a661e41e03018c510be4f2104516"),
                Heads.createUrl("http://textures.minecraft.net/texture/e8021faa331363ab517cbfb4975651e5948997628a4854748c7b2275e7627616"));
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 36, Component.text("Team Menu"));
        this.inventory.setItem(11, border);
        this.inventory.setItem(20, border);
        setInventoryBorder(inventory);
        Team team = Team.getTeam(player);
        if (team == null) {
            putInInventory(inventory, Map.of(10, Heads.createHead(Heads.createHead(urls.getFirst()), Component.text("§6§lCreate Team"), List.of(Component.text("§7Create or join a team to use team this feature"))),
                    19, Heads.createHead(Heads.createComingSoonHead(), Component.text("§6§lNot available"), List.of(Component.text("§7Create or join a team to use team this feature")))));
            for (int i = 0; i != 5; i++) {
                this.inventory.setItem(12 + i, Heads.createHead(Heads.createComingSoonHead(), Component.text("§6§lNot available"), List.of(Component.text("§7Create or join a team to use team this feature."), Component.text("§7Use /team create <name> <tag> or wait for someone to invite you"))));
                this.inventory.setItem(21 + i, Heads.createHead(Heads.createComingSoonHead(), Component.text("§6§lNot available"), List.of(Component.text("§7Create or join a team to use team this feature."), Component.text("§7Use /team create <name> <tag> or wait for someone to invite you"))));
            }
        } else {
            putInInventory(inventory, Map.of(10, Heads.createHead(Heads.createHead(urls.getFirst()), Component.text("§6§l" + team.getName()), List.of(Component.text("§7Click to open team info"))),
                    12, Heads.createHead(Heads.createHead(urls.get(2)), Component.text("§6§lTeam Ender Chest"), List.of(Component.text("§7Click to open the team ender chest"))),
                    13, Heads.createHead(Heads.createHead(urls.get(6)), Component.text("§6§lTeam Top"), List.of(Component.text("§7Click to view the team top"))),
                    14, Heads.createHead(Heads.createHead(urls.get(1)), Component.text("§6§lTeam Warp Menu"), List.of(Component.text("§7Click to open the team warp menu")))));
            TeamPlayer teamPlayer = team.getTeamPlayer(player);
            assert teamPlayer != null;
            if (teamPlayer.getRank().equals(PlayerRank.OWNER)) {
                putInInventory(inventory, Map.of(19, Heads.createHead(Heads.createHead(urls.get(4)), Component.text("§6§lTeam Disband"), List.of(Component.text("§7Click to disband your team"))),
                        21, Heads.createHead(Heads.createHead(urls.get(5)), Component.text("§6§lTeam Open"), List.of(Component.text("§7Click to open your team to everyone"))),
                        22, Heads.createHead(Heads.createHead(urls.get(3)), Component.text("§6§lTeam Pvp"), List.of(Component.text("§7Click to enable/disable team pvp"))),
                        23, Heads.createComingSoonHead()));
            } else {
                this.inventory.setItem(19, Heads.createHead(Heads.createHead(urls.get(4)), Component.text("§6§lTeam Leave"), List.of(Component.text("§7Click to leave your team"))));
                for (int i = 0; i != 3; i++) {
                    this.inventory.setItem(21 + i, Heads.createHead(Heads.createComingSoonHead(), Component.text("§6§lNot available"), List.of(Component.text("§7You do not have permission to use this feature."))));
                }
            }
            for (int i = 0; i != 2; i++) {
                this.inventory.setItem(15 + i, Heads.createComingSoonHead());
                this.inventory.setItem(24 + i, Heads.createComingSoonHead());
            }
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

    private void putInInventory(Inventory inventory, Map<Integer, ItemStack> slotToItem) {
        for (Map.Entry<Integer, ItemStack> entry : slotToItem.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue());
        }
    }
}
