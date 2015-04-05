package com.ktar5.infoboard.Util;

import java.util.Random;

import org.bukkit.ChatColor;

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
		ChatColor rc = colors[i];
		return rc;
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
		ChatColor rc = colors[i];
		return rc;
	}
}
