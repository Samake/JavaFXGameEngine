package com.toxicfrog.settings;

import com.toxicfrog.utils.Utils;

public class Path {

	final public static String MAINPATH = Utils.getFileUrl();
	final public static String MAINPATHCLEAN = Utils.getFileUrlClean();
	
	final public static String STYLES_PATH = MAINPATH + "/styles/";
	final public static String IMAGE_PATH = MAINPATH + "/resources/images/";
	final public static String TEXTURE_PATH = MAINPATH + "/resources/textures/";
	final public static String FONT_PATH = MAINPATH + "/resources/fonts/";
	final public static String SOUND_PATH = MAINPATH + "/resources/sounds/";
	final public static String SETTINGS_PATH = MAINPATHCLEAN + "/settings/";
	final public static String SAVES_PATH = MAINPATHCLEAN + "/saves/";
}
