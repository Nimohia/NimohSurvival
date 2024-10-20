package src.net.jadiefication.Commands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import src.net.jadiefication.API.Command.BaseCommand;
import src.net.jadiefication.GUI.HomeGui;
import src.net.jadiefication.survival.Survival;

import java.util.List;

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
    public @Nullable String permission() {
        return "survival.home";
    }
}
