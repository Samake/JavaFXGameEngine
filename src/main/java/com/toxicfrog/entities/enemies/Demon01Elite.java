package com.toxicfrog.entities.enemies;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.projectiles.EnemyShot;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class Demon01Elite extends Enemy {
	
	private long lastTick = 0;

	public Demon01Elite(Level level, Vector2D position, double rotation, Player player) {
		super(level, "enemies/enemy_01", position, rotation, CoreBalance.DEMON_ELITE_01_SCALE, Utils.randomDouble(CoreBalance.DEMON_ELITE_01_SPEED - 0.25, CoreBalance.DEMON_ELITE_01_SPEED + 0.25), player);
		
		boundingBox.widthScale = 0.25;
		boundingBox.heightScale = 0.35;
		boundingBox.yOffset = height * 0.2;
		
		difficult = 2;
		maxLife = CoreBalance.DEMON_ELITE_01_LIFE;
		life = maxLife;
		damage = CoreBalance.DEMON_ELITE_01_DAMAGE;
		
		lastTick = System.currentTimeMillis();
	}

	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);

		if (!isDeath) {
			long currentTick = System.currentTimeMillis();
			
			if (currentTick >= lastTick + 3000 * delta) {
				attack();
				
				lastTick = currentTick;
			}
		}
	}

	private void attack() {
		movement.clear();
		velocity.clear();
		
		new EnemyShot(level, new Vector2D(position.x, position.y + boundingBox.yOffset), Utils.getPositionArroundVector(position, 0, 9999), player, this);
		new EnemyShot(level, new Vector2D(position.x, position.y + boundingBox.yOffset), Utils.getPositionArroundVector(position, 45, 9999), player, this);
		new EnemyShot(level, new Vector2D(position.x, position.y + boundingBox.yOffset), Utils.getPositionArroundVector(position, 90, 9999), player, this);
		new EnemyShot(level, new Vector2D(position.x, position.y + boundingBox.yOffset), Utils.getPositionArroundVector(position, 135, 9999), player, this);
		new EnemyShot(level, new Vector2D(position.x, position.y + boundingBox.yOffset), Utils.getPositionArroundVector(position, 180, 9999), player, this);
		new EnemyShot(level, new Vector2D(position.x, position.y + boundingBox.yOffset), Utils.getPositionArroundVector(position, 225, 9999), player, this);
		new EnemyShot(level, new Vector2D(position.x, position.y + boundingBox.yOffset), Utils.getPositionArroundVector(position, 270, 9999), player, this);
		new EnemyShot(level, new Vector2D(position.x, position.y + boundingBox.yOffset), Utils.getPositionArroundVector(position, 315, 9999), player, this);
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
