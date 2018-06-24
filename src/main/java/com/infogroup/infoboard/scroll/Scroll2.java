package com.infogroup.infoboard.scroll;

import com.infogroup.infoboard.InfoBoardReborn;

import java.util.ArrayList;
import java.util.List;

public class Scroll2 {

    private InfoBoardReborn plugin;
    private List<String> list;
    private String message;
    private int row, width, skiped;


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
        
        this.list = new ArrayList<>();
        for(int i = 0;i< message.length(); i++){
            list.add(message.substring(i));
        }
        while(list.size() < width){
            list.add(" ");
        }
        buildMessage();
    }

    /**
     * Get the scroll row
     * @return
     */
    public Integer getRow(){
        return this.row;
    }

    public String getMessage(){
        String msg = this.message;
        this.move();
        return msg;
    }

    /**
     * Move the scrolled text one
     * (remove first entry in list and re-add at bottom)
     */
    //TODO
    private void move(){

        if(!list.get(0).equals("&")){
            list.add(list.get(0));
            list.remove(0);
        }else{
            if(list.get(2).equals("&")){
                if(list.get(5).equals("&"))
                list.add(list.get(4));
                list.remove(4);
            }
        }

    }

    /**
     * Build the message
     */
    private void buildMessage(){
        StringBuilder sb = new StringBuilder();
        String color = "";

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals("&")){
                if(list.get(i+2).equals("&")){
                    color = list.get(i) + list.get(i+1) +list.get(i+2) +list.get(i+3);
                }else{
                    color = list.get(i) + list.get(i+1);
                }
                i+=3;
                continue;
            }
            sb.append(color + list.get(i));
        }


        this.message = sb.toString();
    }
}
