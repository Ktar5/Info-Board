package com.infogroup.infoboard.changeable;

import java.util.ArrayList;

public class Changeable {

	private int counter, count = 0;
	private String message;
	private int row, interval;
	private ArrayList<String> lines;

	/**
	 * Create a new changeable
	 *
	 * @param row
	 * @param lines
	 * @param interval
	 */
	public Changeable(int row, ArrayList<String> lines, int interval) {
		this.interval = interval * 20;
		this.row = row;
		this.lines = new ArrayList<>(lines);
		this.message = this.lines.get(0);
	}
	/**
	 * Gets the row
	 * 
	 * @return Integer
	 */
	public int getRow() {
		return this.row;
	}

	/**
	 * Gets the message
	 * 
	 * @return String
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * Moves to the next line
	 */
	public void next() {
		if (counter == (lines.size() - 1)) {
			this.counter = 0;
			this.message = this.lines.get(counter);
		} else {
			this.counter++;
			this.message = this.lines.get(counter);
		}
	}

	/**
	 * Gets the changeable interval
     *
	 * @return Integer
	 */
	public int getInterval() {	return interval; }

	/**
	 * Adds 1 too the count
	 */
	public void addCount() { this.count++; }

	/**
	 * Gets the count
	 *
	 * @return Integer
	 */
	public int getCount(){ return this.count; }

	/**
	 * Resets the count
	 */
	public void resetCount() { this.count = 0; }
}
