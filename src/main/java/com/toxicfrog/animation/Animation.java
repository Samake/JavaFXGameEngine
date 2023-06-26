package com.toxicfrog.animation;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.scene.image.Image;

public class Animation {

	public String name;
	public String path;
	public int imageCount;
	public Image image;
	public Image[] imageSet;
	public double speed = 1.0f;
	public double speedModifier = 1.0f;
	public boolean infinite;
	public double tickValue = 0;
	public double duration;
	
	private int currentImage = 0;
	private long lastTick = 0;

	
	public Animation(String name, String path, int imageCount, double speed, boolean infinite) {
		this.name = name;
		this.path = path;
		this.imageCount = imageCount;
		this.speed = speed;
		this.infinite = infinite;
		
		imageSet = new Image[imageCount];
		
		loadImages();
		
		if (Settings.DEBUG_LOG) {
			Log.print("Loaded AnimationSet: " + name + " - " + imageCount + " files");
		}
	}

	private void loadImages() {
		for (int i = 0; i < imageSet.length; i++) {
			imageSet[i] = ImageCache.getImage(path + "_" + i + ".png", null);
			image = imageSet[i];
		}
		
		image = imageSet[currentImage];
	}

	public void update(double delta) {

		if (imageCount > 0) {
			long currentTick = System.currentTimeMillis();
			
			tickValue = (speed / speedModifier) * delta;
			duration = tickValue * imageCount;

			if (lastTick + tickValue <= currentTick) {
				if (infinite) {
					currentImage = (currentImage + 1)%imageCount;
				} else {
					if (currentImage < imageCount - 1) {
						currentImage += 1;
					}
				}

				lastTick = currentTick;
			}
		}
		
		image = imageSet[currentImage];
	}
}
