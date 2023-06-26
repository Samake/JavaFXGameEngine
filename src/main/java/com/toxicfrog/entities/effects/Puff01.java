package com.toxicfrog.entities.effects;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class Puff01 extends Effect {

	public Puff01(Level level, Vector2D position, double size) {
		super(level, "effects/death", 3, "PUFF_01", position, 0.35, 50, 300);

		boundingBox.widthScale = 0.4;
		boundingBox.heightScale = 0.4;
		boundingBox.yOffset = height * 0.15;
		
		width = size;
		height = size;
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
