package com.infogroup.infoboard.events;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave implements Listener {

    private InfoBoardReborn plugin;

    public Leave(InfoBoardReborn plugin) { this.plugin = plugin; }

    @EventHandler
    public void Leave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(plugin.getSettings().scrollingEnabled()
                || plugin.getSettings().conditionsEnabled()
                || plugin.getSettings().changeableTextEnabled()){
            plugin.getCM().reset(player);
            plugin.getCHM().reset(player);
            plugin.getSM().reset(player);
        }
    }
}
