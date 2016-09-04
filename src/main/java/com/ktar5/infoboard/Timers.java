package com.ktar5.infoboard;

import com.ktar5.infoboard.scoreboard.Create;
import com.ktar5.infoboard.scoreboard.Update;
import com.ktar5.infoboard.scroll.ScrollText;
import com.ktar5.infoboard.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Timers {

    private int showTime, time, rotation;

    public Timers() {
        time = 0;
        rotation = 1;
        showTime = InfoBoard.getFileManager().getBoard()
                .getInt("Info Board." + rotation + ".Show Time");
    }

    /**
     * Get the current page
     *
     * @return page
     */
    public int getPage() {
        return rotation;
    }

    /**
     * Manually set the page showing
     *
     * @param page
     */
    public void setPage(int page) {
        rotation = page;
        time = -1;
        showTime = InfoBoard
                .getFileManager()
                .getBoard()
                .getInt("Info Board." + String.valueOf(rotation)
                        + ".Show Time");
    }

    /**
     * Reset timers back to default
     */
    public void reset() {
        time = 0;
        rotation = 1;
        showTime = InfoBoard
                .getFileManager()
                .getBoard()
                .getInt("Info Board." + String.valueOf(rotation)
                        + ".Show Time");

        Bukkit.getScheduler().cancelTasks(InfoBoard.instance);
        start();
    }

    /**
     * Start all the timers
     */
    public void start() {

        // ============================================ PAGE ROTATION
        // =====================================================
        Bukkit.getServer().getScheduler()
                .scheduleSyncRepeatingTask(InfoBoard.instance, new Runnable() {

                    @Override
                    public void run() {
                        if (time >= showTime) {
                            setPage(getPage() + 1);

                            if (showTime == 0)
                                setPage(1);

                            // Set scoreboard of current InfoBoard.rotation
                            for (Player p : Bukkit.getOnlinePlayers())
                                if (p.hasPermission("InfoBoard.View"))
                                    Create.createScoreBoard(p);
                        }

                        // Add one to the timer
                        time++;
                    }
                }, 0, 20L);

        // =================================================== UPDATE BOARD'S
        // VALUE ==========================================================
        Bukkit.getServer()
                .getScheduler()
                .scheduleSyncRepeatingTask(
                        InfoBoard.instance,
                        new Runnable() {

                            @Override
                            public void run() {

                                for (Player p : Bukkit.getOnlinePlayers())
                                    if (p.hasPermission("InfoBoard.View"))
                                        Update.updateScoreBoard(p);


                            }
                        },
                        0,
                        (long) InfoBoard.getFileManager().getConfig()
                                .getDouble("Update Time") * 20);

        // ===================================================== SCROLLING TEXT
        // ===============================================================
        if (Settings.scrollingEnabled())
            Bukkit.getServer()
                    .getScheduler()
                    .scheduleSyncRepeatingTask(
                            InfoBoard.instance,
                            new Runnable() {

                                @Override
                                public void run() {
                                    for (Player p : Bukkit.getOnlinePlayers())
                                        if (p.hasPermission("InfoBoard.View"))
                                            ScrollText.scroll(p);
                                }
                            },
                            0,
                            (long) (InfoBoard.getFileManager().getConfig()
                                    .getDouble("Scrolling Text.Shift Time") * 20));
    }

    public void stop() {
        time = 0;
        rotation = 1;
        showTime = InfoBoard
                .getFileManager()
                .getBoard()
                .getInt("Info Board." + String.valueOf(rotation)
                        + ".Show Time");

        Bukkit.getScheduler().cancelTasks(InfoBoard.instance);
    }
}
