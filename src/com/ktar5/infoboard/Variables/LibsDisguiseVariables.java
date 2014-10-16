package com.ktar5.infoboard.Variables;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import me.libraryaddict.disguise.DisguiseAPI;

public class LibsDisguiseVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;

		if(newString.contains("<disguised>"))
			newString = newString.replaceAll("<disguised>", String.valueOf(DisguiseAPI.isDisguised((Entity) player)));
		if(newString.contains("<disguisedentity>"))
			newString = newString.replaceAll("<disguisedentity>", (DisguiseAPI.isDisguised((Entity) player)) ? DisguiseAPI.getDisguise((Entity) player).getType().toReadable() : "None");
		
		return newString;
	}
}
