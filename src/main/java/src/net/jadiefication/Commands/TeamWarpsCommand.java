package src.net.jadiefication.Commands;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import src.net.jadiefication.GUI.TeamWarpsGui;
import src.net.jadiefication.survival.Survival;

import java.util.List;

public class TeamWarpsCommand extends BaseCommand {

    public TeamWarpsCommand(Survival plugin) {
        super(plugin);
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        Player player = (Player) commandSourceStack.getSender();
        player.openInventory(new TeamWarpsGui().getInventory());
    }


    @Override
    public @Nullable String permission() {
        return "survival.teamwarps";
    }
}
