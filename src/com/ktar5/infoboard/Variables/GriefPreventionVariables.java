
package com.ktar5.infoboard.Variables;

import me.ryanhamshire.GriefPrevention.GriefPrevention;
import me.ryanhamshire.GriefPrevention.PlayerData;

import org.bukkit.entity.Player;


public class GriefPreventionVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		String prefix = "griefprevention";
		GriefPrevention inst = GriefPrevention.instance;
		PlayerData pd = inst.dataStore.getPlayerData(player.getUniqueId());
		
		if (newString.contains("<" + prefix + "claims>"))
			newString = newString.replaceAll("<" + prefix + "claims>", String.valueOf(pd.claims.size()));
		if (newString.contains("<" + prefix + "spamcount>"))
			newString = newString.replaceAll("<" + prefix + "spamcount>", String.valueOf(pd.spamCount));
		if (newString.contains("<" + prefix + "bonusclaims>"))
			newString = newString.replaceAll("<" + prefix + "bonusclaims>", String.valueOf(pd.bonusClaimBlocks));
		if (newString.contains("<" + prefix + "accruedclaims>"))
			newString = newString.replaceAll("<" + prefix + "accruedclaims>", String.valueOf(pd.accruedClaimBlocks));
		if (newString.contains("<" + prefix + "remainingclaims>"))
			newString = newString.replaceAll("<" + prefix + "remainingclaims>", String.valueOf(pd.getRemainingClaimBlocks()));
		
		return newString;
	}
}
