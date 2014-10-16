
package com.ktar5.infoboard.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import pgDev.bukkit.CommandPoints.CommandPoints;
import pgDev.bukkit.CommandPoints.CommandPointsAPI;

import com.ktar5.infoboard.InfoBoard;


public class CommandPointsVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		CommandPointsAPI cp = ((CommandPoints) Bukkit.getServer().getPluginManager().getPlugin("CommandPoints")).getAPI();
		if (newString.contains("<commandpoints>"))
			try
			{
				newString = newString.replaceAll("<commandpoints>", String.valueOf(cp.getPoints(player.getName(), InfoBoard.me)));
			}
			catch (Exception e)
			{
				newString = newString.replaceAll("<commandpoints>", "0");
			}
		return newString;
	}
}
