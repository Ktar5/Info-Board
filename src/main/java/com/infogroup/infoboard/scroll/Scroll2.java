package com.infogroup.infoboard.scroll;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Scroll2 {

    private InfoBoardReborn plugin;
    private List<String> list;

    private int position, row;
    private ChatColor color = ChatColor.RESET;

    /**
     * Create new scroller
     * @param plugin
     * @param row
     * @param message
     * @param width
     * @param space
     */
    public Scroll2(InfoBoardReborn plugin,int row, String message, int width, int space){
        this.plugin = plugin;
        this.row = row;
        this.list= new ArrayList<String>();

        if(message.length() < width){
            StringBuilder sb = new StringBuilder(message);
            while(sb.length() < width) {
                sb.append(" ");
            }
            message =sb.toString();
        }

        width -= 2;

        if(width < 1){
            width =1;
        }
        if(space < 0 ){
            space =0;
        }
        message = ChatColor.translateAlternateColorCodes('&', message);

        // Add substrings
        for (int i = 0; i < message.length() - width; i++){
            this.list.add(message.substring(i, i + width));
        }

        // Add space between repeats
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < space; ++i) {
            this.list.add(message.substring(message.length() - width + (i > width ? width : i), message.length()) + space);
            if (spaces.length() < width)
                spaces.append(" ");
        }

        //Wrap
        for (int i = 0; i < width - space; ++i) {
            list.add(message.substring(message.length() - width + space + i, message.length()) + space + message.substring(0, i));
        }

        // Join up
        for (int i = 0; i < space; i++) {
            if (i > spaces.length()){
                break;
            }
            list.add(spaces.substring(0, spaces.length() - i) + message.substring(0, width - (space > width ? width : space) + i));
        }
    }

    /**
     *
     * @return
     */
    public String next() {

        StringBuilder sb = getNext();
        if (sb.charAt(sb.length() - 1) == '&') {
            sb.setCharAt(sb.length() - 1, ' ');
        }

        if (sb.charAt(0) == '&') {
            ChatColor c = ChatColor.getByChar(sb.charAt(1));
            if(c !=null){
                color = c;
                sb = getNext();
                if(sb.charAt(0) != ' '){
                    sb.setCharAt(0, ' ');
                }
            }

        }

        return color + sb.toString();
    }

    private StringBuilder getNext() {
        return new StringBuilder(list.get(position++%list.size()).substring(0));
    }
    public Integer getRow(){
        return this.row;
    }
}
