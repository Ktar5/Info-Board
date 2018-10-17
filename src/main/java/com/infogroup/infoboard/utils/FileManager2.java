package com.infogroup.infoboard.utils;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

public class FileManager2 {

    private InfoBoardReborn plugin;

    private HashMap<File, FileConfiguration> files;


    public FileManager2(InfoBoardReborn plugin) {
        this.plugin = plugin;
    }


    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        //Default files
        File boardFile = new File(plugin.getDataFolder() + File.separator + "boards", "board.yml");

        if (!boardFile.exists()) {
            try {
                boardFile.createNewFile();
                copy(plugin.getResource("board.yml"), boardFile);
                if (plugin.getSettings().debug()) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(ChatColor.GREEN + "The board.yml file has been created");
                }
            } catch (IOException ex) {
                if (plugin.getSettings().debug()) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(ChatColor.RED + "Could not create the board.yml file");
                    ex.printStackTrace();
                }
            }
        }
        FileConfiguration board = YamlConfiguration.loadConfiguration(boardFile);
        files.put(boardFile, board);

        // Variables file
        File variableFile = new File(plugin.getDataFolder(), "variables.yml");
        if (!variableFile.exists()) {
            try {
                variableFile.createNewFile();
                copy(plugin.getResource("variables.yml"), variableFile);
                if (plugin.getSettings().debug()) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(ChatColor.GREEN + "The variables.yml file has been created");
                }
            } catch (IOException ex) {
                if (plugin.getSettings().debug()) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(ChatColor.RED + "Could not create the variables.yml file");
                    ex.printStackTrace();
                }
            }
        }
        FileConfiguration variable = YamlConfiguration.loadConfiguration(variableFile);
        files.put(variableFile, variable);

        //Messages file
        File messagesFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            try {
                messagesFile.createNewFile();
                copy(plugin.getResource("messages.yml"), messagesFile);
                if (plugin.getSettings().debug()) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(ChatColor.GREEN + "The messages.yml file has been created");
                }
            } catch (IOException ex) {
                if (plugin.getSettings().debug()) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(ChatColor.RED + "Could not create the messages.yml file");
                    ex.printStackTrace();
                }
            }
        }
        FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesFile);
        files.put(messagesFile, messages);

        //Config file
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }


    /**
     * @param file
     * @return
     */
    public FileConfiguration getFile(String file) {
        if (Objects.equals(file, "config")) {
            return plugin.getConfig();
        }
        for (File f : files.keySet()) {
            if (f.getName().equalsIgnoreCase(file + ".yml")) {
                if (plugin.getSettings().debug()) {
                    Bukkit.getServer().getConsoleSender()
                            .sendMessage(ChatColor.AQUA + "The " + file + ".yml file has been saved");
                }
                return files.get(f);
            }
        }
        if (plugin.getSettings().debug()) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Unable to get file: " + file);
        }
        return null;
    }

    /**
     * @param file
     */
    public void saveFile(String file) {
        if (Objects.equals(file, "config")) {
            plugin.saveConfig();
        }
        for (File f : files.keySet()) {
            if (f.getName().equalsIgnoreCase(file + ".yml")) {
                FileConfiguration fileconfg = files.get(f);
                try {
                    fileconfg = YamlConfiguration.loadConfiguration(f);
                    files.replace(f, files.get(f), fileconfg);
                } catch (Exception ex) {
                    if (plugin.getSettings().debug()) {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not save file: " + file + " because:");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @param file
     */
    public void reloadFile(String file) {
        if (Objects.equals(file, "config")) {
            plugin.reloadConfig();
        }
        for (File f : this.files.keySet()) {
            if (f.getName().equalsIgnoreCase(file + ".yml")) {
                try {
                    files.get(f).save(f);
                } catch (IOException ex) {
                    if (plugin.getSettings().debug()) {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Could not reload file: " + file + " because:");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * import all files located in "InfoBoard\boards"
     */
    public void importFiles() {
        if (this.files.size() < new File(plugin.getDataFolder() + File.separator + "boards").listFiles().length) {
            this.files.clear();
            for (File file : new File(plugin.getDataFolder() + File.separator + "boards").listFiles()) {
                FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
                this.files.put(file, fileConfig);
            }
        }
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("Could not copy " + file.getName() + "because: ");
            ex.printStackTrace();
        }
    }
}
