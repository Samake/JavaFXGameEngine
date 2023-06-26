package com.toxicfrog.entities.projectiles;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Entity;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.enemies.Enemy;
import com.toxicfrog.entities.weapons.Weapon;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class PlayerProjectile extends Projectile {

	public Player player;
	public Weapon weapon;
	public int puncture = 1;
	
	private Entity lastIntersectedEnemy = null;
	
	public PlayerProjectile(Level level, String imagePath, Vector2D position, Vector2D targetPosition, double scale, double speed, int maxDistance, Player player, Weapon weapon) {
		super(level, imagePath, position, targetPosition, scale, speed, maxDistance);

		this.player = player;
		this.weapon = weapon;
		
		if (player != null) {
			if (player.isFlipped) {
				this.position.x = position.x - (player.width * 0.085);
				this.startPosition.x = position.x - (player.width * 0.085);
			} else {
				this.position.x = position.x + (player.width * 0.085);
				this.startPosition.x = position.x + (player.width * 0.085);
			}

			this.position.y = position.y;
			this.startPosition.y = position.y;
			
			movement.x = targetPosition.x - position.x;
			movement.y = targetPosition.y - position.y;
		}  
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);

		if (level != null && player != null && weapon != null) {
			for (Entity entity : level.collidingEntities) {
				if (!entity.equals(lastIntersectedEnemy)) {
					if (checkCollission(entity, ENTITYTYPE.ENEMY)) {
						Enemy enemy = (Enemy) entity;
						
						if (enemy != null) {
							enemy.hit(weapon.damage, velocity);

				            puncture -= 1;
				            lastIntersectedEnemy = entity;
				            
				            if (puncture <= 0) {
				            	destroy();
				            	break;
				            }	
						}
					}
				}
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
