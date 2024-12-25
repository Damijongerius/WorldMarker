package com.dami.worldmarker.Tools.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public class RegionCommand implements TabExecutor {

    /*
    commands:
    /region selector - gives the player the region selector tool
    /region create <name> - creates a region with the given name
    /region delete <name> - deletes the region with the given name
    /region list - lists all regions
    /region info <name> - gives information about the region with the given name
    /region treeRegion setparent <name> <parent> - sets the parent of the region with the given name
    /region treeRegion addDefinition <newDefinition> <parent>
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
