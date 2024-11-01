package src.net.jadiefication.Listeners;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.ApiStatus;

/**
 * Processes chat input for team GUI
 */
@ApiStatus.Experimental
public class TeamGuiChatListener implements Listener {


    private final TeamGuiListener guiListener;

    public TeamGuiChatListener(TeamGuiListener guiListener) {
        this.guiListener = guiListener;
    }

    /**
     * Handles chat messages for GUI input
     * @param event Chat event
     */
    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        var player = event.getPlayer();
        var playerUUID = player.getUniqueId();

        // Check if the player is expecting chat input
        if (guiListener.isExpectingChat(playerUUID)) {
            // Get the chat message
            String plainMessage = Component.text().content(event.message().toString()).toString();

            // Process the chat message (e.g., save it, validate it, etc.)
            player.sendMessage(Component.text("You entered: " + plainMessage));

            // Mark the player as no longer expecting chat input
            guiListener.stopExpectingChat(playerUUID);

            // Optionally cancel the chat event so the message doesn't get sent to chat
            event.setCancelled(true);
        }
    }
}
