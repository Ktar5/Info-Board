package com.infogroup.infoboard.api;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class API {

    private InfoBoardReborn plugin;
    private HashMap<Player, Integer> Boards = new HashMap<>();

    public API(InfoBoardReborn plugin){ this.plugin = plugin; }
    //TODO finish


    public void createBoard(Player player, List<String> lines, String title){}

    public void updateBoard(Player player){}

    public void removeBoard(Player player){}

    public HashMap<Player, Integer> getBoards() { return this.Boards; }

    public Integer getBoard(Player player){ return this.Boards.get(player); }
}
