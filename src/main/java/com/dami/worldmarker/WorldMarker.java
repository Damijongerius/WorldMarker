package com.dami.worldmarker;

import com.sk89q.worldedit.regions.Polygonal2DRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.plugin.java.JavaPlugin;

public final class WorldMarker extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        /*
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get((World) getServer().getWorld("world"));
        regions.addRegion(region);

        Region region = new Polygonal2DRegion();
         */

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
