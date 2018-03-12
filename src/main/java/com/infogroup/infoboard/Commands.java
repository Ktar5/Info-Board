package com.infogroup.infoboard;

import com.infogroup.infoboard.scoreboard.Create;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.List;
import java.util.Objects;

public class Commands implements CommandExecutor {

	private InfoBoardReborn plugin;

	public Commands(InfoBoardReborn plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("InfoBoardReborn")) {
			if (args.length > 0) {
				// HIDE
				if (args[0].equalsIgnoreCase("Hide")) {
					hideCmd(sender);
				}
				// SHOW
				else if (args[0].equalsIgnoreCase("Show")) {
					showCmd(sender);
				}// TOGGLE
				else if(args[0].equalsIgnoreCase("Toggle")){
                    toggleCmd(sender);
                }
				// SET <PAGE>
				else if (args[0].equalsIgnoreCase("Set")) {
					setCmd(sender, args);
				} else if (args[0].equalsIgnoreCase("Info")) {
					infoCmd(sender);
				}
				// RELOAD [FILE]
				else if (args[0].equalsIgnoreCase("Reload")) {
					if (args.length > 1) {
						if (args[1].equalsIgnoreCase("board")) {
							reloadCmd(sender, "board");
						} else if (args[1].equalsIgnoreCase("config")) {
							reloadCmd(sender, "config");
						} else if (args[1].equalsIgnoreCase("messages")) {
							reloadCmd(sender, "messages");
						} else if (args[1].equalsIgnoreCase("All")) {
							reloadCmd(sender, "all");
						} else {
							reloadCmd(sender, "error");
						}
					} else {
						reloadCmd(sender, "all");
					}
				}
				// CREATE <PAGE> <SHOWTIME>
				else if (args[0].equalsIgnoreCase("Create")) {
					createCmd(sender, args);

				}
				// ADD <PAGE> <WORLD> <RANK> <LINE/TITLE> <WORDS>
				else if (args[0].equalsIgnoreCase("Add")) {
					addCmd(sender, args);
				}
			}

			/*
			 * =============================================================================
			 * HELP
			 * =============================================================================
			 */
			else {
				sender.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "========[" + ChatColor.DARK_AQUA
						+ ChatColor.BOLD + " InfoBoardReborn " + ChatColor.ITALIC + " v"
						+ plugin.getDescription().getVersion() + ChatColor.GOLD + " " + ChatColor.STRIKETHROUGH
						+ "]========");
				sender.sendMessage("/IBR Hide");
				sender.sendMessage(ChatColor.YELLOW + "- Hide the board");
				sender.sendMessage("/IBR Show");
				sender.sendMessage(ChatColor.YELLOW + "- Show the board");
                sender.sendMessage("/IBR Toggle");
                sender.sendMessage(ChatColor.YELLOW + "- Toggle(show/hide) the board");
				sender.sendMessage("/IBR Reload [FILE]");
				sender.sendMessage(ChatColor.YELLOW + "- Reloads the given file");
				sender.sendMessage("/IBR Set <Pg>");
				sender.sendMessage(ChatColor.YELLOW + "- Set the page to view");
				sender.sendMessage("/IBR Create <Pg> <ShowTime>");
				sender.sendMessage(ChatColor.YELLOW + "- Creates a page with showtime");
				sender.sendMessage("/IBR Add <Line/Title> <Pg> <World> <Rank> <Line>");
				sender.sendMessage(ChatColor.YELLOW + "- Adds a line to given page, rank and world");

				sender.sendMessage(
						"" + ChatColor.GOLD + ChatColor.STRIKETHROUGH + "--------------------------------------------");
				sender.sendMessage("" + ChatColor.DARK_AQUA + ChatColor.BOLD + "Authors: " + ChatColor.WHITE
						+ ChatColor.BOLD + "Ktar5 & pixar02");
				sender.sendMessage("" + ChatColor.DARK_AQUA + ChatColor.BOLD + "Websites: " + ChatColor.WHITE
						+ ChatColor.BOLD + plugin.pdfFile.getWebsite());

				sender.sendMessage(
						"" + ChatColor.GOLD + ChatColor.STRIKETHROUGH + "--------------------------------------------");
				if (plugin.update) {
					sender.sendMessage(ChatColor.DARK_GREEN + plugin.getFm().getFile("messages").getString("update"));
					sender.sendMessage("" + ChatColor.GOLD + ChatColor.STRIKETHROUGH
							+ "--------------------------------------------");
				}

			}

		}
		return true;
	}

	/*
	 * =============================================================================
	 * ADD <LINE/TITLE> <PAGE> <WORLD> <RANK> <LINE>
	 * =============================================================================
	 *
	 */
	/**
	 * @param sender
	 * @param args
	 */
	public void addCmd(CommandSender sender, String[] args) {
		if (!(sender.hasPermission("ibr.Create"))) {
			sender.sendMessage(ChatColor.RED + plugin.getFm().getFile("messages").getString("no-permission"));

		} else {
			if (args.length >= 6) {
				String what = args[1];
				String rotation = args[2];
				String world = args[3];
				String rank = args[4];
				StringBuilder lineBuilder = new StringBuilder();
				for (int i = 5; i < args.length; i++) {
					String temp = args[i] + " ";
					lineBuilder.append(temp);
				}
				String line = lineBuilder.toString();
				if (Objects.equals(args[2], "")) {
					world = "global";
				}
				if (Objects.equals(args[3], "")) {
					rank = "default";
				}
				if (what.equalsIgnoreCase("Title")) {
					// check if the Title isn't already set
					if (plugin.getFm().getFile("board")
							.getString("InfoBoard." + rotation + "." + world + "." + rank + ".Title") == null) {
						// Title doesn't exist
						plugin.getFm().getFile("board")
								.set("InfoBoard." + rotation + "." + world + "." + rank + ".Title", line);
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("add-success") + rotation));
						sender.sendMessage("Title: " + line);
						plugin.getFm().saveFile("board");
					} else {
						// Title exists
						sender.sendMessage(
                                ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("title-exists")));
					}
				} else if (what.equalsIgnoreCase("Line")) {
					// check if the maximum of lines are exceeded
					if (plugin.getFm().getFile("board")
							.getStringList("InfoBoard." + rotation + "." + world + "." + rank + ".Rows").size() <= 14) {
						List<String> lines = plugin.getFm().getFile("board")
								.getStringList("InfoBoard." + rotation + "." + world + "." + rank + ".Rows");
						lines.add(line);
						plugin.getFm().getFile("board")
								.set("InfoBoard." + rotation + "." + world + "." + rank + ".Rows", lines);
						plugin.getFm().getFile("board");
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("add-success") + rotation));
						sender.sendMessage("Line: " + line);
						plugin.getFm().saveFile("board");

					} else {
						sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("max-lines")));
					}
				} else {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("wrong-usage")));
					sender.sendMessage("/ibr add <line/title> <page> [world] [rank] <line>");
				}
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("wrong-usage")));
				sender.sendMessage("/ibr add <line/title> <page> <world> <rank> <line>");
			}
		}
	}

	/*
	 * =============================================================================
	 * CREATE <PAGE> <SHOWTIME>
	 * =============================================================================
	 */
	/**
	 * @param sender
	 * @param args
	 */
	public void createCmd(CommandSender sender, String[] args) {
		if (!(sender.hasPermission("ibr.Create"))) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("no-permission")));

		} else {
			if (args.length == 3) {
				String rotation = args[1];
				int ShowTime = Integer.valueOf(args[2]);
				// check if the new created page doesn't already exist
				if (plugin.getFm().getFile("board").getString("InfoBoard." + rotation) != null) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("page-exists")));
				} else {
					// create the new page with the given showtime
					plugin.getFm().getFile("board").set("InfoBoard." + rotation + ".ShowTime", ShowTime);
					plugin.getFm().saveFile("board");
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("create-success")));
				}
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("wrong-usage")));
				sender.sendMessage("/ibr create <Page> <ShowTime>");
			}
		}
	}

	/*
	 * =============================================================================
	 * Set <PAGE>
	 * =============================================================================
	 */
	/**
	 * @param sender
	 * @param args
	 */
	public void setCmd(CommandSender sender, String[] args) {
		if (!sender.hasPermission("ibr.Set")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("no-permission")));
		} else if (args.length == 2) {
			String rotate = args[1];

			if (plugin.getFm().getFile("board").getInt("InfoBoard." + rotate + ".ShowTime") != 0) {

				plugin.getTimers().setPage(Integer.valueOf(rotate));
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("set-page") + args[1]));
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.hasPermission("ibr.View")) {
						Create.createScoreBoard(p);
					}
				}
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("invalid-page") + args[1]));
			}
		} else {
			sender.sendMessage(ChatColor.YELLOW + plugin.getFm().getFile("messages").getString("wrong-usage"));
			sender.sendMessage("/ibr set <page>");
		}

	}

	/*
	 * =============================================================================
	 * SHOW
	 * =============================================================================
	 */
	/**
	 * @param sender
	 */
	public void showCmd(CommandSender sender) {
		if (!sender.hasPermission("ibr.Toggle")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("no-permission")));
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("not-player")));
		} else if (!plugin.hidefrom.contains(sender.getName())) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("already-shown")));

		} else {
			plugin.hidefrom.remove(sender.getName());
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("showing")));
		}
	}

	/*
	 * =============================================================================
	 * HIDE
	 * =============================================================================
	 */
	/**
	 * @param sender
	 */
	public void hideCmd(CommandSender sender) {
		if (!sender.hasPermission("ibr.Toggle")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("no-permission")));
		} else if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("not-player")));
		} else if (plugin.hidefrom.contains(sender.getName())) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("already-hidden")));

		} else {
			plugin.hidefrom.add(sender.getName());
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getFm().getFile("messages").getString("hiding")));
			((Player) sender).getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		}
	}

    /*
     * =============================================================================
     * TOGGLE
     * =============================================================================
     */
	public void toggleCmd(CommandSender sender){
	    if(!sender.hasPermission("ibr.Toggle")){
	        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("no-permission")));
        } else if(!(sender instanceof Player)){
	        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("not-player")));
        }else if(plugin.hidefrom.contains(sender.getName())){
	        plugin.hidefrom.remove(sender.getName());
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("showing")));
        }else{
	        plugin.hidefrom.add(sender.getName());
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("hiding")));
            ((Player) sender).getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        }

    }

	/*
	 * =============================================================================
	 * Info
	 * =============================================================================
	 */
	public void infoCmd(CommandSender sender) {
		String SC;
		if (plugin.getSettings().scrollingEnabled()) {
			SC = String.valueOf(ChatColor.DARK_GREEN + "" + plugin.getSettings().scrollingEnabled());
		} else {
			SC = String.valueOf(ChatColor.DARK_RED + "" + plugin.getSettings().scrollingEnabled());
		}
		String CH;
		if (plugin.getSettings().changeableTextEnabled()) {
			CH = String.valueOf(ChatColor.DARK_GREEN + "" + plugin.getSettings().changeableTextEnabled());
		} else {
			CH = String.valueOf(ChatColor.DARK_RED + "" + plugin.getSettings().changeableTextEnabled());
		}
		String C;
		if (plugin.getSettings().conditionsEnabled()) {
			C = String.valueOf(ChatColor.DARK_GREEN + "" + plugin.getSettings().conditionsEnabled());
		} else {
			C = String.valueOf(ChatColor.DARK_RED + "" + plugin.getSettings().conditionsEnabled());
		}
		if (!sender.hasPermission("ibr.Info")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("no-permission")));
		} else {
			sender.sendMessage(ChatColor.GOLD + "" + ChatColor.STRIKETHROUGH + "========[" + ChatColor.DARK_AQUA
					+ ChatColor.BOLD + " InfoBoardReborn " + ChatColor.ITALIC + "v"
					+ plugin.getDescription().getVersion() + ChatColor.GOLD + " " + ChatColor.STRIKETHROUGH
					+ "]========");
			sender.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "Features:");
			//Scrolling info
			sender.sendMessage(ChatColor.GOLD + " - Scrolling Text enabled: " + SC);
			//Conditions info
			sender.sendMessage(ChatColor.GOLD + " - Conditions enabled: " + C);
			if (plugin.getSettings().conditionsEnabled()) {
				sender.sendMessage(ChatColor.DARK_AQUA + "    Conditions loaded: ");
				for (String s : plugin.getSettings().getConditions()) {
					sender.sendMessage(ChatColor.GREEN + "    " + s);
				}
			}
			//Changeables info
			sender.sendMessage(ChatColor.GOLD + " - Changeable Text enabled: " + CH);
			if (plugin.getSettings().changeableTextEnabled()) {
				sender.sendMessage(ChatColor.DARK_AQUA + "    Changeables loaded: ");
				for (String s : plugin.getSettings().getChangeable()) {
					sender.sendMessage(ChatColor.GREEN + "    " + s);
				}
			}
			sender.sendMessage(
					"" + ChatColor.GOLD + ChatColor.STRIKETHROUGH + "--------------------------------------------");
			sender.sendMessage("" + ChatColor.DARK_AQUA + ChatColor.BOLD + "Authors: " + ChatColor.WHITE
					+ ChatColor.BOLD + "Ktar5 & pixar02");
			sender.sendMessage("" + ChatColor.DARK_AQUA + ChatColor.BOLD + "Websites: " + ChatColor.WHITE
					+ ChatColor.BOLD + plugin.pdfFile.getWebsite());

			sender.sendMessage(
					"" + ChatColor.GOLD + ChatColor.STRIKETHROUGH + "--------------------------------------------");
			if (plugin.update) {
				sender.sendMessage(ChatColor.DARK_GREEN + plugin.getFm().getFile("messages").getString("update"));
				sender.sendMessage("" + ChatColor.GOLD + ChatColor.STRIKETHROUGH
						+ "--------------------------------------------");
			}
		}
	}

	/*
	 * =============================================================================
	 * RELOAD [file]
	 * =============================================================================
	 */
	public void reloadCmd(CommandSender sender, String file) {
		if (!sender.hasPermission("ibr.Reload")) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("no-permission")));
		} else {
			/*
			 * =============================================================================
			 * RELOAD board
			 * =============================================================================
			 */
			if (Objects.equals(file, "board")) {

				Bukkit.getScheduler().cancelTasks(plugin);

				for (Player player : Bukkit.getOnlinePlayers()) {
					plugin.getSM().reset(player);
					plugin.getCHM().reset(player);
					plugin.getCM().reset(player);
				}
				plugin.getFm().reloadFile("board");
				plugin.getTimers().reset();

				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.hasPermission("InfoBoard.View")) {
						Create.createScoreBoard(player);
					}
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("board-reload")));

				/*
				 * =============================================================================
				 * RELOAD config
				 * =============================================================================
				 */
			} else if (Objects.equals(file, "config")) {

				Bukkit.getScheduler().cancelTasks(plugin);
				for (Player player : Bukkit.getOnlinePlayers()) {
					plugin.getSM().reset(player);
					plugin.getCHM().reset(player);
					plugin.getCM().reset(player);
				}

				plugin.getFm().reloadFile("config");
				plugin.getSettings().changeable.clear();
				plugin.getSettings().loadChangeable();
				plugin.getTimers().reset();

				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.hasPermission("InfoBoard.View")) {
						Create.createScoreBoard(player);
					}
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("config-reload")));
			}
			/*
			 * =============================================================================
			 * RELOAD messages
			 * =============================================================================
			 */
			else if (Objects.equals(file, "messages")) {
				Bukkit.getScheduler().cancelTasks(plugin);

				plugin.getFm().reloadFile("messages");
				plugin.getTimers().reset();

				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.hasPermission("InfoBoard.View")) {
						Create.createScoreBoard(player);
					}
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("messages-reload")));

			}

			/*
			 * =============================================================================
			 * RELOAD
			 * =============================================================================
			 */
			else if (Objects.equals(file, "all")) {

				Bukkit.getScheduler().cancelTasks(plugin);
				for (Player player : Bukkit.getOnlinePlayers()) {
					plugin.getSM().reset(player);
					plugin.getCHM().reset(player);
					plugin.getCM().reset(player);
				}
				plugin.getFm().reloadFile("board");
				plugin.getFm().reloadFile("config");
				plugin.getFm().reloadFile("messages");

				plugin.getSettings().changeable.clear();
				plugin.getSettings().loadChangeable();
				plugin.getTimers().reset();

				for (Player player : Bukkit.getOnlinePlayers()) {
					if (player.hasPermission("ibr.View")) {
						Create.createScoreBoard(player);
					}
				}
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("all-reload")));
			} else if (Objects.equals(file, "error")) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("wrong-usage")));
				sender.sendMessage("/ibr reload [File]");
			} else {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getFm().getFile("messages").getString("wrong-usage")));
				sender.sendMessage("/ibr reload [File]");
			}
		}
	}
}