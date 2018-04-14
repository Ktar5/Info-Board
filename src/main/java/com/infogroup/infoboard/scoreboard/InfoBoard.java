package com.infogroup.infoboard.scoreboard;

import com.infogroup.infoboard.InfoBoardReborn;

public class InfoBoard {

    private InfoBoardReborn plugin;
    private String name;
    private int number;

    public InfoBoard(InfoBoardReborn plugin,String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public String getName() { return this.name; }

    public Integer getNumber() { return this.number; }
}
