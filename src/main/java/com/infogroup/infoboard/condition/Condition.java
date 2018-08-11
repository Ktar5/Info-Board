package com.infogroup.infoboard.condition;

import com.infogroup.infoboard.GetVariables;
import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Map;

public class Condition {
    private InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

    private String msg, con, check, dmsg;
    private Integer row, interval;
    private Integer count = 0;
    private ArrayList<String> answer;
    private Map<String, String> answers;

    /**
     * Create a new condition
     *
     * @param row
     * @param con
     * @param interval
     */
    public Condition(int row, String con, String check, int interval){
        this.row = row;
        this.interval = interval * 20;
        this.con = con;
        this.check = check;
        this.answers = plugin.getSettings().getConText(con);
        this.dmsg = plugin.getFm().getFile("config").getString("Condition.Conditions." + con + ".answer.default");
        this.msg = plugin.getFm().getFile("config").getString("Condition.Conditions." + con + ".answer.default");

    }

    /**
     * Check if the condition has changed and change the msg if it has.
     *
     * @param player
     */
    public void check(Player player){
       String newCheck = GetVariables.replaceVariables(this.check, player);
       //TODO FIX
       for(String s : this.answer){
           if(s.contains("%")) {
               s = GetVariables.replaceVariables(s, player);
           }
           if(s.equals(newCheck)){
               this.msg = plugin.getFm().getFile("config").getString("Condition.Conditions." + this.getCon() + ".answer." + s);
           } else {
               this.msg = this.dmsg;
           }
       }

    }

    /**
     * Get the name of the condition
     *
     * @return String
     */
    public String getCon(){ return this.con; }

    /**
     * Get the current message
     *
     * @return String
     */
    public String getMessage(){ return this.msg; }

    /**
     * Gets the conditions row
     *
     * @return Integer
     */
    public Integer getRow(){ return this.row; }

    /**
     * Gets the interval of the condition
     *
     * @return Integer
     */
    public Integer getInterval(){ return this.interval; }

    /**
     * Get the current value of Count
     *
     * @return Integer
     */
    public Integer getCount(){ return this.count; }

    /**
     * add 1 to the count
     */
    public void addCount(){ this.count++; }

    /**
     * reset the count
     */
    public void resetCount(){ this.count = 0; }
}
