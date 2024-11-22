package src.net.jadiefication.Commands.Particles;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.Particle.BaseParticleCommand;
import src.net.jadiefication.Core.Particle.ParticleShape;
import src.net.jadiefication.Core.Particle.ParticleShapes;

import java.util.Arrays;
import java.util.Objects;

public class TypedParticleCommand extends BaseParticleCommand {

    private final String type;

    public TypedParticleCommand(JavaPlugin plugin, String type) {
        super(plugin);
        this.type = type;
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {

        Player player = (Player) commandSourceStack.getSender();

        if (strings.length < 4) {
            sendErrorMessage(player, "Please provide a particle shape,radius and duration.");
            return;
        }
        String particle = strings[0];
        int radius, duration;
        try {
            radius = Integer.parseInt(strings[1]);
            duration = Integer.parseInt(strings[2]);
        } catch (NumberFormatException e) {
            sendErrorMessage(player, "Invalid radius. Please provide a valid number.");
            return;
        }
        String particleType = strings[3].toUpperCase();
        if (!EnumUtils.isValidEnum(Particle.class, particleType) || BaseParticleCommand.blackList.contains(particleType)) {
            sendErrorMessage(player, "Invalid particle type. Please provide a valid particle type.");
            return;
        }
        try {
            if (!Objects.equals(type, "none")) ParticleShapes.createTyped(this.plugin, player, radius, duration, Particle.valueOf(particleType), particle, type);
            else ParticleShape.valueOf(particle.toUpperCase()).create(this.plugin, player, radius, duration, Particle.valueOf(particleType));
        } catch (Exception e) {
            sendErrorMessage(player, "An error occurred while creating the particle shape.");
            plugin.getLogger().warning("An error occurred while creating the particle shape: " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void sendErrorMessage(Player player, String message) {
        player.sendMessage(Component.text(message));
    }
}
