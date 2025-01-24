package com.dami.worldmarker.Save;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public abstract class Savable {

    private static Set<Savable> instances = new HashSet<>();
    private final int instanceNumber;
    private static JavaPlugin plugin; // The reference to your Spigot plugin

    public abstract Map<String, Object> saveToYaml();

    protected Savable() {
        instances.add(this);
        instanceNumber = getInstances(this.getClass()).size();
    }

    public static void setJavaPlugin(JavaPlugin plugin){
        Savable.plugin = plugin;
    }


    public CompletableFuture<Void> saveToFileAsync() {
        return saveToFileAsync(instanceNumber + "-");
    }

    public CompletableFuture<Void> saveToFileAsync(String name) {
        return CompletableFuture.runAsync(() -> {
            String filePath = plugin.getDataFolder() + File.separator + name + ".yml";
            File file = new File(filePath);

            // Create the directory structure if it doesn't exist
            File parentDirectory = file.getParentFile();
            if (parentDirectory != null && !parentDirectory.exists()) {
                if (!parentDirectory.mkdirs()) {
                    plugin.getLogger().severe("Failed to create directory: " + parentDirectory.getAbsolutePath());
                    return;
                }
            }

            YamlConfiguration config = new YamlConfiguration();
            config.set("data", saveToYaml());

            try {
                config.save(file);
                plugin.getLogger().info("Data saved to: " + filePath);
            } catch (IOException e) {
                plugin.getLogger().severe("Error while saving data to " + filePath + ": " + e);
            }
        });
    }

    public static Set<Savable> getInstances(Class<?> clazz) {
        Set<Savable> typeInstances = new HashSet<>();
        for (Savable savable : instances) {
            if (savable.getClass().equals(clazz)) {
                typeInstances.add(savable);
            }
        }

        return typeInstances;
    }

    public static Set<Savable> getInstances() {
        return instances;
    }

    public static void Initialize(Set<Savable> newInstances) {
        instances = newInstances;
    }

    public static <T extends Savable> T loadFromFile(Class<T> clazz, String fileName) {

        File file = new File(plugin.getDataFolder() + File.separator + fileName + ".yml");
        if (file.exists()) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            return fromYamlConfig(clazz, config);
        }

        return null;
    }

    private static <T extends Savable> T fromYamlConfig(Class<T> clazz, YamlConfiguration config) {
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            instance.loadFromConfig(config.getConfigurationSection("data"));
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void loadFromConfig(ConfigurationSection config);


    public static <String,T extends Savable>  Map<String,T> loadFromFolder(Class<T> clazz, String folderName) {
        Map<String,T> resultList = new HashMap<>();

        File folder = new File(plugin.getDataFolder() + File.separator + folderName);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".yml")) {
                    try {
                        String fileName = (String) file.getName().substring(0,file.getName().length() - 4);
                        T loadedObject = loadFromFile(clazz, folderName + File.separator + fileName);
                        if (loadedObject != null) {
                            resultList.put(fileName,loadedObject);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return resultList;
    }

    public abstract void loadBaseConfig();
}