package src.net.jadiefication.survival;

import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import src.net.jadiefication.Core.Command.BaseCommand;
import src.net.jadiefication.Core.Command.DialogueCommand;
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

/**
 * Main plugin class for NimohSurvival
 */
public final class Survival extends JavaPlugin implements Listener {

    public static TeamGui teamGui;
    private final TeamGuiListener guiListener = new TeamGuiListener();
    public static Survival instance;
    private static List<InventoryHolder> guis;
    /**
     * Plugin enable logic
     * Initializes systems and registers events/commands
     */
    @Override
    public void onEnable() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "velocity:main");
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(guiListener, this);
        Bukkit.getPluginManager().registerEvents(new TeamGuiChatListener(guiListener), this);

        // Initialize GUIs here
        guis = List.of(new HomeGui(), new TeamGui(), new TeamWarpsGui(), new WarpGui());

        instance = this;
        registerCommand();
    }

    /**
     * Gets plugin instance
     * @return Survival plugin instance
     */
    public static Survival getInstance() {
        return instance;
    }

    /**
     * Registers plugin commands
     */
    private void registerCommand() {
        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            Map<BaseCommand, String> commandStringMap = Map.ofEntries(
                    Map.entry(new ParticleCommand(this), "particle"),
                    Map.entry(new LobbyCommand(this), "lobby"),
                    Map.entry(new PulsingParticleCommand(this), "pulsingparticle"),
                    Map.entry(new FullParticleCommand(this), "fullparticle"),
                    Map.entry(new DialogueCommand(this, List.of(
                            "You ever stopped to wonder why farming is so repetitive?",
                            "But you keep doing it anyway, don't you?",
                            "Why don't you just try using the-",
                            "Sorry, but you aren't ready yet."), "§6§lꜰᴀʀᴍᴇʀ§r"), "farmertalk"),
                    Map.entry(new DialogueCommand(this, List.of(
                            "Fishing takes patience, doesn’t it?",
                            "It’s just you, the water, and a little luck.",
                            "Maybe one day, you'll be ready for the mysteries of the deep.",
                            "But for now, enjoy the quiet moments."), "§6§lꜰɪꜱʜᴇʀ§r"), "fishertalk"),
                    Map.entry(new DialogueCommand(this, List.of(
                            "You ever feel the rush of a real fight?",
                            "Nothing beats outsmarting your opponent, reading their every move.",
                            "But true mastery takes time. You’re not ready for the arena’s toughest yet.",
                            "Keep training... then, we’ll see what you’re made of."), "§6§lʜᴜɴᴛᴇʀ§r"), "huntertalk"),
                    Map.entry(new DialogueCommand(this, List.of(
                            "§l§b<---------------------------------------------------->",
                            "Welcome, §6§lJadiefication§r, to §l§bNimoh",
                            "You can do §4/rtp§r to get into the §awilderness§r, and §9/kit tools",
                            "§l§b<---------------------------------------------------->"
                    ), ""), "joinfirstmessagetest")

            );
            for (Map.Entry<BaseCommand, String> entry : commandStringMap.entrySet()) {
                commands.register(entry.getValue(), entry.getKey());
            }
            commands.register("lobby", new LobbyCommand(this));
        });
    }

    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event) {
        InventoryHolder inventory = event.getInventory().getHolder();
        if (guis.contains(inventory)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public static void onFirstJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.sendMessage(Component.text("§l§b<---------------------------------------------------->"));
            player.sendMessage(Component.text("Welcome, §6§l" + player.getName() + "§r, to §l§bNimoh"));
            player.sendMessage(Component.text("You can do §4/rtp§r to get into the §awilderness§r, and §9/kit tools"));
            player.sendMessage(Component.text("§l§b<---------------------------------------------------->"));
        }
    }

    /**
     * Plugin disable logic
     * Cleans up resources
     */
    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this, "velocity:main");
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
