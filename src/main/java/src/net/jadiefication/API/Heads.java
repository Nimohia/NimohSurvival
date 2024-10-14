package src.net.jadiefication.API;

import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public abstract class Heads {

    public static @NotNull ItemStack createHead(URL url) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
        PlayerTextures textures = profile.getTextures();
        textures.setSkin(url);
        profile.setTextures(textures);
        meta.setPlayerProfile(profile);
        item.setItemMeta(meta);
        return item;
    }

    public static @NotNull ItemStack createComingSoonHead() {
        ItemStack head = createHead("46ba63344f49dd1c4f5488e926bf3d9e2b29916a6c50d610bb40a5273dc8c82");
        ItemMeta meta = head.getItemMeta();
        meta.displayName(Component.text("§8§lᴄᴏᴍɪɴɢ ꜱᴏᴏɴ"));
        meta.lore(List.of(Component.text("§7ᴛʜɪꜱ ɢᴀᴍᴇᴍᴏᴅᴇ ɪꜱ ᴄᴏᴍᴍɪɴɢ ꜱᴏᴏɴ,"), Component.text("§7ᴡᴀᴛᴄʜ ᴏᴜʀ ᴅɪꜱᴄᴏʀᴅ ꜱᴇʀᴠᴇʀ ꜰᴏʀ ᴇxᴛʀᴀ ɪɴꜰᴏʀᴍᴀᴛɪᴏɴ.")));
        head.setItemMeta(meta);
        return head;
    }

    public static @NotNull ItemStack createHead(String url) {
        try {
            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            PlayerProfile profile = Bukkit.createProfile(UUID.randomUUID());
            PlayerTextures textures = profile.getTextures();
            textures.setSkin(new URL("http://textures.minecraft.net/texture/" + url));
            profile.setTextures(textures);
            meta.setPlayerProfile(profile);
            item.setItemMeta(meta);
            return item;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
