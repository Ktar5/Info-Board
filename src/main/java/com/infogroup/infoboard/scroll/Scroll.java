package com.infogroup.infoboard.scroll;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.ChatColor;

public class Scroll {
	private InfoBoardReborn plugin;
	private String message, originalMessage;

	private ChatColor color = ChatColor.RESET;

	private int width, row;
	private int position, pause = 0;

	/**
	 * Create a new scroller
	 *
	 * @param message
	 * @param row
	 * @param width
	 */
	public Scroll(InfoBoardReborn plugin, String message, int row, int width) {
		//TODO Fix colors when scrolling
		this.plugin = plugin;
		this.row = row;
		this.width = width;
		this.originalMessage = message;
		StringBuilder builder = new StringBuilder(message);
		while (builder.length() <= (width * 2)) {
			builder.append("          ").append(message);
		}
		String string = builder.toString();

		string = this.plugin.getMessages().getColored(string);

		this.message = translateMsg(string);
	}

	/**
	 * Get the scrolled message
	 *
	 * @return String
	 */
	public String getMessage() {
//TODO fix the removing of the color char one a move
		String message = this.message.substring(position, Math.min(this.message.length(), (width - 2) + position));
		char COLORCHAR = '&';
		if (message.charAt(0) == COLORCHAR) {
			color = ChatColor.getByChar(message.charAt(1));
		} else {
			message = message.substring(1, message.length());
			message = "" + color + message;
		}
		if (message.charAt(message.length() - 1) == COLORCHAR) {
			message = message.substring(0, message.length() - 2);
			message = message + " ";
		}
		return message;
	}

	/**
	 * Get the row
	 *
	 * @return Integer
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Move position up one unless it's being paused for 3 counts first
	 */
	public void next() {
		if ((position == 0) && (pause != 3)) {
			pause++;
		} else {
			position++;
			pause = 0;
			if (position == (originalMessage.length() + 10)) {
				position = 0;
			}
		}
	}


	public String translateMsg(String msg){
		//TODO FINISH color code fix

		//check if msg contains ColorChar
		if(msg.contains("&")|| msg.contains("")){
			//split every line at the color char
			String[] split = msg.split("&");
			//loop trough all splits and make a Color Code to add per
			String color;
			for(int i= 0; i < split.length; i++){
				//if the Color Code has a length of 1 (means there is more then 1 code)
				if(split[i].length() == 1){
					 color = "&" + split[i] + "&" + split[i+1];
				}else if(split[i].length() > 1){
					color = split[i].substring(0, 1);
				}
			}
			//make to full msg again with a color code every letter
			for(int i =0; i< split.length; i++){
				if(i != 0){
					msg =  msg + split[i];
				}else{
					msg = split[i];
				}
			}
		}
		//return the msg
		return msg;
	}
}
