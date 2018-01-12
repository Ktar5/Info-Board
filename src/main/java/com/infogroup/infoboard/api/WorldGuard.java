package com.infogroup.infoboard.api;

import com.infogroup.infoboard.InfoBoardReborn;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;

public class WorldGuard {
	private InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

	/**
	 * Are the boards allowed to be shown in the players current region
	 *
	 * @param loc
	 * @return
	 */
	public boolean boardsAllowedHere(Location loc) {
		boolean allowed = true;
		if (hasWorldGuardOnServer())
			for (ProtectedRegion region : getRegionsIn(loc))
				if (plugin.getSettings().getRegionsDisabled().contains(region.getId()))
					allowed = false;
		return allowed;
	}

	/**
	 * Get a list of regions the user is currently in
	 *
	 * @param loc
	 * @return list of regions
	 */
	private ArrayList<ProtectedRegion> getRegionsIn(Location loc) {
		ArrayList<ProtectedRegion> inRegions = new ArrayList<>();
		WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");

		RegionManager regions = wg.getRegionManager(loc.getWorld());

		for (ProtectedRegion protectedRegion : regions.getApplicableRegions(loc))
			inRegions.add(protectedRegion);

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