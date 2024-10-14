package src.net.jadiefication.survival;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import src.net.jadiefication.GUI.TeamGui;
import src.net.jadiefication.Listeners.TeamGuiChatListener;
import src.net.jadiefication.Listeners.TeamGuiListener;

public final class Survival extends JavaPlugin implements Listener {

    public static TeamGui teamGui;
    private final TeamGuiListener guiListener = new TeamGuiListener();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(guiListener, this);
        Bukkit.getPluginManager().registerEvents(new TeamGuiChatListener(guiListener), this);
        teamGui = new TeamGui();
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        Bukkit.getPluginManager().disablePlugin(this);
    }
}
