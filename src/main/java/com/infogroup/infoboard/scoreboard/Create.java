package com.infogroup.infoboard.scoreboard;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.changeable.Changeable;
import com.infogroup.infoboard.condition.Condition;
import com.infogroup.infoboard.scroll.Scroll;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.List;

public class Create {
    private static InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

    public static boolean createScoreBoard(Player player) {
        // Set the default variable values
        String worldName;
        String rankName = "default";
        int row, spaces = 0;

        // Make sure the player is allowed to see the scoreboard
        if (plugin.getWG().boardsAllowedHere(player.getLocation())
                && !plugin.getSettings().isWorldDisabled(player.getWorld().getName())
                && player.hasPermission("ibr.View") && !plugin.hidefrom.contains(player.getName())
                && ((player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) || player.getScoreboard()
                .getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard"))) {
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

            // Make sure the rank is on the board, if it is set that to the
            // player's rankName
            if (plugin.getSettings().doesRankHaveScoreBoard(plugin.getTimers().getPage(), worldName, rank)) {
                rankName = rank;
            }
            // Make sure there is a default for the board
            if (!plugin.getSettings().doesRankHaveScoreBoard(plugin.getTimers().getPage(), worldName, rankName)) {
                return false;
            }
            // Remove any old objective from the sidebar
            if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null) {
                player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).unregister();
                player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
            }
            // Create a new scoreboard and set it to the sidebar display
            Board board = new Board();

            // Remove and scrolling texts that the player may have had
            plugin.getSM().reset(player);

            // Remove all changeable texts that the player may have had
            if (plugin.getCHM().getChangeables(player) != null) {
                plugin.getCHM().reset(player);
            }
            List<String> changeables = plugin.getSettings().getChangeable();

            // Remove all the conditions that the player may have had
            if(plugin.getCM().getCons(player) != null){
                plugin.getCM().reset(player);
            }
            List<String> conditions = plugin.getSettings().getConditions();

            // Now we go to the title setting method thats down below
            board.setTitle(plugin.getMessages().getTitle(player, worldName, rankName));

            // Loop through the lines
            List<String> lines = plugin.getFm().getFile("board").getStringList("InfoBoard."
                    + String.valueOf(plugin.getTimers().getPage()) + "." + worldName + "." + rankName + ".Rows");

            for (row = 0; row != lines.size(); row++) {
                String line = lines.get(row);

                // ShouldSet is used for the boolean variables (~! an ~@)
                ShouldSet set = new ShouldSet(player, line);
                line = set.getLine();

                if (set.getBoolean()) {
                    // If the line is empty just assume it's an empty line
                    if (line.equals(" ") || line.equals("")) {
                        //Note, it is the
                        String space = String.valueOf(ChatColor.COLOR_CHAR) + "" + spaces;
                        spaces++;
                        board.add(plugin.getMessages().getColored(space), row);
                    } else // Manage all scrolling lines
                        if (line.startsWith("<scroll>")) {
                            if (plugin.getSettings().scrollingEnabled()) {
                                line = line.replaceAll("<scroll>", "");
                                int longestLine = getLongestLine(lines, player);
                                String string = plugin.getMessages().getLine(line, player);
                                Scroll sc = plugin.getSM().createScroller(player, string, row, longestLine);
                                line = sc.getMessage();
                                board.add(line, row);
                            } else {
                                line = "Enable scroll";
                                board.add(line, row);
                            }
                        } else// Manage all changeable lines
                            if (line.startsWith("<changeable_")) {
                                if (plugin.getSettings().changeableTextEnabled()) {
                                    line = line.replaceAll("<changeable_", "").replaceAll(">", "").replaceAll(" ", "");
                                    if (changeables.contains(line)) {
                                        Changeable ch = plugin.getCHM().createChangeables(player, line, row);
                                        line = ch.getMessage();
                                        line = plugin.getMessages().getLine(line, player);
                                        board.add(line, row);
                                    } else {
                                        line = "Unknown changeable";
                                        board.add(line, row);
                                    }
                                } else {
                                    line = "Enable changeable Text";
                                    board.add(line, row);
                                }
                            }else // Manage all conditions
                                if(line.contains("<condition_")){
                                    if(plugin.getSettings().conditionsEnabled()){
                                        line = line.replace("<condition_", "").replace(">", "");
                                        if(conditions.contains(line)){
                                            Condition con = plugin.getCM().createCon(player, row, line);
                                            line = con.getMessage();
                                            line = plugin.getMessages().getLine(line, player);
                                            board.add(line, row);
                                        }else{
                                            line = "Unknown condition";
                                            board.add(line, row);
                                        }
                                    }else{
                                        line= "Enable conditions";
                                        board.add(line, row);
                                    }
                            }
                            // If the line has a split in it
                            else if (line.contains(";")) {
                                String a = line.split(";")[0];
                                String b = line.split(";")[1];

                                try {
                                    board.add(plugin.getMessages().getLine(a, player),
                                            Integer.valueOf(plugin.getMessages().getLine(b, player)));
                                } catch (NumberFormatException ne) {
                                    board.add(plugin.getMessages().getLine(a, player), row);
                                }
                            }
                            // Just a regular line
                            else {
                                board.add(plugin.getMessages().getLine(line, player), row);
                            }
                }
            }
            // then we just set the scoreboard for the player
            player.setScoreboard(board.getScoreboard());

        }
        return true;
    }

    private static int getLongestLine(List<String> lines, Player player) {
        int longest = 0;
        for (String line : lines) {
            if (!line.contains("<scroll>")) {
                String string = plugin.getMessages().getReplacements(line, player);
                if (string.length() > longest) {
                    longest = string.length();
                }
            }
        }
        return longest;
    }
}