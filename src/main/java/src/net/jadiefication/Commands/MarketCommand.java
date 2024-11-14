package src.net.jadiefication.Commands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.Command.BaseCommand;
import src.net.jadiefication.survival.Survival;

public class MarketCommand extends BaseCommand {

    public MarketCommand(Survival plugin) {
        super(plugin);
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        Player player = (Player) commandSourceStack.getSender();

        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " market");
    }

    @Override
    public String permission() {
        return "survival.market";
    }
}
