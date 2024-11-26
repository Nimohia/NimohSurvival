package src.net.jadiefication.survival;

import com.earth2me.essentials.Essentials;
import de.bluecolored.bluemap.api.BlueMapAPI;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;
import src.net.jadiefication.Commands.SmallCommands.SmallCommand;
import src.net.jadiefication.Core.ActionBar.ActionBarUpdater;
import src.net.jadiefication.Core.Command.BaseCommand;
import src.net.jadiefication.Core.Command.DialogueCommand;
import src.net.jadiefication.Commands.Particles.TypedParticleCommand;
import src.net.jadiefication.GUI.BluemapGui;
import src.net.jadiefication.GUI.TeamGui;
import src.net.jadiefication.Listeners.FirstJoinListener;
import src.net.jadiefication.Listeners.GuiListener;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Main plugin class for NimohSurvival
 */
public final class Survival extends JavaPlugin{

    public static TeamGui teamGui;
    public static Survival instance;
    public static Economy economy;
    public static Essentials essentials;
    public static BlueMapAPI api;

    /**
     * Plugin enable logic
     * Initializes systems and registers events/commands
     */
    @Override
    public void onEnable() {
        essentials = getEssentialsPlugin();
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
        Bukkit.getPluginManager().registerEvents(new FirstJoinListener(), this);

        instance = this;
        registerCommand();
        if (setupEconomy()) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::updateScoreboards, 0L, 20L); // Update every second
            new ActionBarUpdater().runTaskTimerAsynchronously(this, 0L, 1L);
        }
        BlueMapAPI.onEnable(api -> {
            Survival.api = api;
            getLogger().info("BlueMapAPI initialized successfully!");
        });
    }

    public static Essentials getEssentialsPlugin() {
        Plugin essentialsPlugin = Bukkit.getPluginManager().getPlugin("Essentials");
        if (essentialsPlugin != null && essentialsPlugin.isEnabled()) {
            return (Essentials) essentialsPlugin;
        }
        return null;
    }

    /**
     * Gets plugin instance
     * @return Survival plugin instance
     */
    public static Survival getInstance() {
        return instance;
    }

    private boolean setupEconomy() {
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        economy = rsp.getProvider();
        return economy != null;
    }

    private void updateScoreboards() {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard mainScoreboard = manager.getMainScoreboard(); // Use the main scoreboard

        // Ensure the objective exists
        Objective objective = mainScoreboard.getObjective("Balance");
        if (objective == null) {
            objective = mainScoreboard.registerNewObjective("Balance", "dummy", ChatColor.GOLD + "Your Balance");
            objective.setDisplaySlot(null);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            Objective finalObjective = objective;
            Bukkit.getScheduler().runTask(this, () -> { // Schedule on the main thread
                double balance = economy.getBalance(player);

                // Update the player's balance in the scoreboard
                int scoreBalance = (int) balance; // Convert to integer
                finalObjective.getScore(player.getName()).setScore(scoreBalance);

                // Optionally display the scoreboard (this could conflict with other scoreboards)
                player.setScoreboard(mainScoreboard);
            });
        }
    }


    /**
     * Registers plugin commands
     */
    private void registerCommand() {
        LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            Map<BaseCommand, String> commandStringMap = Map.ofEntries(
                    Map.entry(new TypedParticleCommand(this, "none"), "particle"),
                    Map.entry(new TypedParticleCommand(this, "pulsing"), "pulsingparticle"),
                    Map.entry(new TypedParticleCommand(this, "full"), "fullparticle"),
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
        });
    }

    /**
     * Plugin disable logic
     * Cleans up resources
     */
    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        }
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
