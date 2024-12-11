package src.net.jadiefication.Commands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.Command.BaseCommand;

import static src.net.jadiefication.Core.Config.ConfigParser.parseConfig;

public class MainCommand extends BaseCommand {

    public MainCommand(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        if (strings[0].equals("reload")) {
            parseConfig();
        }
    }
}
