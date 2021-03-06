package com.infogroup.infoboard.events;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerJoin implements Listener {
	private InfoBoardReborn plugin;

	public PlayerJoin(InfoBoardReborn pl) {
		this.plugin = pl;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(plugin.getFm().getFile("config").getBoolean("give-onjoin") == false){
			Player player = event.getPlayer();
			plugin.hidefrom.add(player.getName());
		}
		if (event.getPlayer().isOp() && plugin.update) {
			event.getPlayer().sendMessage(ChatColor.DARK_AQUA + plugin.getFm().getFile("messages").getString("update"));
		}
	}

}
