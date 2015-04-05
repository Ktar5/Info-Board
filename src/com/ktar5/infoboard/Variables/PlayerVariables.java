package com.ktar5.infoboard.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;

import com.ktar5.infoboard.Util.VaraibleUtils.Direction;
import com.ktar5.infoboard.Util.VaraibleUtils.Ping;

public class PlayerVariables {

	public static String replaceVariables(String string, Player player) {

		String newString = string;

		// Player Variables
		if (newString.contains("<ping>"))
			newString = newString.replaceAll("<ping>",
					String.valueOf(Ping.getPing(player)));
		if (newString.contains("<player>"))
			newString = newString.replaceAll("<player>", player.getName());
		if (newString.contains("<op>"))
			newString = newString.replaceAll("<op>",
					String.valueOf(player.isOp()));
		if (newString.contains("<exp>"))
			newString = newString
					.replaceAll("<exp>", String.valueOf((double) Math
							.round(player.getExp() * 100.0D) / 100));
		if (newString.contains("<level>"))
			newString = newString.replaceAll("<level>",
					String.valueOf(player.getLevel()));
		if (newString.contains("<hunger>"))
			newString = newString.replaceAll("<hunger>",
					String.valueOf(player.getFoodLevel()));
		if (newString.contains("<health>"))
			newString = newString.replaceAll("<health>", String
					.valueOf((double) Math.round(((Damageable) player)
							.getHealth() * 100.0D) / 100));
		if (newString.contains("<maxhealth>"))
			newString = newString.replaceAll("<maxhealth>",
					String.valueOf(((Damageable) player).getMaxHealth()));
		if (newString.contains("<worldname>"))
			newString = newString.replaceAll("<worldname>", player.getWorld()
					.getName());
		if (newString.contains("<worlddifficulty>"))
			newString = newString.replaceAll("<worlddifficulty>",
					String.valueOf(player.getWorld().getDifficulty()));
		if (newString.contains("<worldanimals>"))
			newString = newString.replaceAll("<worldanimals>",
					String.valueOf(player.getWorld().getAllowAnimals()));
		if (newString.contains("<worldmonsters>"))
			newString = newString.replaceAll("<worldmonsters>",
					String.valueOf(player.getWorld().getAllowMonsters()));
		if (newString.contains("<gamemode>"))
			newString = newString.replaceAll("<gamemode>",
					String.valueOf(player.getGameMode().toString()));
		if (newString.contains("<worldpvp>"))
			newString = newString.replaceAll("<worldpvp>",
					String.valueOf(player.getWorld().getPVP()));
		if (newString.contains("<worldplayers>"))
			newString = newString.replaceAll("<worldplayers>",
					String.valueOf(player.getWorld().getPlayers().size()));
		if (newString.contains("<pitch>"))
			newString = newString.replaceAll("<pitch>",
					String.valueOf((int) player.getLocation().getPitch()));
		if (newString.contains("<yaw>"))
			newString = newString.replaceAll("<yaw>",
					String.valueOf((int) player.getLocation().getYaw()));
		if (newString.contains("<x>"))
			newString = newString.replaceAll("<x>",
					String.valueOf((int) player.getLocation().getX()));
		if (newString.contains("<y>"))
			newString = newString.replaceAll("<y>",
					String.valueOf((int) player.getLocation().getY()));
		if (newString.contains("<z>"))
			newString = newString.replaceAll("<z>",
					String.valueOf((int) player.getLocation().getZ()));
		if (newString.contains("<direction>"))
			newString = newString.replaceAll("<direction>",
					String.valueOf(Direction.getCardinalDirection(player)));
		if (newString.contains("<lifetime>"))
			newString = newString.replaceAll("<lifetime>",
					String.valueOf(player.getTicksLived() / 20));
		if (newString.contains("<time>"))
			newString = newString.replaceAll("<time>",
					String.valueOf(player.getWorld().getTime()));
		if (newString.contains("<helmet>"))
			newString = newString.replaceAll("<helmet>", String.valueOf((player
					.getInventory().getHelmet() == null ? "None" : player
					.getInventory().getHelmet().getType().name())
					+ ""));
		if (newString.contains("<helmetdurability>"))
			newString = newString
					.replaceAll("<helmetdurability>", String.valueOf(player
							.getInventory().getHelmet() == null ? 0 : player
							.getInventory().getHelmet().getDurability()));
		if (newString.contains("<chestplate>"))
			newString = newString
					.replaceAll("<chestplate>", String.valueOf((player
							.getInventory().getChestplate() == null ? "None"
							: player.getInventory().getChestplate().getType()
									.name())
							+ ""));
		if (newString.contains("<chestplatedurability>"))
			newString = newString
					.replaceAll(
							"<chestplatedurability>",
							String.valueOf(player.getInventory()
									.getChestplate() == null ? 0 : player
									.getInventory().getHelmet().getDurability()));
		if (newString.contains("<leggings>"))
			newString = newString
					.replaceAll("<leggings>", String.valueOf((player
							.getInventory().getLeggings() == null ? "None"
							: player.getInventory().getLeggings().getType()
									.name())
							+ ""));
		if (newString.contains("<leggingsdurability>"))
			newString = newString
					.replaceAll("<leggingsdurability>", String.valueOf(player
							.getInventory().getLeggings() == null ? 0 : player
							.getInventory().getHelmet().getDurability()));
		if (newString.contains("<boots>"))
			newString = newString.replaceAll("<boots>", String.valueOf((player
					.getInventory().getBoots() == null ? "None" : player
					.getInventory().getBoots().getType().name())
					+ ""));
		if (newString.contains("<bootsdurability>"))
			newString = newString
					.replaceAll("<bootsdurability>", String.valueOf(player
							.getInventory().getBoots() == null ? 0 : player
							.getInventory().getHelmet().getDurability()));
		if (newString.contains("<hand>"))
			newString = newString.replaceAll("<hand>", String.valueOf((player
					.getInventory().getItemInHand() == null ? "None" : player
					.getInventory().getItemInHand().getType().name())
					+ ""));
		if (newString.contains("<handdurability>"))
			newString = newString.replaceAll(
					"<handdurability>",
					String.valueOf(player.getItemInHand() == null ? 0 : player
							.getInventory().getItemInHand().getDurability()));
		if (newString.contains("<doihave")) {
			String perm = newString.split("<doihave")[1].split(">")[0];

			newString = newString.replaceAll("<doihave" + (perm) + ">",
					String.valueOf(player.hasPermission(perm)));
		}
		if (newString.contains("<doesanyonehave")) {
			String perm = newString.split("<doesanyonehave")[1].split(">")[0];

			boolean someone = false;
			for (Player p : Bukkit.getOnlinePlayers())
				if (p.hasPermission(perm)) {
					someone = true;
					break;
				}
			newString = newString.replaceAll("<doesanyonehave" + (perm) + ">",
					String.valueOf(someone));
		}
		if (newString.contains("<howmanyhave")) {
			String perm = newString.split("<howmanyhave")[1].split(">")[0];

			int someone = 0;
			for (Player p : Bukkit.getOnlinePlayers())
				if (p.hasPermission(perm))
					someone++;

			newString = newString.replaceAll("<howmanyhave" + (perm) + ">",
					String.valueOf(someone));
		}
		return newString;
	}

}
