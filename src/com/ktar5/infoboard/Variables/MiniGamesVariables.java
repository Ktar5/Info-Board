
package com.ktar5.infoboard.Variables;

import org.bukkit.entity.Player;

import com.pauldavdesign.mineauz.minigames.MinigamePlayer;
import com.pauldavdesign.mineauz.minigames.Minigames;


public class MiniGamesVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		
		MinigamePlayer gp = Minigames.plugin.getPlayerData().getMinigamePlayer(player);
		
		if (newString.contains("<minigamesdeaths>"))
			newString = newString.replaceAll("<minigamesdeaths>", String.valueOf(gp.getDeaths()));
		if (newString.contains("<minigameskills>"))
			newString = newString.replaceAll("<minigameskills>", String.valueOf(gp.getKills()));
		if (newString.contains("<minigamesscore>"))
			newString = newString.replaceAll("<minigamesscore>", String.valueOf(gp.getScore()));
		
		return newString;
	}
}
