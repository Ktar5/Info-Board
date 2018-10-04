package com.infogroup.infoboard.animations;

import java.util.ArrayList;
import java.util.HashMap;

public class ChangeableAnimation extends BaseAnimation {

    private int interval, row, position;
    private String option;
    private ArrayList<String> lines;


    public ChangeableAnimation(HashMap<String, String> settings) {
        this.loadSettings(settings);
    }

    /**
     * get the next possible message
     *
     * @return String
     */
    public String next() {
        String message;
        if (position == (lines.size() - 1)) {
            this.position = 0;
            message = this.lines.get(position);
        } else {
            this.position++;
            message = this.lines.get(position);
        }
        return message;
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
        this.position = 0;
        for (int i = 0; settings.get("text" + i) != null; i++) {
            lines.add(settings.get("text" + i));
        }
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
     * @return String
     */
    public String name() {
        return "changeable";
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
