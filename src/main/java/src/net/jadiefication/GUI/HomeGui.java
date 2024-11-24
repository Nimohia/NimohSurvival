package src.net.jadiefication.GUI;

import com.earth2me.essentials.User;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.Core.GUI.Heads;
import src.net.jadiefication.survival.Survival;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static src.net.jadiefication.Core.GUI.Border.setInventoryBorder;
import static src.net.jadiefication.Core.GUI.Heads.createHead;
import static src.net.jadiefication.Core.GUI.Heads.createUrl;
import static src.net.jadiefication.survival.Survival.essentials;

public class HomeGui implements InventoryHolder {

    private final Inventory inventory;

    public HomeGui(Player player) {
        URL url = createUrl("http://textures.minecraft.net/texture/9f30ffbc0110efa34e030860da18c8a1d6b223de0f00d9e4c5d0cfa7ecfafa48");
        ItemStack noHomeItem = createHead(Heads.createComingSoonHead(), Component.text("§6§lNo home"), List.of(Component.text("§7No home set."), Component.text("§7Set a home by doing /sethome <name>.")));
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 45, Component.text("Home Menu"));
        setInventoryBorder(inventory);
        User user = essentials.getUser(player);
        List<String> homes = user.getHomes();
        if (!homes.isEmpty()) {
            for (int i = 0; i < homes.size(); i++) {
                ItemStack item = createHead(createHead(url), Component.text("§6§l" + homes.get(i)), List.of(Component.text("§7Click to warp to home")));
                this.inventory.setItem(21 + i, item);
            }
            for (int i = homes.size(); i < 3; i++) {
                this.inventory.setItem(21 + i, noHomeItem);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                this.inventory.setItem(21 + i, noHomeItem);
            }
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
