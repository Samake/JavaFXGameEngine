package com.toxicfrog.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.core.GameLauncher;
import com.toxicfrog.entities.Entity;
import com.toxicfrog.entities.Object;
import com.toxicfrog.entities.characters.Character01;
import com.toxicfrog.entities.characters.Character02;
import com.toxicfrog.entities.characters.Character03;
import com.toxicfrog.entities.characters.Character04;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.objects.Rock01;
import com.toxicfrog.entities.objects.Rock02;
import com.toxicfrog.entities.objects.Rock03;
import com.toxicfrog.entities.text.TextEntity;
import com.toxicfrog.entities.weapons.Pistol;
import com.toxicfrog.entities.weapons.Rifle;
import com.toxicfrog.entities.weapons.ShotGun;
import com.toxicfrog.entities.weapons.Wand;
import com.toxicfrog.entities.weapons.Weapon;
import com.toxicfrog.enums.ENUMS.CHARACTER;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.enums.ENUMS.WEAPON;
import com.toxicfrog.gui.GameScene;
import com.toxicfrog.logging.Log;
import com.toxicfrog.loot.LootManager;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.settings.Path;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

import javafx.scene.image.Image;

public class Level {

	public int width;
	public int height;
	public long duration;
	public Statistic statistic;
	public EnemySpawner enemySpawner;
	
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Entity> updatedEntities = new ArrayList<Entity>();
	public List<Entity> renderList = new ArrayList<Entity>();

	public List<TextEntity> textEntities = new ArrayList<TextEntity>();
	public List<TextEntity> updatedTextEntities = new ArrayList<TextEntity>();
	public List<TextEntity> renderListText = new ArrayList<TextEntity>();
	
	public List<Entity> collidingEntities = new ArrayList<Entity>();
	
	public Image backgroundImage;
	public Player player = null;
	
	public int dangerLevel = 0;
	public int currentEmenyCount = 0;
	
	public boolean isRunning = false;
	
	private long lastTick = System.currentTimeMillis();
	
	public Level(int width, int height, CHARACTER character, WEAPON weapon, long duration) {
		this.width = width;
		this.height = height;
		this.duration = duration;
		
		start(width, height, character, weapon, duration);
		
		Log.print("Level were started. Size: " + width + "x" + height + ", Duration: " + getDuration());
	}

	private void start(int width, int height, CHARACTER character, WEAPON weapon, long duration) {
		backgroundImage = new Image(Path.TEXTURE_PATH + "environment/ground2_dark.png", InternalSettings.DEFAULT_TEXTURE_SIZE * 2, InternalSettings.DEFAULT_TEXTURE_SIZE * 2, false, false);
		
		statistic = new Statistic();

		player = addPlayerToLevel(width, height, character);
		
		if (player != null) {
			addPlayerWeapon(weapon, player);
			addObjectsToLevel(width, height);
			
			enemySpawner = new EnemySpawner(this, player, (int) duration);
		} else {
			GameLauncher.stopGame();
		}
		
		lastTick = System.currentTimeMillis();
		
		LootManager.reset();
		
		isRunning = true;
	}

	private Player addPlayerToLevel(double width, double height, CHARACTER character) {
		switch(character) {
			case CHAR_01:
				player = new Character01(this, new Vector2D(width / 2, height / 2), 0.0f);
				addEntity(player);
				break;
			case CHAR_02:
				player = new Character02(this, new Vector2D(width / 2, height / 2), 0.0f);
				addEntity(player);
				break;
			case CHAR_03:
				player = new Character03(this, new Vector2D(width / 2, height / 2), 0.0f);
				addEntity(player);
				break;
			case CHAR_04:
				player = new Character04(this, new Vector2D(width / 2, height / 2), 0.0f);
				addEntity(player);
				break;
		}
		
		return player;
	}
	
	private Weapon addPlayerWeapon(WEAPON weaponType, Player player) {
		Weapon weapon = null;
		
		switch(weaponType) {
			case RIFLE :
				weapon = new Rifle(this, player);
				addEntity(weapon);
				break;
			case PISTOL :
				weapon = new Pistol(this, player);
				addEntity(weapon);
				break;
			case SHOTGUN :
				weapon = new ShotGun(this, player);
				addEntity(weapon);
				break;
			case WAND :
				weapon = new Wand(this, player);
				addEntity(weapon);
				break;
		}
		
		return weapon;	
	}

	private void addObjectsToLevel(double width, double height) {
		for (int i = 0; i < 15; i++) {
			addEntity(new Rock01(this, new Vector2D(Utils.randomInteger(0, (int) width), Utils.randomInteger(0, (int) height))));
		}
		
		for (int i = 0; i < 15; i++) {
			addEntity(new Rock02(this, new Vector2D(Utils.randomInteger(0, (int) width), Utils.randomInteger(0, (int) height))));
		}
		
		for (int i = 0; i < 15; i++) {
			addEntity(new Rock03(this, new Vector2D(Utils.randomInteger(0, (int) width), Utils.randomInteger(0, (int) height))));
		}
	}

