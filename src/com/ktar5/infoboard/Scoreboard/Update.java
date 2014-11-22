
package com.ktar5.infoboard.Scoreboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import com.ktar5.infoboard.InfoBoard;
import com.ktar5.infoboard.API.Vault;
import com.ktar5.infoboard.API.WorldGuard;
import com.ktar5.infoboard.Util.Messages;
import com.ktar5.infoboard.Util.Settings;


public class Update {
	
	public static HashMap<Integer, String> getLines(List<String> list) {
		HashMap<Integer, String> toAdd = new HashMap<Integer, String>();
		
		int i = 0;
		for (String line : list)
		{
			if (!line.equals(" ") && !line.equals("") && !line.contains("<scroll>"))
				toAdd.put(-i, line);
			i++;
		}
		return toAdd;
	}
	
	public static boolean updateScoreBoard(Player player) {
		
		String worldName = "global";
		String rankName = "default";
		
		// Make sure the player is allowed to see the board
		if (!Settings.isWorldDisabled(player.getWorld().getName()) && !InfoBoard.hidefrom.contains(player.getName()) && ((player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) || player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard")))
			// If the player no longer has permissions to see the board, remove it
			if (!player.hasPermission("InfoBoard.View") || !WorldGuard.boardsAllowedHere(player.getLocation()))
				player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			else // If the player doesn't have a scoreboard, then just create one
			if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null)
				Create.createScoreBoard(player);
			else
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
				{
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
					return false;
				}
				Board board = new Board(player);
				
				HashMap<Integer, String> toAdd = getLines(InfoBoard.getFileManager().getBoard().getStringList("Info Board." + String.valueOf(InfoBoard.getTimers().getPage()) + "." + worldName + "." + rankName + ".Rows"));
				
				for (Entry<Integer, String> e : toAdd.entrySet())
				{
					
					int row = e.getKey();
					String line = e.getValue();
					ShouldSet set = new ShouldSet(player, line);
					line = set.getLine();
					
					if (set.getBoolean())
						if (line.contains(";"))
						{
							String a = line.split(";")[0];
							String b = line.split(";")[1];
							
							try
							{
								board.remove(Messages.getLine(a, player));
								board.add(Messages.getLine(a, player), Integer.valueOf(Messages.getLine(b, player)));
							}
							catch (NumberFormatException ne)
							{
								board.remove(Messages.getLine(a, player));
								board.add(Messages.getLine(a, player), Integer.valueOf(Messages.getLine(b, player)));
							}
						}
						// Just a regular line
						else{
							
							board.update(Messages.getLine(line, player), -row);
						}
					}
			}
		return true;
	}
}
