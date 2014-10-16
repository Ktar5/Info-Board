
package com.ktar5.infoboard.Variables;

import org.bukkit.entity.Player;

import com.ancientshores.AncientRPG.AncientRPG;
import com.ancientshores.AncientRPG.PlayerData;
import com.ancientshores.AncientRPG.API.ApiManager;
import com.ancientshores.AncientRPG.Classes.AncientRPGClass;
import com.ancientshores.AncientRPG.Guild.AncientRPGGuild;
import com.ancientshores.AncientRPG.Party.AncientRPGParty;


public class AncientRPGVariables {
	
	@SuppressWarnings("static-access")
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		ApiManager api = AncientRPG.getApiManager();
		PlayerData pd = api.getPlayerData(player);
		
		if (newString.contains("<ancientrpgrace>"))
			newString = newString.replaceAll("<ancientrpgrace>", String.valueOf(pd.getRacename()));
		if (newString.contains("<ancientrpgstance>"))
			newString = newString.replaceAll("<ancientrpgstance>", String.valueOf(pd.getStance()));
		
		if (newString.contains("ancientrpgclass"))
		{
			AncientRPGClass pc = api.getPlayerClass(pd);
			
			if (newString.contains("<ancientrpgclass>"))
				newString = newString.replaceAll("<ancientrpgclass>", String.valueOf(pc == null ? "Unknown" : pc.name));
			if (newString.contains("<ancientrpgclasslevel>"))
				newString = newString.replaceAll("<ancientrpgclasslevel>", String.valueOf(pc == null ? 0 : pc.level));
			if (newString.contains("<ancientrpgclassminlevel>"))
				newString = newString.replaceAll("<ancientrpgclassminlevel>", String.valueOf(pc == null ? 0 : pc.minlevel));
			if (newString.contains("<ancientrpgclasshp>"))
				newString = newString.replaceAll("<ancientrpgclasshp>", String.valueOf(pc == null ? 0 : pc.hp));
			if (newString.contains("<ancientrpghpreg>"))
				newString = newString.replaceAll("<ancientrpghpreg>", String.valueOf(pc == null ? 0 : pc.hpreg));
		}
		if (newString.contains("ancientrpgguild"))
		{
			AncientRPGGuild pg = api.getPlayerGuild(player.getName());
			
			if (newString.contains("<ancientrpgguild>"))
				newString = newString.replaceAll("<ancientrpgguild>", String.valueOf(pg == null ? "Unknown" : pg.gName));
			if (newString.contains("<ancientrpgguildleader>"))
				newString = newString.replaceAll("<ancientrpgguildleader>", String.valueOf(pg == null ? "Unknown" : pg.gLeader));
			if (newString.contains("<ancientrpgguildfriendlyfire>"))
				newString = newString.replaceAll("<ancientrpgguildfriendlyfire>", String.valueOf(pg == null ? "false" : pg.friendlyFire));
			if (newString.contains("<ancientrpgguildmaxmembers>"))
				newString = newString.replaceAll("<ancientrpgguildmaxmembers>", String.valueOf(pg == null ? 0 : pg.maxPlayers));
			if (newString.contains("<ancientrpgguildmotd>"))
				newString = newString.replaceAll("<ancientrpgguildmotd>", String.valueOf(pg == null ? "Unknown" : pg.motd));
		}
		if (newString.contains("ancientrpgparty"))
		{
			AncientRPGParty pp = api.getPlayerParty(player);
			
			if (newString.contains("<ancientrpgpartyleader>"))
				newString = newString.replaceAll("<ancientrpgpartyleader>", String.valueOf(pp == null ? "Unknown" : pp.mLeader));
			if (newString.contains("<ancientrpgpartymaxmembers>"))
				newString = newString.replaceAll("<ancientrpgpartymaxmembers>", String.valueOf(pp == null ? 0 : pp.maxPlayers));
			if (newString.contains("<ancientrpgpartyfriendlyfire>"))
				newString = newString.replaceAll("<ancientrpgpartyfriendlyfire>", String.valueOf(pp == null ? "false" : pp.friendlyFire));
		}
		return newString;
	}
}
