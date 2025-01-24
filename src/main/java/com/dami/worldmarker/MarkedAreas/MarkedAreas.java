package com.dami.worldmarker.MarkedAreas;

import com.dami.worldmarker.Save.Savable;
import com.dami.worldmarker.Tool.ToolUsers;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

import java.util.*;

public class MarkedAreas extends Savable {

    Map<String, List<String>> areas = new HashMap<>();

    private final ToolUsers users;
    public MarkedAreas(ToolUsers _users){
        this.users = _users;
    }

    public void CreateArea(UUID player, String identifier, String displayName, int minY, int maxY, World world){

        if(!users.ContainsUsers(player)){
            return;
        }

        // Create a new area
        if(areas.containsKey(world.getName())){
            areas.get(world.getName()).add(identifier);
        }else{
            areas.put(world.getName(), new ArrayList<>(List.of(identifier)));
        }

        //stream normal positions to BlockVector2
        List<Vector> points = users.GetVectorsOut(player);

    }

    @Override
    public Map<String, Object> saveToYaml() {
        Map<String, Object> map = new HashMap<>();

        map.put("areas", areas);

        return map;
    }

    @Override
    public void loadFromConfig(ConfigurationSection config) {
        Object map = config.get("areas");

        System.out.println(map);
    }

    @Override
    public void loadBaseConfig() {

    }
}
