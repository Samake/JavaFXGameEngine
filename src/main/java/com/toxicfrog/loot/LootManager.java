package com.toxicfrog.loot;

import java.util.ArrayList;
import java.util.List;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.entities.Entity;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.items.HealItem;
import com.toxicfrog.entities.items.XPGem;
import com.toxicfrog.level.Level;
import com.toxicfrog.settings.Path;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

import javafx.scene.image.Image;

public class LootManager {
	
	private static List<Image> blueGemTextures = new ArrayList<Image>();
	private static List<Image> greenGemTextures = new ArrayList<Image>();
	private static List<Image> purpleGemTextures = new ArrayList<Image>();
	private static List<Image> redGemTextures = new ArrayList<Image>();
	private static List<Image> yellowGemTexures = new ArrayList<Image>();
	
	private static List<XPGem> blueGems = new ArrayList<XPGem>();
	private static List<XPGem> blueGemsUpdateList;
	private static List<XPGem> greenGems = new ArrayList<XPGem>();
	private static List<XPGem> greenGemsUpdateList;
	private static List<XPGem> purpleGems = new ArrayList<XPGem>();
	private static List<XPGem> purpleGemsUpdateList;
	private static List<XPGem> redGems = new ArrayList<XPGem>();
	private static List<XPGem> redGemsUpdateList;
	private static List<XPGem> yellowGems = new ArrayList<XPGem>();
	private static List<XPGem> yellowGemsUpdateList;
	
	public static void init() {
		for (int i = 0; i < 5; i++) {
			blueGemTextures.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_blue" + "_" + i + ".png", null));
			greenGemTextures.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_green" + "_" + i + ".png", null));
			purpleGemTextures.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_purple" + "_" + i + ".png", null));
			redGemTextures.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_red" + "_" + i + ".png", null));
			yellowGemTexures.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_yellow" + "_" + i + ".png", null));
		}
	}
	
	public static void update(Level level, Player player, double delta) {
		if (player != null && !player.isDeath) {
			blueGemsUpdateList = new ArrayList<XPGem>(blueGems);
			greenGemsUpdateList = new ArrayList<XPGem>(greenGems);
			purpleGemsUpdateList = new ArrayList<XPGem>(purpleGems);
			redGemsUpdateList = new ArrayList<XPGem>(redGems);
			yellowGemsUpdateList = new ArrayList<XPGem>(yellowGems);
			
			combineGems(level, player);
		}
	}

	private static void combineGems(Level level, Player player) {
		if (blueGemsUpdateList.size() > 20) {
			int amount = 0;
			
			for (int i = 0; i < 10; i++) {
				XPGem gem = blueGemsUpdateList.get(i);
				
				if (gem != null) {
					amount += gem.amount;
					gem.destroy();
				}
			}
			
			Vector2D position = new Vector2D(player.position.x + Utils.randomInteger(- 50, 50), player.position.y + Utils.randomInteger(- 50, 50));
			
			createGreenGem(level, player, position, amount);
		}
		
		if (greenGemsUpdateList.size() > 20) {
			int amount = 0;
			
			for (int i = 0; i < 10; i++) {
				XPGem gem = greenGemsUpdateList.get(i);
				
				if (gem != null) {
					amount += gem.amount;
					gem.destroy();
				}
			}
			
			Vector2D position = new Vector2D(player.position.x + Utils.randomInteger(-Settings.WINDOW_WIDTH / 2, Settings.WINDOW_WIDTH / 2), player.position.y + Utils.randomInteger(-Settings.WINDOW_HEIGHT / 2, Settings.WINDOW_HEIGHT / 2));
			
			createPurpleGem(level, player, position, amount);
		}
		
		if (purpleGemsUpdateList.size() > 20) {
			int amount = 0;
			
			for (int i = 0; i < 10; i++) {
				XPGem gem = purpleGemsUpdateList.get(i);
				
				if (gem != null) {
					amount += gem.amount;
					gem.destroy();
				}
			}
			
			Vector2D position = new Vector2D(player.position.x + Utils.randomInteger(- 50, 50), player.position.y + Utils.randomInteger(- 50, 50));
			
			createRedGem(level, player, position, amount);
		}
		
		if (redGemsUpdateList.size() > 20) {
			int amount = 0;
			
			for (int i = 0; i < 10; i++) {
				XPGem gem = redGemsUpdateList.get(i);
				
				if (gem != null) {
					amount += gem.amount;
					gem.destroy();
				}
			}
			
			Vector2D position = new Vector2D(player.position.x + Utils.randomInteger(- 50, 50), player.position.y + Utils.randomInteger(- 50, 50));
			
			createYellowGem(level, player, position, amount);
		}
	}
	
