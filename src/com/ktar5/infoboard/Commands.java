package com.ktar5.infoboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import com.ktar5.infoboard.Scoreboard.Create;
import com.ktar5.infoboard.Scroll.ScrollManager;

public class Commands implements CommandExecutor {

	InfoBoard plugin;

	public Commands(InfoBoard plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("InfoBoard"))
			if (args.length > 0) {
				sender.sendMessage("");
				// ====================================================================================
				// HIDE =====================================
				if (args[0].equalsIgnoreCase("Hide")) {
					if (!sender.hasPermission("InfoBoard.Toggle"))
						sender.sendMessage(this.plugin.ib
								+ "Invalid Permissions.");
					else if (!(sender instanceof Player))
						sender.sendMessage(this.plugin.ib + "Expected a player");
					else if (InfoBoard.hidefrom.contains(sender.getName()))
						sender.sendMessage(this.plugin.ib + "Already hidden");

					else {
						InfoBoard.hidefrom.add(sender.getName());
						sender.sendMessage(this.plugin.ib
								+ "Hiding Info Board.");
						((Player) sender).getScoreboard().clearSlot(
								DisplaySlot.SIDEBAR);
					}
				}
				// ====================================================================================
				// SHOW =====================================
				else if (args[0].equalsIgnoreCase("Show")) {
					if (!sender.hasPermission("InfoBoard.Toggle"))
						sender.sendMessage(this.plugin.ib
								+ "Invalid Permissions.");
					else if (!(sender instanceof Player))
						sender.sendMessage(this.plugin.ib + "Expected a player");
					else if (!InfoBoard.hidefrom.contains(sender.getName()))
						sender.sendMessage(this.plugin.ib + "Not hidden");

					else {
						InfoBoard.hidefrom.remove(sender.getName());
						sender.sendMessage(this.plugin.ib
								+ "Showing Info Board.");
					}
				}
				// ====================================================================================
				// SET =====================================
				else if (args[0].equalsIgnoreCase("Set")) {
					if (!sender.hasPermission("InfoBoard.Set"))
						sender.sendMessage(this.plugin.ib
								+ "Invalid Permissions.");

					else if (args.length == 2) {
						String rotate = args[1];

						if (InfoBoard.getFileManager().getBoard()
								.getInt("Info Board." + rotate + ".Show Time") != 0) {

							InfoBoard.getTimers().setPage(
									Integer.valueOf(args[1]));
							sender.sendMessage("");
							sender.sendMessage(this.plugin.ib
									+ "Rotation set to: " + args[1]);
							for (Player p : Bukkit.getOnlinePlayers())
								if (p.hasPermission("InfoBoard.View"))
									Create.createScoreBoard(p);
						} else
							sender.sendMessage(this.plugin.ib
									+ "Page not found: " + args[1]);

					}
				}
				// ====================================================================================
				// RELOAD =====================================
				else if (args[0].equalsIgnoreCase("Reload"))
					if (!sender.hasPermission("InfoBoard.Reload"))
						sender.sendMessage(this.plugin.ib
								+ "Invalid Permissions.");

					else {
						sender.sendMessage(this.plugin.ib + ChatColor.GREEN
								+ "Configs been reloaded");
						Bukkit.getScheduler().cancelTasks(InfoBoard.me);
						for (Player player : Bukkit.getOnlinePlayers())
							ScrollManager.reset(player);

						InfoBoard.getFileManager().reloadBoard();
						InfoBoard.getFileManager().reloadConfig();

						InfoBoard.getTimers().reset();
						for (Player player : Bukkit.getOnlinePlayers())
							if (player.hasPermission("InfoBoard.View"))
								Create.createScoreBoard(player);

					}

			}
			// ====================================================================================
			// HELP =====================================
			else {
				sender.sendMessage("");
				sender.sendMessage(ChatColor.GOLD + ""
						+ ChatColor.STRIKETHROUGH + "============["
						+ ChatColor.DARK_AQUA + ChatColor.BOLD + " Info Board "
						+ ChatColor.ITALIC + " v"
						+ this.plugin.getDescription().getVersion()
						+ ChatColor.GOLD + " " + ChatColor.STRIKETHROUGH
						+ "]============");
				sender.sendMessage(this.plugin.ib + "/IB Hide      "
						+ ChatColor.YELLOW + "- Hide the board");
				sender.sendMessage(this.plugin.ib + "/IB Show     "
						+ ChatColor.YELLOW + "- Show the board");
				sender.sendMessage(this.plugin.ib + "/IB Reload   "
						+ ChatColor.YELLOW + "- Reload the config");
				sender.sendMessage(this.plugin.ib + "/IB Set <Pg> "
						+ ChatColor.YELLOW + "- Set the page to view");
				sender.sendMessage(this.plugin.ib + ChatColor.GOLD
						+ ChatColor.STRIKETHROUGH
						+ "--------------------------------------------");
				sender.sendMessage(this.plugin.ib + ChatColor.DARK_AQUA
						+ ChatColor.BOLD + "Author: " + ChatColor.WHITE
						+ ChatColor.BOLD + "SniperzCiinema");
				sender.sendMessage(this.plugin.ib + ChatColor.DARK_AQUA
						+ ChatColor.BOLD + "BukkitDev: " + ChatColor.WHITE
						+ ChatColor.BOLD + "http://bit.ly/Info-Board");

				sender.sendMessage(this.plugin.ib + ChatColor.GOLD
						+ ChatColor.STRIKETHROUGH
						+ "--------------------------------------------");
				if (this.plugin.update) {
					sender.sendMessage(ChatColor.DARK_GREEN
							+ "Theres a new update for InfoBoard"
							+ ChatColor.DARK_AQUA + "( " + ChatColor.YELLOW
							+ "" + ChatColor.ITALIC + this.plugin.name
							+ ChatColor.DARK_AQUA + " ).");
					sender.sendMessage(this.plugin.ib + ChatColor.GOLD
							+ ChatColor.STRIKETHROUGH
							+ "--------------------------------------------");
				}

			}
		return true;
	}

}
