package com.infogroup.infoboard.changeable;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Board;
import org.bukkit.Bukkit;
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
			if (plugin.getCHM().getChangeables(player) != null) {
				for (Changeable ch : plugin.getCHM().getChangeables(player)) {
					try {
						// Move changeable over one, and add the new line
						ch.next();
						String newLine = ch.getMessage();

						newLine = plugin.getMessages().getLine(newLine, player);
						Board board = new Board(player);
						board.update(newLine, ch.getRow());
					} catch (Exception ex) {
						if(plugin.getSettings().debug()){
							Bukkit.getConsoleSender().sendMessage("Could not get the next value for Changeable, because: " + ex);
						}
					}
				}
			}
			if (plugin.getCHM().getChangeableTitle(player) != null) {
				try {
					Changeable ch = plugin.getCHM().getChangeableTitle(player);
					// Move changeable over one, and add the new line
					ch.next();
					String newLine = ch.getMessage();

					newLine = plugin.getMessages().getLine(newLine, player);
					Board board = new Board(player);
					board.setTitle(newLine);
				} catch (Exception ex) {
					if(plugin.getSettings().debug()){
						Bukkit.getConsoleSender().sendMessage("Could not get the next value for Changeable, because: " + ex);
					}
				}
			}
		} else {
			plugin.getCHM().reset(player);
		}

	}

}
