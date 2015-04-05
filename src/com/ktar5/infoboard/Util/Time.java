package com.ktar5.infoboard.Util;

public class Time {

	/**
	 * Get the time in a fancy format
	 * 
	 * @param time
	 * @return the time in a stats format
	 */
	public static String getFormatTime(Long time) {
		Long seconds = time;
		long minutes = seconds / 60;
		seconds %= 60;
		long hours = minutes / 60;
		minutes %= 60;
		long days = hours / 24;
		hours %= 24;
		String times = days + "D, " + hours + "H, " + minutes + "M " + seconds
				+ "S";
		return times;
	}
}
