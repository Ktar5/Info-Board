package com.infogroup.infoboard.changeable;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Board;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class ChangeableText {
	private static InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

	public static void change(Player player) {
		// Make sure the user can see the board
		if (!plugin.getSettings().isWorldDisabled(player.getWorld().getName())
				&& !plugin.hidefrom.contains(player.getName())
				&& ((player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) || player.getScoreboard()
						.getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard"))) {

			if (plugin.getCM().getChangeables(player) != null) {
				for (Changeable ch : plugin.getCM().getChangeables(player)) {
					try {
						// Move changeable over one, and add the new line
						ch.next();
						String newLine = ch.getMessage();

						Board board = new Board(player);
						newLine = plugin.getMessages().getLine(newLine, player);
						board.update(newLine, ch.getRow());
					} catch (Exception ignored) {

					}
				}
			}
			if (plugin.getCM().getChangeableTitle(player) != null) {

				try {
					Changeable ch = plugin.getCM().getChangeableTitle(player);
					// Move changeable over one, and add the new line
					ch.next();
					String newLine = ch.getMessage();

					Board board = new Board(player);
					newLine = plugin.getMessages().getLine(newLine, player);
					board.setTitle(newLine);
				} catch (Exception ignored) {

				}

			}
		} else {
			plugin.getCM().reset(player);
		}

	}

}
