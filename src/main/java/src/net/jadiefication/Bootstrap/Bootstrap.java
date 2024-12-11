package src.net.jadiefication.Bootstrap;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Commands.MainCommand;
import src.net.jadiefication.Commands.SmallCommands.SmallCommand;
import src.net.jadiefication.Core.Command.BaseCommand;
import src.net.jadiefication.Core.Item.CustomItem;
import src.net.jadiefication.GUI.*;
import src.net.jadiefication.survival.Survival;

import java.util.List;
import java.util.Map;

import static src.net.jadiefication.Core.Config.ConfigParser.parseConfig;

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
            Map<BaseCommand, String> commandStringMap = Map.ofEntries(
                    Map.entry(new SmallCommand(instance, player -> {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " arena");
                    }), "arena"),
                    Map.entry(new SmallCommand(instance, player -> {
                        player.openInventory(new HomeGui(player).getInventory());
                    }), "home"),
                    Map.entry(new SmallCommand(instance, player -> {
                        player.openInventory(new TeamWarpsGui(player).getInventory());
                    }), "teamwarps"),
                    Map.entry(new SmallCommand(instance, player -> {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " void");
                    }), "spawn"),
                    Map.entry(new SmallCommand(instance, player -> {
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mv tp " + player.getName() + " market");
                    }), "market"),
                    Map.entry(new SmallCommand(instance, player -> {
                        player.openInventory(new WarpGui().getInventory());
                    }), "warp"),
                    Map.entry(new SmallCommand(instance, player -> {
                        player.openInventory(new TeamGui(player).getInventory());
                    }), "teams"),
                    Map.entry(new SmallCommand(instance, player -> {
                        player.openInventory(new BluemapGui().getInventory());
                    }), "bluemappanel"),
                    Map.entry(new SmallCommand(instance, player -> {
                        player.openInventory(new BluemapGui().getInventory());
                    }), "map"),
                    Map.entry(new SmallCommand(instance, player -> {
                        player.setItemInHand(CustomItem.createCustomItem(new ItemStack(Material.SPONGE), Component.text("Test"), List.of(Component.text("Test")), 0));
                    }), "test"),
                    Map.entry(new MainCommand(instance), "survival")

            );
            for (Map.Entry<BaseCommand, String> entry : commandStringMap.entrySet()) {
                commands.register(entry.getValue(), entry.getKey());
            }
        });
    }
}