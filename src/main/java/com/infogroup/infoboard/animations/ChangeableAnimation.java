package com.infogroup.infoboard.animations;

import java.util.HashMap;

public class ChangeableAnimation {

    /*
    Settings contains Keys:
    -Interval
    -Option(BLINK/TIMED)
    - ? STRINGLIST ? OR ENTRIES like TEXT1,TEXT2,...
    -
     */
    private HashMap<String, String> settings;

    public ChangeableAnimation(HashMap<String, String> settings) {
        this.settings = settings;
    }
}
