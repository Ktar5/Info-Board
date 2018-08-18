package com.infogroup.infoboard.condition;

import com.infogroup.infoboard.GetVariables;
import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.entity.Player;

import java.util.Map;

public class Condition {
    private InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);

    private String msg, con, check;
    private Integer row, interval;
    private Integer count = 0;
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
        this.msg = answers.get("default");


    }

    /**
     * Check if the condition has changed and change the msg if it has.
     *
     * @param player
     */
    //TODO FIX
    public void check(Player player){

        String newCheck = GetVariables.replaceVariables(this.check, player);

        //Loop trough entries
        for (Map.Entry<String, String> entry : answers.entrySet()) {

            String key = entry.getKey();

            //check if the check equals an other placeholder, replace by it's value if so
            if (entry.getKey().contains("%")) {
                key = GetVariables.replaceVariables(key, player);
            }
            //check if the key equals the check, if so get key's value and set that as msg, else dmsg is msg
            if (key.equals(newCheck)) {
                this.msg = answers.get(key);
            } else {
                this.msg = answers.get("default");
            }

        }
    }

    /**
     * Get the name of the condition
     *
     * @return String
     */
    public String getCon(){
        return this.con;
    }

    /**
     * Get the current message
     *
     * @return String
     */
    public String getMessage(){
        return this.msg;
    }

    /**
     * Gets the conditions row
     *
     * @return Integer
     */
    public Integer getRow(){
        return this.row;
    }

    /**
     * Gets the interval of the condition
     *
     * @return Integer
     */
    public Integer getInterval(){
        return this.interval;
    }

    /**
     * Get the current value of Count
     *
     * @return Integer
     */
    public Integer getCount(){
        return this.count;
    }

    /**
     * add 1 to the count
     */
    public void addCount(){
        this.count++;
    }

    /**
     * reset the count
     */
    public void resetCount(){
        this.count = 0;
    }
}
