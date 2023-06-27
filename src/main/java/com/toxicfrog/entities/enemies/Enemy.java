package com.toxicfrog.entities.enemies;

import com.toxicfrog.cache.Resources;
import com.toxicfrog.cache.SoundCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Entity;
import com.toxicfrog.entities.NPC;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.effects.Puff01;
import com.toxicfrog.entities.text.TextEntity;
import com.toxicfrog.enums.ENUMS.ANIMATION;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.loot.LootManager;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.sound.SoundManager;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class Enemy extends NPC {
	
	public Player player;
	public Vector2D targetPosition = new Vector2D();
	
	private long lastTick = System.currentTimeMillis();
	private int tickRate = Utils.randomInteger(250, 500);
	
	public double damage = 10;
	public int difficult = 1;
	
	private Line sightLine = new Line();
	private boolean isCollided = false;
	
	private boolean isHit = false;

	public Enemy(Level level, String animationSetPath, Vector2D position, double rotation, double scale, double speed, Player player) {
		super(level, ENTITYTYPE.ENEMY, null, animationSetPath, position, rotation, scale, speed, true);
		
		this.player = player;
		
		if (player != null) {
			targetPosition.x = player.position.x + Utils.randomInteger(-5, 5);
			targetPosition.y = player.position.y + Utils.randomInteger(-5, 5);
		}
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		if (!isDeath) {
			if (player != null && !player.isDeath) {
				long currentTick = System.currentTimeMillis();
				
				if (currentTick > lastTick + (tickRate * delta)) {
					lastTick = currentTick;
					
					if (!isCollided) {
						targetPosition.x = player.position.x + Utils.randomInteger(-5, 5);
						targetPosition.y = player.position.y + Utils.randomInteger(-5, 5);
					} else {
						checkAlternateTargetPoint();
					}
					
					if (checkCollission(player, ENTITYTYPE.PLAYER)) {
						player.hit(damage, null);
					}
					
					if (isHit) {
						isHit = false;
					}
				}
				
				for (Entity entity : level.collidingEntities) {
					isCollided = checkCollission(entity, ENTITYTYPE.OBJECT);
				}
				
				isWalking = false;
				
				if (!isCollided) {
					movement.clear();
				
					if (targetPosition.x < position.x) {
						if (boundingBox.getX() > 0) {
							movement.add(-speed, 0);
						}
	
						isWalking = true;
						isRolling = false;
					}
					
					if (targetPosition.x > position.x) {
						if (boundingBox.getX() + boundingBox.getWidth() < level.width) {
							movement.add(speed, 0);
						}
	
						isWalking = true;
						isRolling = false;
					}
					
					if (targetPosition.y < position.y) {
						if (boundingBox.getY() > 0) {
							movement.add(0, -speed);
						}

						isWalking = true;
						isRolling = false;
					}
					
					if (targetPosition.y > position.y) {
						if (boundingBox.getY() +  boundingBox.getHeight() < level.height) {
							movement.add(0, speed);
						}
						
						isWalking = true;
						isRolling = false;
					}
				}
				
				move(delta);
				
				if (targetPosition.x - 2 < position.x) {
					isFlipped = true;
				} else if (targetPosition.x + 2 > position.x) {
					isFlipped = false;
				}
				
				if (life < 0) {
					isDeath = true;
				}
				
				animationSet.speedModifier = speed;
				
				if (isWalking) {
					animationSet.setCurrentAnimation(ANIMATION.WALK);
				} else {
					animationSet.setCurrentAnimation(ANIMATION.IDLE);
				}
				
				if (isHit) {
					animationSet.setCurrentAnimation(ANIMATION.HIT);
				}
			} else {
				kill();
			}
		} else {
			kill();
		}
	}
	
	private void checkAlternateTargetPoint() {
		lastTick = System.currentTimeMillis();
		boolean isColliding = false;
		
		targetPosition.x = position.x + Utils.randomInteger(-100, 100);
		targetPosition.y = position.y + Utils.randomInteger(-100, 100);
		
		sightLine.setStartX(position.x);
		sightLine.setStartY(position.y);
		sightLine.setEndX(targetPosition.x);
		sightLine.setEndY(targetPosition.y);
		
		for (Entity entity : level.collidingEntities) {
			if (entity != null) {
				if (entity.type.equals(ENTITYTYPE.OBJECT)) {
					if (entity.boundingBox != null) {
						Shape intersect = Shape.intersect(sightLine, entity.boundingBox);

						if (intersect.getBoundsInParent().getWidth() > 0) {
							isColliding = true;
							break;
						}
					}
				}
			}
		}
		
		isCollided = isColliding;
	}

	@Override
	public void hit(double damage, Vector2D projVelo) {
		super.hit(damage, projVelo);
		
		if (!isDeath) {
			isHit = true;
			
			double damageValue = damage - armor;
			life -= damageValue;
			
			new TextEntity(level, String.valueOf((int) damageValue), new Vector2D(position.x + Utils.randomInteger(-5, 5), position.y + Utils.randomInteger(-5, 5)), new Color(1.0, 1.0, 1.0, 1.0), 1.0f);
		
			if (projVelo != null) {
				position.x += projVelo.x * 0.2;
				position.y += projVelo.y * 0.2;
			}
		}
	}
	
	@Override
	public void kill() {
		super.kill();
		
		isDeath = true;
		
		if (!player.isDeath) {
			new Puff01(level, new Vector2D(position.x, position.y), width);
			
			LootManager.generateLoot(level, player, new Vector2D(position.x, position.y), difficult);
			SoundManager.playSound(SoundCache.getSound(Resources.EFFECT_ENEMY_DEATH), InternalSettings.VOLUME_ENEMY_DEATH, false);
			
			level.statistic.killCount += 1;
		}

		destroy();
	}
	
	@Override
	public void destroy() {
		super.destroy();

	}
}
