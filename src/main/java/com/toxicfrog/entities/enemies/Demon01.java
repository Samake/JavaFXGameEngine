package com.toxicfrog.entities.enemies;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class Demon01 extends Enemy {

	public Demon01(Level level, Vector2D position, double rotation, Player player) {
		super(level, "enemies/enemy_01", position, rotation, 0.55, 1.2, player);

		boundingBox.widthScale = 0.25;
		boundingBox.heightScale = 0.35;
		boundingBox.yOffset = height * 0.2;
		
		difficult = 1;
		maxLife = 35;
		life = maxLife;
		damage = 5;
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
