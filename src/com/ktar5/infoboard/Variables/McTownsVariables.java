
package com.ktar5.infoboard.Variables;

import java.util.List;

import net.jmhertlein.mctowns.MCTowns;
import net.jmhertlein.mctowns.structure.Town;

import org.bukkit.entity.Player;


public class McTownsVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		
		List<Town> towns = MCTowns.getTownManager().matchPlayerToTowns(player.getName());
		
		if (newString.contains("<mctownshastown>"))
			newString = newString.replaceAll("<mctownshastown>", String.valueOf(MCTowns.getTownManager().playerIsAlreadyInATown(player)));
		
		if (newString.contains("<mctownstowns>"))
			newString = newString.replaceAll("<mctownstowns>", String.valueOf(towns.size()));
		
		if (newString.contains("<mctownsname"))
		{
			int i = Integer.valueOf(newString.split("<mctownsname")[1].split(">")[0]) - 1;
			
			if (towns.size() >= (i + 1))
				newString = newString.replaceAll("<mctownsname" + (i + 1) + ">", String.valueOf(towns.get(i).getTownName()));
			else
				newString = newString.replaceAll("<mctownsname" + (i + 1) + ">", "Join a Town");
		}
		
		if (newString.contains("<mctownsmayor"))
		{
			int i = Integer.valueOf(newString.split("<mctownsmayor")[1].split(">")[0]) - 1;
			
			if (towns.size() >= (i + 1))
				newString = newString.replaceAll("<mctownsmayor" + (i + 1) + ">", String.valueOf(towns.get(i).getMayor()));
			else
				newString = newString.replaceAll("<mctownsmayor" + (i + 1) + ">", "Unknown");
		}
		
		if (newString.contains("<mctownssize"))
		{
			int i = Integer.valueOf(newString.split("<mctownssize")[1].split(">")[0]) - 1;
			
			if (towns.size() >= (i + 1))
				newString = newString.replaceAll("<mctownssize" + (i + 1) + ">", String.valueOf(towns.get(i).getSize()));
			else
				newString = newString.replaceAll("<mctownssize" + (i + 1) + ">", "0");
		}
		
		if (newString.contains("<mctownsresidents"))
		{
			int i = Integer.valueOf(newString.split("<mctownsresidents")[1].split(">")[0]) - 1;
			
			if (towns.size() >= (i + 1))
				newString = newString.replaceAll("<mctownsresidents" + (i + 1) + ">", String.valueOf(towns.get(i).getResidentNames().length));
			else
				newString = newString.replaceAll("<mctownsresidents" + (i + 1) + ">", "0");
		}
		
		if (newString.contains("<mctownsterritories"))
		{
			int i = Integer.valueOf(newString.split("<mctownsterritories")[1].split(">")[0]) - 1;
			
			if (towns.size() >= (i + 1))
				newString = newString.replaceAll("<mctownsterritories" + (i + 1) + ">", String.valueOf(towns.get(i).getTerritoriesCollection().size()));
			else
				newString = newString.replaceAll("<mctownsterritories" + (i + 1) + ">", "0");
		}
		
		if (newString.contains("<mctownsfriendlyfire"))
		{
			int i = Integer.valueOf(newString.split("<mctownsfriendlyfire")[1].split(">")[0]) - 1;
			
			if (towns.size() >= (i + 1))
				newString = newString.replaceAll("<mctownsfriendlyfire" + (i + 1) + ">", String.valueOf(towns.get(i).allowsFriendlyFire()));
			else
				newString = newString.replaceAll("<mctownsfriendlyfire" + (i + 1) + ">", "Unknown");
		}
		
		if (newString.contains("<mctownsassistants"))
		{
			int i = Integer.valueOf(newString.split("<mctownsassistants")[1].split(">")[0]) - 1;
			
			if (towns.size() >= (i + 1))
				newString = newString.replaceAll("<mctownsassistants" + (i + 1) + ">", String.valueOf(towns.get(i).getAssistantNames().size()));
			else
				newString = newString.replaceAll("<mctownsassistants" + (i + 1) + ">", "0");
		}
		
		return newString;
	}
}
