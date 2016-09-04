package com.ktar5.infoboard.utils;

import com.ktar5.infoboard.GetVariables;
import com.ktar5.infoboard.InfoBoard;
import com.ktar5.infoboard.scroll.ScrollManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {

    /**
     * Get the message in color
     *
     * @param line
     * @return new line
     */
    public static String getColored(String line) {
        line = ChatColor.translateAlternateColorCodes('&', line);
        line = line.replaceAll("&x", RandomChatColor.getColor().toString());
        line = line.replaceAll("&y", RandomChatColor.getFormat().toString());
        return line;
    }

    /**
     * Get the new line by doing everything
     *
     * @param line
     * @param player
     * @return new line
     */
    public static String getLine(String line, Player player) {

        if (line.contains("%"))
            line = getReplacements(line, player);

        line = getColored(line);

        return line;
    }

    /**
     * Get the <strong>Replaced</strong> version of the line
     *
     * @param line
     * @param player
     * @return new line
     */
    public static String getReplacements(String line, Player player) {
        return GetVariables.replaceVariables(line, player);
    }

    /**
     * Get the title from the config and set it
     *
     * @param player
     * @param worldName
     * @param rankName
     * @return
     */
    public static String getTitle(Player player, String worldName,
                                  String rankName) {

        String title = InfoBoard
                .getFileManager()
                .getBoard()
                .getString(
                        "Info Board."
                                + String.valueOf(InfoBoard.getTimers()
                                .getPage()) + "." + worldName + "."
                                + rankName + ".Title");

        if (title.startsWith("<scroll>") && Settings.scrollingEnabled()) {
            title = title.replaceAll("<scroll>", "");
            // and create a Title scroller
            title = ScrollManager.createTitleScroller(player,
                    getLine(title, player)).getMessage();

        } else
            title = getLine(title.substring(0, Math.min(title.length(), 32)),
                    player);

        return title;

    }
}
