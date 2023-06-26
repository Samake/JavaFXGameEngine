package com.toxicfrog.entities.projectiles;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.weapons.Weapon;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class RifleShot extends PlayerProjectile {

	public RifleShot(Level level, Vector2D position, Vector2D targetPosition, Player player, Weapon weapon) {
		super(level, "extras/bullet.png", position, targetPosition, 0.1, 35.0, 1000, player, weapon);
		
		boundingBox.widthScale = 0.4;
		boundingBox.heightScale = 0.4;
		boundingBox.yOffset = height * 0.0;
		
		puncture = 2;
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
