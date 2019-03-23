package com.infogroup.infoboard.api;

import com.infogroup.infoboard.InfoBoardReborn;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class WorldGuardApi {
	private InfoBoardReborn plugin;


	public WorldGuardApi(InfoBoardReborn plugin) {
		this.plugin = plugin;
	}

	/**
	 * Are the boards allowed to be shown in the players current region
	 *
	 * @param player
	 * @return
	 */
	public boolean boardsAllowedHere(Player player) {
		boolean allowed = true;
		if (hasWorldGuardOnServer())
			for (ProtectedRegion region : getRegionsIn(player))
				if (plugin.getSettings().getRegionsDisabled().contains(region.getId()))
					allowed = false;
		return allowed;
	}

	/**
	 * Get a list of regions the user is currently in
	 *
	 * @param player
	 * @return list of regions
	 */
	private ArrayList<ProtectedRegion> getRegionsIn(Player player) {
		ArrayList<ProtectedRegion> inRegions = new ArrayList<>();

		RegionManager regionManager = WorldGuardPlugin.inst().getRegionManager(player.getWorld());

		//TODO HELP???
		for (ProtectedRegion protectedRegion : regionManager.getApplicableRegions(player.getLocation())) {
			inRegions.add(protectedRegion);
		}
		return inRegions;
	}

	/**
	 * Does the server have world guard on it
	 *
	 * @return true/false
	 */
	private boolean hasWorldGuardOnServer() {
		return Bukkit.getPluginManager().getPlugin("WorldGuard") != null;
	}
}