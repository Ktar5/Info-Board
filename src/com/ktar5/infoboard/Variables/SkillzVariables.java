package com.ktar5.infoboard.Variables;

import nl.lolmen.API.SkillzAPI;
import nl.lolmen.Skillz.Skillz;

import org.bukkit.entity.Player;

public class SkillzVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;
		SkillzAPI api = Skillz.api;

		if (newString.contains("<skillzacrobatics>"))
			newString = newString.replaceAll("<skillzacrobatics>",
					String.valueOf(api.getLevel(player, "Acrobatics")));
		if (newString.contains("<skillzarchery>"))
			newString = newString.replaceAll("<skillzarchery>",
					String.valueOf(api.getLevel(player, "Archery")));
		if (newString.contains("<skillzaxescombat>"))
			newString = newString.replaceAll("<skillzaxescombat>",
					String.valueOf(api.getLevel(player, "Axes Combat")));
		if (newString.contains("<skillzdigging>"))
			newString = newString.replaceAll("<skillzdigging>",
					String.valueOf(api.getLevel(player, "Digging")));
		if (newString.contains("<skillzfarming>"))
			newString = newString.replaceAll("<skillzfarming>",
					String.valueOf(api.getLevel(player, "Farming")));
		if (newString.contains("<skillzmining>"))
			newString = newString.replaceAll("<skillzmining>",
					String.valueOf(api.getLevel(player, "Mining")));
		if (newString.contains("<skillzswimming>"))
			newString = newString.replaceAll("<skillzswimming>",
					String.valueOf(api.getLevel(player, "Swimming")));
		if (newString.contains("<skillzswordscombat>"))
			newString = newString.replaceAll("<skillzswordscombat>",
					String.valueOf(api.getLevel(player, "Swords Combat")));
		if (newString.contains("<skillzunarmedcombat>"))
			newString = newString.replaceAll("<skillzunarmedcombat>",
					String.valueOf(api.getLevel(player, "Unarmed Combat")));
		if (newString.contains("<skillzwoodcutting>"))
			newString = newString.replaceAll("<skillzwoodcutting>",
					String.valueOf(api.getLevel(player, "Woodcutting")));

		return newString;
	}
}
