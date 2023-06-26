package com.toxicfrog.level;

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
		if (isStarted) {
			long currentTick = System.currentTimeMillis();
			
			if (currentTick > lastTickMain + tickCount) {
				if (dangerLevel < 3) {
					dangerLevel += 1;
					additionalEnemies = 0;
				}
				
				lastTickMain = currentTick;
			}
			
			maxEnemies = (dangerLevel + 1) * 15;
			
			if (currentTick > lastTickPermn + 3000) {
				additionalEnemies += 1;
				
				lastTickPermn = currentTick;
				
				int maxEnemiesTotal = maxEnemies + additionalEnemies;
				int missedEnemies = maxEnemiesTotal - level.currentEmenyCount;
				
				if (missedEnemies > 0) {
					for (int i = 0; i < missedEnemies; i++) {
						addRandomEnemy(Utils.randomInteger(0, dangerLevel));
					}
				}
			}
		}
	}
	
	private void initFirstSpawn() {
		for (int i = 0; i < maxEnemies; i++) {
			addRandomEnemy(Utils.randomInteger(0, dangerLevel));
		}
	}
	
	private void addRandomEnemy(int randomValue) {
		switch(randomValue) {
			case 0 :
				int elitePercent = Utils.randomInteger(0, 100);
				
				if (elitePercent >= 90) {
					level.addEntity(new Demon01Elite(level, new Vector2D(Utils.randomInteger(0, (int) level.width), Utils.randomInteger(0, (int) level.height)), 0.0, player));
				} else {
					level.addEntity(new Demon01(level, new Vector2D(Utils.randomInteger(0, (int) level.width), Utils.randomInteger(0, (int) level.height)), 0.0, player));
				}
				break;
			case 1 :
				level.addEntity(new Demon02(level, new Vector2D(Utils.randomInteger(0, (int) level.width), Utils.randomInteger(0, (int) level.height)), 0.0, player));
				break;
			case 2 :
				level.addEntity(new Demon03(level, new Vector2D(Utils.randomInteger(0, (int) level.width), Utils.randomInteger(0, (int) level.height)), 0.0, player));
				break;
			case 3 :
				level.addEntity(new Bat01(level, new Vector2D(Utils.randomInteger(0, (int) level.width), Utils.randomInteger(0, (int) level.height)), 0.0, player));
				break;
		}
	}
}
