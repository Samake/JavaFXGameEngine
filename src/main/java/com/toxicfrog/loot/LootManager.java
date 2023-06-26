package com.toxicfrog.loot;

import java.util.ArrayList;
import java.util.List;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.items.HealItem;
import com.toxicfrog.entities.items.XPGem;
import com.toxicfrog.level.Level;
import com.toxicfrog.settings.Path;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

import javafx.scene.image.Image;

public class LootManager {
	
	private static List<Image> blueGems = new ArrayList<Image>();
	private static List<Image> greenGems = new ArrayList<Image>();
	private static List<Image> purpleGems = new ArrayList<Image>();
	private static List<Image> redGems = new ArrayList<Image>();
	private static List<Image> yellowGems = new ArrayList<Image>();
	
	public static void init() {
		for (int i = 0; i < 5; i++) {
			blueGems.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_blue" + "_" + i + ".png", null));
			greenGems.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_green" + "_" + i + ".png", null));
			purpleGems.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_purple" + "_" + i + ".png", null));
			redGems.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_red" + "_" + i + ".png", null));
			yellowGems.add(ImageCache.getImage(Path.TEXTURE_PATH + "gems/gem_yellow" + "_" + i + ".png", null));
		}
	}

	public static void generateLoot(Level level, Player player, Vector2D position, int difficult) {
		if (level != null && player != null) {
			int gemID = getRandomGemID(difficult);
			
			switch(gemID) {
				case 1:
					new XPGem(level, player, position, blueGems.get(Utils.randomInteger(0, blueGems.size() - 1)), 1);
					break;
				case 2:
					new XPGem(level, player, position, greenGems.get(Utils.randomInteger(0, greenGems.size() - 1)), 2);
					break;
				case 3:
					new XPGem(level, player, position, purpleGems.get(Utils.randomInteger(0, purpleGems.size() - 1)), 5);
					break;
				case 4:
					new XPGem(level, player, position, redGems.get(Utils.randomInteger(0, redGems.size() - 1)), 10);
					break;
				case 5:
					new XPGem(level, player, position, yellowGems.get(Utils.randomInteger(0, yellowGems.size() - 1)), 15);
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
