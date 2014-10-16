
package com.ktar5.infoboard.Variables;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class SimpleClansVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		
		ClanManager cm = ((SimpleClans) Bukkit.getPluginManager().getPlugin("SimpleClans")).getClanManager();
		
		ClanPlayer cp = cm.getClanPlayer(player);
		
		if (cp == null)
			cp = cm.getCreateClanPlayer(player.getName());
		
		if (newString.contains("<simpleclanskills>"))
			newString = newString.replaceAll("<simpleclanskills>", String.valueOf(cp.getNeutralKills() + cp.getCivilianKills() + cp.getRivalKills()));
		if (newString.contains("<simpleclansciviliankills>"))
			newString = newString.replaceAll("<simpleclansciviliankills>", String.valueOf(cp.getCivilianKills()));
		if (newString.contains("<simpleclansneutralkills>"))
			newString = newString.replaceAll("<simpleclansneutralkills>", String.valueOf(cp.getNeutralKills()));
		if (newString.contains("<simpleclansrivalkills>"))
			newString = newString.replaceAll("<simpleclansrivalkills>", String.valueOf(cp.getRivalKills()));
		if (newString.contains("<simpleclansrank>"))
			newString = newString.replaceAll("<simpleclansrank>", String.valueOf(cp.getRank()));
		if (newString.contains("<simpleclansweightedkills>"))
			newString = newString.replaceAll("<simpleclansweightedkills>", String.valueOf(cp.getWeightedKills()));
		if (newString.contains("<simpleclanskdr>"))
			newString = newString.replaceAll("<simpleclanskdr>", String.valueOf(cp.getKDR()));
		if (newString.contains("<simpleclanstag>"))
			newString = newString.replaceAll("<simpleclanstag>", String.valueOf(cp.getTag()));
		
		if (newString.contains("<simpleclansclan"))
		{
			
			Clan c = cm.getClanByPlayerName(player.getName());
			
			if (newString.contains("<simpleclansclanaveragewk>"))
				newString = newString.replaceAll("<simpleclansclanaveragewk>", String.valueOf(c == null ? 0 : c.getAverageWK()));
			if (newString.contains("<simpleclansclanbalance>"))
				newString = newString.replaceAll("<simpleclansclanbalance>", String.valueOf(c == null ? 0 : c.getBalance()));
			if (newString.contains("<simpleclansclanfounded>"))
				newString = newString.replaceAll("<simpleclansclanfounded>", String.valueOf(c == null ? 0 : c.getFounded()));
			if (newString.contains("<simpleclansclansize>"))
				newString = newString.replaceAll("<simpleclansclansize>", String.valueOf(c == null ? 0 : c.getSize()));
			if (newString.contains("<simpleclansclankdr>"))
				newString = newString.replaceAll("<simpleclansclankdr>", String.valueOf(c == null ? 0 : c.getTotalKDR()));
			if (newString.contains("<simpleclansclanneutrals>"))
				newString = newString.replaceAll("<simpleclansclanneutrals>", String.valueOf(c == null ? 0 : c.getTotalNeutral()));
			if (newString.contains("<simpleclansclanrivals>"))
				newString = newString.replaceAll("<simpleclansclanrivals>", String.valueOf(c == null ? 0 : c.getTotalRival()));
			if (newString.contains("<simpleclansclanallymembers>"))
				newString = newString.replaceAll("<simpleclansclanallymembers>", String.valueOf(c == null ? 0 : c.getAllAllyMembers().size()));
			if (newString.contains("<simpleclansclanallies>"))
				newString = newString.replaceAll("<simpleclansclanallies>", String.valueOf(c == null ? 0 : c.getAllies().size()));
			if (newString.contains("<simpleclansclanleaders>"))
				newString = newString.replaceAll("<simpleclansclanleaders>", String.valueOf(c == null ? 0 : c.getLeaders().size()));
			if (newString.contains("<simpleclansclanmembers>"))
				newString = newString.replaceAll("<simpleclansclanmembers>", String.valueOf(c == null ? 0 : c.getMembers()));
			if (newString.contains("<simpleclansclanonline>"))
				newString = newString.replaceAll("<simpleclansclanonline>", String.valueOf(c == null ? 0 : c.getOnlineMembers()));
			if (newString.contains("<simpleclansclantag>"))
				newString = newString.replaceAll("<simpleclansclantag>", String.valueOf(c == null ? "Unknown" : c.getTag()));
		}
		return newString;
	}
}
