package com.ktar5.infoboard.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.ktar5.infoboard.InfoBoard;

@SuppressWarnings("deprecation")
public class FileManager {

	private YamlConfiguration variable;
	private File boardFile;
	private File variableFile;
	private YamlConfiguration board;

	public FileManager() {

		getVariables().options().copyDefaults(true);
		saveVariables();
		getBoard().options().copyDefaults(true);
		saveBoard();
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	/**
	 * Get the board file
	 * 
	 * @return
	 */
	public FileConfiguration getBoard() {
		if (board == null) {
			reloadBoard();
			saveBoard();
		}
		return board;
	}

	public FileConfiguration getConfig() {
		return InfoBoard.instance.getConfig();
	}

	/**
	 * Get the variables file
	 * 
	 * @return
	 */
	public FileConfiguration getVariables() {
		if (variable == null) {
			reloadVariables();
			saveVariables();
		}
		return variable;
	}

	/**
	 * Reload Boards file
	 */
	public void reloadBoard() {
		if (boardFile == null)
			boardFile = new File(Bukkit.getPluginManager()
					.getPlugin("InfoBoardReborn").getDataFolder(), "Board.yml");
		board = YamlConfiguration.loadConfiguration(boardFile);
		// Look for defaults in the jar
		InputStream defConfigStream = Bukkit.getPluginManager()
				.getPlugin("InfoBoardReborn").getResource("Board.yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
			if (!boardFile.exists() || (boardFile.length() == 0))
				board.setDefaults(defConfig);
		}
	}

	/**
	 * Get the config
	 */
	public void reloadConfig() {
		InfoBoard.instance.reloadConfig();
	}

	/**
	 * Reload variables file
	 */
	public void reloadVariables() {
		if (variableFile == null)
			variableFile = new File(Bukkit.getPluginManager()
					.getPlugin("InfoBoardReborn").getDataFolder(),
					"Variables.yml");
		variable = YamlConfiguration.loadConfiguration(variableFile);
		// Look for defaults in the jar
		InputStream defConfigStream = Bukkit.getPluginManager()
				.getPlugin("InfoBoardReborn").getResource("Variables.yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
			variable.setDefaults(defConfig);
		}
	}

	/**
	 * Save Board file
	 */
	public void saveBoard() {
		if ((board == null) || (boardFile == null))
			return;
		try {
			getBoard().save(boardFile);
		} catch (IOException ex) {
			Bukkit.getLogger().log(Level.SEVERE,
					"Could not save config " + boardFile, ex);
		}
	}

	/**
	 * Save Config
	 */
	public void saveConfig() {
		InfoBoard.instance.saveConfig();
	}

	/**
	 * Save Variables file
	 */
	public void saveVariables() {
		if ((variable == null) || (variableFile == null))
			return;
		try {
			getVariables().save(variableFile);
		} catch (IOException ex) {
			Bukkit.getLogger().log(Level.SEVERE,
					"Could not save config " + variableFile, ex);
		}
	}

}
