package com.infogroup.infoboard.api;

import com.infogroup.infoboard.InfoBoardReborn;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Vault {
	private InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

	/**
	 * Gets the players rank
	 *
	 * @param player
	 * @return the rank
	 */
	public String getRank(Player player) {
		String rank = "default";
		if (hasVaultOnServer())
			try {
				rank = plugin.permission.getPlayerGroups(player.getWorld().getName(), player.getPlayer())[0];
			} catch (UnsupportedOperationException ignored) {
			}
		return rank;
	}

	/**
	 * Does the server have vault and did everything for vault load okay
	 *
	 * @return true/false
	 */
	private boolean hasVaultOnServer() {
		return (Bukkit.getPluginManager().getPlugin("Vault") != null) && (plugin.permission != null)
				&& (plugin.permissionB);
	}

	/**
	 * Load economy and permissions
	 */
	public void load() {
		if (!setupEconomy() || !setupPermissions()) {
			System.out.println(
					"InfoBoard will be shut down because VAULT either does not have economy or permissions setup");
			System.out.println(
					"InfoBoard will be shut down because VAULT either does not have economy or permissions setup");
			System.out.println(
					"InfoBoard will be shut down because VAULT either does not have economy or permissions setup");
			Bukkit.getPluginManager().disablePlugin(plugin);
		}
	}

	/**
	 * Load Economy
	 *
	 * @return if it loaded
	 */
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);

		if (economyProvider != null) {
			plugin.economy = economyProvider.getProvider();
		}

		if (plugin.economy != null) {
			plugin.economyB = true;
		}
		return (plugin.economy != null);
	}

	/**
	 * Load Permissions
	 *
	 * @return if it loaded
	 */
	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = Bukkit.getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null)
			plugin.permission = permissionProvider.getProvider();
		if (plugin.permission != null)
			plugin.permissionB = true;

		return (plugin.permission != null);
	}
}