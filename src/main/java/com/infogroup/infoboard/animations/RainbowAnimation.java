package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class RainbowAnimation extends BaseAnimation {

    private int interval, row;
    private String option, text;

    /*
    Settings contains Keys:
    -interval
    -text
    -  ENTRIES like color1,color2,...
    - row
    */

    public RainbowAnimation(HashMap<String, String> settings) {
        this.loadSettings(settings);
    }

    /**
     * get the next possible message
     *
     * @return String
     */
    public String next() {

        return "";
    }

    /**
     * Load settings
     *
     * @param settings
     */
    protected void loadSettings(HashMap<String, String> settings) {
        this.row = Integer.parseInt(settings.get("row"));
        this.interval = Integer.parseInt(settings.get("interval"));
        this.text = settings.get("text");
    }

    /**
     * get the animation's row
     * @return Integer
     */
    public Integer getRow() {
        return this.row;
    }

    /**
     * @return
     */
    public String name() {
        return "rainbow";
    }

    /**
     * Get the Interval
     *
     * @return Integer
     */
    public Integer getInterval() {
        return this.interval;
    }
}
