
package com.ktar5.infoboard.Util.VaraibleUtils;

/**
 * @author LazyLemons
 */
public class Lag implements Runnable {
	
	public static int			TICK_COUNT	= 0;
	public static Long[]	TICKS				= new Long[600];
	public static Long		LAST_TICK		= 0L;
	
	public static long getElapsed(int tickID) {
		if ((Lag.TICK_COUNT - tickID) >= Lag.TICKS.length)
		{
		}
		long time = Lag.TICKS[(tickID % Lag.TICKS.length)];
		return System.currentTimeMillis() - time;
	}
	
	public static double getTPS() {
		return getTPS(100);
	}
	
	public static double getTPS(int ticks) {
		if (Lag.TICK_COUNT < ticks)
			return 20.0D;
		int target = (Lag.TICK_COUNT - 1 - ticks) % Lag.TICKS.length;
		long elapsed = System.currentTimeMillis() - Lag.TICKS[target];
		return ticks / (elapsed / 1000.0D);
	}
	
	@Override
	public void run() {
		Lag.TICKS[(Lag.TICK_COUNT % Lag.TICKS.length)] = System.currentTimeMillis();
		Lag.TICK_COUNT += 1;
	}
}
