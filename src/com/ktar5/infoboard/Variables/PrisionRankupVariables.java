
package com.ktar5.infoboard.Variables;

import net.craftservers.prisonrankup.PrisonRankup;

import org.bukkit.entity.Player;


public class PrisionRankupVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		String prefix = "prisonrankup";
		String name = player.getName();
		
		if (newString.contains("<" + prefix + "rank>"))
			newString = newString.replaceAll("<" + prefix + "rank>", PrisonRankup.getRank(name) != null ? PrisonRankup.getRank(name).getName() : "None");
		if (newString.contains("<" + prefix + "nextrank>"))
			newString = newString.replaceAll("<" + prefix + "nextrank>", PrisonRankup.getNextRank(name).getName() != null ? PrisonRankup.getNextRank(name).getName() : "None");
		if (newString.contains("<" + prefix + "rankprice>"))
			newString = newString.replaceAll("<" + prefix + "rankprice>", PrisonRankup.getNextRank(name) != null ? PrisonRankup.getNextRank(name).getPriceString() : "0");
		
		return newString;
	}
}
