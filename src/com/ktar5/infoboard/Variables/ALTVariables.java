
package com.ktar5.infoboard.Variables;

public class ALTVariables {
	
	public static String replaceVariables(String string) {
		String newString = string;
		
		while (newString.contains("<ucode"))
		{
			String code = (newString.split("<ucode")[1]).split(">")[0];
			newString = newString.replaceAll("<ucode" + code + ">", String.valueOf((char) Integer.parseInt(code, 16)));
		}
		if (newString.contains("<1>"))
			newString = newString.replaceAll("<1>", "☺");
		if (newString.contains("<2>"))
			newString = newString.replaceAll("<2>", "☻");
		if (newString.contains("<3>"))
			newString = newString.replaceAll("<3>", "♥");
		if (newString.contains("<4>"))
			newString = newString.replaceAll("<4>", "♦");
		if (newString.contains("<5>"))
			newString = newString.replaceAll("<5>", "♣");
		if (newString.contains("<6>"))
			newString = newString.replaceAll("<6>", "♠");
		if (newString.contains("<7>"))
			newString = newString.replaceAll("<7>", "•");
		if (newString.contains("<8>"))
			newString = newString.replaceAll("<8>", "◘");
		if (newString.contains("<9>"))
			newString = newString.replaceAll("<9>", "○");
		if (newString.contains("<10>"))
			newString = newString.replaceAll("<10>", "◙");
		if (newString.contains("<16>"))
			newString = newString.replaceAll("<16>", "►");
		if (newString.contains("<17>"))
			newString = newString.replaceAll("<17>", "◄");
		if (newString.contains("<21>"))
			newString = newString.replaceAll("<21>", "§");
		if (newString.contains("<174>"))
			newString = newString.replaceAll("<174>", "«");
		if (newString.contains("<175>"))
			newString = newString.replaceAll("<175>", "»");
		if (newString.contains("<179>"))
			newString = newString.replaceAll("<179>", "│");
		if (newString.contains("<180>"))
			newString = newString.replaceAll("<180>", "┤");
		if (newString.contains("<185>"))
			newString = newString.replaceAll("<185>", "╣");
		if (newString.contains("<186>"))
			newString = newString.replaceAll("<186>", "║");
		if (newString.contains("<187>"))
			newString = newString.replaceAll("<187>", "╗");
		if (newString.contains("<188>"))
			newString = newString.replaceAll("<188>", "�?");
		if (newString.contains("<204>"))
			newString = newString.replaceAll("<204>", "╠");
		if (newString.contains("<205>"))
			newString = newString.replaceAll("<205>", "�?");
		if (newString.contains("<247>"))
			newString = newString.replaceAll("<247>", "≈");
		
		if (newString.contains("<skull>"))
			newString = newString.replaceAll("<skull>", "☠");
		if (newString.contains("<arrow>"))
			newString = newString.replaceAll("<arrow>", "➳");
		if (newString.contains("<nuke>"))
			newString = newString.replaceAll("<nuke>", "☢");
		if (newString.contains("<rightup>"))
			newString = newString.replaceAll("<rightup>", "⋰");
		if (newString.contains("<leftup>"))
			newString = newString.replaceAll("<leftup>", "⋱");
		return newString;
	}
}
