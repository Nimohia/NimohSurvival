package src.net.jadiefication.survival;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import src.net.jadiefication.API.Command.BaseCommand;
import src.net.jadiefication.Commands.*;
import src.net.jadiefication.Commands.Particles.FullParticleCommand;
import src.net.jadiefication.Commands.Particles.ParticleCommand;
import src.net.jadiefication.Commands.Particles.PulsingParticleCommand;
import src.net.jadiefication.GUI.HomeGui;
import src.net.jadiefication.GUI.TeamGui;
import src.net.jadiefication.GUI.TeamWarpsGui;
import src.net.jadiefication.GUI.WarpGui;
import src.net.jadiefication.Listeners.TeamGuiChatListener;
import src.net.jadiefication.Listeners.TeamGuiListener;

import java.util.List;
import java.util.Map;

public final class Survival extends JavaPlugin implements Listener {

    public static TeamGui teamGui;
    private final TeamGuiListener guiListener = new TeamGuiListener();
    public static Survival instance;
    private List<InventoryHolder> guis;

    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "velocity:main");
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(guiListener, this);
        Bukkit.getPluginManager().registerEvents(new TeamGuiChatListener(guiListener), this);

        // Initialize GUIs here
        teamGui = new TeamGui();
        guis = List.of(new HomeGui(), new TeamGui(), new TeamWarpsGui(), new WarpGui());

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
            Map<BaseCommand, String> commandStringMap = Map.of(
                    new ParticleCommand(this), "particle",
                    new LobbyCommand(this), "lobby",
                    new PulsingParticleCommand(this), "pulsingparticle",
                    new FullParticleCommand(this), "fullparticle"
            );
            for (Map.Entry<BaseCommand, String> entry : commandStringMap.entrySet()) {
                commands.register(entry.getValue(), entry.getKey());
            }
            commands.register("lobby", new LobbyCommand(this));
        });
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this, "velocity:main");
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
