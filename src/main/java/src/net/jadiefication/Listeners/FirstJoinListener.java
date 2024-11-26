package src.net.jadiefication.Listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FirstJoinListener implements Listener {

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
}
