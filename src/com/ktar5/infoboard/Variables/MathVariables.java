package com.ktar5.infoboard.Variables;

import org.bukkit.entity.Player;

import com.ktar5.infoboard.Util.Messages;

public class MathVariables {

	public static String replaceVariables(String string, Player player) {
		// TODO: Apperently the update method doesn't work if math is in it
		String newString = string;

		if (newString.contains("+")) {
			String first = newString.split("<math")[1].split("\\+")[0];
			String second = newString.split("\\+")[1].split(">")[0] + ">";

			double a = Double.valueOf(Messages.getLine(first, player));
			double b = Double.valueOf(Messages.getLine(second, player));

			newString = newString.replaceAll("<math" + first + "\\+" + second
					+ ">", String.valueOf(a + b));
		}
		if (newString.contains("-")) {
			String first = newString.split("<math")[1].split("\\-")[0];
			String second = newString.split("\\-")[1].split(">")[0] + ">";

			double a = Double.valueOf(Messages.getLine(first, player));
			double b = Double.valueOf(Messages.getLine(second, player));
			newString = newString.replaceAll("<math" + first + "-" + second
					+ ">", String.valueOf(a - b));
		}
		if (newString.contains("*")) {
			String first = newString.split("<math")[1].split("\\*")[0];
			String second = newString.split("\\*")[1].split(">")[0] + ">";

			double a = Double.valueOf(Messages.getLine(first, player));
			double b = Double.valueOf(Messages.getLine(second, player));
			newString = newString.replaceAll("<math" + first + "*" + second
					+ ">", String.valueOf(a * b));
		}
		if (newString.contains("/")) {
			String first = newString.split("<math")[1].split("\\/")[0];
			String second = newString.split("\\/")[1].split(">")[0] + ">";

			double a = Double.valueOf(Messages.getLine(first, player));
			double b = Double.valueOf(Messages.getLine(second, player));
			newString = newString.replaceAll("<math" + first + "/" + second
					+ ">", String.valueOf(a / b));
		}

		return newString;
	}
}
