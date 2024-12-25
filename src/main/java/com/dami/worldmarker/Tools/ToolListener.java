package com.dami.worldmarker.Tools;

import com.dami.worldmarker.Regions.TemporaryRegions;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ToolListener implements Listener {

    TemporaryRegions temporaryRegions = new TemporaryRegions();

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent e){
        // eliminate interactions that are not clicks
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        // eliminate interactions where no item is in hand
        ItemStack item = e.getItem();
        if (item == null || item.getType() != Material.WOODEN_HOE) {
            return;
        }

        if(e.getClickedBlock() == null){
            return;
        }

        //give the clicked block to another function that saves it temporarily
        //for when the actions are complete so they can be translated to a region
        temporaryRegions.addBlockIfAllowed(e.getPlayer().getUniqueId(), e.getClickedBlock());
    }
}
