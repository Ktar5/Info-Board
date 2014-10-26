
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
	
	private YamlConfiguration	variable;
	private File							boardFile;
	private File							variableFile;
	private YamlConfiguration	board;
	
	public FileManager()
	{
		
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
		if (this.board == null)
		{
			reloadBoard();
			saveBoard();
		}
		return this.board;
	}
	
	public FileConfiguration getConfig() {
		return InfoBoard.me.getConfig();
	}
	
	/**
	 * Get the variables file
	 * 
	 * @return
	 */
	public FileConfiguration getVariables() {
		if (this.variable == null)
		{
			reloadVariables();
			saveVariables();
		}
		return this.variable;
	}
	
	/**
	 * Reload Boards file
	 */
	public void reloadBoard() {
		if (this.boardFile == null)
			this.boardFile = new File(Bukkit.getPluginManager().getPlugin("InfoBoardReborn").getDataFolder(),
					"Board.yml");
		this.board = YamlConfiguration.loadConfiguration(this.boardFile);
		// Look for defaults in the jar
		InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("InfoBoardReborn").getResource("Board.yml");
		if (defConfigStream != null)
		{
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			if (!this.boardFile.exists() || (this.boardFile.length() == 0))
				this.board.setDefaults(defConfig);
		}
	}
	
	/**
	 * Get the config
	 */
	public void reloadConfig() {
		InfoBoard.me.reloadConfig();
	}
	
	/**
	 * Reload variables file
	 */
	public void reloadVariables() {
		if (this.variableFile == null)
			this.variableFile = new File(
					Bukkit.getPluginManager().getPlugin("InfoBoardReborn").getDataFolder(), "Variables.yml");
		this.variable = YamlConfiguration.loadConfiguration(this.variableFile);
		// Look for defaults in the jar
		InputStream defConfigStream = Bukkit.getPluginManager().getPlugin("InfoBoardReborn").getResource("Variables.yml");
		if (defConfigStream != null)
		{
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			this.variable.setDefaults(defConfig);
		}
	}
	
	/**
	 * Save Board file
	 */
	public void saveBoard() {
		if ((this.board == null) || (this.boardFile == null))
			return;
		try
		{
			getBoard().save(this.boardFile);
		}
		catch (IOException ex)
		{
			Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + this.boardFile, ex);
		}
	}
	
	/**
	 * Save Config
	 */
	public void saveConfig() {
		InfoBoard.me.saveConfig();
	}
	
	/**
	 * Save Variables file
	 */
	public void saveVariables() {
		if ((this.variable == null) || (this.variableFile == null))
			return;
		try
		{
			getVariables().save(this.variableFile);
		}
		catch (IOException ex)
		{
			Bukkit.getLogger().log(Level.SEVERE, "Could not save config " + this.variableFile, ex);
		}
	}
	
}
