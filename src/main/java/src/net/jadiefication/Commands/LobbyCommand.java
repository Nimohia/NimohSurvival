package src.net.jadiefication.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import src.net.jadiefication.survival.Survival;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class LobbyCommand implements CommandExecutor, TabExecutor {

    private final Survival plugin;

    public LobbyCommand(Survival plugin) {
        this.plugin = plugin;
        // Registering the plugin message channel for Velocity
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "bungeecord:main");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            // Send the player to the lobby server using Velocity
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(byteArray);

            try {
                out.writeUTF("Connect");
                out.writeUTF("lobby");  // Change "lobby" to your actual lobby server name in Velocity's config
            } catch (IOException e) {
                e.printStackTrace();
            }

            player.sendPluginMessage(plugin, "bungeecord:main", byteArray.toByteArray());

        } else {
            sender.sendMessage("You must be a player to use this command.");
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return List.of();
    }
}
