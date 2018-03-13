package com.infogroup.infoboard;

import com.infogroup.infoboard.changeable.ChangeableText;
import com.infogroup.infoboard.condition.ConditionText;
import com.infogroup.infoboard.scoreboard.Create;
import com.infogroup.infoboard.scoreboard.Update;
import com.infogroup.infoboard.scroll.ScrollText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Timers {
	private int showtime;
	private int time;
	private int rotation;

	private InfoBoardReborn plugin;

    public Timers(InfoBoardReborn pl) {
		this.plugin = pl;
    }

    public Timers() {
        time = 0;
        rotation = 1;
        showtime = plugin.getFm().getFile("board").getInt("InfoBoard." + rotation + ".ShowTime");

    }

	/**
	 * @return
	 */
	public int getPage() {
		return rotation;
	}

	/**
	 * @param page
	 */
	public void setPage(int page) {
		rotation = page;
		time = -1;
		showtime = plugin.getFm().getFile("board").getInt("InfoBoard." + rotation + ".ShowTime");
	}

	public void start() {
		/*
		 * =========================================================================
		 * PAGE ROTATION
		 * =========================================================================
		 */
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (time >= showtime) {
                setPage(getPage() + 1);

                if (showtime == 0) {
                    setPage(1);
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("ibr.view")) {
                        Create.createScoreBoard(p);
                    }
                }
            }
            time++;
        }, 0, 20);

		/*
		 * =========================================================================
		 * UPDATES BOARD'S VALUE
		 * =========================================================================
		 */
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("ibr.View")) {
                    Update.updateScoreBoard(p);
                }
            }
        }, 0, (long) plugin.getFm().getFile("config").getDouble("Update Time.Lines") * 20);
		/*
		 * =========================================================================
		 * UPDATES BOARD TITLE VALUE
		 * =========================================================================
		 */
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            // TODO finish
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("ibr.View")) {
                    Update.updateTitle(p);
                }
            }

        }, 0, (long) plugin.getFm().getFile("config").getDouble("Update Time.Title") * 20);

		/*
		 * =========================================================================
		 * SCROLLING TEXT
		 * =========================================================================
		 */
		if (plugin.getSettings().scrollingEnabled()) {
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("ibr.view")) {
                        ScrollText.scroll(p);
                    }
                }

            }, 0, (long) (plugin.getFm().getFile("config").getDouble("Scrolling Text.Shift Time") * 20));
		}

		/*
		 * =========================================================================
		 * UPDATE TIMER
		 * =========================================================================
		 */
		if (plugin.getSettings().updater()) {
			Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                plugin.logger.info("Checking for updates...");
                try {
                    plugin.getUC().checkUpdate(plugin.pdfFile.getVersion());
                } catch (Exception ex) {
                    plugin.logger.warning("Failed to check for updates, because: " + ex);
                    ex.printStackTrace();
                }
            }, 0, 3600 * 20);
		}
        /*
         * =========================================================================
         * Changeable timer
         * =========================================================================
         */
        if(plugin.getSettings().changeableTextEnabled()){
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                for(Player p : Bukkit.getOnlinePlayers()){
                	ChangeableText.change(p);
                }
            },0, 1);
        }
        /*
         * =========================================================================
         * Condition timer
         * =========================================================================
         */
        if(plugin.getSettings().conditionsEnabled()){
            //TODO TEST
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                for(Player p : Bukkit.getOnlinePlayers()){
                    ConditionText.change(p);
                }
            },0, 1);
        }

	}

	public void stop() {
		time = 0;
		rotation = 1;
		showtime = plugin.getFm().getFile("board").getInt("InfoBoard." + String.valueOf(rotation) + ".ShowTime");
		Bukkit.getScheduler().cancelTasks(plugin);
	}

	public void reset() {
		time = 0;
		rotation = 1;
		showtime = plugin.getFm().getFile("board").getInt("InfoBoard." + String.valueOf(rotation) + ".ShowTime");

		Bukkit.getScheduler().cancelTasks(plugin);
		start();
	}
}
