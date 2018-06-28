package com.infogroup.infoboard.scroll;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Scroll2 {

    private InfoBoardReborn plugin;
    private List<String> list;
    private String message;
    private int row, width, spaces;


    /**
     * Create a new scroller
     * @param plugin
     * @param row
     * @param message
     * @param width
     * @param spaces
     */
    public Scroll2(InfoBoardReborn plugin, int row, String message, int width, int spaces) {
        this.plugin = plugin;
        this.row = row;
        this.message = message;
        this.width = width;
        this.spaces = spaces;

        this.list = new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            String color = "";

            if (list.get(i).equals("&")) {
                if (list.get(i + 2).equals("&")) {
                    color = list.get(i) + list.get(i + 1) + list.get(i + 2) + list.get(i + 3);
                } else {
                    color = list.get(i) + list.get(i + 1);
                }
                i += 3;
                continue;
            }
            list.add(color + message.substring(i));
        }
        while (list.size() < width) {
            list.add(" ");
        }
    }

    /**
     * Get the current message and move the scroller
     * @return
     */
    public String getMessage(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++){
            sb.append(i);
        }

        list.add(list.get(0));
        list.remove(0);

        return ChatColor.translateAlternateColorCodes('&', sb.toString());
    }

    /**
     * get the total width of the message
     * @return
     */
    public Integer getWidth(){ return this.width; }

    /**
     * get the amount of spaces between repeats
     * @return
     */
    public Integer getSpaces(){ return this.spaces; }

    /**
     * Get the scroll row
     * @return
     */
    public Integer getRow(){ return this.row; }
}
