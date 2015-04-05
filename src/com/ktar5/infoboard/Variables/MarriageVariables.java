package com.ktar5.infoboard.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lenis0012.bukkit.marriage.MPlayer;
import com.lenis0012.bukkit.marriage.Marriage;

@SuppressWarnings("deprecation")
public class MarriageVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;
		String prefix = "marriage";
		Marriage marriage = Marriage.instance;
		MPlayer mp = marriage.getMPlayer(player);

		if (newString.contains("<" + prefix + "partner>"))
			newString = newString.replaceAll("<" + prefix + "partner>",
					mp.getPartner() != null ? mp.getPartner() : "Unknown");
		if (newString.contains("<" + prefix + "online>"))
			newString = newString.replaceAll("<" + prefix + "online>", String
					.valueOf(Bukkit.getPlayer(mp.getPartner() != null ? "true"
							: "false")));
		if (newString.contains("<" + prefix + "married>"))
			newString = newString.replaceAll("<" + prefix + "married>",
					String.valueOf(mp.isMarried()));

		return newString;
	}
}
