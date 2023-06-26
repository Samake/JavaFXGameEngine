package com.toxicfrog.entities.characters;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class Character04 extends Player {

	public Character04(Level level, Vector2D position, double rotation) {
		super(level, "characters/char_04", position, rotation, 0.85, 4.5);

		boundingBox.widthScale = 0.225;
		boundingBox.heightScale = 0.3;
		boundingBox.yOffset = height * 0.225;
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
	}
	
	@Override
	public void hit(double damage, Vector2D projVelo) {
		super.hit(damage, projVelo);

	}
	
	@Override
	public void kill() {
		super.kill();
		
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
