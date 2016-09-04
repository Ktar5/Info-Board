package me.ktar5.infoboard.logger;

import me.ktar5.infoboard.InfoBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * A more advanced logging system for plugins.
 * Created by Carter on 4/26/2015.
 */
public class Logger {

    private ChatColor defaultColor;
    private boolean debugEnabled = false, tagEnabled = true;

    /**
     * Builds a new console under the specified plugin with a default color of WHITE.
     */
    public Logger() {
        this(ChatColor.WHITE);
    }

    /**
     * Builds a new console under the specified plugin and default color.
     *
     * @param defaultColor The color.
     */
    public Logger(ChatColor defaultColor) {
        this.defaultColor = defaultColor;
    }

    /**
     * Gets the current default color of this console.
     *
     * @return The color.
     */
    public ChatColor getDefaultColor() {
        return defaultColor;
    }

    /**
     * Sets the current default color of this console.
     *
     * @param defaultColor The color.
     */
    public void setDefaultColor(ChatColor defaultColor) {
        this.defaultColor = defaultColor;
    }

    /**
     * Determines whether debug mode is enabled or not.
     *
     * @return True if enabled, false if not.
     */
    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    /**
     * Enables or disables debug mode.
     *
     * @param debugEnabled True to enable, false to disable.
     */
    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    /**
     * Determines whether the prefix tag is enabled.
     *
     * @return True if enabled, false if not.
     */
    public boolean isTagEnabled() {
        return tagEnabled;
    }

    /**
     * Enables or disabled the prefix tag.
     *
     * @param tagEnabled True to enable, false to disable.
     */
    public void setTagEnabled(boolean tagEnabled) {
        this.tagEnabled = tagEnabled;
    }

    private String getMessage(LoggerPrefix prefix, String message) {
        if (prefix.requiresDebugMode() && !debugEnabled) return null;
        StringBuilder builder = new StringBuilder();

        if (tagEnabled) builder.append(defaultColor).append('[')
                .append(InfoBoard.instance().getDescription().getPrefix() != null
                        ? InfoBoard.instance().getDescription().getPrefix()
                        : InfoBoard.instance().getName())
                .append("]: ");
        builder.append(prefix.toString()).append(defaultColor).append(ChatColor.translateAlternateColorCodes('&', message));

        return builder.toString();
    }

    /**
     * Logs a message to the console. If debug mode is not enabled, DEBUG and CONFIG level messages will not output.
     * If the prefix tag is enabled, the plug-in's name precedes the output.
     *
     * @param prefix  The prefix.
     * @param message The message.
     */
    public void log(LoggerPrefix prefix, String message) {
        String msg = getMessage(prefix, message);
        if (msg != null) Bukkit.getConsoleSender().sendMessage(msg);
    }

    /**
     * Logs an info message to the console.
     *
     * @param message The message.
     */
    public void info(String message) {
        log(LoggerPrefix.INFO, message);
    }

    /**
     * Logs a warning to the console.
     *
     * @param message The warning message.
     */
    public void warn(String message) {
        log(LoggerPrefix.WARN, message);
    }

    /**
     * Logs an error to the console.
     *
     * @param message The error message.
     */
    public void error(String message) {
        log(LoggerPrefix.ERROR, message);
    }

    /**
     * Logs a fatal error to the console.
     *
     * @param message The fatal message.
     */
    public void fatal(String message) {
        log(LoggerPrefix.FATAL, message);
    }

    /**
     * Logs a debug message to the console if debug mode is enabled.
     *
     * @param message The message.
     */
    public void debug(String message) {
        log(LoggerPrefix.DEBUG, message);
    }

    /**
     * Logs a config message to the console if debug mode is enabled.
     *
     * @param message The message.
     */
    public void config(String message) {
        log(LoggerPrefix.CONFIG, message);
    }

}
