package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class RainbowAnimation extends BaseAnimation {

    private int interval, row;
    private String option;

    /*
    Settings contains Keys:
    -interval
    -text
    - ? StringList ? colors OR ENTRIES like color1,color2,...
    -
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
        return "rainbow";
    }
}
