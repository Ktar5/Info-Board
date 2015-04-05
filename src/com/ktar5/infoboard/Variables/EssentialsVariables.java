package com.ktar5.infoboard.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

public class EssentialsVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;

		Essentials e = (Essentials) Bukkit.getServer().getPluginManager()
				.getPlugin("Essentials");
		User user = e.getUser(player);

		if (user != null) {
			if (newString.contains("<esshomes>"))
				newString = newString.replaceAll("<esshomes>",
						String.valueOf(user.getHomes().size()));
			if (newString.contains("<essmuted>"))
				newString = newString.replaceAll("<essmuted>",
						String.valueOf(user.getMuted()));
			if (newString.contains("<esshidden>"))
				newString = newString.replaceAll("<esshidden>",
						String.valueOf(user.isHidden()));
			if (newString.contains("<essgodmode>"))
				newString = newString.replaceAll("<essgodmode>",
						String.valueOf(user.isGodModeEnabled()));
			if (newString.contains("<essnickname>"))
				newString = newString.replaceAll("<essnickname>",
						String.valueOf(user.getNickname()));
			if (newString.contains("<esstprequest>"))
				newString = newString.replaceAll("<esstprequest>",
						String.valueOf(user.getTeleportRequest() != null));
			if (newString.contains("<essvanished>"))
				newString = newString.replaceAll("<essvanished>",
						String.valueOf(e.getVanishedPlayers().size()));
		}
		if (newString.contains("<essjails>"))
			newString = newString.replaceAll("<essjails>",
					String.valueOf(e.getJails().getCount()));

		return newString;
	}
}
