package com.infogroup.infoboard.scroll;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Board;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ScrollManager3 {
    private InfoBoardReborn plugin;

    private HashMap<Player, ArrayList<Scroll3>> scrollers = new HashMap<>();
    private HashMap<Player, Scroll3> title = new HashMap<>();

    public ScrollManager3(InfoBoardReborn plugin) {
        this.plugin = plugin;
    }

    /**
     * Create a scroller
     *
     * @param p
     * @param message
     * @param width
     * @return
     */
    public Scroll3 createScroller(Player p, String message, int width) {

        Scroll3 sc = new Scroll3( message, width,10,'&');
        ArrayList<Scroll3> scs;
        if (this.scrollers.containsKey(p)) {
            scs = this.scrollers.get(p);
        } else {
            scs = new ArrayList<>();
        }
        scs.add(sc);
        this.scrollers.put(p, scs);
        return sc;

    }

    /**
     * Create a title scroller
     *
     * @param p
     * @param message
     * @return
     */
    public Scroll3 createTitleScroller(Player p, String message) {
        Scroll3 sc = new Scroll3(message, 0, 16,'&');
        this.title.put(p, sc);
        return sc;
    }

    /**
     * Get the players scrollers
     *
     * @param p
     * @return
     */
    public ArrayList<Scroll3> getScrollers(Player p) {
        return this.scrollers.get(p);
    }


    /**
     * Get the players title scroller
     *
     * @param p
     * @return
     */
    public Scroll3 getTitleScroller(Player p) {
        return this.title.get(p);
    }

    /**
     * Reset all the players scrollers
     *
     * @param p
     */
    public void reset(Player p) {
        if (getScrollers(p) != null) {
            for (Scroll3 sc : getScrollers(p)) {
                String lastString = sc.next();
                new Board(p).remove(lastString);
            }
        }

        this.scrollers.remove(p);
        this.title.remove(p);
    }
}
