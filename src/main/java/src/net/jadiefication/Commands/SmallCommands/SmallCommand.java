package src.net.jadiefication.Commands.SmallCommands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.Command.BaseCommand;

import java.util.function.Consumer;

public class SmallCommand extends BaseCommand {

    private Consumer<Player> function;

    public SmallCommand(JavaPlugin plugin, Consumer<Player> function) {
        super(plugin);
        this.function = function;
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        function.accept((Player) commandSourceStack.getSender());
    }
}