	public void update(GameScene scene, double delta) {
		renderList.clear();
		updatedEntities.clear();
		updatedTextEntities.clear();;
		renderListText.clear();;
		
		collidingEntities.clear();
		
		updatedEntities.addAll(entities);
		updatedTextEntities.addAll(textEntities);

		currentEmenyCount = 0;
		
		for (Entity entity : updatedEntities) {
			if (entity != null) {
				if (entity.type.equals(ENTITYTYPE.ENEMY)) {
					collidingEntities.add(entity);
					currentEmenyCount += 1;
				}

				if (entity.type.equals(ENTITYTYPE.OBJECT)) {
					Object entityObject = (Object) entity;
					
					if (entityObject.hasCollission) {
						collidingEntities.add(entity);
					}
				}
			}
		}
		
		for (Entity entity : updatedEntities) {
			if (entity != null) {
				entity.update(scene.input, scene.camera, delta);
				
				if (checkIfInScene(entity, scene.camera)) {
					renderList.add(entity);
				};
			}
		}
		
		for (TextEntity entity : updatedTextEntities) {
			if (entity != null) {
				entity.update(scene.input, scene.camera, delta);
				
				if (checkIfInScene(entity, scene.camera)) {
					renderListText.add(entity);
				};
			}
		}

		Collections.sort(renderList, new Comparator<Entity>() {
	        @Override
	        public int compare(Entity e1, Entity e2) {
	        	if (e1.boundingBox.sortHeight > e2.boundingBox.sortHeight) {
	        		return 1;
	        	} else if (e1.boundingBox.sortHeight < e2.boundingBox.sortHeight) {
	        		return -1;
	        	} else {
	        		return 0;
	        	}
	        }
	    });
		
		Collections.sort(renderListText, new Comparator<TextEntity>() {
	        @Override
	        public int compare(TextEntity e1, TextEntity e2) {
	        	if (e1.position.y > e2.position.y) {
	        		return 1;
	        	} else if (e1.position.y < e2.position.y) {
	        		return -1;
	        	} else {
	        		return 0;
	        	}
	        }
	    });
		
		if (player != null && !player.isDeath && isRunning) {
			long currentTick = System.currentTimeMillis();
			
			if (lastTick + 1000 * delta <= currentTick) {
				duration -= 1000 * delta;
				lastTick = currentTick;
				
				if (duration <= 0) {
					stop();
				}
			}
			
			if (enemySpawner != null) {
				enemySpawner.update();
				dangerLevel = enemySpawner.dangerLevel;
			}
		} else {
			stop();
		}
		
		LootManager.update(this, player, delta);
    }
	
	private boolean checkIfInScene(Entity entity, Camera camera) {
		if (entity != null && camera != null) {
			
			double screenXPos = entity.position.x - camera.position.x;
			double screenYPos = entity.position.y - camera.position.y;
			
			if (screenXPos + (entity.width / 2) >= 0 && screenXPos - (entity.width / 2) <= Settings.WINDOW_WIDTH) {
				if (screenYPos + (entity.height / 2) >= 0 && screenYPos - (entity.height / 2) <= Settings.WINDOW_HEIGHT) {
					return true;
				}
			}
		}
		
		return false;
	}

	private void stop() {
		List<Entity> deleteEntityList = new ArrayList<Entity>(entities);

		for (Entity entity : deleteEntityList) {
			if (entity != null) {
				if (!entity.type.equals(ENTITYTYPE.PLAYER)) {
					removeEntity(entity);	
				}
			}
		}
		
		List<TextEntity> deleteTextEntityList = new ArrayList<TextEntity>(textEntities);
		
		for (TextEntity entity : deleteTextEntityList) {
			if (entity != null) {
				removeTextEntity(entity);
			}
		}
		
		isRunning = false;
	}

	public void addEntity(Entity entity) {
		if (!entities.contains(entity)) {
			entities.add(entity);
		}
	}
	
	public void removeEntity(Entity entity) {
		if (entities.contains(entity)) {
			entities.remove(entity);
			
			LootManager.remove(entity);
			
			entity = null;
		}
	}
	
	public void addTextEntity(TextEntity entity) {
		if (!textEntities.contains(entity)) {
			textEntities.add(entity);
		}
	}
	
	public void removeTextEntity(TextEntity entity) {
		if (textEntities.contains(entity)) {
			textEntities.remove(entity);
			entity = null;
		}
	}
	
	public String getDuration() {
		long second = (duration / 1000) % 60;
		long minute = (duration / (1000 * 60)) % 60;

		return String.format("%02d:%02d", minute, second);
	}

	public void destroy() {
		List<Entity> deleteEntityList = new ArrayList<Entity>(entities);

		for (Entity entity : deleteEntityList) {
			if (entity != null) {
				removeEntity(entity);
			}
		}
		
		List<TextEntity> deleteTextEntityList = new ArrayList<TextEntity>(textEntities);
		
		for (TextEntity entity : deleteTextEntityList) {
			if (entity != null) {
				removeTextEntity(entity);
			}
		}
		
		renderList.clear();
		entities.clear();
		
		Log.print("Level were stopped.");
	}
}
