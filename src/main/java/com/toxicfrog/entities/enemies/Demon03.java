package com.toxicfrog.entities.enemies;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class Demon03 extends Enemy {

	public Demon03(Level level, Vector2D position, double rotation, Player player) {
		super(level, "enemies/enemy_02", position, rotation, 0.65, 1.8, player);
		
		boundingBox.widthScale = 0.2;
		boundingBox.heightScale = 0.3;
		boundingBox.yOffset = height * 0.225;
		
		difficult = 3;
		maxLife = 100;
		life = maxLife;
		damage = 12;
	}

	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		if (!isDeath) {
			
		}
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
