package com.toxicfrog.sound;

import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	
	public static int soundCount = 0;

	public static MediaPlayer playSound(Media media, double volume, boolean infinite) {
		
		if (soundCount < Settings.MAXSOUNDS) {
			final MediaPlayer player = new MediaPlayer(media);
			player.setVolume(volume * Settings.MASTERVOLUME);
			
			if (infinite) {
				player.setCycleCount(99999);
			} else {
				player.setCycleCount(1);
			}
			
			player.play();
			
			soundCount++;
			
			player.setOnEndOfMedia(() -> {
				if (player.getCycleCount() <= 1) {
					player.stop();
					player.dispose();
					
					soundCount--;
					
					if (soundCount <= 0) {
						soundCount = 0;
					}
				}
			});
			
			if (Settings.DEBUG_LOG) {
				Log.print("Play sound " + media);
			}
			
			return player;
		}

		return null;
	}
}
