package com.toxicfrog.sound;

import java.util.ArrayList;
import java.util.List;

import com.toxicfrog.enums.ENUMS.SOUNDTYPE;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundManager {
	
	public static List<SoundPlayer> sounds = new ArrayList<SoundPlayer>();

	public static MediaPlayer playSound(Media media, double volume, boolean infinite, SOUNDTYPE type) {
		
		if (sounds.size() < Settings.MAXSOUNDS) {
			MediaPlayer player = new MediaPlayer(media);

			if (infinite) {
				player.setCycleCount(99999);
			} else {
				player.setCycleCount(1);
			}
			
			player.play();
			
			player.setOnEndOfMedia(() -> {
				if (player.getCycleCount() <= 1) {
					removePlayer(player);
				}
			});
			
			if (Settings.DEBUG_LOG) {
				Log.print("Play sound " + media);
			}
			
			sounds.add(new SoundPlayer(player, volume, type));
			
			return player;
		}

		return null;
	}
	
	public static void removePlayer(MediaPlayer player) {
		List<SoundPlayer> soundsUpdate = new ArrayList<SoundPlayer>(sounds);
		
		for (SoundPlayer soundPlayer : soundsUpdate) {
			if (soundPlayer != null) {
				if (soundPlayer.player.equals(player)) {
					soundPlayer.destroy();
					sounds.remove(soundPlayer);
					soundPlayer = null;
				}
			}
		}
	}

	public static void updateVolume() {
		List<SoundPlayer> soundsUpdate = new ArrayList<SoundPlayer>(sounds);
		
		for (SoundPlayer player : soundsUpdate) {
			if (player != null) {
				player.updateVolume();
			}
		}
	}
	
	public static void destroy() {
		List<SoundPlayer> soundsUpdate = new ArrayList<SoundPlayer>(sounds);
		
		for (SoundPlayer soundPlayer : soundsUpdate) {
			if (soundPlayer != null) {
				soundPlayer.destroy();
				sounds.remove(soundPlayer);
				soundPlayer = null;
			}
		}
	}
}
