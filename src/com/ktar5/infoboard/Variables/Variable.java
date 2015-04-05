package com.ktar5.infoboard.Variables;

import org.bukkit.entity.Player;

/**
 * @author Carter
 *
 * @param <A>
 */
public abstract class Variable<A> {

	private String name;
	private A value;
	private boolean isPlayerSpecific;

	/**
	 * @param name
	 * @param defaultValue
	 */
	public Variable(String name, A defaultValue, boolean playerSpecific){
		this.isPlayerSpecific = playerSpecific;
		this.name = name;
		this.value = defaultValue;

	}

	/**
	 * 
	 */
	private void register(){

	}

	/**
	 * @param player The player who this variable will belong to, null if
	 * 		doesnt need player
	 * @return The variable in a displayable format, this will be put on the 
	 * 		scoreboard itself.
	 */
	public abstract String getStringValue(Player player);

	/**
	 * @param player The player to get this variable
	 * @return
	 */
	public abstract A getValue(Player player);

}
