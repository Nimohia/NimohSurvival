package src.net.jadiefication.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import src.net.jadiefication.Core.Block.CustomBlockEntity;

import java.util.Map;

public class BlockEntityListener implements Listener {

    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();
        if (CustomBlockEntity.blockItems.containsKey(Map.of(item, item.getItemMeta()))) {
            event.setCancelled(true);
        }
    }
}
