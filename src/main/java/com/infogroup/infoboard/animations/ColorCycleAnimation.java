package com.infogroup.infoboard.animations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class ColorCycleAnimation extends BaseAnimation {

    private int row, interval, position;
    private String text, option;
    private ArrayList<String> colors;


    public ColorCycleAnimation(HashMap<String, String> settings) {
        loadSettings(settings);
    }

    /**
     * Get the next message
     *
     * @return String
     */
    public String next() {
        if (position == (colors.size() - 1)) {
            position = 0;
            return colors.get(position) + text;
        } else {
            this.position++;
            return colors.get(position) + text;
        }
    }

    /**
     * Get the next random message
     * @return String
     */
    public String randomNext() {
        Random r = new Random();
        return colors.get(r.nextInt(colors.size()));

    }

    /**
     * Load all required settings
     *
     * @param settings
     */
    protected void loadSettings(HashMap<String, String> settings) {
        this.text = settings.get("text");
        this.interval = Integer.parseInt(settings.get("interval"));
        this.option = settings.get("option");
        this.position = 0;
        this.row = Integer.parseInt(settings.get("row"));
        for (int i = 0; settings.get("text" + i) != null; i++) {
            colors.add(settings.get("text" + i));
        }
    }

    /**
     * Get the row
     * @return
     */
    public Integer getRow() {
        return this.row;
    }

    /**
     * Get the Animation name
     * @return String
     */
    public String name() {
        return "CC";
    }

    /**
     * Get the interval
     * @return Integer
     */
    public Integer getInterval() {
        return this.interval;
    }


}
