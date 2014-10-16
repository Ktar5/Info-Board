
package com.ktar5.infoboard.Variables;

import me.BukkitPVP.PointsAPI.PointsAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class PointsAPIVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		PointsAPI points = (PointsAPI) Bukkit.getPluginManager().getPlugin("PointsAPI");
		
		if (newString.contains("<pointsapi>"))
			newString = newString.replaceAll("<pointsapi>", String.valueOf(points.getPoints(player)));
		
		return newString;
	}
}
