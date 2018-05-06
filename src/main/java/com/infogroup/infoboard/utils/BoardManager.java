package com.infogroup.infoboard.utils;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.InfoBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class BoardManager {
    private InfoBoardReborn plugin;
    private HashMap<String, InfoBoard> boards = new HashMap<>();

    //TODO FINISH and Change to per file scoreboard (FileManager)
    public BoardManager(InfoBoardReborn plugin){
        this.plugin = plugin;
        loadBoards();
    }

    public InfoBoard createBoard(String name){

        return null;
    }

    public void removeBoard(String name){
        if(!this.boards.containsKey(name)){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Board does not exist!");
        }else{
            boards.remove(name);
            //TODO REMOVE FILE
        }
    }

    private void loadBoards(){
        FileConfiguration board = plugin.getFm().getFile("board");
        for(int i= 0; i < board.getStringList("InfoBoard").size(); i++){

        }
    }

    public void addRow(String name, int pos, String row) {
        if (!boards.containsValue(name)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Board does not exist!");
        }else{
            InfoBoard board = boards.get(name);
            if(board.getRows().size() < 15){
                board.addRow(row, pos);
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Successfully added row: "+ row + " to " + name);
            }else{
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "This board already has 15 rows!");
            }
        }
    }

    public InfoBoard getBoard(int name){
        return this.boards.get(name);
    }
}
