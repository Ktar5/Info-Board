
package com.ktar5.infoboard.Variables;

import nl.lolmewn.stats.api.StatsAPI;
import nl.lolmewn.stats.player.StatData;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.ktar5.infoboard.Util.Time;


public class StatsVariables {
	
	private static StatData getStatsData(String player, World world, String stat) {
		RegisteredServiceProvider<StatsAPI> stats = Bukkit.getServer().getServicesManager().getRegistration(nl.lolmewn.stats.api.StatsAPI.class);
		StatsAPI statsAPI = stats.getProvider();
		
		if (statsAPI.isUsingBetaFunctions())
			return statsAPI.getPlayer(player).getStatData(statsAPI.getStat(stat), world.getName(), true);
		else
			return statsAPI.getPlayer(player).getStatData(statsAPI.getStat(stat), world.getName(), true);
	}
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		String name = player.getName();
		
		if (newString.contains("<statstime>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Playtime");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statstime>", String.valueOf(value));
		}
		if (newString.contains("<statstimeformated>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Playtime");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statstime>", String.valueOf(Time.getFormatTime((long) value)));
		}
		if (newString.contains("<statsmoves>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Move");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsmoves>", String.valueOf(value));
		}
		if (newString.contains("<statsarrows>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Arrows");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsarrows>", String.valueOf(value));
		}
		if (newString.contains("<statsjoins>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Joins");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsjoins>", String.valueOf(value));
		}
		if (newString.contains("<statsfish>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Fish catched");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsfish>", String.valueOf(value));
		}
		if (newString.contains("<statsdamagetaken>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Damage taken");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsdamagetaken>", String.valueOf(value));
		}
		if (newString.contains("<statskicked>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Times kicked");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statskicked>", String.valueOf(value));
		}
		if (newString.contains("<statstoolsbroken>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Tools broken");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statstoolsbroken>", String.valueOf(value));
		}
		if (newString.contains("<statseggs>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Eggs thrown");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statseggs>", String.valueOf(value));
		}
		if (newString.contains("<statsitemscrafted>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Items crafted");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsitemscrafted>", String.valueOf(value));
		}
		if (newString.contains("<statsate>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Omnomnom");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsate>", String.valueOf(value));
		}
		if (newString.contains("<statsonfire>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "On fire");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsonfire>", String.valueOf(value));
		}
		if (newString.contains("<statswords>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Words said");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statswords>", String.valueOf(value));
		}
		if (newString.contains("<statscommands>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Commands done");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statscommaneds>", String.valueOf(value));
		}
		if (newString.contains("<statsvotes>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Votes");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsvotes>", String.valueOf(value));
		}
		if (newString.contains("<statsworldchange>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Worldchange");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsworldchange>", String.valueOf(value));
		}
		if (newString.contains("<statsbucketfill>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Bucketfill");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsbucketfill>", String.valueOf(value));
		}
		if (newString.contains("<statsbucketempty>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Bucketempty");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsabucketempty>", String.valueOf(value));
		}
		if (newString.contains("<statsbedenter>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Bedenter");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsbedenter>", String.valueOf(value));
		}
		if (newString.contains("<statsitemdrops>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Itemdrops");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsitemdrops>", String.valueOf(value));
		}
		if (newString.contains("<statsitempickups>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Itempickups");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsitempickups>", String.valueOf(value));
		}
		if (newString.contains("<statsteleports>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Teleports");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsteleports>", String.valueOf(value));
		}
		if (newString.contains("<statsshear>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Shear");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsshear>", String.valueOf(value));
		}
		if (newString.contains("<statsxpgained>"))
		{
			StatData stat;
			
			int value = 0;
			
			stat = getStatsData(name, player.getWorld(), "Xp gained");
			
			for (final Object[] vars : stat.getAllVariables())
				value += stat.getValue(vars);
			
			newString = newString.replaceAll("<statsxpgained>", String.valueOf(value));
		}
		if (newString.contains("<statsplaced"))
		{
			
			String s = newString.split("<statsplaced")[1].split(">")[0];
			int value = 0;
			String id = s;
			if (id.equals("all"))
				id = null;
			
			StatData blockStat;
			blockStat = getStatsData(name, player.getWorld(), "Block place");
			for (final Object[] vars : blockStat.getAllVariables())
				if ((id == null) || vars[0].toString().equals(s))
					value += blockStat.getValue(vars);
			
			newString = newString.replaceAll("<statsplaced" + s + ">", String.valueOf(value));
		}
		if (newString.contains("<statsbroken"))
		{
			
			String s = newString.split("<statsplaced")[1].split(">")[0];
			int value = 0;
			String id = s;
			if (id.equals("all"))
				id = null;
			
			StatData blockStat;
			blockStat = getStatsData(name, player.getWorld(), "Block break");
			for (final Object[] vars : blockStat.getAllVariables())
				if ((id == null) || vars[0].toString().equals(s))
					value += blockStat.getValue(vars);
			
			newString = newString.replaceAll("<statsbroken" + s + ">", String.valueOf(value));
			
		}
		if (newString.contains("<statskills"))
		{
			String s = newString.split("<statskills")[1].split(">")[0];
			EntityType mob = null;
			int value = 0;
			if (s.equals("all"))
				mob = null;
			else
				try
				{
					mob = EntityType.valueOf(s.toUpperCase());
				}
				catch (Exception e)
				{
					value = 0;
				}
			StatData blockStat;
			blockStat = getStatsData(name, player.getWorld(), "Kill");
			for (final Object[] vars : blockStat.getAllVariables())
				if ((mob == null) || (EntityType.valueOf(vars[0].toString().toUpperCase()) == mob))
					value += blockStat.getValue(vars);
			
			newString = newString.replaceAll("<statskills" + s + ">", String.valueOf(value));
		}
		if (newString.contains("<statsdeaths"))
		{
			String s = newString.split("<statsdeaths")[1].split(">")[0];
			int value = 0;
			String cause = s;
			if (s.equals("all"))
				cause = null;
			
			StatData blockStat;
			blockStat = getStatsData(name, player.getWorld(), "Death");
			for (final Object[] vars : blockStat.getAllVariables())
				if ((cause == null) || vars[0].toString().toUpperCase().equals(s.toUpperCase()))
					value += blockStat.getValue(vars);
			
			newString = newString.replaceAll("<statsdeaths" + s + ">", String.valueOf(value));
		}
		return newString;
	}
}
