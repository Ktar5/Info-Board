package com.ktar5.infoboard.Variables;

import org.bukkit.entity.Player;

import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.TownyUniverse;

public class TownyVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;

		if (newString.contains("<townyname>"))
			try {
				newString = newString.replaceAll(
						"<townyname>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.getName()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townyname>", "Unknown");
			}

		if (newString.contains("<townytitle>"))
			try {
				newString = newString.replaceAll(
						"<townytitle>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTitle()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townytitle>", "Unknown");
			}
		if (newString.contains("<townynation>"))
			try {
				newString = newString.replaceAll(
						"<townynation>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.getNation()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townynation>", "Unknown");
			}
		if (newString.contains("<townyresidents>"))
			try {
				newString = newString.replaceAll(
						"<townyresidents>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.getNumResidents()));
			} catch (NotRegisteredException e2) {
				newString = newString.replaceAll("<townyresidents>", "0");

			}
		if (newString.contains("<townyfriends>"))
			try {
				newString = newString.replaceAll(
						"<townyfriends>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getFriends()
								.size()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townyfriends>", "0");
			}
		if (newString.contains("<townybank>"))
			try {
				newString = newString.replaceAll(
						"<townybank>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.getHoldingBalance()));
			} catch (NotRegisteredException e) {
				if (newString.contains("<townybank>"))
					newString = newString.replaceAll("<townybank>", "0");

			} catch (EconomyException e) {
				newString = newString.replaceAll("<townybank>", "0");
			}
		if (newString.contains("<townymayor>"))
			try {
				newString = newString.replaceAll(
						"<townymayor>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.getMayor().getName()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townymayor>", "Unknown");
			}
		if (newString.contains("<townysize>"))
			try {
				newString = newString.replaceAll(
						"<townysize>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.getTotalBlocks()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townysize>", "0");

			}
		if (newString.contains("<townytag>"))
			try {
				newString = newString.replaceAll(
						"<townytag>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.getTag()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townytag>", "Unknown");
			}
		if (newString.contains("<townypvp>"))
			try {
				newString = newString.replaceAll(
						"<townypvp>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.isPVP()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townypvp>", "Unknown");
			}
		if (newString.contains("<townyopen>"))
			try {
				newString = newString.replaceAll(
						"<townyopen>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.isOpen()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townyopen>", "Unknown");
			}
		if (newString.contains("<townypublic>"))
			try {
				newString = newString.replaceAll(
						"<townypublic>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.isPublic()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townypublic>", "Unknown");
			}
		if (newString.contains("<townyexplosions>"))
			try {
				newString = newString.replaceAll(
						"<townyexplosions>",
						String.valueOf(TownyUniverse.getDataSource()
								.getResident(player.getName()).getTown()
								.isBANG()));
			} catch (NotRegisteredException e) {
				newString = newString
						.replaceAll("<townyexplosions>", "Unknown");
			}
		if (newString.contains("<townyintown>"))
			newString = newString.replaceAll("<townyintown>", String
					.valueOf(TownyUniverse.getTownName(player.getLocation())));

		if (newString.contains("<townyinmayor>"))
			try {
				newString = newString.replaceAll(
						"<townyinmayor>",
						String.valueOf(TownyUniverse
								.getTownBlock(player.getLocation()).getTown()
								.getMayor()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townyinmayor>", "Unknown");
			}
		if (newString.contains("<townyinresidents>"))
			try {
				newString = newString.replaceAll(
						"<townyinresidents>",
						String.valueOf(TownyUniverse
								.getTownBlock(player.getLocation()).getTown()
								.getResidents().size()));
			} catch (NotRegisteredException e1) {
				newString = newString.replaceAll("<townyinresidents>", "0");
			}
		if (newString.contains("<townyinsize>"))
			try {
				newString = newString.replaceAll(
						"<townyinsize>",
						String.valueOf(TownyUniverse
								.getTownBlock(player.getLocation()).getTown()
								.getTotalBlocks()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townyinsize>", "0");
			}
		if (newString.contains("<townyintag>"))
			try {
				newString = newString.replaceAll(
						"<townyintag>",
						String.valueOf(TownyUniverse
								.getTownBlock(player.getLocation()).getTown()
								.getTag()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townyintags>", "Unknown");
			}
		if (newString.contains("<townyinpvp>"))
			try {
				newString = newString.replaceAll(
						"<townyinpvp>",
						String.valueOf(TownyUniverse
								.getTownBlock(player.getLocation()).getTown()
								.isPVP()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townyinpvp>", "Unknown");
			}
		if (newString.contains("<townyinopen>"))
			try {
				newString = newString.replaceAll(
						"<townyinopen>",
						String.valueOf(TownyUniverse
								.getTownBlock(player.getLocation()).getTown()
								.isOpen()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townyinopen>", "Unknown");
			}
		if (newString.contains("<townyinpublic>"))
			try {
				newString = newString.replaceAll(
						"<townyinpublic>",
						String.valueOf(TownyUniverse
								.getTownBlock(player.getLocation()).getTown()
								.isPublic()));
			} catch (NotRegisteredException e) {
				newString = newString.replaceAll("<townyinpublic>", "Unknown");
			}

		return newString;
	}
}
