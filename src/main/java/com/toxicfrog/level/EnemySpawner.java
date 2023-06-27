package com.toxicfrog.level;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.enemies.Bat01;
import com.toxicfrog.entities.enemies.Demon01;
import com.toxicfrog.entities.enemies.Demon01Elite;
import com.toxicfrog.entities.enemies.Demon02;
import com.toxicfrog.entities.enemies.Demon03;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class EnemySpawner {
	
	public int dangerLevel = 0;
	
	private Level level;
	private Player player;
	
	private int maxEnemies = 0;
	private int additionalEnemies = 0;
	
	private int tickCount = 25000;
	private long lastTickMain = 0;
	private long lastTickPermn = 0;
	
	private boolean isStarted = true;
	
	public EnemySpawner(Level level, Player player, int duration) {
		this.level = level;
		this.player = player;
		this.tickCount = duration / 4;
		
		lastTickMain = System.currentTimeMillis();
		lastTickPermn = System.currentTimeMillis();
		
		if (isStarted) {
			initFirstSpawn();
		}
	}

	public void update() {
		if (isStarted && player != null && !player.isDeath) {
			long currentTick = System.currentTimeMillis();
			
			if (currentTick > lastTickMain + tickCount) {
				if (dangerLevel < 3) {
					dangerLevel += 1;
					additionalEnemies = 0;
				}
				
				lastTickMain = currentTick;
			}
			
			maxEnemies = (dangerLevel + 1) * CoreBalance.NPC_MAX_SPAWN_VALUE;
			
			if (currentTick > lastTickPermn + CoreBalance.NPC_SPAWN_TICKCOUNT) {
				additionalEnemies += 1;
				
				lastTickPermn = currentTick;
				
				int maxEnemiesTotal = maxEnemies + additionalEnemies;
				int missedEnemies = maxEnemiesTotal - level.currentEmenyCount;
				
				if (missedEnemies > 0) {
					for (int i = 0; i < missedEnemies; i++) {
						Vector2D position = Utils.getPositionArroundVector(player.position, Utils.randomDouble(0, 360), Utils.randomDouble(CoreBalance.NPC_MIN_SPAWN_DISTANCE, CoreBalance.NPC_MAX_SPAWN_DISTANCE));
						
						if (position.x >= 0 && position.x <= level.width) {
							if (position.y >= 0 && position.y <= level.height) {
								addRandomEnemy(Utils.randomInteger(0, dangerLevel), position);
							}
						}
					}
				}
			}
		}
	}
	
	private void initFirstSpawn() {
		for (int i = 0; i < maxEnemies; i++) {
			Vector2D position = Utils.getPositionArroundVector(player.position, Utils.randomDouble(0, 360), Utils.randomDouble(CoreBalance.NPC_MIN_SPAWN_DISTANCE, CoreBalance.NPC_MAX_SPAWN_DISTANCE));
			
			if (position.x >= 0 && position.x <= level.width) {
				if (position.y >= 0 && position.y <= level.height) {
					addRandomEnemy(Utils.randomInteger(0, dangerLevel), position);
				}
			}
		}
	}
	
	private void addRandomEnemy(int randomValue, Vector2D position) {
		switch(randomValue) {
			case 0 :
				int elitePercent = Utils.randomInteger(0, 1000);
				
				if (elitePercent >= CoreBalance.NPC_ELITE_SPAWN_VALUE) {
					level.addEntity(new Demon01Elite(level, position, 0.0, player));
				} else {
					level.addEntity(new Demon01(level, position, 0.0, player));
				}
				
				break;
			case 1 :
				level.addEntity(new Demon02(level, position, 0.0, player));
				break;
			case 2 :
				level.addEntity(new Demon03(level, position, 0.0, player));
				break;
			case 3 :
				level.addEntity(new Bat01(level, position, 0.0, player));
				break;
		}
	}
}