	public static void remove(Entity entity) {
		if (blueGemsUpdateList != null) {
			for (XPGem gem : blueGemsUpdateList) {
				if (gem != null && entity != null) {
					if (gem.equals(entity)) {
						blueGems.remove(gem);
					}
				}
			}
		}
		
		if (greenGemsUpdateList != null) {
			for (XPGem gem : greenGemsUpdateList) {
				if (gem != null && entity != null) {
					if (gem.equals(entity)) {
						greenGems.remove(gem);
					}
				}
			}
		}
		
		if (purpleGemsUpdateList != null) {
			for (XPGem gem : purpleGemsUpdateList) {
				if (gem != null && entity != null) {
					if (gem.equals(entity)) {
						purpleGems.remove(gem);
					}
				}
			}
		}
		
		if (redGemsUpdateList != null) {
			for (XPGem gem : redGemsUpdateList) {
				if (gem != null && entity != null) {
					if (gem.equals(entity)) {
						redGems.remove(gem);
					}
				}
			}
		}
		
		if (yellowGemsUpdateList != null) {
			for (XPGem gem : yellowGemsUpdateList) {
				if (gem != null && entity != null) {
					if (gem.equals(entity)) {
						yellowGems.remove(gem);
					}
				}
			}
		}
	}
	
	public static void reset() {
		blueGems.clear();
		greenGems.clear();
		purpleGems.clear();
		redGems.clear();
		yellowGems.clear();
	}

	public static void generateLoot(Level level, Player player, Vector2D position, int difficult) {
		if (level != null && player != null) {
			int gemID = getRandomGemID(difficult);
			
			switch(gemID) {
				case 1:
					createBlueGem(level, player, position, CoreBalance.BLUE_GEM_XP);
					break;
				case 2:
					createGreenGem(level, player, position, CoreBalance.GREEN_GEM_XP);
					break;
				case 3:
					createPurpleGem(level, player, position, CoreBalance.PURPLE_GEM_XP);
					break;
				case 4:
					createRedGem(level, player, position, CoreBalance.RED_GEM_XP);
					break;
				case 5:
					createYellowGem(level, player, position, CoreBalance.YELLOW_GEM_XP);
					break;
			}
			
			if (player.life < player.maxLife) {
				int randomValue = Utils.randomInteger(0, 1000);
				
				if (randomValue <= 15) {
					new HealItem(level, player, position);
				}
			}
		}
	}

	private static void createBlueGem(Level level, Player player, Vector2D position, int amount) {
		blueGems.add(new XPGem(level, player, position, blueGemTextures.get(Utils.randomInteger(0, blueGemTextures.size() - 1)), amount));
	}
	
	private static void createGreenGem(Level level, Player player, Vector2D position, int amount) {
		greenGems.add(new XPGem(level, player, position, greenGemTextures.get(Utils.randomInteger(0, greenGemTextures.size() - 1)), amount));
	}

	private static void createPurpleGem(Level level, Player player, Vector2D position, int amount) {
		purpleGems.add(new XPGem(level, player, position, purpleGemTextures.get(Utils.randomInteger(0, purpleGemTextures.size() - 1)), amount));
	}
	
	private static void createRedGem(Level level, Player player, Vector2D position, int amount) {
		redGems.add(new XPGem(level, player, position, redGemTextures.get(Utils.randomInteger(0, redGemTextures.size() - 1)), amount));
	}
	
	private static void createYellowGem(Level level, Player player, Vector2D position, int amount) {
		yellowGems.add(new XPGem(level, player, position, yellowGemTexures.get(Utils.randomInteger(0, yellowGemTexures.size() - 1)), amount));
	}
	
	private static int getRandomGemID(int difficult) {
		int gemID = 1;
		
		if (difficult <= 1) {
			int randomValue = Utils.randomInteger(0, 100);
			
			if (randomValue > 15) {
				gemID = 1;
			} else {
				gemID = 2;
			}
		}
		
		if (difficult == 2) {
			int randomValue = Utils.randomInteger(0, 100);
			
			if (randomValue > 15) {
				gemID = 2;
			} else {
				gemID = 3;
			}
		}
		
		if (difficult == 3) {
			int randomValue = Utils.randomInteger(0, 100);
			
			if (randomValue > 15) {
				gemID = 3;
			} else {
				gemID = 4;
			}			
		}
		
		if (difficult >= 4) {
			int randomValue = Utils.randomInteger(0, 100);
			
			if (randomValue > 15) {
				gemID = 4;
			} else {
				gemID = 5;
			}
		}
		
		return gemID;
	}
}
