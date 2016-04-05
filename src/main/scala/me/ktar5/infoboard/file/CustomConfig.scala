package me.ktar5.infoboard.file

import java.io.File
import java.util

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.{FileConfiguration, YamlConfiguration}
import org.bukkit.plugin.java.JavaPlugin

/*
 * Copyright (C) 2013-Current Carter Gale (Ktar5) <buildfresh@gmail.com>
 * 
 * This file is part of infoboard.
 * 
 * infoboard can not be copied and/or distributed without the express
 * permission of the aforementioned owner.
 */
protected class CustomConfig(val folder: File, val fileName: String) {
    private var _config: FileConfiguration = null
    val configFile: File = new File(folder, fileName)

    reloadConfig()

    def config = {
        _config
    }

    def doesConfigExist(name: String): Boolean = {
        var fileName: String = name
        if (!fileName.endsWith(".yml")) {
            fileName += ".yml"
        }
        new File(CustomConfig.PLUGIN.getDataFolder, fileName).exists
    }

    def reloadConfig() {
        if (!configFile.exists) {
            CustomConfig.PLUGIN.getLogger.info("Attempting to save resource: " + configFile.getName)
            CustomConfig.PLUGIN.saveResource(fileName, true)
        }
        _config = YamlConfiguration.loadConfiguration(configFile)
    }

    def saveConfig() {
        try {
            config.save(configFile)
        } catch {
            case e: Exception =>
                CustomConfig.PLUGIN.getLogger.severe(String.format("Couldn't save '%s', because: '%s'", fileName, e.getMessage))
        }
        reloadConfig()
    }

    def set(path: String, value: Any, save: Boolean) {
        config.set(path, value)
        if (save) {
            saveConfig()
        }
    }

    def set(path: String, value: Any) {
        set(path, value, save = false)
    }

    def keys(deep: Boolean): util.Set[String] = this.config.getKeys(deep)

    def configSection(path: String): ConfigurationSection = this.config.getConfigurationSection(path)


    def has(path: String): Boolean =  config.contains(path)

    def get[T](path: String, tClass: Class[T]): Option[T] = {
        if (tClass.isPrimitive) {
            throw new IllegalArgumentException(tClass + " is of a primitive type. Disallowed type.")
        }
        if (!has(path)) {
            throw new IllegalArgumentException(path + " does not exist.")
        }
        val obj: Any = config.get(path)
        if (obj == null) {
            return Option.empty[T]
        }
        if (!tClass.isInstance(obj)) {
            throw new IllegalArgumentException(path + " is not of type " + tClass.getSimpleName)
        }
        Option(tClass.cast(obj))
    }

    def get[T](path: String, tClass: Class[T], tDefault: T): T = {
        if (tClass.isPrimitive) {
            throw new IllegalArgumentException(tClass + " is of a primitive type. Disallowed type.")
        }
        if (!has(path)) {
            throw new IllegalArgumentException(path + " does not exist.")
        }
        val obj: Any = config.get(path)
        if (obj == null) {
            return tDefault
        }
        if (!tClass.isInstance(obj)) {
            throw new IllegalArgumentException(path + " is not of type " + tClass.getSimpleName)
        }
        tClass.cast(obj)
    }

    def stringList(path: String): util.List[String] = {
        if (!has(path)) {
            return new util.ArrayList[String]
        }
        config.getStringList(path)
    }
}

object CustomConfig{
    private val PLUGIN: JavaPlugin = JavaPlugin.getProvidingPlugin(classOf[CustomConfig])

    def apply(folder: File, fileName: String): CustomConfig = {
        if (!fileName.endsWith(".yml")) {
            return CustomConfig(folder, fileName + ".yml")
        }
        CustomConfig(folder, fileName)
    }
}