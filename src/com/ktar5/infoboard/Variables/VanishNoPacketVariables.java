package com.ktar5.infoboard.Variables;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kitteh.vanish.staticaccess.VanishNoPacket;
import org.kitteh.vanish.staticaccess.VanishNotLoadedException;

@SuppressWarnings("deprecation")
public class VanishNoPacketVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;
		String name = player.getName();

		if (newString.contains("<vanishhidden>"))
			try {
				newString = newString.replaceAll("<vanishhidden>",
						String.valueOf(VanishNoPacket.isVanished(name)));
			} catch (VanishNotLoadedException e1) {
				newString = newString.replaceAll("<vanishhidden>", "false");
			}
		if (newString.contains("<vanishonline>")) {
			ArrayList<String> online = new ArrayList<String>();
			for (Player p : Bukkit.getOnlinePlayers())
				try {
					if (!VanishNoPacket.isVanished(p.getName()))
						online.add(p.getName());
				} catch (VanishNotLoadedException ignored) {
				}
			newString = newString.replaceAll("<vanishonline>",
					String.valueOf(online.size()));

		}
		return newString;
	}
}
