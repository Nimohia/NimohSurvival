package src.net.jadiefication.Core.ActionBar;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static src.net.jadiefication.survival.Survival.economy;

public class ActionBarUpdater extends BukkitRunnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            // Retrieve the player's balance (replace with your logic)
            int balance = (int) economy.getBalance(player);

            // Display the actionbar (Kyori Adventure API for modern Paper)
            player.sendActionBar(Component.text("\uE102\uE820\uE103\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820\uE820" + balance));
        }
    }
}
