package src.net.jadiefication.Core.Particle;

import com.destroystokyo.paper.ParticleBuilder;
import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

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
        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks >= duration) {
                    this.cancel();
                    return;
                }
                Location location = player.getLocation();
                ParticleBuilder builder = new ParticleBuilder(particle)
                        .count(1)
                        .offset(0, 0, 0)
                        .extra(0)
                        .receivers(player);

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
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates cube particle pattern
     */
    public static void createCube(Plugin plugin, Player player, float size, float duration, Particle particle) {
        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks >= duration) {
                    this.cancel();
                    return;
                }
                Location center = player.getLocation();
                ParticleBuilder builder = new ParticleBuilder(particle)
                    .count(1)
                    .offset(0, 0, 0)
                    .extra(0)
                    .receivers(player);

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
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates circle particle pattern
     */
    public static void createCircle(Plugin plugin, Player player, float radius, float duration, Particle particle) {
        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks >= duration) {
                    this.cancel();
                    return;
                }
                Location center = player.getLocation();
                ParticleBuilder builder = new ParticleBuilder(particle)
                        .count(1)
                        .offset(0, 0, 0)
                        .extra(0)
                        .receivers(player);

                for (double angle = 0; angle < 2 * Math.PI; angle += Math.PI / 16) {
                    double x = radius * Math.cos(angle);
                    double z = radius * Math.sin(angle);
                    Location particleLocation = center.clone().add(x, 0, z);
                    builder.location(particleLocation).spawn();
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates pulsing particle effect
     */
    public static void createPulsing(Plugin plugin, Player player, float maxRadius, float duration, Particle particle, String shape) {
        new BukkitRunnable() {
            int ticks = 0;
            float currentRadius = 0;
            boolean expanding = true;

            @Override
            public void run() {
                if (ticks >= duration) {
                    this.cancel();
                    return;
                }

                if(!EnumUtils.isValidEnum(ParticleShape.class, shape.toUpperCase())) {
                    return;
                } else {
                    switch (shape.toUpperCase()) {
                        case "SPHERE":
                            createSphere(plugin, player, currentRadius, 1, particle);
                            break;
                        case "CUBE":
                            createCube(plugin, player, currentRadius, 1, particle);
                            break;
                        case "CIRCLE":
                            createCircle(plugin, player, currentRadius, 1, particle);
                            break;
                        case "SQUARE":
                            createSquare(plugin, player, currentRadius, 1, particle);
                            break;
                        case "HELIX":
                            createHelix(plugin, player, 2, currentRadius, 1, particle);
                            break;
                        case "ORBIT":
                            createOrbit(plugin, player, currentRadius, 1, particle);
                            break;
                    }
                }

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

                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates full shape particle effect
     */
    public static void createFullShape(Plugin plugin, Player player, float size, float duration, Particle particle, String shape) {
        new BukkitRunnable() {
            int ticks = 0;

            @Override
            public void run() {
                if (ticks >= duration) {
                    this.cancel();
                    return;
                }

                if(!EnumUtils.isValidEnum(ParticleShape.class, shape.toUpperCase())) {
                    return;
                } else {
                    switch (shape.toUpperCase()) {
                        case "SPHERE":
                            for (float i = -size; i < size; i += 1) {
                                createSphere(plugin, player, i, 1, particle);
                            }
                            break;
                        case "CUBE":
                            for (float i = -size; i < size; i += 1) {
                                createCube(plugin, player, i, 1, particle);
                            }
                            break;
                        case "CIRCLE":
                            for (float i = -size; i < size; i += 1) {
                                createCircle(plugin, player, i, 1, particle);
                            }
                            break;
                        case "SQUARE":
                            for (float i = -size; i < size; i += 1) {
                                createSquare(plugin, player, i, 1, particle);
                            }
                            break;
                        case "HELIX":
                            for (float i = -size; i < size; i += 1) {
                                createHelix(plugin, player, 2, i, 1, particle);
                            }
                            break;
                        case "ORBIT":
                            for (float i = -size; i < size; i += 1) {
                                createOrbit(plugin, player, i, 1, particle);
                            }
                            break;
                    }
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates square particle pattern
     */
    public static void createSquare(Plugin plugin, Player player, float size, float duration, Particle particle) {
        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks >= duration) {
                    this.cancel();
                    return;
                }
                Location center = player.getLocation();
                ParticleBuilder builder = new ParticleBuilder(particle)
                    .count(1)
                    .offset(0, 0, 0)
                    .extra(0)
                    .receivers(player);

                for (float i = -size; i <= size; i++) {
                    builder.location(center.clone().add(i, 0, -size)).spawn();
                    builder.location(center.clone().add(i, 0, size)).spawn();
                    builder.location(center.clone().add(-size, 0, i)).spawn();
                    builder.location(center.clone().add(size, 0, i)).spawn();
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates helix particle pattern
     */
    public static void createHelix(Plugin plugin, Player player, float height, double radius, float duration, Particle particle) {
        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks >= duration) {
                    this.cancel();
                    return;
                }
                Location center = player.getLocation();
                ParticleBuilder builder = new ParticleBuilder(particle)
                    .count(1)
                    .offset(0, 0, 0)
                    .extra(0)
                    .receivers(player);

                for (double y = 0; y < height; y += 0.1) {
                    double angle = y * 2 * Math.PI / 3;
                    double x = radius * Math.cos(angle);
                    double z = radius * Math.sin(angle);
                    builder.location(center.clone().add(x, y, z)).spawn();
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Creates orbiting particle effect
     */
    public static void createOrbit(Plugin plugin, Player player, float radius, float duration, Particle particle) {
        new BukkitRunnable() {
            double angle = 0;
            int ticks = 0;
            public void run() {
                if (ticks >= duration) {
                    this.cancel();
                    return;
                }
                ParticleBuilder builder = new ParticleBuilder(particle)
                    .count(1)
                    .offset(0, 0, 0)
                    .extra(0)
                    .receivers(player);

                double x = radius * Math.cos(angle);
                double z = radius * Math.sin(angle);
                Location particleLocation = player.getLocation().add(x, 1.5, z);
                builder.location(particleLocation).spawn();

                angle += Math.PI / 16;
                if (angle >= 2 * Math.PI) {
                    angle = 0;
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }
}