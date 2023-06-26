package com.toxicfrog.entities.damagezones;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Entity;
import com.toxicfrog.entities.Object;
import com.toxicfrog.entities.effects.Fire01;
import com.toxicfrog.entities.enemies.Enemy;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class FireZone extends Object {
	
	private long lastTick = 0;
	private long lastTickDuration = 0;
	
	private int duration = 3500;
	private int radius = 50;
	private int maxFires = 5;
	private int damage = 0;
	
	public FireZone(Level level, Vector2D position, int damage) {
		super(level, ENTITYTYPE.DAMAGEZONE, null, null, position, 0, 1.0, 0.0, false);
		
		this.damage = damage;
		
		isRendered = false;
		
		lastTick = System.currentTimeMillis();
		lastTickDuration = System.currentTimeMillis();
		
		for (int i = 0; i < maxFires; i++) {
			new Fire01(level, new Vector2D(position.x + Utils.randomInteger(-radius, radius), position.y + Utils.randomInteger(-radius, radius)), duration);
		}
		
		level.addEntity(this);
	}

	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		long currentTick = System.currentTimeMillis();
		
		if (currentTick > lastTick + (500 * delta)) {
			for (Entity entity : level.collidingEntities) {
				if (entity != null && entity.type.equals(ENTITYTYPE.ENEMY)) {
					Enemy enemy = (Enemy) entity;
					
					if (enemy != null) {
						int distance = (int) Utils.computeDistanceOfTwoPoints((int) position.x, (int) position.y, (int) entity.position.x, (int) entity.position.y);
						
						if (distance < radius * 2) {
							
							enemy.hit(damage, null);
					
							new Fire01(level, entity.position, 1000);
						}
					}
				}
			}
			
			lastTick = currentTick;
		}
		
		if (currentTick > lastTickDuration + (duration * delta)) {
			lastTickDuration = currentTick;
			destroy();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
