package com.toxicfrog.entities.projectiles;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.enemies.Enemy;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class EnemyShot extends EnemyProjectile {

	public EnemyShot(Level level, Vector2D position, Vector2D targetPosition, Player player, Enemy enemy) {
		super(level, "extras/bulletEnemy.png", position, targetPosition, 0.15, 5.0, 500, player, enemy);
		
		boundingBox.widthScale = 0.5;
		boundingBox.heightScale = 0.5;
		boundingBox.yOffset = height * 0.0;

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
