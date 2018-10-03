package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class ChangeableAnimation extends BaseAnimation {

    private int interval, row;
    private String option;

    /*
    Settings contains Keys:
    -interval
    -option(BLINK/TIMED)
    - ? STRINGLIST ? text OR ENTRIES like TEXT1,TEXT2,...
    -row
     */
    private HashMap<String, String> settings;

    public ChangeableAnimation(HashMap<String, String> settings) {
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
        this.option = settings.get("option");


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
        return "changeable";
    }
}
