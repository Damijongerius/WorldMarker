package com.dami.worldmarker.Tool;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

public class ToolListeners implements Listener {

    private ToolUsers users;

    public ToolListeners(ToolUsers _users){
        this.users = _users;
    }

    public void OnBlockBreak(BlockBreakEvent e){

        UUID uuid = e.getPlayer().getUniqueId();

        if(!users.ContainsUsers(uuid)){
           return;
        }

        if(e.getPlayer().getItemInUse() != null && e.getPlayer().getItemInUse().getType() == Material.WOODEN_HOE){

        }
    }
}
