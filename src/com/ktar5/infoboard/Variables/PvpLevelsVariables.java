package com.ktar5.infoboard.Variables;

import org.bukkit.entity.Player;

import com.lenis0012.bukkit.pvp.PvpLevels;
import com.lenis0012.bukkit.pvp.PvpPlayer;

public class PvpLevelsVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;
		String prefix = "pvplevels";
		PvpPlayer pp = PvpLevels.instance.getPlayer(player);

		if (newString.contains("<" + prefix + "level>"))
			newString = newString.replaceAll("<" + prefix + "level>",
					String.valueOf(pp.getLevel()));
		if (newString.contains("<" + prefix + "kills>"))
			newString = newString.replaceAll("<" + prefix + "kills>",
					String.valueOf(pp.getKills()));
		if (newString.contains("<" + prefix + "deaths>"))
			newString = newString.replaceAll("<" + prefix + "deaths>",
					String.valueOf(pp.getDeaths()));
		if (newString.contains("<" + prefix + "kdr>"))
			newString = newString.replaceAll("<" + prefix + "kdr>",
					String.valueOf(pp.getKills() / pp.getDeaths()));

		return newString;
	}
}
