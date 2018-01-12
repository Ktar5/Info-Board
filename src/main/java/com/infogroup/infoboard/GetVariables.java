package com.infogroup.infoboard;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class GetVariables {
	private static InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

	public static String replaceVariables(String string, Player player) {
		String newString;

		// setting all placeholders that are hooked in placeholder API
		newString = PlaceholderAPI.setPlaceholders(player, string);

		// Bukkit.broadcastMessage(newString);

		// Custom Variables
		for (String custom : plugin.getFm().getFile("config").getConfigurationSection("Custom Variables")
				.getKeys(true)) {
			if (newString.contains(custom)) {
				newString = newString.replaceAll(custom, plugin.getMessages()
						.getColored(plugin.getFm().getFile("config").getString("Custom Variables." + custom)));
			}
		}

		return newString;
	}
}