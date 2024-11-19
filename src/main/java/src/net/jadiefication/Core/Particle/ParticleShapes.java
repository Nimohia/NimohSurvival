package src.net.jadiefication.Core.Particle;

import com.destroystokyo.paper.ParticleBuilder;
import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

/**
 * Complex particle effect generator
 */
public abstract class ParticleShapes {

    /**
     * Creates sphere particle pattern
     * @param plugin Plugin instance
     * @param player Target player
     * @param size Sphere size
     * @param duration Effect duration
     * @param particle Particle type
     */
    public static void createSphere(Plugin plugin, Player player, float size, float duration, Particle particle) {
        new ParticleEffect(duration) {
            @Override
            protected void spawnParticles() {
                final Location location = player.getLocation();
                ParticleBuilder builder = getBaseBuilder(particle, player);

                double phi = Math.PI * (3 - Math.sqrt(5));
                float points = size * 100;

                for (int i = 0; i < points; i++) {
                    double y = 1 - (i / (double)(points - 1)) * 2;
                    double radius = Math.sqrt(1 - y * y);
                    double theta = phi * i;

                    double x = Math.cos(theta) * radius;
                    double z = Math.sin(theta) * radius;

                    Location particleLocation = location.clone().add(x * size, y * size, z * size);
                    builder.location(particleLocation).spawn();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates cube particle pattern
     */
    public static void createCube(Plugin plugin, Player player, float size, float duration, Particle particle) {
        new ParticleEffect(duration) {
            @Override
            protected void spawnParticles() {
                Location center = player.getLocation();
                ParticleBuilder builder = getBaseBuilder(particle, player);;

                float step = 0.1f; // Smaller step for higher density
                for (float x = -size; x <= size; x += step) {
                    for (float y = -size; y <= size; y += step) {
                        for (float z = -size; z <= size; z += step) {
                            if (Math.abs(x) > size - step && Math.abs(y) > size - step ||
                                    Math.abs(x) > size - step && Math.abs(z) > size - step ||
                                    Math.abs(y) > size - step && Math.abs(z) > size - step) {
                                builder.location(center.clone().add(x, y, z)).spawn();
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates circle particle pattern
     */
    public static void createCircle(Plugin plugin, Player player, float radius, float duration, Particle particle) {
        new ParticleEffect(duration) {
            @Override
            protected void spawnParticles() {
                Location center = player.getLocation();
                ParticleBuilder builder = getBaseBuilder(particle, player);

                for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 16) {
                    double x = radius * Math.cos(angle);
                    double z = radius * Math.sin(angle);
                    Location particleLocation = center.clone().add(x, 0, z);
                    builder.location(particleLocation).spawn();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates pulsing particle effect
     */
    public static void createTyped(Plugin plugin, Player player, float maxRadius, float duration, Particle particle, String shape, String type) {
        if (Objects.equals(type, "pulsing")) {
            new ParticleEffect(duration) {
                float currentRadius = 0;
                boolean expanding = true;

                @Override
                protected void spawnParticles() {

                    ParticleShape.valueOf(shape.toUpperCase()).create(plugin, player, currentRadius, 1, particle);

                    if (expanding) {
                        currentRadius += 0.1f;
                        if (currentRadius >= maxRadius) {
                            expanding = false;
                        }
                    } else {
                        currentRadius -= 0.1f;
                        if (currentRadius <= 0) {
                            expanding = true;
                        }
                    }
                }
            }.runTaskTimer(plugin, 0L, 1L);
        } else if (Objects.equals(type, "full")) {
            new ParticleEffect(duration) {
                @Override
                protected void spawnParticles() {
                    for (float i = -maxRadius; i < maxRadius; i += 1){
                        ParticleShape.valueOf(shape.toUpperCase()).create(plugin, player, maxRadius, duration, particle);
                    }
                }
            }.runTaskTimer(plugin, 0L, 1L);
        }
    }

    /**
     * Creates square particle pattern
     */
    public static void createSquare(Plugin plugin, Player player, float size, float duration, Particle particle) {
        new ParticleEffect(duration) {
            @Override
            protected void spawnParticles() {
                Location center = player.getLocation();
                ParticleBuilder builder = getBaseBuilder(particle, player);

                for (float i = -size; i <= size; i++) {
                    builder.location(center.clone().add(i, 0, -size)).spawn();
                    builder.location(center.clone().add(i, 0, size)).spawn();
                    builder.location(center.clone().add(-size, 0, i)).spawn();
                    builder.location(center.clone().add(size, 0, i)).spawn();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates helix particle pattern
     */
    public static void createHelix(Plugin plugin, Player player, float height, double radius, float duration, Particle particle) {
        new ParticleEffect(duration) {
            @Override
            protected void spawnParticles() {
                Location center = player.getLocation();
                ParticleBuilder builder = getBaseBuilder(particle, player);

                for (double y = 0; y < height; y += 0.1) {
                    double angle = y * 2 * Math.PI / 3;
                    double x = radius * Math.cos(angle);
                    double z = radius * Math.sin(angle);
                    builder.location(center.clone().add(x, y, z)).spawn();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates orbiting particle effect
     */
    public static void createOrbit(Plugin plugin, Player player, float radius, float duration, Particle particle) {
        new ParticleEffect(duration) {
            double angle = 0;

            @Override
            protected void spawnParticles() {
                ParticleBuilder builder = getBaseBuilder(particle, player);

                double x = radius * Math.cos(angle);
                double z = radius * Math.sin(angle);
                Location particleLocation = player.getLocation().add(x, 1.5, z);
                builder.location(particleLocation).spawn();

                angle += Math.PI / 16;
                if (angle >= 2 * Math.PI) {
                    angle = 0;
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private static ParticleBuilder getBaseBuilder(Particle particle, Player player) {
        return new ParticleBuilder(particle)
                .count(1)
                .offset(0, 0, 0)
                .extra(0)
                .receivers(player);
    }
}

