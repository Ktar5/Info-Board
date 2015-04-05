package com.ktar5.infoboard.Variables;

import me.libraryaddict.disguise.DisguiseAPI;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class LibsDisguiseVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;

		if (newString.contains("<disguised>"))
			newString = newString.replaceAll("<disguised>",
					String.valueOf(DisguiseAPI.isDisguised(player)));
		if (newString.contains("<disguisedentity>"))
			newString = newString.replaceAll("<disguisedentity>",
					(DisguiseAPI.isDisguised(player)) ? DisguiseAPI
							.getDisguise(player).getType()
							.toReadable() : "None");

		return newString;
	}
}
