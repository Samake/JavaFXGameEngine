package com.toxicfrog.entities.enemies;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class Demon02 extends Enemy {

	public Demon02(Level level, Vector2D position, double rotation, Player player) {
		super(level, "enemies/enemy_04", position, rotation, 0.6, 1.3, player);

		boundingBox.widthScale = 0.275;
		boundingBox.heightScale = 0.325;
		boundingBox.yOffset = height * 0.2;
		
		difficult = 2;
		maxLife = 65;
		life = maxLife;
		damage = 8;
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
