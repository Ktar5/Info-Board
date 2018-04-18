package com.infogroup.infoboard.utils;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.InfoBoard;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class BoardManager {
    private InfoBoardReborn plugin;
    private HashMap<Integer, InfoBoard> boards = new HashMap<>();

    //TODO FINISH and Change to per file scoreboard (FileManager)
    public BoardManager(InfoBoardReborn plugin){
        this.plugin = plugin;
        loadBoards();
    }

    public InfoBoard createBoard(){

        return null;
    }

    public void removeBoard(){

    }

    private void loadBoards(){
        FileConfiguration board = plugin.getFm().getFile("board");
        for(int i= 0; i < board.getStringList("InfoBoard").size(); i++){

        }
    }

    public InfoBoard getBoard(int number){
        return this.boards.get(number);
    }
}
