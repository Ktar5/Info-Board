package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class RainbowAnimation extends BaseAnimation {

    /*
    Settings contains Keys:
    -interval
    -text
    - ? StringList ? colors OR ENTRIES like color1,color2,...
    -
    */
    private HashMap<String, String> settings;

    public RainbowAnimation(HashMap<String, String> settings) {
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
