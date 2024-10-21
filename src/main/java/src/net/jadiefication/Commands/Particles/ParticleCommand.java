package src.net.jadiefication.Commands.Particles;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.API.Particle.BaseParticleCommand;
import src.net.jadiefication.API.Particle.ParticleShapes;

import java.util.Arrays;

public class ParticleCommand extends BaseParticleCommand {

    public ParticleCommand(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void execute(@NotNull CommandSourceStack commandSourceStack, @NotNull String[] strings) {
        if (strings.length < 4) {
            Player player = (Player) commandSourceStack.getSender();
            player.sendMessage(Component.text("Please provide a particle shape,radius and duration."));
            return;
        }
        String particle = strings[0];
        int radius;
        int duration;
        try {
            radius = Integer.parseInt(strings[1]);
            duration = Integer.parseInt(strings[2]);
        } catch (NumberFormatException e) {
            Player player = (Player) commandSourceStack.getSender();
            player.sendMessage(Component.text("Invalid radius. Please provide a valid number."));
            return;
        }
        String particleType = strings[3].toUpperCase();
        if (!EnumUtils.isValidEnum(Particle.class, particleType)) {
            Player player = (Player) commandSourceStack.getSender();
            player.sendMessage(Component.text("Invalid particle type. Please provide a valid particle type."));
            return;
        }
        if (BaseParticleCommand.blackList.contains(particleType)) {
            Player player = (Player) commandSourceStack.getSender();
            player.sendMessage(Component.text("Invalid particle type. Please provide a valid particle type."));
            return;
        }
        Player player = (Player) commandSourceStack.getSender();
        try {
            switch (particle.toUpperCase()) {
                case "SPHERE" -> ParticleShapes.createSphere(this.plugin, player, radius, duration, Particle.valueOf(particleType));
                case "SQUARE" -> ParticleShapes.createSquare(this.plugin, player, radius, duration, Particle.valueOf(particleType));
                case "CIRCLE" -> ParticleShapes.createCircle(this.plugin, player, radius, duration, Particle.valueOf(particleType));
                case "HELIX" -> ParticleShapes.createHelix(this.plugin, player, 5, radius, duration, Particle.valueOf(particleType));
                case "ORBIT" -> ParticleShapes.createOrbit(this.plugin, player, radius, duration, Particle.valueOf(particleType));
                case "CUBE" -> ParticleShapes.createCube(this.plugin, player, radius, duration, Particle.valueOf(particleType));
                default -> player.sendMessage(Component.text("Gotta specify a valid particle shape!"));
            }
        } catch (Exception e) {
            player.sendMessage(Component.text("An error occurred while creating the particle shape."));
            plugin.getLogger().warning("An error occurred while creating the particle shape: " + Arrays.toString(e.getStackTrace()));
        }

    }
}
