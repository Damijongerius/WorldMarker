package com.dami.worldmarker.Regions.Models;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;


public class Selection {
    String world;

    Map<Vector, BlockInformation> blocks = new HashMap<>();

    public Selection(String _world){
        this.world = _world;
    }

    //adds the block to the blockHashMap
    public boolean addBlock(Location location, BlockInformation information){

        World locationWorld = location.getWorld();

        //checks if the world is equal to the world assigned at the constructor
        if(locationWorld == null || locationWorld.getName().equals(world)){
            return false;
        }

        Vector vector = location.toVector();

        //if the block is already in the Hashmap do not add it
        if(blocks.containsKey(vector)){
            return false;
        }

        //add the block
        blocks.put(vector,information);
        return true;
    }
}
