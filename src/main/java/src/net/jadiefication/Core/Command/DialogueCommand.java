package src.net.jadiefication.Core.Command;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DialogueCommand extends BaseCommand{

    private List<String> texts;
    private String name;

    public DialogueCommand(JavaPlugin plugin, List<String> texts, String name) {
        super(plugin);
        this.texts = texts;
        this.name = name;
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        Player player = (Player) commandSourceStack.getSender();

        new BukkitRunnable() {
            int index = 0;

            @Override
            public void run() {
                if (index >= texts.size()) {
                    this.cancel(); // Stop the task when all messages are sent
                    return;
                }
                String component = texts.get(index);
                player.sendMessage(Component.text(name + ": " + component));
                index++;
            }
        }.runTaskTimer(plugin, 0L, 50L);
    }

    @Override
    public @Nullable String permission() {
        return "survival.dialogue";
    }
}
