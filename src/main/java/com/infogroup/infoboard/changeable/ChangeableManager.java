package com.infogroup.infoboard.changeable;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Board;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ChangeableManager {
	private InfoBoardReborn plugin;

	private HashMap<Player, ArrayList<Changeable>> changeables = new HashMap<>();
	private HashMap<Player, Changeable> title = new HashMap<>();

	public ChangeableManager(InfoBoardReborn plugin) {
		this.plugin = plugin;
	}

	/**
	 * Create a changeable
	 *
	 * @param p
	 * @param changeable
	 * @param row
	 * @return changeable
	 */
	public Changeable createChangeables(Player p, String changeable, int row, String option) {
		ArrayList<String> lines = plugin.getSettings().getText(changeable);
		int time = plugin.getSettings().getInterval(changeable);
		Changeable ch = new Changeable(row, lines, time, plugin.getSettings().getChangeableOption(changeable));
		ArrayList<Changeable> chs;
		if (this.changeables.containsKey(p)) {
			chs = this.changeables.get(p);
		} else {
			chs = new ArrayList<>();
		}
		chs.add(ch);
		this.changeables.put(p, chs);
		return ch;
	}

	/**
	 * Create a changeable Title
	 *
	 * @param p
	 * @param changeable
	 * @return changeable
	 */
	public Changeable createChangeableTitle(Player p, String changeable) {
		ArrayList<String> lines = plugin.getSettings().getText(changeable);
		int time = plugin.getSettings().getInterval(changeable);
		Changeable ch = new Changeable( 0, lines, time, plugin.getSettings().getChangeableOption(changeable));
		this.title.put(p, ch);
		return ch;
	}

	/**
	 * Get the players changeable title
	 * 
	 * @param p
	 * @return Changeable
	 */
	public Changeable getChangeableTitle(Player p) {
		return this.title.get(p);
	}

	/**
	 * Get the players changeables
	 * 
	 * @param p
	 * @return players changeables
	 */
	public ArrayList<Changeable> getChangeables(Player p) {
		return this.changeables.get(p);
	}

	/**
	 * Resets the players changeable
	 * 
	 * @param p
	 */
	public void reset(Player p) {
		if (getChangeables(p) != null) {
			for (Changeable ch : getChangeables(p)) {
				String lastString = ch.getMessage();
				new Board(p).remove(lastString);
			}
		}
		this.changeables.remove(p);
		this.title.remove(p);
	}
}
