package src.net.jadiefication.Core.Command;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Foundation for all plugin commands with integrated permission handling
 */
public abstract class BaseCommand implements BasicCommand {

    /**
     * @param plugin JavaPlugin instance for command registration
     */
    protected final JavaPlugin plugin;

    public BaseCommand(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Core command execution logic
     * @param commandSourceStack Command execution context
     * @param strings Command arguments
     */
    @Override
    public abstract void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings);

    /**
     * Validates if sender can use the command
     * @param sender Command sender to validate
     * @return true if sender is a Player
     */
    @Override
    public boolean canUse(@NotNull CommandSender sender) {
        return sender instanceof Player;
    }

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        return BasicCommand.super.suggest(commandSourceStack, args);
    }

    /**
     * Generates permission string based on command class name
     * @return Permission node string
     */
    @Override
    public @Nullable String permission() {
        return "survival." + getClass().getSimpleName().toLowerCase().replace("command", "");
    }

    /**
     * Sends no permission message to player
     * @param player Target player
     */
    protected void sendNoPermissionMessage(Player player) {
        player.sendMessage("You don't have permission to use this command.");
    }
}
