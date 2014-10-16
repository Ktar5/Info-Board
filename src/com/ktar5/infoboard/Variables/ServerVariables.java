
package com.ktar5.infoboard.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ktar5.infoboard.Util.VaraibleUtils.Lag;


public class ServerVariables {
	
	public static String replaceVariables(String string) {
		String newString = string;
		double tps = Lag.getTPS();
		
		// Server Variables
		
		if (newString.contains("<onlineplayers>"))
			newString = newString.replaceAll("<onlineplayers>", String.valueOf(Bukkit.getOnlinePlayers().length));
		if (newString.contains("<motd>"))
			newString = newString.replaceAll("<motd>", String.valueOf(Bukkit.getMotd()));
		if (newString.contains("<maxplayers>"))
			newString = newString.replaceAll("<maxplayers>", String.valueOf(Bukkit.getMaxPlayers()));
		if (newString.contains("<servername>"))
			newString = newString.replaceAll("<servername>", String.valueOf(Bukkit.getServerName()));
		if (newString.contains("<tps>"))
			newString = newString.replaceAll("<tps>", String.valueOf(Math.round(tps * 100.0D) / 100.0D));
		if (newString.contains("<freeram>"))
			newString = newString.replaceAll("<freeram>", String.valueOf((Runtime.getRuntime().freeMemory() / 1024) / 1024));
		if (newString.contains("<maxram>"))
			newString = newString.replaceAll("<maxram>", String.valueOf((Runtime.getRuntime().maxMemory() / 1024) / 1024));
		if (newString.contains("<usedram>"))
			newString = newString.replaceAll("<usedram>", String.valueOf(((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()) / 1024) / 1024));
		if (newString.contains("<availableprocessors>"))
			newString = newString.replaceAll("<availableprocessors>", String.valueOf(Runtime.getRuntime().availableProcessors()));
		if (newString.contains("<peoplewith"))
		{
			String perm = newString.split("<peoplewith")[1].split(">")[0];
			
			int i = 0;
			for (Player ppl : Bukkit.getOnlinePlayers())
				if (ppl.hasPermission(perm))
					i++;
			newString = newString.replaceAll("<peoplewith" + (perm) + ">", String.valueOf(i));
		}
		return newString;
	}
}
