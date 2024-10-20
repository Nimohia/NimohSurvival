package src.net.jadiefication.API.Particle;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class ParticleShapes {

    public static void createSphere(Plugin plugin, Player player, int size, int duration, Particle particle) {
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
                        .extra(0);

                double phi = Math.PI * (3 - Math.sqrt(5));
                int points = size * 100;

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

    public static void createCube(Plugin plugin, Player player, int size, int duration, Particle particle) {
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
                    .extra(0);

                for (int x = -size; x <= size; x++) {
                    for (int y = -size; y <= size; y++) {
                        for (int z = -size; z <= size; z++) {
                            if (Math.abs(x) == size || Math.abs(y) == size || Math.abs(z) == size) {
                                builder.location(center.clone().add(x, y, z)).spawn();
                            }
                        }
                    }
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    public static void createCircle(Plugin plugin, Player player, int radius, int duration, Particle particle) {
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
                        .extra(0);

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

    public static void createSquare(Plugin plugin, Player player, int size, int duration, Particle particle) {
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
                    .extra(0);

                for (int i = -size; i <= size; i++) {
                    builder.location(center.clone().add(i, 0, -size)).spawn();
                    builder.location(center.clone().add(i, 0, size)).spawn();
                    builder.location(center.clone().add(-size, 0, i)).spawn();
                    builder.location(center.clone().add(size, 0, i)).spawn();
                }
                ticks++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    public static void createHelix(Plugin plugin, Player player, int height, double radius, int duration, Particle particle) {
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
                    .extra(0);

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

    public static void createOrbit(Plugin plugin, Player player, int radius, int duration, Particle particle) {
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
                    .extra(0);

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