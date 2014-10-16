
package com.ktar5.infoboard.Variables;

import me.edge209.OnTime.DataIO;
import me.edge209.OnTime.OnTimeAPI.data;

import org.bukkit.entity.Player;

import com.ktar5.infoboard.Util.Time;


public class OnTimeVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		
		// TOTAL
		if (newString.contains("<ontimetotalplay>"))
			newString = newString.replaceAll("<ontimetotalplay>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.TOTALPLAY)));
		if (newString.contains("<ontimetotalplayformated>"))
			newString = newString.replaceAll("<ontimetotalplayformated>", String.valueOf(Time.getFormatTime(DataIO.getPlayerTimeData(player.getName(), data.TOTALPLAY))));
		if (newString.contains("<ontimetotalvote>"))
			newString = newString.replaceAll("<ontimetotalvote>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.TOTALVOTE)));
		if (newString.contains("<ontimetotalrefer>"))
			newString = newString.replaceAll("<ontimetotalrefer>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.TOTALREFER)));
		if (newString.contains("<ontimetotalpoint>"))
			newString = newString.replaceAll("<ontimetotalpoint>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.TOTALPOINT)));
		
		// MONTH
		if (newString.contains("<ontimemonthplay>"))
			newString = newString.replaceAll("<ontimemonthplay>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.MONTHPLAY)));
		if (newString.contains("<ontimemonthplayformated>"))
			newString = newString.replaceAll("<ontimemonthplayformated>", String.valueOf(Time.getFormatTime(DataIO.getPlayerTimeData(player.getName(), data.MONTHPLAY))));
		if (newString.contains("<ontimemonthvote>"))
			newString = newString.replaceAll("<ontimemonthvote>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.TOTALVOTE)));
		if (newString.contains("<ontimemonthrefer>"))
			newString = newString.replaceAll("<ontimemonthrefer>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.MONTHREFER)));
		
		// TODAY
		if (newString.contains("<ontimetodayplay>"))
			newString = newString.replaceAll("<ontimetodayplay>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.TODAYPLAY)));
		if (newString.contains("<ontimetodayplayformated>"))
			newString = newString.replaceAll("<ontimetodayplayformated>", String.valueOf(Time.getFormatTime(DataIO.getPlayerTimeData(player.getName(), data.TODAYPLAY))));
		if (newString.contains("<ontimetodayvote>"))
			newString = newString.replaceAll("<ontimetodayvote>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.TODAYVOTE)));
		if (newString.contains("<ontimetodayrefer>"))
			newString = newString.replaceAll("<ontimetodayrefer>", String.valueOf(DataIO.getPlayerTimeData(player.getName(), data.TODAYREFER)));
		
		return newString;
	}
}
