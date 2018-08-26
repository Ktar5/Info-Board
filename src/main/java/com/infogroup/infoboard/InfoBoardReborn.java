package com.infogroup.infoboard;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.infogroup.infoboard.api.API;
import com.infogroup.infoboard.api.Vault;
import com.infogroup.infoboard.api.WorldGuard;
import com.infogroup.infoboard.changeable.ChangeableManager;
import com.infogroup.infoboard.condition.ConditionManager;
import com.infogroup.infoboard.events.ChangeWorld;
import com.infogroup.infoboard.events.Damage;
import com.infogroup.infoboard.events.PlayerJoin;
import com.infogroup.infoboard.scroll.ScrollManager;
import com.infogroup.infoboard.utils.*;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class InfoBoardReborn extends JavaPlugin {

    public boolean update = false;
    public ArrayList<String> hidefrom = new ArrayList<>();
    public Economy economy;
    public Permission permission;
    public boolean economyB;
    public boolean permissionB;
    PluginDescriptionFile pdfFile = getDescription();
    Logger logger = getLogger();

    private UpdateChecker UC;
    private Timers timers;
    private FileManager fm;
    private BoardManager bm;
    private Settings settings;
    private Messages msgs;
    private ChangeableManager CHM;
    private ScrollManager SM;
    private ConditionManager CM;
    private WorldGuard WG;
    private Vault V;
    private ProtocolManager PM;
    private API api;


    public void onEnable() {

        saveDefaultConfig();
        dependencies();
        this.Instance();

        loadMetrics();

        // events
        registerEvents();

        // commands
        getCommand("InfoBoardReborn").setExecutor(new Commands(this));

        if (settings.changeableTextEnabled()) {
            logger.info("Feature: Changeable Text is enabled!");
            logger.info(settings.getChangeable().size() + " changeable(s) loaded.");
        }
        if (settings.conditionsEnabled()){
            logger.info("Feature: Condition is enabled!");
            logger.info(settings.getConditions().size() + " condition(s) loaded.");
        }
        if (settings.scrollingEnabled()) {
            logger.info("Feature: Scrolling is enabled!");
        }
        logger.info(pdfFile.getName() + " has been enabled (V." + pdfFile.getVersion() + ")");

    }

    public void onDisable() {
        timers.stop();
        Bukkit.getScheduler().cancelTasks(this);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null) {
                if (player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).getName().equalsIgnoreCase("InfoBoard")) {
                    player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
                }
            }
        }
        logger.info(pdfFile.getName() + " has been disabled (V." + pdfFile.getVersion() + ")");

    }

    /**
     * register events
     */
    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new ChangeWorld(this), this);
        pm.registerEvents(new PlayerJoin(this), this);
        pm.registerEvents(new Damage(this), this);
    }

    /**
     * Load the metrics system
     */
    private void loadMetrics() {
        Metrics metrics = new Metrics(this);
        metrics.addCustomChart(new Metrics.SimpleBarChart("features", () -> {
            Map<String, Integer> map = new HashMap<>();
            if (settings.changeableTextEnabled()) {
                map.put("Changeable", 1);
            }
            if (settings.scrollingEnabled()) {
                map.put("Scroll", 1);
            }
            if (settings.conditionsEnabled()) {
                map.put("Condition", 1);
            }
            return map;
        }));
    }

    /**
     * Instance all classes that are necessary
     */
    private void Instance() {
        this.fm = new FileManager(this);
        this.settings = new Settings(this);
        fm.setup();
        this.msgs = new Messages(this);
        this.timers = new Timers(this);
        this.CHM = new ChangeableManager(this);
        this.CM = new ConditionManager(this);
        this.SM = new ScrollManager(this);
        this.V = new Vault(this);
        this.WG = new WorldGuard(this);
        this.UC = new UpdateChecker(this);
        this.api = new API(this);
        this.bm = new BoardManager(this);
        if(Bukkit.getServer().getPluginManager().getPlugin("ProtocolLib")!= null){
            this.PM = ProtocolLibrary.getProtocolManager();
        }

        timers.start();
        V.load();
    }

    /**
     * Check for all dependencies
     */
    private void dependencies() {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!");
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("WorldGuard")) {
            throw new RuntimeException("Could not find WorldGuard!! Plugin can not work without it!");
        }
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            throw new RuntimeException("Could not find Vault!! Plugin can not work without it!");
        }
    }

    /**
     * Get's Filemanager
     * @return
     */
    public FileManager getFm() { return this.fm; }

    /**
     * Get's Settings
     * @return
     */
    public Settings getSettings() { return this.settings; }

    /**
     * Get's Messages
     * @return
     */
    public Messages getMessages() { return this.msgs; }

    /**
     * Get's ChangeableManager
     * @return
     */
    public ChangeableManager getCHM() { return this.CHM; }

    /**
     * Get's ScrollManager
     * @return
     */
    public ScrollManager getSM() { return this.SM; }

    /**
     * Get's WorldGuard
     * @return
     */
    public WorldGuard getWG() { return this.WG; }

    /**
     * Get's Vault
     * @return
     */
    public Vault getV() { return this.V; }

    /**
     * Get's Timers
     * @return
     */
    public Timers getTimers() { return this.timers; }

    /**
     * Get's UpdateChecker
     * @return
     */
    public UpdateChecker getUC() { return this.UC; }

    /**
     * Get's BoardManager
     * @return
     */
    public BoardManager getBM(){ return this.bm; }

    /**
     * Get's ConditionManager
     * @return
     */
    public ConditionManager getCM(){return this.CM; }

    /**
     * Get's ProtocolManager
     * @return
     */
    public ProtocolManager getPM(){ return this.PM; }

    /**
     * Get's API
     * @return
     */
    public API getAPI(){return this.api;}
}
