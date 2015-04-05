package com.ktar5.infoboard.API;

import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.ktar5.infoboard.Util.Settings;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class WorldGuard {

	/**
	 * Are the boards allowed to be shown in the players current region
	 * 
	 * @param loc
	 * @return
	 */
	public static boolean boardsAllowedHere(Location loc) {
		boolean allowed = true;
		if (hasWorldGuardOnServer())
			for (ProtectedRegion region : getRegionsIn(loc))
				if (Settings.getRegionsDisabled().contains(region.getId()))
					allowed = false;
		return allowed;
	}

	/**
	 * Get a list of regions the user is currently in
	 * 
	 * @param loc
	 * @return list of regions
	 */
	private static ArrayList<ProtectedRegion> getRegionsIn(Location loc) {
		ArrayList<ProtectedRegion> inRegions = new ArrayList<ProtectedRegion>();
		WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager()
				.getPlugin("WorldGuard");

		RegionManager regions = wg.getRegionManager(loc.getWorld());

		Iterator<ProtectedRegion> iter = regions.getApplicableRegions(loc)
				.iterator();
		while (iter.hasNext())
			inRegions.add(iter.next());

		return inRegions;
	}

	/**
	 * Does the server have world guard on it
	 * 
	 * @return true/false
	 */
	private static boolean hasWorldGuardOnServer() {
		return Bukkit.getPluginManager().getPlugin("WorldGuard") != null;
	}
}
