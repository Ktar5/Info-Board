package com.infogroup.infoboard.condition;

import com.infogroup.infoboard.GetVariables;
import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Condition {
    private InfoBoardReborn plugin;
    private String msg;
    private String con;
    private Integer row;
    private Integer interval;
    private ArrayList<String> awnsers;
    private String check;

    /**
     *
     * @param plugin
     * @param p
     * @param row
     * @param con
     * @param interval
     */
    public Condition(InfoBoardReborn plugin, Player p, int row, String con, int interval){
        this.plugin = plugin;
        this.row = row;
        this.interval = interval;
        this.con = con;
        this.awnsers = plugin.getSettings().getConText(con);
        this.check = plugin.getFm().getFile("config").getString("Condition.Conditions." + con + ".check");
        /*
         * =========================================================================
         * CHANGEABLE TEXT UPDATES VALUE
         * =========================================================================
         */
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> ConditionText.change(p), 0, (long) this.interval * 20);
    }

    /**
     *
     * @param player
     */
    public void change(Player player){
        //TODO TEST
       String newCheck = GetVariables.replaceVariables(check, player);
        for(String s : awnsers){
            if(s.equals(newCheck)){
                this.msg = plugin.getFm().getFile("config").getString("Condition.Conditions." + getCon() + ".awnser." + s);
            }else{
                this.msg = plugin.getFm().getFile("config").getString("Condition.Conditions." + getCon()+ ".awnser.default");
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
