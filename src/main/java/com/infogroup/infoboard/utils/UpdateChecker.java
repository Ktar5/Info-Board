package com.infogroup.infoboard.utils;

import com.infogroup.infoboard.InfoBoardReborn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

public class UpdateChecker {
	private InfoBoardReborn plugin;
//TODO test if update always available is fixed
	public UpdateChecker(InfoBoardReborn pl) {
		plugin = pl;
	}

	/**
	 * 
	 * @param currentVersion
	 * @param newVersion
	 * @return
	 */

	private boolean checkHigher(String currentVersion, String newVersion) {
		String current = toReadable(currentVersion);
		String newVers = toReadable(newVersion);
		return current.compareTo(newVers) < 0;
	}

	/**
	 * 
	 * @param currentVersion
	 * @throws Exception
	 */
	public void checkUpdate(String currentVersion) {
		String version = getVersion("98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4", 44270);
		if (plugin.getSettings().debug()) {
			plugin.getLogger().info("online: " + version);
			plugin.getLogger().info("plugin: " + currentVersion);
		}
		if (!checkHigher(currentVersion, version)) {
			plugin.getLogger().info("You are running the most recent version!");
			plugin.update = false;
		} else {
			plugin.getLogger().info("There is an update available!");
			plugin.update = true;
		}
	}

	/**
	 * 
	 * @param key
	 * @param resourceId
	 * @return
	 */
	private String getVersion(String key, int resourceId) {
		String version = null;
		try {
			HttpURLConnection con = (HttpURLConnection) new URL("https://www.spigotmc.org/api/general.php")
					.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.getOutputStream().write(("key=" + key + "&resource=" + resourceId).getBytes("UTF-8"));
			version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
		} catch (IOException ex) {
			if (plugin.getSettings().debug()) {
				plugin.getLogger().warning("Could not connect to Spigot!");
				ex.printStackTrace();
			}
		}

		return version;
	}

	/**
	 * 
	 * @param version
	 * @return
	 */
	public String toReadable(String version) {
		String[] split = Pattern.compile(".", Pattern.LITERAL).split(version.replace("v", ""));
		StringBuilder versionBuilder = new StringBuilder();
		for (String s : split)
			versionBuilder.append(s);
		version = versionBuilder.toString();
		return version;
	}
}
