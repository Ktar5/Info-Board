package com.ktar5.infoboard.Variables;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerPointsVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;
		PlayerPointsAPI pp = ((PlayerPoints) Bukkit.getServer()
				.getPluginManager().getPlugin("PlayerPoints")).getAPI();

		if (newString.contains("<playerpoints>"))
			newString = newString.replaceAll("<playerpoints>",
					String.valueOf(pp.look(player.getUniqueId())));

		return newString;
	}
}
