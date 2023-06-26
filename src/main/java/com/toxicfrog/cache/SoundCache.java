package com.toxicfrog.cache;

import java.util.HashMap;
import java.util.Map;

import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.scene.media.Media;

public class SoundCache {

	private static Map<String, Media> soundCache = new HashMap<String, Media>();
	
	public static Media getSound(String path) {
		if (path != null && !path.isEmpty()) {
			if (soundCache.containsKey(path)) {
				return soundCache.get(path);
			} else {
				Media media = null;

            	try {
            		media = new Media(path);
            	} catch (Exception ex) {
            	    System.err.println(ex.getMessage());
            	}
				
				if (media != null) {
					soundCache.put(path, media);
					
					if (Settings.DEBUG_LOG) {
						Log.print(soundCache.size() + " sounds were cached! Current: " + path);
					}
					
					return media;
				}
			}
		}
		
		return null;
	}
}
