package src.net.jadiefication.Commands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.API.Command.BaseCommand;
import src.net.jadiefication.GUI.TeamWarpsGui;
import src.net.jadiefication.survival.Survival;

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
    public String permission() {
        return "survival.teamwarps";
    }
}
