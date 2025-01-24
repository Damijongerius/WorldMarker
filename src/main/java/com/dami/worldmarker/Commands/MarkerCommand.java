package com.dami.worldmarker.Commands;

import com.dami.worldmarker.MarkedAreas.MarkedAreas;
import com.dami.worldmarker.Tool.ToolUsers;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class MarkerCommand implements TabExecutor {

    private final ToolUsers users;

    private final MarkedAreas markedAreas;

    public MarkerCommand(ToolUsers _users, MarkedAreas _markedAreas){
        this.users = _users;
        this.markedAreas = _markedAreas;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("You must be a player to use this command.");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0){
            player.sendMessage("Usage: /marker <create|delete|list|edit|help|tool>");
            return true;
        }

        switch (args[0]) {

            case "create" -> {
                if(args.length < 5){
                    player.sendMessage("Usage: /marker create <Identifier> <DisplayName> <minY> <maxY>");
                    return true;
                }

                String displayName = args[2];

                displayName = displayName.replace("_", " ");

                displayName = displayName.replace("&", "§");


                markedAreas.CreateArea(player.getUniqueId(),args[1],displayName,Integer.parseInt(args[3]),Integer.parseInt(args[4]),player.getWorld());


            }

            case "delete"-> {}
            case "list"-> {}
            case "edit"-> {}
            case "help"-> {}
            case "tool"-> {
                if(users.ContainsUsers(player.getUniqueId())) {
                    users.GetVectorsOut(player.getUniqueId());
                    player.sendMessage("Tool disabled.");
                }else{
                    users.AddUser(player.getUniqueId(),player.getWorld());
                    player.sendMessage("Tool enabled.");

                }
            }
            default-> {}
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String arg, String[] args) {
        return null;
    }
}
