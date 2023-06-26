package com.toxicfrog.entities.effects;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class Smoke01 extends Effect {

	public Smoke01(Level level, Vector2D position, int duration) {
		super(level, "effects/smoke01", 6, "SMOKE_01", position, 0.35, Utils.randomFloat(55, 90), duration);

		boundingBox.widthScale = 0.4;
		boundingBox.heightScale = 0.65;
		boundingBox.yOffset = height * 0.175;
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
