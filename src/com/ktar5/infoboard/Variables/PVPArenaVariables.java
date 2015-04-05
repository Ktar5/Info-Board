package com.ktar5.infoboard.Variables;

import net.slipcor.pvparena.api.PVPArenaAPI;
import net.slipcor.pvparena.arena.Arena;
import net.slipcor.pvparena.arena.ArenaPlayer;
import net.slipcor.pvparena.classes.PAStatMap;
import net.slipcor.pvparena.managers.ArenaManager;
import net.slipcor.pvparena.managers.StatisticsManager.type;

import org.bukkit.entity.Player;

public class PVPArenaVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;
		String prefix = "pvparena";

		PAStatMap stats = ArenaPlayer.parsePlayer(player.getName())
				.getStatistics();

		String ArenaName = PVPArenaAPI.getArenaName(player);
		Arena a = ArenaManager.getArenaByName(ArenaName);

		if (newString.contains("<" + prefix + "arenaname>"))
			newString = newString.replaceAll("<" + prefix + "arenaname>",
					ArenaName == null ? "Unknown" : ArenaName);
		if (newString.contains("<" + prefix + "arenaowner>"))
			newString = newString.replaceAll("<" + prefix + "arenaowner>",
					a == null ? "Unknown" : a.getOwner());
		if (newString.contains("<" + prefix + "arenaprefix>"))
			newString = newString.replaceAll("<" + prefix + "arenaprefix>",
					a == null ? "Unknown" : a.getPrefix());
		if (newString.contains("<" + prefix + "arenaround>"))
			newString = newString.replaceAll("<" + prefix + "arenaround>",
					String.valueOf(a == null ? 0 : a.getRound()));
		if (newString.contains("<" + prefix + "arenateams>"))
			newString = newString.replaceAll("<" + prefix + "arenateams>",
					String.valueOf(a == null ? 0 : a.getTeams().size()));

		if (newString.contains("<" + prefix + "statskills>"))
			newString = newString.replaceAll("<" + prefix + "statskills>",
					String.valueOf(stats.getStat(type.KILLS)));
		if (newString.contains("<" + prefix + "statsdeaths>"))
			newString = newString.replaceAll("<" + prefix + "statsdeath>",
					String.valueOf(stats.getStat(type.DEATHS)));
		if (newString.contains("<" + prefix + "statsdamage>"))
			newString = newString.replaceAll("<" + prefix + "statsdamage>",
					String.valueOf(stats.getStat(type.DAMAGE)));
		if (newString.contains("<" + prefix + "statsdamagetake>"))
			newString = newString.replaceAll("<" + prefix + "statsdamagetake>",
					String.valueOf(stats.getStat(type.DAMAGETAKE)));
		if (newString.contains("<" + prefix + "statslosses>"))
			newString = newString.replaceAll("<" + prefix + "statslosses>",
					String.valueOf(stats.getStat(type.LOSSES)));
		if (newString.contains("<" + prefix + "statswins>"))
			newString = newString.replaceAll("<" + prefix + "statswins>",
					String.valueOf(stats.getStat(type.WINS)));
		if (newString.contains("<" + prefix + "statsmaxdamage>"))
			newString = newString.replaceAll("<" + prefix + "statsmaxdamage>",
					String.valueOf(stats.getStat(type.MAXDAMAGE)));
		if (newString.contains("<" + prefix + "statsmaxdamagetake>"))
			newString = newString.replaceAll("<" + prefix
					+ "statsmaxdamagetake>",
					String.valueOf(stats.getStat(type.MAXDAMAGETAKE)));

		return newString;
	}
}
