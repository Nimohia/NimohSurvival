package src.net.jadiefication.Commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import src.net.jadiefication.survival.Survival;

import java.util.List;

public class SpawnCommand extends BaseCommand {

    public SpawnCommand(Survival plugin) {
        super(plugin);
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        Player player = (Player) commandSourceStack.getSender();
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " void");
    }

    @Override
    public @Nullable String permission() {
        return "survival.spawn";
    }
}
