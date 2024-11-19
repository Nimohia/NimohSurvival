package src.net.jadiefication.Core.Particle;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public enum ParticleShape {
    SPHERE {
        @Override
        public void create(Plugin plugin, Player player, float size, float duration, Particle particle) {
            ParticleShapes.createSphere(plugin, player, size, duration, particle);
        }
    },
    CUBE {
        @Override
        public void create(Plugin plugin, Player player, float size, float duration, Particle particle) {
            ParticleShapes.createCube(plugin, player, size, duration, particle);
        }
    },
    CIRCLE {
        @Override
        public void create(Plugin plugin, Player player, float size, float duration, Particle particle) {
            ParticleShapes.createCircle(plugin, player, size, duration, particle);
        }
    },
    SQUARE {
        @Override
        public void create(Plugin plugin, Player player, float size, float duration, Particle particle) {
            ParticleShapes.createSquare(plugin, player, size, duration, particle);
        }
    },
    HELIX {
        @Override
        public void create(Plugin plugin, Player player, float size, float duration, Particle particle) {
            ParticleShapes.createHelix(plugin, player, 2, size, duration, particle);
        }
    },
    ORBIT {
        @Override
        public void create(Plugin plugin, Player player, float size, float duration, Particle particle) {
            ParticleShapes.createOrbit(plugin, player, size, duration, particle);
        }
    };

    public abstract void create(Plugin plugin, Player player, float size, float duration, Particle particle);
}
