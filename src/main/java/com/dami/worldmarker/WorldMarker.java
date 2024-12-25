package com.dami.worldmarker;

import com.dami.worldmarker.ConfigReload.ConfigManager;
import com.dami.worldmarker.Regions.Models.Tree.TreeDefinitions;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldMarker extends JavaPlugin {

    private ConfigManager configManager = new ConfigManager();

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    private void InitializeConfigurations(){
        configManager.initializeConfig("treeDefinitions", TreeDefinitions.class);
    }
}
