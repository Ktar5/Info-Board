package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class BlinkAnimation extends BaseAnimation {

    private int interval, row;
    private boolean colored;
    private String color, text;


    /*
        Coloring a given string for a given time and undo it, keep repeating.
         */
    public BlinkAnimation(HashMap<String, String> settings) {
        this.loadSettings(settings);
    }

    /**
     * get the next possible message
     *
     * @return String
     */
    public String next() {
        String message = this.text;
        if (colored == true) {
            return message;
        } else {
            return this.color + message;
        }
    }

    /**
     * Is the message colored?
     *
     * @return colored
     */
    public boolean isColored() {
        return this.colored;
    }

    /**
     * Load settings
     *
     * @param settings
     */
    protected void loadSettings(HashMap<String, String> settings) {
    /*
        Settings contains Keys:
        -color
        -return color
        -interval
        -text
        -row
     */

        this.row = Integer.parseInt(settings.get("row"));
        this.interval = Integer.parseInt(settings.get("interval"));
        this.color = settings.get("color");
        this.text = settings.get("text");


        this.colored = false;
    }

    /**
     * get the animation's row
     *
     * @return Integer
     */
    public Integer getRow() {
        return this.row;
    }

    /**
     * @return
     */
    public String name() {
        return "blink";
    }

    /**
     * Get the interval
     *
     * @return Integer
     */
    public Integer getInterval() {
        return this.interval;
    }
}
