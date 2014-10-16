
package com.ktar5.infoboard.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.griefcraft.lwc.LWC;
import com.griefcraft.lwc.LWCPlugin;


public class LWCVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		
		LWCPlugin lwcPlugin = (LWCPlugin) Bukkit.getPluginManager().getPlugin("LWC");
		LWC lwc = lwcPlugin.getLWC();
		if (newString.contains("<lwclocks>"))
			newString = newString.replaceAll("<lwclocks>", String.valueOf(lwc.getPhysicalDatabase().getProtectionCount(player.getName())));
		
		return newString;
	}
}
