package com.infogroup.infoboard.condition;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Board;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ConditionManager {
//TODO FINISH!
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
    Condition con =  new Condition(plugin,p, row, c, plugin.getSettings().getConInterval(c));
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
    public void createTitleCon(Player p){

    }

    /**
     *
     * @param player
     * @return
     */
    public ArrayList<Condition> getCons(Player player){
       return cons.get(player);
    }

    /**
     *
     * @param player
     * @return
     */
    public Condition getTitleCon(Player player){
        return titleCon.get(player);
    }

    /**
     *
     * @param p
     */
    public void reset(Player p){
        if(getCons(p)!= null){
            for(Condition con : getCons(p)){
                String lastString = con.getMessage();
                new Board(p).remove(lastString);
            }
        }
        this.cons.remove(p);
        this.titleCon.remove(p);
    }

}