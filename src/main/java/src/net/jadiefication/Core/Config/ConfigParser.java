package src.net.jadiefication.Core.Config;

import org.bukkit.Bukkit;
import src.net.jadiefication.Core.Config.BlockEntity.BlockEntityCommandParser;

import java.util.List;
import java.util.Map;

import static src.net.jadiefication.survival.Survival.cachedCustomEntities;
import static src.net.jadiefication.survival.Survival.instance;

public class ConfigParser {

    public static void parseConfig() {
        List<Map<?, ?>> blocks = instance.getConfig().getMapList("custom-entities");
        for (Map<?, ?> block : blocks) {
            if (!cachedCustomEntities.contains(block.entrySet().iterator().next().getKey().toString())) {
                cachedCustomEntities.add(block.entrySet().iterator().next().getKey().toString());
                BlockEntityCommandParser.parse(block.entrySet().iterator().next().getValue().toString(), block.entrySet().iterator().next().getKey().toString());
            }
        }
    }
}
