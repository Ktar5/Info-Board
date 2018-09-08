package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class ChangeableAnimation extends BaseAnimation {

    /*
    Settings contains Keys:
    -interval
    -option(BLINK/TIMED)
    - ? STRINGLIST ? text OR ENTRIES like TEXT1,TEXT2,...
    -row
     */
    private HashMap<String, String> settings;

    public ChangeableAnimation(HashMap<String, String> settings) {
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
