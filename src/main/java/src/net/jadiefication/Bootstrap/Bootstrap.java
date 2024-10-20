package src.net.jadiefication.Bootstrap;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.bootstrap.PluginBootstrap;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.API.Command.BaseCommand;
import src.net.jadiefication.Commands.*;
import src.net.jadiefication.survival.Survival;

import java.util.Map;

public class Bootstrap implements PluginBootstrap {

    @Override
    public void bootstrap(@NotNull BootstrapContext bootstrapContext) {
        Survival instance = Survival.getInstance();
        LifecycleEventManager<BootstrapContext> manager = bootstrapContext.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            Map<BaseCommand, String> commandStringMap = Map.of(
                    new ArenaCommand(instance), "arena",
                    new HomeCommand(instance), "home",
                    new TeamWarpsCommand(instance), "teamwarps",
                    new SpawnCommand(instance), "spawn",
                    new MarketCommand(instance), "market"
            );
            for (Map.Entry<BaseCommand, String> entry : commandStringMap.entrySet()) {
                commands.register(entry.getValue(), entry.getKey());
            }
        });
    }
}