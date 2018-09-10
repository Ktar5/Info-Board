package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class BlinkAnimation extends BaseAnimation {


    /*
        Settings contains Keys:
        -color
        -return color
        -interval
        -text
        -row
         */
    private HashMap<String, String> settings;
    private boolean colored;

    /*
    Coloring a given string for a given time and undo it, keep repeating.
     */
    public BlinkAnimation(HashMap<String, String> settings) {
        this.settings = settings;
        this.colored = false;
    }

    /**
     * get the next possible message
     *
     * @return String
     */
    public String next() {
        String message = settings.get("text");
        if (colored == true) {
            return message;
        } else {
            return settings.get("color") + message;
        }
    }

    /**
     * get the animation's row
     *
     * @return Integer
     */
    public Integer getRow() {
        return Integer.parseInt(settings.get("row"));
    }

    /**
     * @return
     */
    public String name() {
        return "blink";
    }
}
