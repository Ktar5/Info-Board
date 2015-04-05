package com.ktar5.infoboard.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.ktar5.infoboard.InfoBoard;

public class VaultVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;
		if ((InfoBoard.economy != null) && InfoBoard.economyB) {
			if (newString.contains("<money>"))
				newString = newString.replaceAll("<money>", String
						.valueOf((long) InfoBoard.economy.getBalance(player)));

			if (newString.contains("<vaultcurrencyplural>"))
				newString = newString.replaceAll("<vaultcurrencyplural>",
						String.valueOf(InfoBoard.economy.currencyNamePlural()));

			if (newString.contains("<vaultcurrencysingle>"))
				newString = newString
						.replaceAll("<vaultcurrencysingle>", String
								.valueOf(InfoBoard.economy
										.currencyNameSingular()));
		}

		if (InfoBoard.permission != null) {
			if (newString.contains("<rank>"))
				newString = newString
						.replaceAll(
								"<rank>",
								InfoBoard.permission.getPlayerGroups(
										player.getWorld(), player.getName())[0] != null ? String
										.valueOf(InfoBoard.permission
												.getPlayerGroups(
														player.getWorld(),
														player.getName())[0])
										: "None");

			if (newString.contains("<ranklist")) {
				String r = newString.split("<ranklist")[1].split(">")[0];
				StringBuilder group = new StringBuilder();

				for (Player user : Bukkit.getOnlinePlayers())
					if (InfoBoard.permission.getGroups()[0].equalsIgnoreCase(r)) {
						group.append(user.getName());
						group.append(", ");
					}
				try {
					group.delete(group.length() - 3, group.length() - 1);
				} catch (Exception e) {
					group.append("No one....");
				}
				newString = newString.replaceAll("<ranklist" + r + ">",
						group.toString());

			}
		}
		return newString;
	}
}
