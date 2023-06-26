package com.toxicfrog.entities.characters;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class Character03 extends Player{

	public Character03(Level level, Vector2D position, double rotation) {
		super(level, "characters/char_03", position, rotation, 0.85, 5.5);

		boundingBox.widthScale = 0.225;
		boundingBox.heightScale = 0.375;
		boundingBox.yOffset = height * 0.175;
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
