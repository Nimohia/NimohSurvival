package src.net.jadiefication.Commands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.Command.BaseCommand;
import src.net.jadiefication.GUI.HomeGui;
import src.net.jadiefication.survival.Survival;

public class HomeCommand extends BaseCommand {

    public HomeCommand(Survival plugin) {
        super(plugin);
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        Player player = (Player) commandSourceStack.getSender();
        player.openInventory(new HomeGui().getInventory());
    }

    @Override
    public String permission() {
        return "survival.home";
    }
}
