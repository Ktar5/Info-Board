package com.ktar5.infoboard.Variables;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Map.Entry;

public class WorldGuardVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;

		ArrayList<ProtectedRegion> inRegions = new ArrayList<ProtectedRegion>();
		WorldGuardPlugin wg = (WorldGuardPlugin) Bukkit.getPluginManager()
				.getPlugin("WorldGuard");
		LocalPlayer lplayer = wg.wrapPlayer(player);
		RegionManager regions = wg.getRegionManager(player.getWorld());

		ArrayList<String> playersRegions = new ArrayList<String>();
		for (Entry<String, ProtectedRegion> r : regions.getRegions().entrySet())
			if (r.getValue().isOwner(lplayer))
				playersRegions.add(r.getKey());
		for (ProtectedRegion protectedRegion : regions.getApplicableRegions(
				player.getLocation()))
			inRegions.add(protectedRegion);

		// //////////////////////////////////////////////////

		if (newString.contains("<worldguardinid>"))
			if (!(inRegions.isEmpty() || (inRegions.get(0) == null)))
				newString = newString.replaceAll("<worldguardinid>",
						String.valueOf(inRegions.get(0).getId()));
			else
				newString = newString.replaceAll("<worldguardinid>", "Unknown");

		if (newString.contains("<worldguardinowner>"))
			if (!(inRegions.isEmpty() || (inRegions.get(0) == null)))
				newString = newString.replaceAll(
						"<worldguardinowner>",
						String.valueOf(inRegions.get(0).getOwners()
								.getPlayers().iterator().next()));
			else
				newString = newString.replaceAll("<worldguardinowner>",
						"Unknown");

		if (newString.contains("<worldguardinvolume>"))
			if (!(inRegions.isEmpty() || (inRegions.get(0) == null)))
				newString = newString.replaceAll("<worldguardinvolume>",
						String.valueOf(inRegions.get(0).volume()));
			else
				newString = newString.replaceAll("<worldguardinvolume>", "0");

		if (newString.contains("<worldguardinmembers>"))
			if (!(inRegions.isEmpty() || (inRegions.get(0) == null)))
				newString = newString.replaceAll("<worldguardinmembers>",
						String.valueOf(inRegions.get(0).getMembers().size()));
			else
				newString = newString.replaceAll("<worldguardinmembers>", "0");

		if (newString.contains("<worldguardinflag")) {
			String flag = newString.split("<worldguardinflag")[1].split(">")[0];

			if (!(inRegions.isEmpty() || (inRegions.get(0) == null)))

				newString = newString.replaceAll(
						"<worldguardinflag" + (flag) + ">",
						String.valueOf(inRegions.get(0).getFlag(
								DefaultFlag.fuzzyMatchFlag(flag))));
			else
				newString = newString.replaceAll("<worldguardinflag" + (flag)
						+ ">", "Unknown");
		}
		return newString;
	}
}
