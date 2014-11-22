package com.ktar5.infoboard.Variables;

import net.clustermc.ClusterDisguises.cooldowns.Cooldown;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ClusterDisguiseVariables {
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		if (newString.contains("<clustdisguisecooldown>")){
			newString = newString.replaceAll("<clustdisguisecooldown>", (((int) Cooldown.getRemaining(player.getUniqueId(), "cooldown")) > 61) 
					? (String.valueOf((int) Cooldown.getRemaining(player.getUniqueId(), "cooldown")/60 ) + " Min.") 
							: (String.valueOf((int) Cooldown.getRemaining(player.getUniqueId(), "cooldown") ) + " Sec."));		
		}
		if(newString.contains("<clustdisguiseduration>")){
		newString = newString.replaceAll("<clustdisguiseduration>", String.valueOf((int) Cooldown.getRemaining(player.getUniqueId(), "duration") + " Sec."));	
		}
		return newString;
	}
}
