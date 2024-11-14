package src.net.jadiefication.Commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.Command.BaseCommand;
import src.net.jadiefication.survival.Survival;

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
    public String permission() {
        return "survival.lobby";
    }
}
