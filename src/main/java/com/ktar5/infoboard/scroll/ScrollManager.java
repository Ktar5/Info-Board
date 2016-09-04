package com.ktar5.infoboard.scroll;

import com.ktar5.infoboard.scoreboard.Board;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ScrollManager {

    private static HashMap<Player, ArrayList<Scroll>> scrollers = new HashMap<Player, ArrayList<Scroll>>();
    private static HashMap<Player, Scroll> title = new HashMap<Player, Scroll>();

    /**
     * Create a scroller
     *
     * @param p
     * @param message
     * @param row
     * @param width
     * @return
     */
    public static Scroll createScroller(Player p, String message, int row,
                                        int width) {
        Scroll sc = new Scroll(message, row, width);
        ArrayList<Scroll> scs;
        if (ScrollManager.scrollers.containsKey(p))
            scs = ScrollManager.scrollers.get(p);
        else
            scs = new ArrayList<Scroll>();
        scs.add(sc);
        ScrollManager.scrollers.put(p, scs);
        return sc;
    }

    /**
     * Create a title scroller
     *
     * @param p
     * @param message
     * @return
     */
    public static Scroll createTitleScroller(Player p, String message) {

        Scroll sc = new Scroll(message, 0, 16);
        ScrollManager.title.put(p, sc);

        return sc;
    }

    /**
     * Get the players scrollers
     *
     * @param p
     * @return
     */
    public static ArrayList<Scroll> getScrollers(Player p) {
        return ScrollManager.scrollers.get(p);
    }

    /**
     * Get the players title scroller
     *
     * @param p
     * @return
     */
    public static Scroll getTitleScroller(Player p) {
        return ScrollManager.title.get(p);
    }

    /**
     * Reset all the players scrollers
     *
     * @param p
     */
    public static void reset(Player p) {
        if (getScrollers(p) != null)
            for (Scroll sc : getScrollers(p)) {
                String lastString = sc.getMessage();
                new Board(p).remove(lastString);
            }
        ScrollManager.scrollers.remove(p);
        ScrollManager.title.remove(p);
    }

}
