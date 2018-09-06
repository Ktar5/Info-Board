package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class BlinkAnimation {

    /*
    Settings contains Keys:
    -Color
    -Return Color
    -Interval
    -Message
     */
    private HashMap<String, String> settings;

    /*
    Coloring a given string for a given time and undo it, keep repeating.
     */
    public BlinkAnimation(HashMap<String, String> settings) {
        this.settings = settings;
    }

}
