package com.infogroup.infoboard.scoreboard;

import com.infogroup.infoboard.InfoBoardReborn;

import java.util.List;

public class InfoBoard {

    private InfoBoardReborn plugin;
    private String name;
    private int number;
    private String world, rank;


    private String title;
    private List<String> rows;

    public InfoBoard(InfoBoardReborn plugin, String name) {
        this.plugin = plugin;
        this.name = name;


    }

    public String getName() { return this.name; }

    public Integer getNumber() { return this.number; }

    public List<String> getRows(){ return this.rows; }

    public void addRow(String row, int pos){ this.rows.add(pos, row); }
}
