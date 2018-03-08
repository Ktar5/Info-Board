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
    public Condition createCon(Player p, int row, String c){
        Condition con =  new Condition(p, row, c, plugin.getSettings().getConInterval(c));
        ArrayList<Condition> conList;
        if(cons.containsKey(p)){
            conList = cons.get(p);
        }else{
            conList = new ArrayList<>();
        }
        conList.add(con);
        cons.put(p, conList);
        return con;
    }

    /**
     *
     * @param p
     */
    public Condition createTitleCon(Player p, String s){
        Condition con = new Condition(p, 0, s, plugin.getSettings().getConInterval(s));
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
                String lastString = con.getMessage();
                new Board(p).remove(lastString);
            }
        }
        this.cons.remove(p);
        this.titleCon.remove(p);
    }

}
