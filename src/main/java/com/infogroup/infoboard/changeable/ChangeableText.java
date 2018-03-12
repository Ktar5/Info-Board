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
						if (ch.getCount() == ch.getInterval()) {
							// Move changeable over one, and add the new line
							ch.next();
							String newLine = ch.getMessage();

							newLine = plugin.getMessages().getLine(newLine, player);
							Board board = new Board(player);
							board.update(newLine, ch.getRow());
							ch.resetCount();
						} else {
							ch.addCount();
						//	Bukkit.broadcastMessage("Count: " + ch.getCount());
						}
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
					if (ch.getCount() == ch.getInterval()) {
						// Move changeable over one, and add the new line
						ch.next();
						String newLine = ch.getMessage();

						newLine = plugin.getMessages().getLine(newLine, player);
						Board board = new Board(player);
						board.setTitle(newLine);
						ch.resetCount();
					} else {
						ch.addCount();
					}
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
