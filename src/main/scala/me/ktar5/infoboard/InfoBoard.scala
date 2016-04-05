package me.ktar5.infoboard

import org.bukkit.plugin.java.JavaPlugin

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

    }

}

object InfoBoard{

    var _instance: InfoBoard = null

}