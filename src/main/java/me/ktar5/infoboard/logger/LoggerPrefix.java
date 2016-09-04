package me.ktar5.infoboard.logger;

import org.bukkit.ChatColor;

/**
 * An enumeration constant for the prefix on plugin logging.
 * Created by Carter on 4/26/2015.
 */
public enum LoggerPrefix {

    /**
     * A generic informational output. No prefix.
     */
    INFO,

    /**
     * A warning: something has gone wrong but the system can continue.
     */
    WARN,

    /**
     * An error: a system has failed or malfunctioned, but the system will attempt to continue.
     */
    ERROR,

    /**
     * A fatal error: the system must halt and cease continuing.
     */
    FATAL,

    /**
     * A debug message. This will only appear when debug mode is enabled.
     */
    DEBUG,

    /**
     * A config message. This will only appear when debug mode is enabled.
     */
    CONFIG;

    public String toString() {
        switch (this) {
            case INFO:
                return "";
            case WARN:
                return ChatColor.YELLOW + "WARN ";
            case ERROR:
                return ChatColor.RED + "ERROR ";
            case FATAL:
                return ChatColor.DARK_RED + "FATAL ";
            case DEBUG:
                return ChatColor.LIGHT_PURPLE + "DEBUG ";
            case CONFIG:
                return ChatColor.AQUA + "CONFIG ";
        }
        return "";
    }

    public boolean requiresDebugMode() {
        return (this == DEBUG || this == CONFIG);
    }
}
