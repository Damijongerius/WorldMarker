package com.dami.worldmarker.Tool;

import org.bukkit.util.Vector;

import java.util.*;

public class ToolUsers {
    private Map<UUID, List<Vector>> users = new HashMap<>();

    public boolean ContainsUsers(UUID uuid){
        return users.containsKey(uuid);
    }

    public void AddUser(UUID uuid){
        if(users.containsKey(uuid)){
            System.out.printf("already exists");
            return;
        }

        users.put(uuid,new ArrayList<>());
    }

    public void AddPosition(UUID uuid,Vector vector){
        users.get(uuid).add(vector);
    }

    public List<Vector> GetVectorsOut(UUID uuid){

        List<Vector> positions = users.get(uuid);
        users.remove(uuid);

        return positions;

    }
}
