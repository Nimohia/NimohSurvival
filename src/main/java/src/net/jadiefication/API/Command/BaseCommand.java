package src.net.jadiefication.API.Command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public abstract class BaseCommand implements BasicCommand {
    protected final JavaPlugin plugin;

    public BaseCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public abstract void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings);

    @Override
    public boolean canUse(@NotNull CommandSender sender) {
        return sender instanceof Player;
    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        return BasicCommand.super.suggest(commandSourceStack, args);
    }

    @Override
    public @Nullable String permission() {
        return "survival." + getClass().getSimpleName().toLowerCase().replace("command", "");
    }

    protected void sendNoPermissionMessage(Player player) {
        player.sendMessage("You don't have permission to use this command.");
    }
}
