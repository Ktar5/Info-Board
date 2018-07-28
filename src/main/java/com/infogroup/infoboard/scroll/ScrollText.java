package com.infogroup.infoboard.scroll;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Board;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class ScrollText {
	private static InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

	public static void scroll(Player player) {
		// Make sure the user can see the board
		if (!plugin.getSettings().isWorldDisabled(player.getWorld().getName())
				&& !plugin.hidefrom.contains(player.getName())
				&& ((player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) || player.getScoreboard()
						.getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard"))) {
			if (plugin.getSM().getScrollers(player) != null) {
				for (Scroll sc : plugin.getSM().getScrollers(player)) {
					try {
						// Move scroller over one, and add the new line
						//sc.next();
						String newLine = sc.getMessage();

						Board board = new Board(player);
						board.update(newLine, sc.getRow());
					} catch (Exception ex) {
						if(plugin.getSettings().debug()){
							Bukkit.getConsoleSender().sendMessage("Could not scroll, because: " + ex);
						}
					}
				}
			}

			if (plugin.getSM().getTitleScroller(player) != null) {
				try {
					Scroll sc = plugin.getSM().getTitleScroller(player);

					// Change the boards title to the next scroller
					sc.next();
					String newLine = sc.getMessage();

					Board board = new Board(player);
					board.setTitle(newLine);
				} catch (Exception ex) {
					if(plugin.getSettings().debug()){
						Bukkit.getConsoleSender().sendMessage("Could not scroll, because: " + ex);
					}
				}
			}
		} else {
			plugin.getSM().reset(player);
		}

	}
}
