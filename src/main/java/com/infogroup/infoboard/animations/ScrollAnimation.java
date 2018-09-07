package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class ScrollAnimation {

    /*
    Settings contains Keys:
    -Width
    -Length
    -Single
    -Message
     */
    private HashMap<String, String> settings;

    public ScrollAnimation(HashMap<String, String> settings) {
        this.settings = settings;
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
     * get the animation's row
     *
     * @return Integer
     */
    public Integer getRow() {
        return Integer.parseInt(settings.get("row"));
    }
}
