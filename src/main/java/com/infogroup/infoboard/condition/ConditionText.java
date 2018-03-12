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
                       if (con.getCount() == con.getInterval()) {
                           // Check if the condition has changed
                           con.check(player);
                           String newLine = con.getMessage();

                           newLine = plugin.getMessages().getLine(newLine, player);
                           Board board = new Board(player);
                           board.update(newLine, con.getRow());
                           con.resetCount();
                       } else {
                           con.addCount();
                       }
                   } catch (Exception ex) {
                        if(plugin.getSettings().debug()){
                            Bukkit.getConsoleSender().sendMessage("Could not recheck condition, because: "+ ex);
                            ex.printStackTrace();
                        }
                   }
               }
           }
           if (plugin.getCM().getTitleCon(player) != null) {
               try {
                   Condition con = plugin.getCM().getTitleCon(player);
                   if (con.getCount() == con.getInterval()) {
                       // Check if the condition has changed
                       con.check(player);
                       String newLine = con.getMessage();

                       newLine = plugin.getMessages().getLine(newLine, player);
                       Board board = new Board(player);
                       board.setTitle(newLine);
                       con.resetCount();
                   } else {
                       con.addCount();
                   }
               } catch (Exception ex) {
                   if(plugin.getSettings().debug()){
                       Bukkit.getConsoleSender().sendMessage("Could not recheck condition, because: "+ ex);
                       ex.printStackTrace();
                   }
               }
           } else {
               plugin.getCM().reset(player);
           }
        }
    }
}
