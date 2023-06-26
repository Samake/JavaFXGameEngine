package com.toxicfrog.entities.effects;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class Fire01 extends Effect {

	public Fire01(Level level, Vector2D position, int duration) {
		super(level, "effects/fire01", 6, "FIRE_01", position, 0.35, Utils.randomFloat(55, 90), duration);

		boundingBox.widthScale = 0.4;
		boundingBox.heightScale = 0.3;
		boundingBox.yOffset = height * 0.3;
	}

	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		new Smoke01(level, new Vector2D(position.x, position.y), duration / 2);
	}
}
