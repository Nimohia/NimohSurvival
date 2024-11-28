package src.net.jadiefication.Listeners;

import com.booksaw.betterTeams.PlayerRank;
import com.booksaw.betterTeams.Team;
import com.booksaw.betterTeams.TeamPlayer;
import com.earth2me.essentials.User;
import com.earth2me.essentials.Warps;
import com.earth2me.essentials.commands.WarpNotFoundException;
import de.bluecolored.bluemap.api.BlueMapAPI;
import net.ess3.api.InvalidWorldException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import src.net.jadiefication.GUI.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

import static src.net.jadiefication.survival.Survival.api;
import static src.net.jadiefication.survival.Survival.essentials;

public class GuiListener implements Listener {

    private static Map<String, Consumer<Player>> commands = Map.ofEntries(
            Map.entry("Team Ender Chest", player -> player.performCommand("team echest")),
            Map.entry("Team Warp Menu", player -> {
                player.closeInventory();
                player.openInventory(new TeamWarpsGui(player).getInventory());
            }),
            Map.entry("Team Top", player -> player.performCommand("team top")),
            Map.entry("Team Disband", player -> {
                if (isOwner(player)) {
                    player.performCommand("team disband");
                    player.closeInventory();
                }
            }),
            Map.entry("Team Open", player -> {
                if (isOwner(player)) {
                    player.performCommand("team open");
                }
            }),
            Map.entry("Team Pvp", player -> {
                if (isOwner(player)) {
                    player.performCommand("team pvp");
                }
            }),
            Map.entry("Team Leave", player -> {
                if (!isOwner(player)) {
                    player.performCommand("team leave");
                }
            })

    );

    private static boolean isOwner(Player player) {
        Team team = com.booksaw.betterTeams.Team.getTeam(player);
        TeamPlayer teamPlayer = team.getTeamPlayer(player);
        assert teamPlayer != null;
        return teamPlayer.getRank().equals(PlayerRank.OWNER);
    }

    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event) {
        InventoryHolder inventory = event.getInventory().getHolder();
        Player player = (Player) event.getWhoClicked();
        if (inventory instanceof WarpGui || inventory instanceof HomeGui || inventory instanceof TeamGui || inventory instanceof TeamWarpsGui
        || inventory instanceof BluemapGui) {
            event.setCancelled(true);
            if (inventory instanceof HomeGui) {
                User user = essentials.getUser(player);
                List<String> homes = user.getHomes();
                String homeName = ((TextComponent) Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta().displayName())).content().replace("§6§l", "");
                if (homes.contains(homeName)) {
                    player.performCommand("homes " + homeName);
                }
            }
            if (inventory instanceof TeamWarpsGui) {
                com.booksaw.betterTeams.Team team = com.booksaw.betterTeams.Team.getTeam(player);
                String teamName = ((TextComponent) Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta().displayName())).content().replace("§6§l", "");
                if (team.getWarp(teamName) != null) {
                    player.performCommand("team warp " + teamName);
                }
            }
            if (inventory instanceof TeamGui) {
                String buttonName = ((TextComponent) Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta().displayName())).content().replace("§6§l", "");
                com.booksaw.betterTeams.Team team = com.booksaw.betterTeams.Team.getTeam(player);
                if (team != null) {
                    if (Objects.equals(buttonName, team.getName())) {
                        player.performCommand("team info " + team.getName());
                    }
                    Consumer<Player> command = commands.get(buttonName);
                    if (command != null) {
                        command.accept(player);
                    }
                }
            }
            if (inventory instanceof BluemapGui) {
                String buttonName = ((TextComponent) Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta().displayName())).content().replace("§6§l", "");
                UUID uuid = player.getUniqueId();
                if (Objects.equals(buttonName, "Bluemap visibility")) {
                    api.getWebApp().setPlayerVisibility(uuid, !api.getWebApp().getPlayerVisibility(uuid));
                    player.sendMessage(Component.text("Bluemap visibility set to " + (api.getWebApp().getPlayerVisibility(uuid) ? "VISIBLE" : "INVISIBLE")));
                } else if (Objects.equals(buttonName, "Bluemap")) {
                    player.sendMessage(Component.text("§6§lThe link to bluemap is nimoh.zanity.net/map"));
                }
            }
            if (inventory instanceof WarpGui) {
                String buttonName = ((TextComponent) Objects.requireNonNull(Objects.requireNonNull(event.getCurrentItem()).getItemMeta().displayName())).content().replace("§6§lWarp to ", "");
                Warps warps = essentials.getWarps();
                try {
                    if (warps.getWarp(buttonName) != null) {
                        player.performCommand("warps " + buttonName);
                    }
                } catch (Exception ignored) {

                }


            }
        }
    }
}
