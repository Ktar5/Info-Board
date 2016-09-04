package com.ktar5.infoboard.utils;

import org.bukkit.ChatColor;

import java.util.Random;

/**
 * @author SniperzCiinema
 */

public class RandomChatColor {

    public static ChatColor getColor(ChatColor... chatColors) {
        Random r = new Random();
        ChatColor[] colors;
        if (chatColors.length == 0)
            colors = ChatColor.values();
        else
            colors = chatColors;
        int i = r.nextInt(colors.length);
        while (!colors[i].isColor())
            i = r.nextInt(colors.length);
        return colors[i];
    }

    public static ChatColor getFormat(ChatColor... chatColors) {
        Random r = new Random();
        ChatColor[] colors;
        if (chatColors.length == 0)
            colors = ChatColor.values();
        else
            colors = chatColors;
        int i = r.nextInt(colors.length);
        while (!colors[i].isFormat())
            i = r.nextInt(colors.length);
        return colors[i];
    }
}
