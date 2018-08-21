package com.infogroup.infoboard.condition;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Board;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ConditionManager {

    private InfoBoardReborn plugin;
    private HashMap<Player, ArrayList<Condition>> cons =  new HashMap<>();
    private HashMap<Player, Condition> titleCon = new HashMap<>();

    public ConditionManager (InfoBoardReborn plugin){
        this.plugin = plugin;
    }

    /**
     *
     * @param p
     * @param row
     */
    public Condition createCon(Player p, int row, String con){
        Condition cond =  new Condition(row, con, plugin.getFm().getFile("config").getString("Condition.Conditions." + con + ".check"),
                plugin.getSettings().getConInterval(con));
        ArrayList<Condition> conList;
        if(cons.containsKey(p)){
            conList = cons.get(p);
        }else{
            conList = new ArrayList<>();
        }
        conList.add(cond);
        this.cons.put(p, conList);
        return cond;
    }

    /**
     *
     * @param p
     */
    public Condition createTitleCon(Player p, String co){
        Condition con = new Condition(0, co,
                plugin.getFm().getFile("config").getString("Condition.Conditions." + co + ".check"),
                plugin.getSettings().getConInterval(co));
        this.titleCon.put(p, con);
        return con;
    }

    /**
     *
     * @param player
     * @return
     */
    public ArrayList<Condition> getCons(Player player){
       return this.cons.get(player);
    }

    /**
     *
     * @param player
     * @return
     */
    public Condition getTitleCon(Player player){
        return this.titleCon.get(player);
    }

    /**
     *
     * @param p
     */
    public void reset(Player p){
        if(getCons(p)!= null){
            for(Condition con : this.getCons(p)){
                String lastString = con.getMessage(p);
                new Board(p).remove(lastString);
            }
        }
        this.cons.remove(p);
        this.titleCon.remove(p);
    }

}
