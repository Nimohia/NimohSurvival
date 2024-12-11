package src.net.jadiefication.Core.Config.BlockEntity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static src.net.jadiefication.survival.Survival.*;


public abstract class BlockEntityCommandParser {

    private final static Pattern passengers = Pattern.compile("Passengers:\\[(.+)\\]\\}", Pattern.DOTALL);
    private final static Pattern entity = Pattern.compile("\\{id:\"(?<id>[^\"]+)\",block_state:\\{Name:\"(?<name>[^\"]+)\",Properties:(?<properties>\\{.*?\\})\\},transformation:\\[(?<transformation>[\\d.,\\-f]+)\\]\\}");
    private static Map<String, List<DisplayEntitySettings>> entities = new HashMap<>();
    private static final Gson gson = new Gson();

    public static void parse(String command, String blockName) {
        entities = new HashMap<>();
        List<DisplayEntitySettings> displayEntities = new ArrayList<>();
        Matcher matcher = passengers.matcher(command);
        if (matcher.find()) {
            String passengers = matcher.group(1);
            Matcher entityMatcher = entity.matcher(passengers);
            while (entityMatcher.find()) {
                String id = entityMatcher.group("id");
                String name = entityMatcher.group("name");
                String properties = entityMatcher.group("properties");
                List<Float> transformations = new ArrayList<>();
                String[] transformationArray = entityMatcher.group("transformation").split(",");
                for (String s : transformationArray) {
                    try {
                        transformations.add(Float.parseFloat(s.replace("f", "")));
                    } catch (NumberFormatException e) {
                        Bukkit.getLogger().warning("Invalid transformation value: " + s);
                    }
                }
                displayEntities.add(new DisplayEntitySettings(id, new BlockStateSettings(name, parseProperties(properties)), transformations));
            }
        }
        entities.put(blockName, displayEntities);
        saveData();
    }

    private static Map<String, Object> parseProperties(String properties) {
        if (properties.equals("{}")) return Map.of();
        properties = properties.replace("{", "").replace("}", "");
        Map<String, Object> propertiesMap = new HashMap<>();
        for (String s : properties.split(",")) {
            String[] split = s.split(":", 2); // Changed to 2 to avoid empty parts in case of trailing commas
            if (split.length < 2) continue; // Skip malformed entries
            split[0] = split[0].strip();
            split[1] = split[1].strip();
            if (split[1].equalsIgnoreCase("TRUE") || split[1].equalsIgnoreCase("FALSE")) {
                propertiesMap.put(split[0], split[1].equalsIgnoreCase("TRUE"));
            } else if (isNumeric(split[1])) {
                propertiesMap.put(split[0], Integer.parseInt(split[1]));
            } else if (isFloat(split[1])) {
                propertiesMap.put(split[0], Float.parseFloat(split[1]));
            } else {
                propertiesMap.put(split[0], split[1].replace("\"", ""));
            }
        }
        return propertiesMap;
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void saveData() {
        try {
            Map<String, List<DisplayEntitySettings>> existingData = new HashMap<>();
            if (Files.exists(jsonFile.toPath())) {
                String existingJsonData = Files.readString(jsonFile.toPath());
                TypeToken<Map<String, List<DisplayEntitySettings>>> token = new TypeToken<>() {};
                existingData = gson.fromJson(existingJsonData, token.getType());
            }

            if (existingData == null) {
                Files.writeString(jsonFile.toPath(), gson.toJson(entities));
                return;
            }
            // Merge existing data with new data
            existingData.putAll(entities);

            String jsonData = gson.toJson(existingData);
            Files.writeString(jsonFile.toPath(), jsonData);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failed to save block entity data: " + e.getMessage());
        }
    }

    public static Map<String, DisplayEntitySettings> getData() {
        try {
            if (Files.exists(jsonFile.toPath())) {
                String jsonData = Files.readString(jsonFile.toPath());
                TypeToken<Map<String, List<DisplayEntitySettings>>> token = new TypeToken<>() {};
                Map<String, List<DisplayEntitySettings>> rawData = gson.fromJson(jsonData, token.getType());

                // Convert the List<DisplayEntitySettings> to single DisplayEntitySettings
                Map<String, DisplayEntitySettings> result = new HashMap<>();
                if (rawData != null) {
                    rawData.forEach((key, list) -> {
                        if (!list.isEmpty()) {
                            result.put(key, list.get(0));
                        }
                    });
                }
                return result;
            }
        } catch (IOException e) {
            Bukkit.getLogger().severe("Failed to load block entity data: " + e.getMessage());
        }
        return Map.of();
    }
}
