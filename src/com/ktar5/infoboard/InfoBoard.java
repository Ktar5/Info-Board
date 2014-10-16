
package com.ktar5.infoboard;

import java.io.IOException;
import java.util.ArrayList;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import com.ktar5.infoboard.API.Vault;
import com.ktar5.infoboard.Util.FileManager;
import com.ktar5.infoboard.Util.Metrics;
import com.ktar5.infoboard.Util.Updater;
import com.ktar5.infoboard.Util.VaraibleUtils.Lag;


public class InfoBoard extends JavaPlugin {
	
	public static Plugin						me;
	public boolean									update		= false;
	public String										name			= "InfoBoard";
	
	public String										ib				= "" + ChatColor.RED + ChatColor.BOLD + "➳" + ChatColor.GRAY;
	
	public static Economy						economy;
	public static Permission				permission;
	public static boolean						economyB;
	public static boolean						permissionB;
	private static Timers						timers;
	private static FileManager			fileManager;
	
	public static ArrayList<String>	hidefrom	= new ArrayList<String>();
	
	/**
	 * @return the fileManager
	 */
	public static FileManager getFileManager() {
		return InfoBoard.fileManager;
	}
	
	/**
	 * @return the timer
	 */
	public static Timers getTimers() {
		return InfoBoard.timers;
	}
	
	/**
	 * Check for updates
	 */
	private void checkUpdates() {
		if (getFileManager().getConfig().getBoolean("Check for Updates"))
		{
			try
			{
				Updater updater = new Updater(this, 65787, getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
				
				this.update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
				this.name = updater.getLatestName();
				
			}
			catch (Exception ex)
			{
				System.out.println("The auto-updater tried to contact dev.bukkit.org, but was unsuccessful.");
			}
			if (this.update)
				System.out.println("Theres a new update for InfoBoard(" + this.name + ").");
		}
		
	}
	
	/**
	 * Load Metrics
	 */
	private void loadMetrics() {
		try
		{
			Metrics metrics = new Metrics(this);
			metrics.start();
			System.out.println("Metrics was started!");
		}
		catch (IOException e)
		{
			System.out.println("Metrics was unable to start...");
		}
	}
	
	@Override
	public void onDisable() {
		Bukkit.getScheduler().cancelTasks(this);
		for (Player player : Bukkit.getOnlinePlayers())
			if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null)
				if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard"))
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
	}
	
	@Override
	public void onEnable() {
		InfoBoard.me = this;
		InfoBoard.fileManager = new FileManager();
		
		InfoBoard.timers = new Timers();
		getTimers().start();
		
		Vault.load();
		loadMetrics();
		checkUpdates();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
		getCommand("InfoBoard").setExecutor(new Commands(this));
		
		// Start TPS
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
		
	}
	
}
