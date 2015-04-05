package com.ktar5.infoboard.API;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.ktar5.infoboard.InfoBoard;

public class Vault {

	/**
	 * Gets the players rank
	 * 
	 * @param player
	 * @return the rank
	 */
	public static String getRank(Player player) {
		String rank = "default";
		if (hasVaultOnServer())
			try {
				rank = InfoBoard.permission.getPlayerGroups(player.getWorld(),
						player.getName())[0];
			} catch (UnsupportedOperationException UOE) {
			}
		return rank;
	}

	/**
	 * Does the server have vault and did everything for vault load okay
	 * 
	 * @return true/false
	 */
	private static boolean hasVaultOnServer() {
		return (Bukkit.getPluginManager().getPlugin("Vault") != null)
				&& (InfoBoard.permission != null) && (InfoBoard.permissionB);
	}

	/**
	 * Load economy and permissions
	 */
	public static void load() {
		setupEconomy();
		setupPermissions();
	}

	/**
	 * Load Economy
	 * 
	 * @return if it loaded
	 */
	private static boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer()
				.getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null)
			InfoBoard.economy = economyProvider.getProvider();
		if (InfoBoard.economy != null)
			InfoBoard.economyB = true;

		return (InfoBoard.economy != null);
	}

	/**
	 * Load Permissions
	 * 
	 * @return if it loaded
	 */
	private static boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = Bukkit
				.getServer()
				.getServicesManager()
				.getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null)
			InfoBoard.permission = permissionProvider.getProvider();
		if (InfoBoard.permission != null)
			InfoBoard.permissionB = true;

		return (InfoBoard.permission != null);
	}

}
