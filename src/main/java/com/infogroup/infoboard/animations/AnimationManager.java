package com.infogroup.infoboard.animations;

import com.infogroup.infoboard.InfoBoardReborn;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimationManager {

    //TODO FINISH + ALL ANIMATIONS
    private InfoBoardReborn plugin;
    private ArrayList<BaseAnimation> animations;

    public AnimationManager(InfoBoardReborn plugin) {
        this.plugin = plugin;
    }


    //ADD create animation giving settings in HashMap
    public BaseAnimation createAnimation(String line) {

        BaseAnimation target = animations.get(0);


        return null;
    }

    //ADD ...


    /**
     * "<Name>KEY:VALUE_
     * KEY:VALUE_
     * KEY
     * :
     * VALUE_
     * KEY:VALUE_...</>"
     *
     * @param line
     * @return
     */
    private HashMap<String, String> loadSettings(String line) {
        HashMap<String, String> settings = new HashMap<>();


        settings.put("name", "");
        for (int i = 0; i < line.split("_").length; i++) {
            settings.put(line.split("_")[i], line.split("_")[i].split(":")[2]);
        }

        return settings;
    }
}
