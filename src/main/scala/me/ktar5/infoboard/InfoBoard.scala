package me.ktar5.infoboard

import java.io.IOException

import me.clip.placeholderapi.PlaceholderAPI
import me.ktar5.infoboard.logger.Logger
import net.milkbowl.vault.chat.Chat
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission
import org.bukkit.Bukkit
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.{EventHandler, EventPriority}
import org.bukkit.plugin.java.JavaPlugin
import org.mcstats.Metrics

/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of infoboard.
 * 
 * infoboard can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */

class InfoBoard extends JavaPlugin{


    override def onEnable(): Unit = {
        InfoBoard._instance = this

        if(!InfoBoard.vault.init()){
            InfoBoard.logger.warn("Hey! Vault is not enabled, here be dragons")
        }

        try {
            val metrics = new Metrics(this)
            metrics.start()
        } catch {
            case ioe: IOException => InfoBoard.logger.fatal("Hey! Metrics were unable to start... this is odd")
        }

        if (Bukkit.getPluginManager.isPluginEnabled("PlaceholderAPI")) {
            //todo
        } else {
            //todo
            InfoBoard.logger.fatal("Could not find PlaceholderAPI!! Plugin can not work without it!")
            throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!")
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    def onJoin(event: PlayerJoinEvent) {

        var withoutPlaceholdersSet = "%player_name% &ajoined the server! He/she is rank &f%vault_rank%";

        var withPlaceholdersSet = PlaceholderAPI.setPlaceholders(event.getPlayer(), withoutPlaceholdersSet);

        event.setJoinMessage(withPlaceholdersSet);
    }

}

object InfoBoard{

    val vault = new Vault
    val logger: Logger = new Logger

    private var _instance: InfoBoard = null
    def instance = _instance

    class Vault {
        private var _econ: Economy = null
        private var _perms: Permission = null
        private var _chat: Chat = null

        def econ = _econ
        def perms = _perms
        def chat = _chat

        def init(): Boolean ={
            if (instance.getServer.getPluginManager.getPlugin("Vault") == null) {
                return false
            }
            _econ = instance.getServer.getServicesManager.getRegistration[Economy](Class[Economy]).getProvider
            _chat = instance.getServer.getServicesManager.getRegistration[Chat](Class[Chat]).getProvider
            _perms = instance.getServer.getServicesManager.getRegistration[Permission](Class[Permission]).getProvider
            true
        }

    }

}