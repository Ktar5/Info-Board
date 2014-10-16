
package com.ktar5.infoboard.Variables;

import net.slipcor.pvpstats.PSMySQL;
import net.slipcor.pvpstats.PVPData;

import org.bukkit.entity.Player;


public class PVPStatsVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		String name = player.getName();
		
		if (newString.contains("<pvpstatsdeaths>"))
			newString = newString.replaceAll("<pvpstatsdeaths>", String.valueOf(PSMySQL.getEntry(name, "deaths")));
		if (newString.contains("<pvpstatskills>"))
			newString = newString.replaceAll("<pvpstatskills>", String.valueOf(PSMySQL.getEntry(name, "kills")));
		if (newString.contains("<pvpstatsmaxstreak>"))
			newString = newString.replaceAll("<pvpstatsmaxstreak>", String.valueOf(PSMySQL.getEntry(name, "streak")));
		if (newString.contains("<pvpstatsstreak>"))
			newString = newString.replaceAll("<pvpstatsstreak>", String.valueOf(PVPData.getStreak(name)));
		
		return newString;
	}
}
