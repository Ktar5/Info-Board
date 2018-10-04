package com.infogroup.infoboard.animations;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;

public class RainbowAnimation extends BaseAnimation {

    private int interval, row, position;
    private String text;

    private ArrayList<ChatColor> colors;

    public RainbowAnimation(HashMap<String, String> settings) {
        this.loadSettings(settings);
    }

    /**
     * get the next possible message
     *
     * @return String
     */
    public String next() {
        if (this.position == (colors.size() - 1)) {
            this.position = 0;
            return colors.get(this.position) + this.text;
        } else {
            this.position++;
            return colors.get(this.position) + this.text;
        }
    }

    /**
     * Load settings
     *
     * @param settings
     */
    protected void loadSettings(HashMap<String, String> settings) {
        this.row = Integer.parseInt(settings.get("row"));
        this.interval = Integer.parseInt(settings.get("interval"));
        this.text = settings.get("text");
        this.position = 0;
        loadRainbow();
    }

    /**
     * get the animation's row
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

    /**
     * Get the Interval
     *
     * @return Integer
     */
    public Integer getInterval() {
        return this.interval;
    }

    /**
     * add all color off the rainbow
     */
    private void loadRainbow() {
        this.colors.add(ChatColor.DARK_RED);
        this.colors.add(ChatColor.GOLD);
        this.colors.add(ChatColor.YELLOW);
        this.colors.add(ChatColor.GREEN);
        this.colors.add(ChatColor.BLUE);
        this.colors.add(ChatColor.DARK_BLUE);
        this.colors.add(ChatColor.DARK_PURPLE);
        this.colors.add(ChatColor.LIGHT_PURPLE);
    }
}
