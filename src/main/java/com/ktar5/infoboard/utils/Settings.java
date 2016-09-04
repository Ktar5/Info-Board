package com.ktar5.infoboard.utils;

import com.ktar5.infoboard.InfoBoard;

import java.util.List;

public class Settings {

    /**
     * Determines if the rank has valid scoreboard
     *
     * @param rotation
     * @return true/false
     */
    public static boolean doesGlobalHaveScoreBoard(int rotation) {
        boolean hasBoard = false;
        for (String s : InfoBoard
                .getFileManager()
                .getBoard()
                .getConfigurationSection(
                        "Info Board." + String.valueOf(rotation)).getKeys(true))
            if (!s.contains("."))
                if (s.equals("global")) {
                    hasBoard = true;
                    break;
                }
        return hasBoard;
    }

    public static boolean doesRankHaveScoreBoard(int rotation, String world,
                                                 String rank) {
        boolean hasBoard = false;
        for (String s : InfoBoard
                .getFileManager()
                .getBoard()
                .getConfigurationSection(
                        "Info Board." + String.valueOf(rotation) + "." + world)
                .getKeys(true))
            if (!s.contains("."))
                if (s.equals(rank)) {
                    hasBoard = true;
                    break;
                }
        return hasBoard;
    }

    /**
     * Determines if the world given has a valid scoreboard
     *
     * @param rotation
     * @param world
     * @return true/false
     */
    public static boolean doesWorldHaveScoreBoard(int rotation, String world) {
        boolean hasBoard = false;
        for (String s : InfoBoard
                .getFileManager()
                .getBoard()
                .getConfigurationSection(
                        "Info Board." + String.valueOf(rotation)).getKeys(true))
            if (!s.contains("."))
                if (s.equalsIgnoreCase(world)) {
                    hasBoard = true;
                    break;
                }
        return hasBoard;
    }

    /**
     * Get the list of blocked regions for worldguard
     *
     * @return list
     */
    public static List<String> getRegionsDisabled() {
        return InfoBoard.getFileManager().getConfig()
                .getStringList("WorldGuard.Prevent Showing In");
    }

    /**
     * Determine if the world given is blocked
     *
     * @param world
     * @return
     */
    public static boolean isWorldDisabled(String world) {
        return InfoBoard.getFileManager().getConfig()
                .getStringList("Disabled Worlds").contains(world)
                || (world == null);
    }

    /**
     * Get if scrolling is enabled
     *
     * @return true/false
     */
    public static boolean scrollingEnabled() {
        return InfoBoard.getFileManager().getConfig()
                .getBoolean("Scrolling Text.Enable");
    }
}
