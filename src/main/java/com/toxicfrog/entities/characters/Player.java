package com.toxicfrog.entities.characters;

import com.toxicfrog.cache.Resources;
import com.toxicfrog.cache.SoundCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.NPC;
import com.toxicfrog.entities.text.TextEntity;
import com.toxicfrog.enums.ENUMS.ANIMATION;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.sound.SoundManager;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends NPC {
	
	public double maxEnergy = 100;
	public double energy = 100;
	public double energyRecover = 0.5;
	public double lifeRecover = 0.05;
	public double magnify = 0.0;
	public int playerLevel = 0;
	public int maxExp = 50;
	public int exp = 0;
	
	private long lastRollTick = 0;
	
	public Player(Level level, String animationSetPath, Vector2D position, double rotation, double scale, double speed) {
		super(level, ENTITYTYPE.PLAYER, null, animationSetPath, position, rotation, scale, speed, true);
		
		maxLife = 100;
		life = maxLife;
		maxEnergy = 100;
		energy = maxEnergy;
		energyRecover = 0.25;
		lifeRecover = 0.015;
		magnify = 0.0;
		playerLevel = 0;
		maxExp = 50;
		exp = 0;
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		translateCamera(camera);
		
		long currentTick = System.currentTimeMillis();
		
		if (!isDeath) {
			isWalking = false;
			isRunning = false;
			isFlipped = false;
			
			movement.clear();

			if (input.mousePosition.x <= input.middle.x) {
				isFlipped = true;
			}
			
			if (input.isKeyPressed(KeyCode.SHIFT)) {
				if (energy > 0) {
					isRunning = true;
				}
			}
			
			if (input.isKeyPressed(KeyCode.SPACE)) {
				if (energy > 0) {
					roll();
				}
			}
			
			if (isRunning) {
				if (energy > 0) {
					speed *= 1.5;
					energy -= (0.5 * delta);
				} else {
					isRunning = false;
				}
			}
			
			if (isRolling) {
				if (energy > 0) {
					speed *= 2.5;
					energy -= (0.75 * delta);
				} else {
					isRolling = false;
				}
				
				if (lastRollTick + animationSet.getDuration() <= currentTick) {
					isRolling = false;
				}
			}
			
			if (energy < maxEnergy) {
				energy += energyRecover * delta;
				
				if (energy >= maxEnergy) {
					energy = maxEnergy;
				}
				
				if (energy <= 0) {
					energy = 0;
				}
			}
			
			if (life < maxLife) {
				life += lifeRecover * delta;
				
				if (life >= maxLife) {
					life = maxLife;
				}
				
				if (life <= 0) {
					life = 0;
					kill();
				}
			}
			
			if (exp >= maxExp) {
				levelUp();
			}
			
			if (input.isKeyPressed(KeyCode.A)) {
				if (boundingBox.getX() > 0) {
					movement.add(-speed, 0);
				}
				
				isWalking = true;
			}
			
			if (input.isKeyPressed(KeyCode.D)) {
				if (boundingBox.getX() + boundingBox.getWidth() < level.width) {
					movement.add(speed, 0);
				}
				
				isWalking = true;
			}
			
			if (input.isKeyPressed(KeyCode.W)) {
				if (boundingBox.getY() > 0) {
					movement.add(0, -speed);
				}

				isWalking = true;
			}
			
			if (input.isKeyPressed(KeyCode.S)) {
				if (boundingBox.getY() +  boundingBox.getHeight() < level.height) {
					movement.add(0, speed);
				}
				
				isWalking = true;
			}
			
			move();
		}
		
		handleAnimation();
	}
	
	private void translateCamera(Camera camera) {
		if (camera != null) {
			double xCap = (Settings.WINDOW_WIDTH / 2) - (width * 2) - boundingBox.getWidth();
			double yCap = Settings.WINDOW_HEIGHT - (height * 2.15) - boundingBox.getHeight();
			
			double camMinX = xCap;
			double camMaxX = level.width - Settings.WINDOW_WIDTH;
			double camMinY = yCap;
			double camMaxY = level.height - Settings.WINDOW_HEIGHT;
			
			double xCamPos = position.x - (Settings.WINDOW_WIDTH / 2);
			double yCamPos = position.y - (Settings.WINDOW_HEIGHT / 2);
			
			if (xCamPos > camMinX && xCamPos < camMaxX) {
				camera.position.x = xCamPos;
			}
			
			if (yCamPos > camMinY  && yCamPos < camMaxY) {
				camera.position.y = yCamPos;
			}
		}
	}

	private void handleAnimation() {
		animationSet.speedModifier = speed;
		
		if (!isDeath) {
			animationSet.setCurrentAnimation(ANIMATION.IDLE);
			
			if (isWalking) {
				animationSet.setCurrentAnimation(ANIMATION.WALK);
			} 
			
			if (isRolling) {
				animationSet.setCurrentAnimation(ANIMATION.ROLL);
			}
		} else {
			animationSet.setCurrentAnimation(ANIMATION.DEATH);
		}
	}
	
	private void levelUp() {
		if (!isDeath) {
			playerLevel += 1;
			exp = 0;
			
			new TextEntity(level, "Level Up!", new Vector2D(position.x + Utils.randomInteger(-5, 5), position.y + Utils.randomInteger(-5, 5)), new Color(0.4, 1.0, 0.4, 1.0), 5.0);
			SoundManager.playSound(SoundCache.getSound(Resources.EFFECT_LEVEL_UP), InternalSettings.VOLUME_LEVEL_UP, false);
		}
	}
	
	private void roll() {
		if (!isRolling) {
			isRolling = true;
			
			lastRollTick = System.currentTimeMillis();
		}
	}

	@Override
	public void hit(double damage, Vector2D projVelo) {
		super.hit(damage, projVelo);
		
		if (!isDeath) {
			if (!InternalSettings.GOD_MODE) {
				double damageValue = damage - armor;
				life -= damageValue;
				
				new TextEntity(level, String.valueOf((int) damageValue), new Vector2D(position.x + Utils.randomInteger(-5, 5), position.y + Utils.randomInteger(-5, 5)), new Color(1.0, 0.4, 0.4, 1.0), 5.0);
			}
		}
	}
	
	@Override
	public void kill() {
		super.kill();
		
		if (!isDeath) {
			isDeath = true;

			life = 0;
			energy = 0;
			
			movement.clear();
			velocity.clear();
		}
	}
	
	public void addExp(int amount) {
		if (!isDeath) {
			exp += amount;
		}
	}
	
	public void heal(int amount) {
		if (!isDeath) {
			life += amount;
			
			if (life >= maxLife) {
				life = maxLife;
			}
		}
	}

	@Override
	public void destroy() {
		super.destroy();
		
	}
}
