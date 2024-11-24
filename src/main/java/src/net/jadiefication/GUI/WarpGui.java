package src.net.jadiefication.GUI;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import src.net.jadiefication.survival.Survival;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static src.net.jadiefication.Core.GUI.Border.setInventoryBorder;
import static src.net.jadiefication.Core.GUI.Heads.createUrl;

public class WarpGui implements InventoryHolder {

    private final Inventory inventory;

    public WarpGui() {
        URL url = createUrl("http://textures.minecraft.net/texture/be9b6d7adac520a707221a053af045dc8d5b6feb13108d0851005428165267b0");
        this.inventory = Survival.getPlugin(Survival.class).getServer().createInventory(this, 27, Component.text("Warp Menu"));
        setInventoryBorder(inventory);
        for (File file : Objects.requireNonNull(new File("plugins/Essentials/warps").listFiles())) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(file.toURL().openStream()));
                while (reader.ready()) {
                    String content = reader.readLine();
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
