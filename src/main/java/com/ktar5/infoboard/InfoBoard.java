package com.ktar5.infoboard;

import com.ktar5.infoboard.API.Vault;
import com.ktar5.infoboard.utils.FileManager;
import com.ktar5.infoboard.utils.Metrics;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import java.io.IOException;
import java.util.ArrayList;

public class InfoBoard extends JavaPlugin {

    public static Plugin instance;
    public static Economy economy;
    public static Permission permission;
    public static boolean economyB;
    public static boolean permissionB;
    public static ArrayList<String> hidefrom = new ArrayList<String>();
    private static Timers timers;
    private static FileManager fileManager;
    public boolean update = false;
    public String name = "InfoBoard";
    public String ib = "" + ChatColor.RED + ChatColor.BOLD + "➳"
            + ChatColor.GRAY;

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
     * Load Metrics
     */
    private void loadMetrics() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
            System.out.println("Metrics was started!");
        } catch (IOException e) {
            System.out.println("Metrics was unable to start...");
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        for (Player player : Bukkit.getOnlinePlayers())
            if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null)
                if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR)
                        .getName().equalsIgnoreCase("InfoBoard"))
                    player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);

        instance = null;
    }

    @Override
    public void onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!");
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            throw new RuntimeException("Could not find WorldGuard!! Plugin can not work without it!");
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            throw new RuntimeException("Could not find Vault!! Plugin can not work without it!");
        }
        InfoBoard.instance = this;
        InfoBoard.fileManager = new FileManager();

        InfoBoard.timers = new Timers();
        getTimers().start();

        Vault.load();
        loadMetrics();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
        getCommand("InfoBoard").setExecutor(new Commands(this));

    }

}
