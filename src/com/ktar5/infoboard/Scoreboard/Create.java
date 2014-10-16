
package com.ktar5.infoboard.Scoreboard;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import com.ktar5.infoboard.InfoBoard;
import com.ktar5.infoboard.API.Vault;
import com.ktar5.infoboard.API.WorldGuard;
import com.ktar5.infoboard.Scroll.Scroll;
import com.ktar5.infoboard.Scroll.ScrollManager;
import com.ktar5.infoboard.Util.Messages;
import com.ktar5.infoboard.Util.Settings;


public class Create {
	
	public static boolean createScoreBoard(Player player) {
		// Set the default variable values
		String worldName = "global";
		String rankName = "default";
		int row, spaces = 0;
		
		// Make sure the player is allowed to see the scoreboard
		if (WorldGuard.boardsAllowedHere(player.getLocation()) && !Settings.isWorldDisabled(player.getWorld().getName()) && player.hasPermission("InfoBoard.View") && !InfoBoard.hidefrom.contains(player.getName()) && ((player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) || player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard")))
		{
			// Get the board's world name
			if (Settings.doesWorldHaveScoreBoard(InfoBoard.getTimers().getPage(), player.getWorld().getName()))
				worldName = player.getWorld().getName();
			else if (Settings.doesGlobalHaveScoreBoard(InfoBoard.getTimers().getPage()))
				worldName = "global";
			else
				return false;
			
			// Get the players rank name
			String rank = Vault.getRank(player);
			
			// Make sure the rank is on the board, if it is set that to the player's rankName
			if (Settings.doesRankHaveScoreBoard(InfoBoard.getTimers().getPage(), worldName, rank))
				rankName = rank;
			
			// Make sure there is a default for the board
			if (!Settings.doesRankHaveScoreBoard(InfoBoard.getTimers().getPage(), worldName, rankName))
				return false;
			
			// Remove any old objective from the sidebar
			if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null)
			{
				player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).unregister();
				player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			}
			// Create a new scoreboard and set it to the sidebar display
			Board board = new Board();
			
			// Remove and scrolling texts that the player may have had
			ScrollManager.reset(player);
			
			// Now we go to the title setting method thats down below
			board.setTitle(Messages.getTitle(player, worldName, rankName));
			
			// Loop through the lines
			List<String> lines = InfoBoard.getFileManager().getBoard().getStringList("Info Board." + String.valueOf(InfoBoard.getTimers().getPage()) + "." + worldName + "." + rankName + ".Rows");
			
			for (row = 0; row != lines.size(); row++)
			{
				String line = lines.get(row);
				
				// ShouldSet is used for the boolean variables (~! an ~@)
				ShouldSet set = new ShouldSet(player, line);
				line = set.getLine();
				
				if (set.getBoolean())
					// If the line is empty just assume it's an empty line
					if (line.equals(" ") || line.equals(""))
					{
						String space = "§" + spaces;
						spaces++;
						board.add(Messages.getColored(space), row);
					}
					else // Manage all scrolling lines
					if (line.startsWith("<scroll>"))
					{
						
						if (Settings.scrollingEnabled())
						{
							line = line.replaceAll("<scroll>", "");
							int longestLine = getLongestLine(lines, player);
							String string = Messages.getLine(line, player);
							Scroll sc = ScrollManager.createScroller(player, string, -row, longestLine);
							line = sc.getMessage();
							board.add(line, row);
						}
						else
						{
							line = "Enable Scroll";
							board.add(line, row);
						}
					}
					// If the line has a split in it
					else if (line.contains(";"))
					{
						String a = line.split(";")[0];
						String b = line.split(";")[1];
						
						try
						{
							board.add(Messages.getLine(a, player), Integer.valueOf(Messages.getLine(b, player)));
						}
						catch (NumberFormatException ne)
						{
							board.add(Messages.getLine(a, player), 0);
						}
					}
					// Just a regular line
					else
						board.add(Messages.getLine(line, player), row);
			}
			// then we just set the scoreboard for the player
			player.setScoreboard(board.getScoreboard());
			
		}
		return true;
	}
	
	public static int getLongestLine(List<String> lines, Player player) {
		int longest = 0;
		for (String line : lines)
			if (!line.contains("<scroll>"))
			{
				String string = Messages.getReplacements(line, player);
				if (string.length() > longest)
					longest = string.length();
				
			}
		return longest;
	}
}
