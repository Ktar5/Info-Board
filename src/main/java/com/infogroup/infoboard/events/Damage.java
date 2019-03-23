package com.infogroup.infoboard.events;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Damage implements Listener{
    private InfoBoardReborn plugin;

    public Damage(InfoBoardReborn pl) {
            this.plugin = pl;
        }
    //TODO FINISH, Show PVP/Damage board on event

    /**
     *
     * @param event
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
         if(event.getDamager() instanceof Player){

             Player damager = (Player) event.getDamager();
             Entity target = event.getEntity();


         }
     }
}

