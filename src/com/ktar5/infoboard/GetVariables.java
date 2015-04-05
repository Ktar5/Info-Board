package com.ktar5.infoboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ktar5.infoboard.Util.Messages;
import com.ktar5.infoboard.Variables.ALTVariables;
import com.ktar5.infoboard.Variables.CommandPointsVariables;
import com.ktar5.infoboard.Variables.CrankedVariables;
import com.ktar5.infoboard.Variables.EssentialsVariables;
import com.ktar5.infoboard.Variables.FactionsVariables;
import com.ktar5.infoboard.Variables.GriefPreventionVariables;
import com.ktar5.infoboard.Variables.HeroesVariables;
import com.ktar5.infoboard.Variables.InfectedVariables;
import com.ktar5.infoboard.Variables.JobsVariables;
import com.ktar5.infoboard.Variables.LWCVariables;
import com.ktar5.infoboard.Variables.LibsDisguiseVariables;
import com.ktar5.infoboard.Variables.MarriageVariables;
import com.ktar5.infoboard.Variables.MathVariables;
import com.ktar5.infoboard.Variables.McTownsVariables;
import com.ktar5.infoboard.Variables.MiniGamesVariables;
import com.ktar5.infoboard.Variables.MultiverseVariables;
import com.ktar5.infoboard.Variables.OnTimeVariables;
import com.ktar5.infoboard.Variables.PVPArenaVariables;
import com.ktar5.infoboard.Variables.PVPStatsVariables;
import com.ktar5.infoboard.Variables.PlayerPointsVariables;
import com.ktar5.infoboard.Variables.PlayerVariables;
import com.ktar5.infoboard.Variables.PlotMeVariables;
import com.ktar5.infoboard.Variables.PointsAPIVariables;
import com.ktar5.infoboard.Variables.PrisionRankupVariables;
import com.ktar5.infoboard.Variables.PvpLevelsVariables;
import com.ktar5.infoboard.Variables.ServerVariables;
import com.ktar5.infoboard.Variables.SimpleClansVariables;
import com.ktar5.infoboard.Variables.SkillzVariables;
import com.ktar5.infoboard.Variables.StatsVariables;
import com.ktar5.infoboard.Variables.TownyVariables;
import com.ktar5.infoboard.Variables.VanishNoPacketVariables;
import com.ktar5.infoboard.Variables.VaultVariables;
import com.ktar5.infoboard.Variables.WorldGuardVariables;
import com.ktar5.infoboard.Variables.mcMMOVariables;

