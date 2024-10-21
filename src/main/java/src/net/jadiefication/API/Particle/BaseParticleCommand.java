package src.net.jadiefication.API.Particle;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.API.Command.BaseCommand;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class BaseParticleCommand extends BaseCommand {

    public BaseParticleCommand(JavaPlugin plugin) {
        super(plugin);
    }

    public static final List<String> blackList = List.of("ENTITY_EFFECT", "DUST", "ITEM", "BLOCK", "FALLING_DUST", "DUST_COLOR_TRANSITION",
            "VIBRATION", "SCULK_CHARGE", "SHRIEK", "DUST_PILLAR", "BLOCK_MARKER");

    @Override
    public abstract void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings);

    @Override
    public @NotNull Collection<String> suggest(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] args) {
        Collection<String> suggestions = List.of();
        switch (args.length) {
            case 1 -> suggestions = Arrays.stream(ParticleShape.values()).map(ParticleShape::name).toList();
            case 2 -> suggestions = List.of("<radius>");
            case 3 -> suggestions = List.of("<duration>");
            case 4 -> suggestions = Arrays.stream(Particle.values()).map(Particle::name).toList();
        }
        return suggestions;
    }

    @Override
    public boolean canUse(@NotNull CommandSender sender) {
        return sender.isOp();
    }
}
