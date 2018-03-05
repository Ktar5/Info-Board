package com.infogroup.infoboard.changeable;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Changeable {
	private InfoBoardReborn plugin = InfoBoardReborn.getPlugin(InfoBoardReborn.class);
	
	private int counter, count = 0;
	private String message;
	private int row, interval;
	private ArrayList<String> lines;

	/**
	 * Create a new changeable
	 * 
	 * @param p
	 * @param row
	 * @param lines
	 * @param interval
	 */
	public Changeable(Player p, int row, ArrayList<String> lines, int interval) {
		this.interval = interval;
		this.row = row;
		this.lines = new ArrayList<>(lines);
		this.message = this.lines.get(0);
		/*
		 * =========================================================================
		 * CHANGEABLE TEXT UPDATES VALUE
		 * =========================================================================
		 */
	//	Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> ChangeableText.change(p), 0, (long) this.interval * 20);

	}

    /**
     *
     */

    public void add(){
        if (count != interval){
            this.count++;
        } else {
            this.count =0;
            this.next();
        }


    }
	/**
	 * Gets the row
	 * 
	 * @return row (Integer)
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets the Message
	 * 
	 * @return message (String)
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Moves to the next line
	 */
	public void next() {
		if (lines.size() == counter) {
			counter = 0;
		} else {
			message = lines.get(counter);
			counter++;
		}
	}

	/**
	 * Gets the changeable interval
	 * 
	 * @return interval (Integer)
	 */
	public int getInterval() {
		return interval;
	}

}
