package src.net.jadiefication.Commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
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

public class LobbyCommand extends BaseCommand {

    private final Survival plugin;

    public LobbyCommand(Survival plugin) {
        super(plugin);
        this.plugin = plugin;
        // Registering the plugin message channel for Velocity
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "velocity:main");
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {


        Player player = (Player) commandSourceStack.getSender();
        // Send the player to the lobby server using Velocity
        ByteArrayDataOutput out = ByteStreams.newDataOutput();

        out.writeUTF("Connect");
        out.writeUTF("lobby");  // Change "lobby" to your actual lobby server name in Velocity's config

        player.sendPluginMessage(plugin, "velocity:main", out.toByteArray());
    }

    @Override
    public @Nullable String permission() {
        return "survival.lobby";
    }
}
