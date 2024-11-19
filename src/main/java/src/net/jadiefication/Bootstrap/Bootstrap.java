package src.net.jadiefication.Bootstrap;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Commands.SmallCommands.SmallCommand;
import src.net.jadiefication.Core.Command.BaseCommand;
import src.net.jadiefication.GUI.HomeGui;
import src.net.jadiefication.GUI.TeamWarpsGui;
import src.net.jadiefication.survival.Survival;

import java.util.Map;

/**
 * Plugin bootstrap handler for NimohSurvival
 * Manages initial plugin setup and command registration
 */
public class Bootstrap implements PluginBootstrap {

    /**
     * Bootstraps the plugin and registers commands
     * @param bootstrapContext Context provided by Paper for plugin initialization
     */
    @Override
    public void bootstrap(@NotNull BootstrapContext bootstrapContext) {
        // Registers core commands like arena, home, teamwarps, spawn, market
        // Uses lifecycle event system for command registration
        // Maps commands to their string identifiers
        Survival instance = Survival.getInstance();
        LifecycleEventManager<BootstrapContext> manager = bootstrapContext.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            Map<BaseCommand, String> commandStringMap = Map.of(
                    new SmallCommand(instance, player -> {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " arena");
                    }), "arena",
                    new SmallCommand(instance, player -> {
                        player.openInventory(new HomeGui().getInventory());
                    }), "homegui",
                    new SmallCommand(instance, player -> {
                        player.openInventory(new TeamWarpsGui().getInventory());
                    }), "teamwarps",
                    new SmallCommand(instance, player -> {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " void");
                    }), "spawn",
                    new SmallCommand(instance, player -> {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " market");
                    }), "market"
            );
            for (Map.Entry<BaseCommand, String> entry : commandStringMap.entrySet()) {
                commands.register(entry.getValue(), entry.getKey());
            }
        });
    }
}