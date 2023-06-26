package com.toxicfrog.entities.projectiles;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Entity;
import com.toxicfrog.entities.Object;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.settings.Path;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class Projectile extends Object {


	public Vector2D startPosition = new Vector2D();
	public Vector2D targetPosition;
	public int maxDistance;
	public int distance;

	public Projectile(Level level, String imagePath, Vector2D position, Vector2D targetPosition, double scale, double speed, int maxDistance) {
		super(level, ENTITYTYPE.PROJECTILE, imagePath, null, position, 0, scale, speed, false);

		this.startPosition = new Vector2D(position);
		this.targetPosition = targetPosition;
		this.maxDistance = maxDistance;

		if (imagePath != null) {
			image = ImageCache.getImage(Path.TEXTURE_PATH + imagePath, null);
			
			level.addEntity(this);
		} else {
			destroy();
		}
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);

		if (movement.x != 0 || movement.y != 0) {
			double mag = Math.sqrt(movement.x * movement.x + movement.y * movement.y);
			velocity.x = (movement.x / mag) * speed;
			velocity.y = (movement.y / mag) * speed;
		}
		
		distance = (int) Utils.computeDistanceOfTwoPoints((int) position.x, (int) position.y, (int) startPosition.x, (int) startPosition.y);
		
		if (distance <= maxDistance) {
			position.add(velocity);
		} else {
			destroy();
		}
		
		if (level != null) {
			for (Entity entity : level.collidingEntities) {
				if (checkCollission(entity, ENTITYTYPE.OBJECT)) {
					destroy();
	            	break;
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
