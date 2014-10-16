
package com.ktar5.infoboard.Variables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.herocraftonline.heroes.Heroes;
import com.herocraftonline.heroes.characters.CharacterManager;
import com.herocraftonline.heroes.characters.Hero;
import com.herocraftonline.heroes.characters.classes.HeroClass;
import com.herocraftonline.heroes.characters.party.HeroParty;


public class HeroesVariables {
	
	public static String replaceVariables(String string, Player player) {
		String newString = string;
		
		Heroes heroes = (Heroes) Bukkit.getPluginManager().getPlugin("Heroes");
		CharacterManager cm = heroes.getCharacterManager();
		Hero hero = cm.getHero(player);
		
		if (newString.contains("<heroesmana>"))
			newString = newString.replaceAll("<heroesmana>", String.valueOf(hero.getMana()));
		if (newString.contains("<heroeslevel>"))
			newString = newString.replaceAll("<heroeslevel>", String.valueOf(hero.getLevel()));
		if (newString.contains("<heroesmaxmana>"))
			newString = newString.replaceAll("<heroesmaxmana>", String.valueOf(hero.getMaxMana()));
		if (newString.contains("<heroesmanaregen>"))
			newString = newString.replaceAll("<heroesmanaregen>", String.valueOf(hero.getManaRegen()));
		
		if (newString.contains("heroesparty"))
		{
			
			HeroParty hp = hero.getParty();
			
			if (newString.contains("<heroespartyleader>"))
				if (hp != null)
					newString = newString.replaceAll("<heroespartyleader>", String.valueOf(hp.getLeader()));
				else
					newString = newString.replaceAll("<heroespartyleader>", "Unknown");
			
			if (newString.contains("<heroespartysize>"))
				if (hp != null)
					newString = newString.replaceAll("<heroespartysize>", String.valueOf(hp.getMembers().size()));
				else
					newString = newString.replaceAll("<heroespartysize>", "0");
			
			if (newString.contains("<heroespartyisnopvp>"))
				if (hp != null)
					newString = newString.replaceAll("<heroespartyisnopvp>", String.valueOf(hp.isNoPvp()));
				else
					newString = newString.replaceAll("<heroespartyisnopvp>", "false");
		}
		if (newString.contains("heroesclass") && !newString.contains("heroesclass2"))
		{
			HeroClass hc = hero.getHeroClass();
			
			if (newString.contains("<heroesclassismaster>"))
				newString = newString.replaceAll("<heroesclassismaster>", String.valueOf(hero.isMaster(hc)));
			if (newString.contains("<heroesclass>"))
				newString = newString.replaceAll("<heroesclass>", String.valueOf(hc.getName()));
			if (newString.contains("<heroesclasstier>"))
				newString = newString.replaceAll("<heroesclasstier>", String.valueOf(hc.getTier()));
			if (newString.contains("<heroesclassbasehealth>"))
				newString = newString.replaceAll("<heroesclassbasehealth>", String.valueOf(hc.getBaseMaxHealth()));
			if (newString.contains("<heroesclassbasemana>"))
				newString = newString.replaceAll("<heroesclassbasemana>", String.valueOf(hc.getBaseMaxMana()));
			if (newString.contains("<heroesclassexpchange>"))
				newString = newString.replaceAll("<heroesclassexpchange>", String.valueOf(hc.getExpModifier()));
			if (newString.contains("<heroesclassexploss>"))
				newString = newString.replaceAll("<heroesclassexploss>", String.valueOf(hc.getExpLoss()));
			if (newString.contains("<heroesclassmaxlevel>"))
				newString = newString.replaceAll("<heroesclassmaxlevel>", String.valueOf(hc.getMaxLevel()));
		}
		if (newString.contains("<heroesclass2"))
		{
			
			HeroClass hc2 = hero.getSecondClass();
			
			if (newString.contains("<heroesclass2>"))
				if (hc2 != null)
					newString = newString.replaceAll("<heroesclass2>", String.valueOf(hc2.getName()));
				else
					newString = newString.replaceAll("<heroesclass2>", "Unknown");
			
			if (newString.contains("<heroesclass2tier>"))
				if (hc2 != null)
					newString = newString.replaceAll("<heroesclass2tier>", String.valueOf(hc2.getTier()));
				else
					newString = newString.replaceAll("<heroesclass2tier>", "0");
			
			if (newString.contains("<heroesclass2basehealth>"))
				if (hc2 != null)
					newString = newString.replaceAll("<heroesclass2basehealth>", String.valueOf(hc2.getBaseMaxHealth()));
				else
					newString = newString.replaceAll("<heroesclass2basehealth>", "0");
			
			if (newString.contains("<heroesclass2basemana>"))
				if (hc2 != null)
					newString = newString.replaceAll("<heroesclass2basemana>", String.valueOf(hc2.getBaseMaxMana()));
				else
					newString = newString.replaceAll("<heroesclass2basemana>", "0");
			
			if (newString.contains("<heroesclass2expchange>"))
				if (hc2 != null)
					newString = newString.replaceAll("<heroesclass2expchange>", String.valueOf(hc2.getExpModifier()));
				else
					newString = newString.replaceAll("<heroesclass2expchange>", "0");
			
			if (newString.contains("<heroesclass2exploss>"))
				if (hc2 != null)
					newString = newString.replaceAll("<heroesclass2exploss>", String.valueOf(hc2.getExpLoss()));
				else
					newString = newString.replaceAll("<heroesclass2exploss>", "0");
			
			if (newString.contains("<heroesclass2maxlevel>"))
				if (hc2 != null)
					newString = newString.replaceAll("<heroesclass2maxlevel>", String.valueOf(hc2.getMaxLevel()));
				else
					newString = newString.replaceAll("<heroesclass2maxlevel>", "0");
			
			if (newString.contains("<heroesclass2ismaster>"))
				if (hc2 != null)
					newString = newString.replaceAll("<heroesclass2ismaster>", String.valueOf(hero.isMaster(hc2)));
				else
					newString = newString.replaceAll("<heroesclass2ismaster>", "false");
		}
		return newString;
	}
}
