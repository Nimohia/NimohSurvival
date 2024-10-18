package src.net.jadiefication.survival;

import com.mojang.brigadier.Command;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import src.net.jadiefication.Commands.*;
import src.net.jadiefication.GUI.TeamGui;
import src.net.jadiefication.Listeners.TeamGuiChatListener;
import src.net.jadiefication.Listeners.TeamGuiListener;

import java.util.List;

public final class Survival extends JavaPlugin implements Listener {

    public static TeamGui teamGui;
    private final TeamGuiListener guiListener = new TeamGuiListener();
    public static Survival instance;

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "velocity:main");
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(guiListener, this);
        Bukkit.getPluginManager().registerEvents(new TeamGuiChatListener(guiListener), this);
        teamGui = new TeamGui();
        instance = this;
        registerCommand();
    }

    public static Survival getInstance() {
        return instance;
    }

    private void registerCommand() {
        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register("lobby", new LobbyCommand(this));
        });
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this, "velocity:main");
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
