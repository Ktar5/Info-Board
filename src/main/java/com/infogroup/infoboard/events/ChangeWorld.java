package com.infogroup.infoboard.events;

import com.infogroup.infoboard.InfoBoardReborn;
import com.infogroup.infoboard.scoreboard.Create;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.scoreboard.DisplaySlot;

public class ChangeWorld implements Listener {

	private InfoBoardReborn plugin;

	public ChangeWorld(InfoBoardReborn plugin) { this.plugin = plugin; }

	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		Player player = event.getPlayer();
		player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		Create.createScoreBoard(player);
	}
}
