package com.infogroup.infoboard.scroll;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.Bukkit;

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
    public Scroll2(InfoBoardReborn plugin, String message, int row, int width, int spaces) {
        this.plugin = plugin;
        this.row = row;
        this.message = message;
        this.width = width;
        this.spaces = spaces;

        //DEBUG
        Bukkit.broadcastMessage(message);
        //new arraylist
        this.list = new ArrayList<>();
        //loop trough all Chars oof the message
        for (int i = 0; i < message.length(); i++) {
            //create String for all colors per section
            String color = "";
            //check when color code starts
            if (message.substring(i).equals("&")) {
                //check if there is a following color code
                if (message.substring(i+2).equals("&")) {
                    //if there is, String color = color codes
                    color = message.substring(i, i+3);
                    //skip 3 chars in the string
                    i += 3;
                } else {
                    //only 1 pair, String color = color codes
                    color = message.substring(i, i+1);
                    //skip 1 char
                    i += 1;
                }

                continue;
            }
            //add the correct color per remaining string
            list.add(color + message.charAt(i));

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
            sb.append(list.get(i));
        }

        list.add(list.get(0));
        list.remove(0);

        return sb.toString();
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
