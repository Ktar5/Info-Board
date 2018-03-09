package com.infogroup.infoboard.condition;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Board;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

public class ConditionText {

    private static InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

    public static void change(Player player){
       if(!plugin.getSettings().isWorldDisabled(player.getWorld().getName())
               && !plugin.hidefrom.contains(player.getName())
               && ((player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) || player.getScoreboard()
               .getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard")) ) {

           if (plugin.getCM().getCons(player) != null) {
               for (Condition con : plugin.getCM().getCons(player)) {
                   try {
                       // Check if the condition has changed
                       con.check(player);
                       String newLine = con.getMessage();
                      // Bukkit.broadcastMessage("Success in getting msg: "+ newLine);

                       Board board = new Board(player);
                       newLine = plugin.getMessages().getLine(newLine, player);
                       board.update(newLine, con.getRow());
                   } catch (Exception ex) {
                        if(plugin.getSettings().debug()){
                            Bukkit.getConsoleSender().sendMessage("Could not recheck condition, because: "+ ex);
                        }
                   }
               }
           }
           if (plugin.getCM().getTitleCon(player) != null) {
               try {
                   Condition con = plugin.getCM().getTitleCon(player);
                   // Check if the condition has changed
                   con.check(player);
                   String newLine = con.getMessage();

                   Board board = new Board(player);
                   newLine = plugin.getMessages().getLine(newLine, player);
                   board.setTitle(newLine);
               } catch (Exception ex) {
                   if(plugin.getSettings().debug()){
                       Bukkit.getConsoleSender().sendMessage("Could not recheck condition, because: "+ ex);
                   }
               }
           } else {
               plugin.getCM().reset(player);
           }
        }
    }
}
