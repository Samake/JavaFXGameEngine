package com.toxicfrog.sound;

import com.toxicfrog.enums.ENUMS.SOUNDTYPE;
import com.toxicfrog.settings.Settings;

import javafx.scene.media.MediaPlayer;

public class SoundPlayer {

	public MediaPlayer player;
	private double volume;
	private SOUNDTYPE type;
	
	public SoundPlayer(MediaPlayer player, double volume, SOUNDTYPE type) {
		this.player = player;
		this.volume = volume;
		this.type = type;
		
		updateVolume();
	}
	
	public void updateVolume() {
		if (player != null) {
			switch (type) {
				case MUSIC:
					double volumeMusic = volume * Settings.MUSICVOLUME;
					volumeMusic *= Settings.MASTERVOLUME;
					
					if (volumeMusic <= 0) {volumeMusic = 0;}
					if (volumeMusic >= 1) {volumeMusic = 1;}
					
					player.setVolume(volumeMusic);
					
					break;
				case SOUND:
					double volumeSound = volume;
					volumeSound *= Settings.MASTERVOLUME;
					
					if (volumeSound <= 0) {volumeSound = 0;}
					if (volumeSound >= 1) {volumeSound = 1;}
					
					player.setVolume(volumeSound);

					break;
			}
		}
	}
	
	public void destroy() {
		if (player != null) {
			player.stop();
			player.dispose();
			
			player = null;
		}
	}
}