public class GetVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;

		// Math Variables
		if (newString.contains("<math"))
			newString = MathVariables.replaceVariables(newString, player);

		// Server Variables
		newString = ServerVariables.replaceVariables(newString);

		// Player Variables
		newString = PlayerVariables.replaceVariables(newString, player);

		// Infected Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Infected") != null)
			if (newString.contains("<infected"))
				newString = InfectedVariables.replaceVariables(newString,
						player);

		// LibsDisguises Support
		if (Bukkit.getServer().getPluginManager().getPlugin("LibsDisguises") != null)
			if (newString.contains("<disguised"))
				newString = LibsDisguiseVariables.replaceVariables(newString,
						player);

		// LibsDisguises Support
		/*if (Bukkit.getServer().getPluginManager().getPlugin("ClusterDisguises") != null)
			if (newString.contains("<clustdisguise"))
				newString = ClusterDisguiseVariables.replaceVariables(
						newString, player);
		 */
		// Essentials Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Essentials") != null)
			if (newString.contains("<ess"))
				newString = EssentialsVariables.replaceVariables(newString,
						player);

		// AncientRPG Support
		/*		if (Bukkit.getServer().getPluginManager().getPlugin("AncientRPG") != null)
			if (newString.contains("<ancientrpg"))
				newString = AncientRPGVariables.replaceVariables(newString,
						player);
		 */
		// LWC Support
		if (Bukkit.getServer().getPluginManager().getPlugin("LWC") != null)
			if (newString.contains("<lwc"))
				newString = LWCVariables.replaceVariables(newString, player);

		// Marriage Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Marriage") != null)
			if (newString.contains("<marriage"))
				newString = MarriageVariables.replaceVariables(newString,
						player);

		// Cranked Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Cranked") != null)
			if (newString.contains("<cranked"))
				newString = CrankedVariables
				.replaceVariables(newString, player);

		// Cranked Support
		if (Bukkit.getServer().getPluginManager().getPlugin("CommandPoints") != null)
			if (newString.contains("<commandpoints"))
				newString = CommandPointsVariables.replaceVariables(newString,
						player);

		// McTowns Support
		if (Bukkit.getServer().getPluginManager().getPlugin("MCTowns") != null)
			if (newString.contains("<mctowns"))
				newString = McTownsVariables
				.replaceVariables(newString, player);

		// Towny Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Towny") != null)
			if (newString.contains("<towny"))
				newString = TownyVariables.replaceVariables(newString, player);

		// Factions Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Factions") != null)
			if (newString.contains("<factions"))
				newString = FactionsVariables.replaceVariables(newString,
						player);

		// GriefPrevention Support
		if (Bukkit.getServer().getPluginManager().getPlugin("GriefPrevention") != null)
			if (newString.contains("<griefprevention"))
				newString = GriefPreventionVariables.replaceVariables(
						newString, player);

		// Heroes Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Heroes") != null)
			if (newString.contains("<heroes"))
				newString = HeroesVariables.replaceVariables(newString, player);

		// mcMMO Support
		if (Bukkit.getServer().getPluginManager().getPlugin("mcMMO") != null)
			if (newString.contains("<mcmmo"))
				newString = mcMMOVariables.replaceVariables(newString, player);

		// Vault Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null)
			if (newString.contains("<vault") || newString.contains("<rank")
					|| newString.contains("<money"))
				newString = VaultVariables.replaceVariables(newString, player);

		// Jobs Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Jobs") != null)
			if (newString.contains("<jobs"))
				newString = JobsVariables.replaceVariables(newString, player);

		// PlayerPoints Support
		if (Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints") != null)
			if (newString.contains("<playerpoints"))
				newString = PlayerPointsVariables.replaceVariables(newString,
						player);

		// PvpStats Support
		if (Bukkit.getServer().getPluginManager().getPlugin("pvpstats") != null)
			if (newString.contains("<pvpstats"))
				newString = PVPStatsVariables.replaceVariables(newString,
						player);

		// PlotMe Support
		if (Bukkit.getServer().getPluginManager().getPlugin("PlotMe") != null)
			if (newString.contains("<plotme"))
				newString = PlotMeVariables.replaceVariables(newString, player);

		// PVPArena Support
		if (Bukkit.getServer().getPluginManager().getPlugin("pvparena") != null)
			if (newString.contains("<pvparena"))
				newString = PVPArenaVariables.replaceVariables(newString,
						player);

		// PvpLevels Support
		if (Bukkit.getServer().getPluginManager().getPlugin("PvpLevels") != null)
			if (newString.contains("<pvplevels"))
				newString = PvpLevelsVariables.replaceVariables(newString,
						player);

		// PointsAPI Support
		if (Bukkit.getServer().getPluginManager().getPlugin("PointsAPI") != null)
			if (newString.contains("<pointsapi"))
				newString = PointsAPIVariables.replaceVariables(newString,
						player);

		// PrisonRankup Support
		if (Bukkit.getServer().getPluginManager().getPlugin("PrisonRankup") != null)
			if (newString.contains("<prisonrankup"))
				newString = PrisionRankupVariables.replaceVariables(newString,
						player);

		// Stats Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Stats") != null)
			if (newString.contains("<stats"))
				newString = StatsVariables.replaceVariables(newString, player);

		// SimpleClans Support
		if (Bukkit.getServer().getPluginManager().getPlugin("SimpleClans") != null)
			if (newString.contains("<simpleclans"))
				newString = SimpleClansVariables.replaceVariables(newString,
						player);

		if (Bukkit.getServer().getPluginManager().getPlugin("Skillz") != null)
			if (newString.contains("<skillz"))
				newString = SkillzVariables.replaceVariables(newString, player);

		// WorldGuard Support
		if (Bukkit.getServer().getPluginManager().getPlugin("WorldGuard") != null)
			if (newString.contains("<worldguard"))
				newString = WorldGuardVariables.replaceVariables(newString,
						player);

		// VanishNoPacket Support
		if (Bukkit.getServer().getPluginManager().getPlugin("VanishNoPacket") != null)
			if (newString.contains("<vanish"))
				newString = VanishNoPacketVariables.replaceVariables(newString,
						player);

		// MiniGames Support
		if (Bukkit.getServer().getPluginManager().getPlugin("MiniGames") != null)
			if (newString.contains("<minigames"))
				newString = MiniGamesVariables.replaceVariables(newString,
						player);

		// Multiverse Support
		if (Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core") != null)
			if (newString.contains("<multiverse"))
				newString = MultiverseVariables.replaceVariables(newString,
						player);

		// OnTime Support
		if (Bukkit.getServer().getPluginManager().getPlugin("OnTime") != null)
			if (newString.contains("<ontime"))
				newString = OnTimeVariables.replaceVariables(newString, player);

		// UTF-8
		newString = ALTVariables.replaceVariables(newString);

		// Custom Variables
		for (String custom : InfoBoard.getFileManager().getConfig()
				.getConfigurationSection("Custom Variables").getKeys(true))
			if (newString.contains(custom))
				newString = newString.replaceAll(
						custom,
						Messages.getColored(InfoBoard.getFileManager()
								.getConfig()
								.getString("Custom Variables." + custom)));

		return newString;
	}
}
