package com.dami.worldmarker.Regions;

import com.dami.worldmarker.Regions.Models.BlockInformation;
import com.dami.worldmarker.Regions.Models.Selection;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TemporaryRegions {

    Map<UUID, Selection> playerSelections = new HashMap<>();

    //checks for directional and if the block is a solid
    public boolean addBlockIfAllowed(UUID player, Block block) {

        // Check if the block uses Directional
        BlockData blockData = block.getBlockData();
        if (blockData instanceof Directional directional) {
            // Get block type
            Material blockType = block.getType();
            if (!blockType.isSolid()) {
                return false; // Not a full block
            }

            BlockFace face = directional.getFacing();

            Location location = block.getLocation();

            Material material = block.getType();

            //returns the result from the function
            return addBlock(player,face,location,material);
        }


        return false; // Not a Directional block
    }

    //tries adding the BlockInformation to the selection class
    private boolean addBlock(UUID player, BlockFace face, Location location, Material material ){

        BlockInformation information = new BlockInformation(material,face);

        //is the hashmap does not contain the player add the player
        if(!playerSelections.containsKey(player)){

            World world = location.getWorld();

            if(world == null){
                return false;
            }

            Selection selection = new Selection(world.toString());
            playerSelections.put(player,selection);
        }

        //returns the result of the function
        return playerSelections.get(player).addBlock(location,information);
    }
}
