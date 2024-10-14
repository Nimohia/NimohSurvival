package src.net.jadiefication.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static src.net.jadiefication.survival.Survival.teamGui;

public class TeamGuiListener implements Listener {

    private final Set<UUID> expectingChat = new HashSet<>();

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(teamGui.getInventory())) {
            event.setCancelled(true);
            if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.DIAMOND) {
                expectingChat.add(event.getWhoClicked().getUniqueId());
                event.getWhoClicked().sendMessage("Please type your response in chat.");
                event.getWhoClicked().closeInventory();
            }
        }
    }

    public boolean isExpectingChat(UUID playerUUID) {
        return expectingChat.contains(playerUUID);
    }

    public void stopExpectingChat(UUID playerUUID) {
        expectingChat.remove(playerUUID);
    }
}
