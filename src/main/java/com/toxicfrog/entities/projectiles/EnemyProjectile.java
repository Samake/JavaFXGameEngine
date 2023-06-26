package com.toxicfrog.entities.projectiles;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.enemies.Enemy;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class EnemyProjectile extends Projectile {

	private Player player;
	private Enemy enemy;

	public EnemyProjectile(Level level, String imagePath, Vector2D position, Vector2D targetPosition, double scale, double speed, int maxDistance, Player player, Enemy enemy) {
		super(level, imagePath, position, targetPosition, scale, speed, maxDistance);

		this.player = player;
		this.enemy = enemy;
		
		if (enemy != null) {
			movement.x = targetPosition.x - position.x;
			movement.y = targetPosition.y - position.y;
		} else {
			destroy();
		}
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);

		if (level != null && player != null && enemy != null && !enemy.isDeath) {
			if (checkCollission(player, ENTITYTYPE.PLAYER)) {
				player.hit((int) (enemy.damage * 0.5), null);
				destroy();
			}
		} else {
			destroy();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
