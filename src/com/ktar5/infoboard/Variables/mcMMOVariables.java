package com.ktar5.infoboard.Variables;

import org.bukkit.entity.Player;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.party.PartyManager;
import com.gmail.nossr50.util.player.UserManager;

public class mcMMOVariables {

	// TODO: Add Alchemy
	public static String replaceVariables(String string, Player player) {
		String newString = string;

		McMMOPlayer mp = UserManager.getPlayer(player.getName());
		if (newString.contains("<mcmmopowerlevel>"))
			newString = newString.replaceAll("<mcmmopowerlevel>",
					String.valueOf(mp.getPowerLevel()));
		if (newString.contains("<mcmmoacrobatics>"))
			newString = newString.replaceAll("<mcmmoacrobatics>",
					String.valueOf(mp.getAcrobaticsManager().getSkillLevel()));
		if (newString.contains("<mcmmoalchemy>"))
			newString = newString.replaceAll("<mcmmoalchemy>",
					String.valueOf(mp.getAlchemyManager().getSkillLevel()));
		if (newString.contains("<mcmmoarchery>"))
			newString = newString.replaceAll("<mcmmoarchery>",
					String.valueOf(mp.getArcheryManager().getSkillLevel()));
		if (newString.contains("<mcmmoaxes>"))
			newString = newString.replaceAll("<mcmmoaxes>",
					String.valueOf(mp.getAxesManager().getSkillLevel()));
		if (newString.contains("<mcmmoexcavation>"))
			newString = newString.replaceAll("<mcmmoexcavation>",
					String.valueOf(mp.getExcavationManager().getSkillLevel()));
		if (newString.contains("<mcmmofishing>"))
			newString = newString.replaceAll("<mcmmofishing>",
					String.valueOf(mp.getFishingManager().getSkillLevel()));
		if (newString.contains("<mcmmoherbalism>"))
			newString = newString.replaceAll("<mcmmoherbalism>",
					String.valueOf(mp.getHerbalismManager().getSkillLevel()));
		if (newString.contains("<mcmmomining>"))
			newString = newString.replaceAll("<mcmmomining>",
					String.valueOf(mp.getMiningManager().getSkillLevel()));
		if (newString.contains("<mcmmorepair>"))
			newString = newString.replaceAll("<mcmmorepair>",
					String.valueOf(mp.getRepairManager().getSkillLevel()));
		if (newString.contains("<mcmmosmelting>"))
			newString = newString.replaceAll("<mcmmosmelting>",
					String.valueOf(mp.getSmeltingManager().getSkillLevel()));
		if (newString.contains("<mcmmoswords>"))
			newString = newString.replaceAll("<mcmmoswords>",
					String.valueOf(mp.getSwordsManager().getSkillLevel()));
		if (newString.contains("<mcmmotaming>"))
			newString = newString.replaceAll("<mcmmotaming>",
					String.valueOf(mp.getTamingManager().getSkillLevel()));
		if (newString.contains("<mcmmounarmed>"))
			newString = newString.replaceAll("<mcmmounarmed>",
					String.valueOf(mp.getUnarmedManager().getSkillLevel()));
		if (newString.contains("<mcmmowoodcutting>"))
			newString = newString.replaceAll("<mcmmowoodcutting>",
					String.valueOf(mp.getWoodcuttingManager().getSkillLevel()));

		if (newString.contains("<mcmmomaxpowerlevel>"))
			newString = newString.replaceAll("<mcmmomaxpowerlevel>",
					String.valueOf(ExperienceAPI.getPowerLevelCap()));
		if (newString.contains("<mcmmomaxacrobatics>"))
			newString = newString.replaceAll("<mcmmomaxacrobatics>",
					String.valueOf(ExperienceAPI.getLevelCap("ACROBATICS")));
		if (newString.contains("<mcmmomaxalchemy>"))
			newString = newString.replaceAll("<mcmmomaxalchemy>",
					String.valueOf(ExperienceAPI.getLevelCap("ALCHEMY")));
		if (newString.contains("<mcmmomaxarchery>"))
			newString = newString.replaceAll("<mcmmomaxarchery>",
					String.valueOf(ExperienceAPI.getLevelCap("ARCHERY")));
		if (newString.contains("<mcmmomaxaxes>"))
			newString = newString.replaceAll("<mcmmomaxaxes>",
					String.valueOf(ExperienceAPI.getLevelCap("AXES")));
		if (newString.contains("<mcmmomaxexcavation>"))
			newString = newString.replaceAll("<mcmmomaxexcavation>",
					String.valueOf(ExperienceAPI.getLevelCap("EXCAVATION")));
		if (newString.contains("<mcmmomaxfishing>"))
			newString = newString.replaceAll("<mcmmomaxfishing>",
					String.valueOf(ExperienceAPI.getLevelCap("FISHING")));
		if (newString.contains("<mcmmomaxherbalism>"))
			newString = newString.replaceAll("<mcmmomaxherbalism>",
					String.valueOf(ExperienceAPI.getLevelCap("HERBALISM")));
		if (newString.contains("<mcmmomaxmining>"))
			newString = newString.replaceAll("<mcmmomaxmining>",
					String.valueOf(ExperienceAPI.getLevelCap("MINING")));
		if (newString.contains("<mcmmomaxrepair>"))
			newString = newString.replaceAll("<mcmmomaxrepair>",
					String.valueOf(ExperienceAPI.getLevelCap("REPAIR")));
		if (newString.contains("<mcmmomaxsmelting>"))
			newString = newString.replaceAll("<mcmmomaxsmelting>",
					String.valueOf(ExperienceAPI.getLevelCap("SMELTING")));
		if (newString.contains("<mcmmomaxswords>"))
			newString = newString.replaceAll("<mcmmomaxswords>",
					String.valueOf(ExperienceAPI.getLevelCap("SWORDS")));
		if (newString.contains("<mcmmomaxtaming>"))
			newString = newString.replaceAll("<mcmmomaxtaming>",
					String.valueOf(ExperienceAPI.getLevelCap("TAMING")));
		if (newString.contains("<mcmmomaxunarmed>"))
			newString = newString.replaceAll("<mcmmomaxunarmed>",
					String.valueOf(ExperienceAPI.getLevelCap("UNARMED")));
		if (newString.contains("<mcmmomaxwoodcutting>"))
			newString = newString.replaceAll("<mcmmomaxwoodcutting>",
					String.valueOf(ExperienceAPI.getLevelCap("WOODCUTTING")));

		if (newString.contains("<mcmmonextacrobatics>"))
			newString = newString.replaceAll("<mcmmonextacrobatics>", String
					.valueOf(ExperienceAPI.getXPToNextLevel(player,
							"ACROBATICS")));
		if (newString.contains("<mcmmonextalchemy>"))
			newString = newString
					.replaceAll("<mcmmonextalchemy>", String
							.valueOf(ExperienceAPI.getXPToNextLevel(player,
									"ALCHEMY")));
		if (newString.contains("<mcmmonextarchery>"))
			newString = newString
					.replaceAll("<mcmmonextarchery>", String
							.valueOf(ExperienceAPI.getXPToNextLevel(player,
									"ARCHERY")));
		if (newString.contains("<mcmmonextaxes>"))
			newString = newString.replaceAll("<mcmmonextaxes>", String
					.valueOf(ExperienceAPI.getXPToNextLevel(player, "AXES")));
		if (newString.contains("<mcmmonextexcavation>"))
			newString = newString.replaceAll("<mcmmonextexcavation>", String
					.valueOf(ExperienceAPI.getXPToNextLevel(player,
							"EXCAVATION")));
		if (newString.contains("<mcmmonextfishing>"))
			newString = newString
					.replaceAll("<mcmmonextfishing>", String
							.valueOf(ExperienceAPI.getXPToNextLevel(player,
									"FISHING")));
		if (newString.contains("<mcmmonextherbalism>"))
			newString = newString.replaceAll("<mcmmonextherbalism>", String
					.valueOf(ExperienceAPI
							.getXPToNextLevel(player, "HERBALISM")));
		if (newString.contains("<mcmmonextmining>"))
			newString = newString.replaceAll("<mcmmonextmining>", String
					.valueOf(ExperienceAPI.getXPToNextLevel(player, "MINING")));
		if (newString.contains("<mcmmonextrepair>"))
			newString = newString.replaceAll("<mcmmonextrepair>", String
					.valueOf(ExperienceAPI.getXPToNextLevel(player, "REPAIR")));
		if (newString.contains("<mcmmonextsmelting>"))
			newString = newString.replaceAll("<mcmmonextsmelting>",
					String.valueOf(ExperienceAPI.getXPToNextLevel(player,
							"SMELTING")));
		if (newString.contains("<mcmmonextswords>"))
			newString = newString.replaceAll("<mcmmonextswords>", String
					.valueOf(ExperienceAPI.getXPToNextLevel(player, "SWORDS")));
		if (newString.contains("<mcmmonexttaming>"))
			newString = newString.replaceAll("<mcmmonexttaming>", String
					.valueOf(ExperienceAPI.getXPToNextLevel(player, "TAMING")));
		if (newString.contains("<mcmmonextunarmed>"))
			newString = newString
					.replaceAll("<mcmmonextunarmed>", String
							.valueOf(ExperienceAPI.getXPToNextLevel(player,
									"UNARMED")));
		if (newString.contains("<mcmmonextwoodcutting>"))
			newString = newString.replaceAll("<mcmmonextwoodcutting>", String
					.valueOf(ExperienceAPI.getXPToNextLevel(player,
							"WOODCUTTING")));

		if (newString.contains("<mcmmoparty>"))
			newString = newString
					.replaceAll(
							"<mcmmoparty>",
							String.valueOf(""
									+ (PartyManager.getParty(player.getName()) != null ? PartyManager
											.getParty(player.getName())
											.getName() : "Unknown")));
		if (newString.contains("<mcmmopartyleader>"))
			newString = newString.replaceAll("<mcmmopartyleader>", String
					.valueOf(""
							+ (PartyManager.getParty(player.getName())
									.getLeader() != null ? PartyManager
									.getParty(player.getName()).getLeader()
									: "Unknown")));
		if (newString.contains("<mcmmopartyexpshare>"))
			newString = newString
					.replaceAll(
							"<mcmmopartyexpshare>",
							String.valueOf(""
									+ (PartyManager.getParty(player.getName()) != null ? PartyManager
											.getParty(player.getName())
											.getXpShareMode().toString()
											: "Unknown")));
		if (newString.contains("<mcmmopartymembers>"))
			newString = newString
					.replaceAll(
							"<mcmmopartymembers>",
							String.valueOf(""
									+ (PartyManager.getParty(player.getName()) != null ? PartyManager
											.getParty(player.getName())
											.getMembers().size()
											: "0")));
		if (newString.contains("<mcmmopartymemberson>"))
			newString = newString
					.replaceAll(
							"<mcmmopartymemberson>",
							String.valueOf(""
									+ (PartyManager.getParty(player.getName()) != null ? PartyManager
											.getParty(player.getName())
											.getOnlineMembers().size()
											: "0")));

		if (newString.contains("<mcmmogodmode>"))
			newString = newString.replaceAll("<mcmmogodmode>", String
					.valueOf(UserManager.getPlayer(player.getName())
							.getGodMode()));

		return newString;
	}
}
