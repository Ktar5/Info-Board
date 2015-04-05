package com.ktar5.infoboard.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;

public class MultiverseVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;
		String prefix = "multiverse";

		MultiverseCore mv = (MultiverseCore) Bukkit.getServer()
				.getPluginManager().getPlugin("Multiverse-Core");
		MultiverseWorld mw = mv.getMVWorldManager().getMVWorld(
				player.getWorld());

		if (newString.contains("<" + prefix + "alias>"))
			newString = newString.replaceAll("<" + prefix + "alias>",
					mw.getAlias());
		if (newString.contains("<" + prefix + "autoheal>"))
			newString = newString.replaceAll("<" + prefix + "autoheal>",
					String.valueOf(mw.getAutoHeal()));
		if (newString.contains("<" + prefix + "hunger>"))
			newString = newString.replaceAll("<" + prefix + "hunger>",
					String.valueOf(mw.getHunger()));
		if (newString.contains("<" + prefix + "price>"))
			newString = newString.replaceAll("<" + prefix + "price>",
					String.valueOf(mw.getPrice()));
		return newString;
	}
}
