package com.ktar5.infoboard.Variables;

import com.worldcretornica.plotme.Plot;
import com.worldcretornica.plotme.PlotManager;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class PlotMeVariables {

	public static String replaceVariables(String string, Player player) {
		String newString = string;

		HashMap<String, Plot> plotsMap = PlotManager.getPlots(player);
		ArrayList<Plot> plots = new ArrayList<Plot>();
		for (Entry<String, Plot> set : plotsMap.entrySet())
			plots.add(set.getValue());

		if (newString.contains("<plotmeplots>"))
			newString = newString.replaceAll("<plotmeplots>",
					String.valueOf(PlotManager.getPlots(player).size()));

		if (newString.contains("<plotmeinid>"))
			if (PlotManager.getPlotById(player.getLocation()) != null)
				newString = newString.replaceAll("<plotmeinid>",
						String.valueOf(PlotManager.getPlotById(player
								.getLocation()).id));
			else
				newString = newString.replaceAll("<plotmeinid>", "0");

		if (newString.contains("<plotmeinowner>"))
			if (PlotManager.getPlotById(player.getLocation()) != null)
				newString = newString.replaceAll(
						"<plotmeinowner>",
						String.valueOf(PlotManager.getPlotById(
								player.getLocation()).getOwner()));
			else
				newString = newString.replaceAll("<plotmeinowner>", "Unknown");

		if (newString.contains("<plotmeincomments>"))
			if (PlotManager.getPlotById(player.getLocation()) != null)
				newString = newString.replaceAll(
						"<plotmeincomments>",
						String.valueOf(PlotManager.getPlotById(
								player.getLocation()).getCommentsCount()));
			else
				newString = newString.replaceAll("<plotmeincomments>", "0");

		if (newString.contains("<plotmeinbid>"))
			if (PlotManager.getPlotById(player.getLocation()) != null)
				newString = newString.replaceAll("<plotmeinbid>",
						String.valueOf(PlotManager.getPlotById(player
								.getLocation()).currentbid));
			else
				newString = newString.replaceAll("<plotmeinbid>", "0");

		if (newString.contains("<plotmeinbidder>"))
			if ((PlotManager.getPlotById(player.getLocation()) != null)
					&& (!PlotManager.getPlotById(player.getLocation()).currentbidder.equals("")))
				newString = newString.replaceAll("<plotmeinbidder>",
						String.valueOf(PlotManager.getPlotById(player
								.getLocation()).currentbidder));
			else
				newString = newString.replaceAll("<plotmeinbidder>", "Unknown");

		if (newString.contains("<plotmeinfinished>"))
			if (PlotManager.getPlotById(player.getLocation()) != null)
				newString = newString.replaceAll("<plotmeinfinished>",
						String.valueOf(PlotManager.getPlotById(player
								.getLocation()).finished));
			else
				newString = newString.replaceAll("<plotmeinfinished>", "false");

		if (newString.contains("<plotmeinforsale>"))
			if (PlotManager.getPlotById(player.getLocation()) != null)
				newString = newString.replaceAll("<plotmeinforsale>",
						String.valueOf(PlotManager.getPlotById(player
								.getLocation()).forsale));
			else
				newString = newString.replaceAll("<plotmeinforsale>", "false");

		if (newString.contains("<plotmeid")) {
			int i = Integer
					.valueOf(newString.split("<plotmeid")[1].split(">")[0]) - 1;

			if (plots.size() >= (i + 1))
				newString = newString.replaceAll("<plotmeid" + (i + 1) + ">",
						String.valueOf(plots.get(i).id));
			else
				newString = newString.replaceAll("<plotmeid" + (i + 1) + ">",
						"0");
		}
		if (newString.contains("<plotmebid")
				&& !newString.contains("<plotmebidder")) {
			int i = Integer
					.valueOf(newString.split("<plotmebid")[1].split(">")[0]) - 1;

			if (plots.size() >= (i + 1))
				newString = newString.replaceAll("<plotmebid" + (i + 1) + ">",
						String.valueOf(plots.get(i).currentbid));
			else
				newString = newString.replaceAll("<plotmebid" + (i + 1) + ">",
						"0");
		}
		if (newString.contains("<plotmebidder")) {
			int i = Integer.valueOf(newString.split("<plotmebidder")[1]
					.split(">")[0]) - 1;

			if ((plots.size() >= (i + 1))
					&& (!String.valueOf(plots.get(i).currentbidder).equals("")))
				newString = newString.replaceAll("<plotmebidder" + (i + 1)
						+ ">", String.valueOf(plots.get(i).currentbidder));
			else
				newString = newString.replaceAll("<plotmebidder" + (i + 1)
						+ ">", "Unknown");
		}
		if (newString.contains("<plotmecomments")) {
			int i = Integer.valueOf(newString.split("<plotmecomments")[1]
					.split(">")[0]) - 1;

			if (plots.size() >= (i + 1))
				newString = newString.replaceAll("<plotmecomments" + (i + 1)
						+ ">", String.valueOf(plots.get(i).getCommentsCount()));
			else
				newString = newString.replaceAll("<plotmecomments" + (i + 1)
						+ ">", "0");
		}

		if (newString.contains("<plotmefinished")) {
			int i = Integer.valueOf(newString.split("<plotmefinished")[1]
					.split(">")[0]) - 1;

			if (plots.size() >= (i + 1))
				newString = newString.replaceAll("<plotmefinished" + (i + 1)
						+ ">", String.valueOf(plots.get(i).finished));
			else
				newString = newString.replaceAll("<plotmefinished" + (i + 1)
						+ ">", "Unknown");
		}
		if (newString.contains("<plotmeforsale")) {
			int i = Integer.valueOf(newString.split("<plotmeforsale")[1]
					.split(">")[0]) - 1;

			if (plots.size() >= (i + 1))
				newString = newString.replaceAll("<plotmeforsale" + (i + 1)
						+ ">", String.valueOf(plots.get(i).forsale));
			else
				newString = newString.replaceAll("<plotmeforsale" + (i + 1)
						+ ">", "0");
		}

		return newString;
	}
}
