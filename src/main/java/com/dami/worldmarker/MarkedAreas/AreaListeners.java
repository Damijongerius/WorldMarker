package com.dami.worldmarker.MarkedAreas;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

public class AreaListeners extends BukkitRunnable implements Listener {

    MarkedAreas areas;

    private Map<Player,String> PlayerAreas = new java.util.HashMap<>();
    private boolean skip = false;

    public AreaListeners( JavaPlugin plugin, MarkedAreas _areas) {
        this.areas = _areas;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.runTaskTimer(plugin, 5, 5);
    }

    @Override
    public void run() {
        if (areas == null || areas.getRegions() == null) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerAreas.remove(player);

            skip = false;
            areas.getRegions().forEach((protectedRegion, regionInformation) -> {
                if (skip) return;

                BlockVector3 playerPos = BlockVector3.at(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
                if (Objects.equals(regionInformation.worldName, player.getWorld().getName()) && protectedRegion.contains(playerPos)) {
                    PlayerAreas.put(player, regionInformation.displayName);
                    skip = true;
                }
            });
        }

        if (PlayerAreas == null) return;

        for (Map.Entry<Player, String> entry : PlayerAreas.entrySet()) {
            Objects.requireNonNull(entry.getKey().getPlayer()).spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(entry.getValue()));
        }
    }

}
