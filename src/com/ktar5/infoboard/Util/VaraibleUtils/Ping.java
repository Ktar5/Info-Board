
package com.ktar5.infoboard.Util.VaraibleUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import org.bukkit.entity.Player;


/**
 * @author chdxD1
 * @author Reflections by: Shevchik
 */
public class Ping {
	
	public static int getPing(Player p) {
		int ping = new Random(50).nextInt();
		try
		{
			Method getHandleMethod = p.getClass().getDeclaredMethod("getHandle");
			getHandleMethod.setAccessible(true);
			Object nmsplayer = getHandleMethod.invoke(p);
			Field pingField = nmsplayer.getClass().getDeclaredField("ping");
			pingField.setAccessible(true);
			ping = pingField.getInt(nmsplayer);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ping;
	}
	
}
