package com.infogroup.infoboard.utils;

import com.infogroup.infoboard.InfoBoardReborn;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.Objects;

public class FileManager {
    private InfoBoardReborn plugin;

    // Files & configs
    private FileConfiguration board;
    private FileConfiguration variable;
    private FileConfiguration messages;

    private File boardFile;
    private File variableFile;
    private File messagesFile;

    public FileManager(InfoBoardReborn plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }
        boardFile = new File(plugin.getDataFolder() + "/boards/", "board.yml");
        variableFile = new File(plugin.getDataFolder(), "variables.yml");
        messagesFile = new File(plugin.getDataFolder(), "messages.yml");


		/*
         * Checking if board.yml exists creating it if not
		 */
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
                            .sendMessage(ChatColor.RED + "Could not create the board.yml file" + ex);
                    ex.printStackTrace();
                }
            }
        }
        board = YamlConfiguration.loadConfiguration(boardFile);
		/*
		 * Checking if variables.yml exists creating it if not
		 */
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
                            .sendMessage(ChatColor.RED + "Could not create the variables.yml file" + ex);
                    ex.printStackTrace();
                }
            }
        }
        variable = YamlConfiguration.loadConfiguration(variableFile);

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
                            .sendMessage(ChatColor.RED + "Could not create the messages.yml file" + ex);
                    ex.printStackTrace();
                }
            }
        }
        messages = YamlConfiguration.loadConfiguration(messagesFile);
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }

    /*
     * saveBoard -> saveFile(board) _______________________________________
     * saveMessages -> saveFile(messages) _________________________________
     * saveVariable -> saveFile(variable)__________________________________
     * saveConfig -> saveFile(config)______________________________________
     */
    public void saveFile(String s) {
        try {
            if (Objects.equals(s, "board")) {
                board.save(boardFile);
            } else if (Objects.equals(s, "variable")) {
                variable.save(variableFile);
            } else if (Objects.equals(s, "messages")) {
                messages.save(messagesFile);
            } else if (Objects.equals(s, "config")) {
                plugin.saveConfig();
            }

            if (plugin.getSettings().debug()) {
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.AQUA + "The " + s + ".yml file has been saved");
            }
        } catch (IOException ex) {
            if (plugin.getSettings().debug()) {
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.RED + "Could not save the " + s + ".yml file" + ex);
                ex.printStackTrace();
            }
        }
    }

    /*
     * reloadBoard -> reloadFile(board) ___________________________________
     * reloadMessages -> reloadFile(messages) _____________________________
     * reloadVariable -> reloadFile(variable)______________________________
     * reloadConfig -> reloadFile(config)__________________________________
     */
    public void reloadFile(String s) {
        if (Objects.equals(s, "board")) {
            board = YamlConfiguration.loadConfiguration(boardFile);
        } else if (Objects.equals(s, "config")) {
            plugin.reloadConfig();
        } else if (Objects.equals(s, "messages")) {
            messages = YamlConfiguration.loadConfiguration(messagesFile);
        } else if (Objects.equals(s, "variable")) {
            variable = YamlConfiguration.loadConfiguration(variableFile);
        }
    }

    /*
     * getBoard -> getFile(board) _________________________________________
     * getMessages -> getFile(messages) ___________________________________
     * getVariable -> getFile(variable)____________________________________
     * getConfig -> getFile(config)________________________________________
     */
    public FileConfiguration getFile(String s) {
        if (Objects.equals(s,"board")) {
            return board;
        } else if (Objects.equals(s, "config")) {
            return plugin.getConfig();
        } else if (Objects.equals(s, "messages")) {
            return messages;
        } else if (Objects.equals(s, "variable")) {
            return variable;
        } else {
            return null;
        }

    }

    /**
     *
     */
    private void loadBoards() {
        String path = plugin.getDataFolder() + "/boards/";
        for (File file : new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "boards").listFiles()) {
            FileConfiguration name = YamlConfiguration.loadConfiguration(file);

        }
    }

    /**
     * Delete given fle
     * @param s
     * @return
     */
    public boolean deleteFile(String s){
        switch (s){
            case "board":
                boardFile.delete();
                return true;
            case "messages":
                messagesFile.delete();
                return true;
            case "variables":
                variableFile.delete();
                return true;
            case "config":
                File config = new File(plugin.getDataFolder(), "config.yml");
                config.delete();
                return true;
            default:
                return false;
        }
    }

    /**
     * @param in
     * @param file
     */
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
            Bukkit.getConsoleSender().sendMessage("Could not copy " + file.getName()+ "because: ex");
            ex.printStackTrace();
        }
    }
}
