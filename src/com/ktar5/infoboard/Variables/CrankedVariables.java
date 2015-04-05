package com.ktar5.infoboard.Variables;

import me.sniperzciinema.cranked.Handlers.Arena.ArenaManager;
import me.sniperzciinema.cranked.Handlers.Player.CPlayer;
import me.sniperzciinema.cranked.Handlers.Player.CPlayerManager;

import org.bukkit.entity.Player;

public class CrankedVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;

		CPlayer cp = CPlayerManager.getCrankedPlayer(player);

		if (newString.contains("<crankedkills>"))
			newString = newString.replaceAll("<crankedkills>",
					String.valueOf(cp.getOverallKills()));

		if (newString.contains("<crankeddeaths>"))
			newString = newString.replaceAll("<crankeddeaths>",
					String.valueOf(cp.getOverallDeaths()));

		if (newString.contains("<crankedscore>"))
			newString = newString.replaceAll("<crankedscore>",
					String.valueOf(cp.getScore()));

		if (newString.contains("<crankedplayers")) {
			String arenaName = newString.split("<crankedplayers")[1].split(">")[0];

			if (ArenaManager.getArena(arenaName) != null)
				newString = newString.replaceAll(
						"<crankedplayers" + arenaName + ">",
						String.valueOf(ArenaManager.getArena(arenaName)
								.getPlayers().size()));
			else
				newString = newString.replaceAll("<crankedplayers" + arenaName
						+ ">", "0");
		}
		if (newString.contains("<crankedcreator")) {
			String arenaName = newString.split("<crankedcreator")[1].split(">")[0];

			if (ArenaManager.getArena(arenaName) != null)
				newString = newString.replaceAll("<crankedcreator" + arenaName
						+ ">", String.valueOf(ArenaManager.getArena(arenaName)
						.getCreator()));
			else
				newString = newString.replaceAll("<crankedcreator" + arenaName
						+ ">", "Unknown");
		}
		return newString;
	}
}
