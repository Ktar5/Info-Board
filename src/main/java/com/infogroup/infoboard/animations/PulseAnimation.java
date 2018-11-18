package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class PulseAnimation extends BaseAnimation {

    private int interval, row;
    private String text;


    public PulseAnimation(HashMap<String, String> settings) {
        this.loadSettings(settings);
    }

    /**
     * Get's the next possible message if possible
     *
     * @return String
     */
    public String next() {
        //TODO
        return null;
    }

    /**
     * Load the settings for the animation
     *
     * @param settings
     */
    protected void loadSettings(HashMap<String, String> settings) {
        this.row = Integer.parseInt(settings.get("row"));
        this.interval = Integer.parseInt(settings.get("interval"));
        this.text = settings.get("text");
        //naming?
        settings.get("first");
        settings.get("mid");
        settings.get("last");

    }

    /**
     * Get the animation's row
     *
     * @return
     */
    public Integer getRow() {
        return this.row;
    }

    /**
     * Get the animation's name
     *
     * @return
     */
    public String name() {
        return "pulse";
    }

    /**
     * Get animation's the interval
     *
     * @return
     */
    public Integer getInterval() {
        return this.interval;
    }
}
