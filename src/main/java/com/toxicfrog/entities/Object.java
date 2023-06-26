package com.toxicfrog.entities;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.settings.Path;
import com.toxicfrog.utils.Vector2D;

public class Object extends Entity {
	
	public boolean hasCollission = true;

	public Object(Level level, ENTITYTYPE type, String imagePath, String animationSetPath, Vector2D position, double rotation, double scale, double speed, boolean renderShadows) {
		super(level, type, imagePath, animationSetPath, position, rotation, scale, speed, renderShadows);
		
		if (imagePath != null) {
			image = ImageCache.getImage(Path.TEXTURE_PATH + imagePath, null);
		}
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
