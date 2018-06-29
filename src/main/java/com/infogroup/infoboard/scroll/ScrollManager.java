package com.infogroup.infoboard.scroll;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Board;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ScrollManager {
	private InfoBoardReborn plugin;

	private HashMap<Player, ArrayList<Scroll2>> scrollers = new HashMap<>();
	private HashMap<Player, Scroll> title = new HashMap<>();

	public ScrollManager(InfoBoardReborn plugin) {
		this.plugin = plugin;
	}

	/**
	 * Create a scroller
	 *
	 * @param p
	 * @param message
	 * @param row
	 * @param width
	 * @return
	 */
	public Scroll2 createScroller(Player p, String message, int row, int width) {

		Scroll2 sc = new Scroll2(plugin, message, row, width,10);
		ArrayList<Scroll2> scs;
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
	public Scroll createTitleScroller(Player p, String message) {

		Scroll sc = new Scroll(plugin, message, 0, 16);
		this.title.put(p, sc);
		return sc;
	}

	/**
	 * Get the players scrollers
	 *
	 * @param p
	 * @return
	 */
	public ArrayList<Scroll2> getScrollers(Player p) {
		return this.scrollers.get(p);
	}


	/**
	 * Get the players title scroller
	 *
	 * @param p
	 * @return
	 */
	public Scroll getTitleScroller(Player p) {
		return this.title.get(p);
	}

	/**
	 * Reset all the players scrollers
	 *
	 * @param p
	 */
	public void reset(Player p) {
		if (getScrollers(p) != null) {
			for (Scroll2 sc : getScrollers(p)) {
				String lastString = sc.getMessage();
				new Board(p).remove(lastString);
			}
		}

		this.scrollers.remove(p);
		this.title.remove(p);
	}

}
