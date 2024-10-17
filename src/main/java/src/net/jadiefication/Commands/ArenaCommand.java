package src.net.jadiefication.Commands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.survival.Survival;

public class ArenaCommand extends BaseCommand {

    public ArenaCommand(Survival plugin) {
        super(plugin);
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        Player player = (Player) commandSourceStack.getSender();
        if (player.hasPermission(permission())) {
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " arena");
        } else {
            sendNoPermissionMessage(player);
        }
    }

    @Override
    public String permission() {
        return "survival.arena";
    }
}
