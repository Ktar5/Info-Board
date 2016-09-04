package com.ktar5.infoboard;

import com.ktar5.infoboard.utils.Messages;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GetVariables {

    public static String replaceVariables(String string, Player player) {
        String newString = PlaceholderAPI.setPlaceholders(player, string);
        Bukkit.broadcastMessage(newString);

        // Custom Variables
        for (String custom : InfoBoard.getFileManager().getConfig().getConfigurationSection("Custom Variables").getKeys(true)) {
            if (newString.contains(custom)) {
                newString = newString.replaceAll(
                        custom,
                        Messages.getColored(InfoBoard.getFileManager()
                                .getConfig()
                                .getString("Custom Variables." + custom)));
            }
        }

        return newString;
    }
}
