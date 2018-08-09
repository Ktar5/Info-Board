package com.infogroup.infoboard;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class GetVariables {
	private static InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

	public static String replaceVariables(String string, Player player) {
		String newString;

		// setting all placeholders that are hooked in placeholder API
		newString = PlaceholderAPI.setPlaceholders(player, string);

		// Held item variables
		if (newString.contains("%player_mainhand_displayname%"))
			newString = newString.replace("%player_mainhand_displayname%", player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
		if (newString.contains("%player_offhand_displayname%"))
			newString = newString.replace("%player_offhand_displayname%", player.getInventory().getItemInOffHand().getItemMeta().getDisplayName());
		if (newString.contains("%player_mainhand_durability%"))
			newString = newString.replace("%player_mainhand_durability%", Short.toString((short) (player.getInventory().getItemInMainHand().getType().getMaxDurability() - player.getInventory().getItemInMainHand().getDurability())));
		if (newString.contains("%player_offhand_durability%"))
			newString = newString.replace("%player_offhand_durability%", Short.toString((short) (player.getInventory().getItemInOffHand().getType().getMaxDurability() - player.getInventory().getItemInOffHand().getDurability())));

		// Armor variables
		if (newString.contains("%armor_helmet_displayname%"))
			newString = newString.replace("%armor_helmet_displayname%", player.getInventory().getHelmet().getItemMeta().getDisplayName());
		if (newString.contains("%armor_chestplate_displayname%"))
			newString = newString.replace("%armor_chestplate_displayname%", player.getInventory().getChestplate().getItemMeta().getDisplayName());
		if (newString.contains("%armor_leggings_displayname%"))
			newString = newString.replace("%armor_leggings_displayname%", player.getInventory().getLeggings().getItemMeta().getDisplayName());
		if (newString.contains("%armor_boots_displayname%"))
			newString = newString.replace("%armor_boots_displayname%", player.getInventory().getBoots().getItemMeta().getDisplayName());
		if (newString.contains("%armor_helmet_durability%"))
			newString = newString.replace("%armor_helmet_durability%", Short.toString((short) (player.getInventory().getHelmet().getType().getMaxDurability() - player.getInventory().getHelmet().getDurability())));
		if (newString.contains("%armor_chestplate_durability%"))
			newString = newString.replace("%armor_chestplate_durability%", Short.toString((short) (player.getInventory().getChestplate().getType().getMaxDurability() - player.getInventory().getChestplate().getDurability())));
		if (newString.contains("%armor_leggings_durability%"))
			newString = newString.replace("%armor_leggings_durability%", Short.toString((short) (player.getInventory().getLeggings().getType().getMaxDurability() - player.getInventory().getLeggings().getDurability())));
		if (newString.contains("%armor_boots_durability%"))
			newString = newString.replace("%armor_boots_durability%", Short.toString((short) (player.getInventory().getBoots().getType().getMaxDurability() - player.getInventory().getBoots().getDurability())));

		// Custom Variables
		for (String custom : plugin.getFm().getFile("config").getConfigurationSection("Custom Variables").getKeys(true)) {
			if (newString.contains(custom))
				newString = newString.replaceAll(custom, plugin.getMessages().getColored(plugin.getFm().getFile("config").getString("Custom Variables." + custom)));
		}

		return newString;
	}
}
