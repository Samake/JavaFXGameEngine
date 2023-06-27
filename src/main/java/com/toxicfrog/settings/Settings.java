package com.toxicfrog.settings;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {
	
	private static Properties properties = new Properties();
	public static int WINDOW_WIDTH = 1280;
	public static int WINDOW_HEIGHT = 720;
	public static boolean WINDOW_FULLSCREEN = false;
	public static boolean RENDER_SHADOWS = true;
	
	public static int MAXSOUNDS = 16;
	public static double MASTERVOLUME = 1.0;
	public static double MUSICVOLUME = 1.0;
	
	public static boolean DEBUG_LOG = true;
	public static boolean DEBUG_GUI = true;
	public static boolean RENDER_COLLISSION = false;

	public static void load() {
		try {
			properties.load(new FileInputStream(Path.SETTINGS_PATH + "system.ini"));
			
			WINDOW_WIDTH = Integer.valueOf(properties.getProperty("WINDOW_WIDTH"));
			WINDOW_HEIGHT = Integer.valueOf(properties.getProperty("WINDOW_HEIGHT"));
			WINDOW_FULLSCREEN = Boolean.valueOf(properties.getProperty("FULLSCREEN"));
			RENDER_SHADOWS = Boolean.valueOf(properties.getProperty("RENDER_SHADOWS"));
			MAXSOUNDS = Integer.valueOf(properties.getProperty("MAXSOUNDS"));
			MASTERVOLUME = Double.valueOf(properties.getProperty("MASTERVOLUME"));
			MUSICVOLUME = Double.valueOf(properties.getProperty("MUSICVOLUME"));
			DEBUG_LOG = Boolean.valueOf(properties.getProperty("DEBUG_LOG"));
			DEBUG_GUI = Boolean.valueOf(properties.getProperty("DEBUG_GUI"));
			RENDER_COLLISSION = Boolean.valueOf(properties.getProperty("RENDER_COLLISSION"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
