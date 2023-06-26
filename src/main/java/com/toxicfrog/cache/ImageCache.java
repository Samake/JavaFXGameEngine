package com.toxicfrog.cache;

import java.util.HashMap;
import java.util.Map;

import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.settings.Settings;

import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public class ImageCache {

	private static Map<String, Image> imageCache = new HashMap<String, Image>();
	private static ImageCursor cursor;
		
	public static Image getImage(String path, double[] size) {
		if (size != null) {
			return getImage(path, size[0], size[1]);
		} else {
			return getImage(path, InternalSettings.DEFAULT_TEXTURE_SIZE, InternalSettings.DEFAULT_TEXTURE_SIZE);
		}
	}

	public static Image getImage(String path, double width, double height) {
		if (path != null && !path.isEmpty()) {
			if (imageCache.containsKey(path)) {
				return imageCache.get(path);
			} else {
            	Image image = null;
            	
            	try {
            		image = new Image(path, width, height, false, false);
            	} catch (Exception ex) {
            	    System.err.println(ex.getMessage());
            	}
            	
				if (image != null) {
					imageCache.put(path, image);
					
					if (Settings.DEBUG_LOG) {
						Log.print("Loading texture: " + path + ", size: " + image.getWidth() + "x" + image.getHeight());
					}
				}
				
                return image;
            }
		}

		return null;
	}
	
	public static ImageCursor getCursor() {
		Image image = getImage(Resources.CURSOR_TEXTURE, null);
		
		if (image != null) {
			if (cursor == null) {
				cursor = new ImageCursor(image);
			}
		}
		
		return cursor;
	}
}
