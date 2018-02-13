package com.infogroup.infoboard.scoreboard;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Update {
	private static InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

	public static HashMap<Integer, String> getLines(List<String> list) {
		HashMap<Integer, String> toAdd = new HashMap<>();

		int i = 0;
		for (String line : list) {
			if (!line.equals(" ") && !line.equals("")
					&& !line.contains("<scroll>")
					&& !line.contains("<changeable_")
				&& !line.contains("<condition_")){
				toAdd.put(-i, line);
			}
			i++;
		}
		return toAdd;
	}

	/**
	 * Gets the title
	 * 
	 * @param list
	 */
	public static void getTitle(List<String> list) {
		ArrayList<String> titles = new ArrayList<>();
		for (String title : list) {
			if (!title.equals(" ") && title.equals(" ")
					&& !title.contains("<scroll>")
					&& !title.contains("<changeable_")) {
				titles.add(title);
			}
		}
	}

	/**
	 * updates the scoreboard
	 * 
	 * @param player
	 * @return true/false (boolean)
	 */
	public static boolean updateScoreBoard(Player player) {

		String worldName;
		String rankName = "default";

		// Make sure the player is allowed to see the board
		if (!plugin.getSettings().isWorldDisabled(player.getWorld().getName())
				&& !plugin.hidefrom.contains(player.getName())
				&& ((player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) || player.getScoreboard()
						.getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard"))) {
			// If the player no longer has permissions to see the board, remove
			// it
			if (!player.hasPermission("ibr.View") || !plugin.getWG().boardsAllowedHere(player.getLocation())) {
				player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			} else {// If the player doesn't have a scoreboard, then just create
					// one
				if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) {
					Create.createScoreBoard(player);
				} else {

					// Get the board's world name
					if (plugin.getSettings().doesWorldHaveScoreBoard(plugin.getTimers().getPage(),
							player.getWorld().getName())) {
						worldName = player.getWorld().getName();
					} else if (plugin.getSettings().doesGlobalHaveScoreBoard(plugin.getTimers().getPage())) {
						worldName = "global";
					} else {
						return false;
					}
					// Get the players rank name
					String rank = plugin.getV().getRank(player);

					// Make sure the rank is on the board, if it is set that to
					// the player's rankName
					if (plugin.getSettings().doesRankHaveScoreBoard(plugin.getTimers().getPage(), worldName, rank)) {
						rankName = rank;
					}
					// Make sure there is a default for the board
					if (!plugin.getSettings().doesRankHaveScoreBoard(plugin.getTimers().getPage(), worldName,
							rankName)) {
						player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
						return false;
					}
					Board board = new Board(player);

					HashMap<Integer, String> toAdd = getLines(plugin.getFm().getFile("board")
							.getStringList("InfoBoard." + String.valueOf(plugin.getTimers().getPage()) + "." + worldName
									+ "." + rankName + ".Rows"));

					for (Entry<Integer, String> e : toAdd.entrySet()) {

						int row = e.getKey();
						String line = e.getValue();
						ShouldSet set = new ShouldSet(player, line);
						line = set.getLine();

						if (set.getBoolean())
							if (line.contains(";")) {
								String a = line.split(";")[0];
								String b = line.split(";")[1];

								try {
									board.remove(plugin.getMessages().getLine(a, player));
									board.add(plugin.getMessages().getLine(a, player),
											Integer.valueOf(plugin.getMessages().getLine(b, player)));
								} catch (NumberFormatException ne) {
									board.remove(plugin.getMessages().getLine(a, player));
									board.add(plugin.getMessages().getLine(a, player),
											Integer.valueOf(plugin.getMessages().getLine(b, player)));
								}
							}
							// Just a regular line
							else {

								board.update(plugin.getMessages().getLine(line, player), -row);
							}
					}
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * @param player
	 * @param s
	 * @return true/false (boolean)
	 */
	public static boolean updateTitle(Player player, String s) {
		//TODO Finish
		return true;
	}
}