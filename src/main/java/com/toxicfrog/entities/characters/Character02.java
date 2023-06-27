package com.toxicfrog.entities.characters;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;


public class Character02 extends Player{

	public Character02(Level level, Vector2D position, double rotation) {
		super(level, "characters/char_02", position, rotation, CoreBalance.CHARACTER_02_SCALE, CoreBalance.CHARACTER_02_SPEED);
		
		boundingBox.widthScale = 0.225;
		boundingBox.heightScale = 0.325;
		boundingBox.yOffset = height * 0.2;
		
		maxLife = CoreBalance.CHARACTER_02_LIFE;
		life = maxLife;
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
