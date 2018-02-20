package com.infogroup.infoboard.utils;

import com.infogroup.infoboard.GetVariables;
import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {
	private InfoBoardReborn plugin;

	public Messages(InfoBoardReborn plugin) {
		this.plugin = plugin;
	}

	/**
	 * Get the message in color
	 *
	 * @param line
	 * @return new line (String)
	 */
	public String getColored(String line) {
		line = ChatColor.translateAlternateColorCodes('&', line);
		line = line.replaceAll("&x", RandomChatColor.getColor().toString());
		line = line.replaceAll("&y", RandomChatColor.getFormat().toString());
		return line;
	}

	/**
	 * Get the new line by doing everything
	 *
	 * @param line
	 * @param player
	 * @return new line (String)
	 */
	public String getLine(String line, Player player) {
		if (line.contains("%")) {
			line = getReplacements(line, player);
		}
		if (line.contains("<") && line.contains(">")) {
			line = getReplacements(line, player);
		}

		line = getColored(line);
		return line;
	}

	/**
	 * Get the <strong>Replaced</strong> version of the line
	 *
	 * @param line
	 * @param player
	 * @return new line (String)
	 */
	public String getReplacements(String line, Player player) {
		return GetVariables.replaceVariables(line, player);
	}

	/**
	 * Get the title from the config and set it
	 *
	 * @param player
	 * @param worldName
	 * @param rankName
	 * @return title (String)
	 */
	public String getTitle(Player player, String worldName, String rankName) {

		String title = plugin.getFm().getFile("board").getString("InfoBoard."
				+ String.valueOf(plugin.getTimers().getPage()) + "." + worldName + "." + rankName + ".Title");

		if (title.startsWith("<scroll>") && plugin.getSettings().scrollingEnabled()) {
			title = title.replaceAll("<scroll>", "");
			// and create a Title scroller
			title = plugin.getSM().createTitleScroller(player, getLine(title, player)).getMessage();

		} else if (title.startsWith("<changeable_") && plugin.getSettings().changeableTextEnabled()) {
            title =title.replaceAll("<changeable_", "").replaceAll(">", "");
			// create changeable title
			title = plugin.getCHM().createChangeableTitle(player, title).getMessage();

		} else if(title.startsWith("<condition_")&& plugin.getSettings().conditionsEnabled()){
            title = title.replaceAll("<condition_","").replaceAll(">","");
            // create changeable title
            title = plugin.getCM().createTitleCon(player, title).getMessage();

		} else {
			title = getLine(title.substring(0, Math.min(title.length(), 32)), player);
		}
		return title;

	}
}