package src.net.jadiefication.Core.Messages;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class CooldownMessages {

    public static void createCooldownMessage(long time, JavaPlugin plugin, Runnable function) {
        Bukkit.getScheduler().runTaskLater(plugin, function, time);
    }
}
