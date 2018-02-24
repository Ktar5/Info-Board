package com.infogroup.infoboard.condition;

import com.infogroup.infoboard.GetVariables;
import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Condition {
    private InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

    private String msg, con, check;
    private Integer row, interval;
    private ArrayList<String> answers;

    /**
     *
     * @param p
     * @param row
     * @param con
     * @param interval
     */
    public Condition(Player p, int row, String con, int interval){
        this.row = row;
        this.interval = interval;
        this.con = con;
        this.check = plugin.getFm().getFile("config").getString("Condition.Conditions." + con + ".check");
        this.answers =  plugin.getSettings().getConText(con);
        this.msg = plugin.getFm().getFile("config").getString("Condition.Conditions." + con + ".answer.default");

        /*
         * =========================================================================
         * CHANGEABLE TEXT UPDATES VALUE
         * =========================================================================
         */
        //TODO TIMER works?
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, () -> {
            ConditionText.change(p);
            Bukkit.broadcastMessage("Timer works");
            }, 0, (long) (this.interval * 20));
    }

    /**
     *
     * @param player
     */
    public void check(Player player){
        //TODO TEST and FIX
       String newCheck = GetVariables.replaceVariables(this.check, player);
       Bukkit.broadcastMessage("Check: "+ newCheck);
       for(String s : this.answers){
           if(s.contains("%")){
            s = GetVariables.replaceVariables(s, player);
           }
           if(s.equals(newCheck)){
               this.msg = plugin.getFm().getFile("config").getString("Condition.Conditions." + getCon() + ".answer." + s);
           } else {
               this.msg = plugin.getFm().getFile("config").getString("Condition.Conditions." + getCon() + ".answer.default");
           }
       }
    }

    /**
     *
     * @return
     */
    public String getCon(){ return this.con; }

    /**
     *
     * @return
     */
    public String getMessage(){ return this.msg; }

    /**
     *
     * @return
     */
    public Integer getRow(){ return this.row; }
}
