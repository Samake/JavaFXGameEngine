package com.toxicfrog.balancing;

import com.toxicfrog.settings.Settings;

public class CoreBalance {

	/* GAMEPLAY */
	final public static double GAME_ROUND_LENGTH = 10.0;
	
	final public static double NPC_ANIMATION_DEFAULT = 200;
	final public static double NPC_MIN_SPAWN_DISTANCE = Settings.WINDOW_WIDTH / 1.5;
	final public static double NPC_MAX_SPAWN_DISTANCE = Settings.WINDOW_WIDTH;
	final public static int NPC_ELITE_SPAWN_VALUE = 975;
	final public static int NPC_MAX_SPAWN_VALUE = 10;
	final public static int NPC_SPAWN_TICKCOUNT = 3500;
	
	/* BASIC STATS */
	final public static double CHARACTER_SPRINT_COSTS = 8.0;
	final public static double CHARACTER_ROLL_COSTS = 10.0;
	final public static double CHARACTER_LIFE_RECOVER = 0.0125;
	final public static double CHARACTER_ENERGY_RECOVER = 0.2;
	final public static double CHARACTER_MAGNIFY = 1.5;
	
	/* PLAYER */
	final public static double CHARACTER_01_SCALE = 0.85;
	final public static double CHARACTER_01_SPEED = 5.0;
	final public static double CHARACTER_01_LIFE = 100;
	
	final public static double CHARACTER_02_SCALE = 0.85;
	final public static double CHARACTER_02_SPEED = 6.0;
	final public static double CHARACTER_02_LIFE = 90;
	
	final public static double CHARACTER_03_SCALE = 0.85;
	final public static double CHARACTER_03_SPEED = 5.5;
	final public static double CHARACTER_03_LIFE = 95;
	
	final public static double CHARACTER_04_SCALE = 0.85;
	final public static double CHARACTER_04_SPEED = 4.5;
	final public static double CHARACTER_04_LIFE = 150;
	
	/* ENEMIES */
	final public static double DEMON_01_SCALE = 0.55;
	final public static double DEMON_01_SPEED = 2.5;
	final public static double DEMON_01_LIFE = 35;
	final public static double DEMON_01_DAMAGE = 5;
	
	final public static double DEMON_ELITE_01_SCALE = 1.0;
	final public static double DEMON_ELITE_01_SPEED = 0.75;
	final public static double DEMON_ELITE_01_LIFE = 500;
	final public static double DEMON_ELITE_01_DAMAGE = 25;
	
	final public static double DEMON_02_SCALE = 0.6;
	final public static double DEMON_02_SPEED = 1.3;
	final public static double DEMON_02_LIFE = 65;
	final public static double DEMON_02_DAMAGE = 8;
	
	final public static double DEMON_03_SCALE = 0.65;
	final public static double DEMON_03_SPEED = 1.8;
	final public static double DEMON_03_LIFE = 100;
	final public static double DEMON_03_DAMAGE = 12;
	
	final public static double BAT_01_SCALE = 0.45;
	final public static double BAT_01_SPEED = 2.2;
	final public static double BAT_01_LIFE = 150;
	final public static double BAT_01_DAMAGE = 15;
	
	/* WEAPONS */
	final public static int WEAPON_DAMAGE_PISTOL = 35;
	final public static int WEAPON_PUNCTURE_PISTOL = 1;
	final public static double WEAPON_SPEED_PISTOL = 35.0;
	final public static int WEAPON_DISTANCE_PISTOL = 300;
	final public static double WEAPON_PROJECTILE_SIZE_PISTOL = 0.1;
	
	final public static int WEAPON_DAMAGE_RIFLE = 55;
	final public static int WEAPON_PUNCTURE_RIFLE = 2;
	final public static double WEAPON_SPEED_RIFLE = 55.0;
	final public static int WEAPON_DISTANCE_RIFLE = 1000;
	final public static double WEAPON_PROJECTILE_SIZE_RIFLE = 0.09;
	
	final public static int WEAPON_DAMAGE_WAND = 25;
	final public static int WEAPON_PUNCTURE_WAND = 1;
	final public static double WEAPON_SPEED_WAND = 25.0;
	final public static int WEAPON_DISTANCE_WAND = 250;
	final public static double WEAPON_PROJECTILE_SIZE_WAND = 0.2;
	
	final public static int WEAPON_DAMAGE_SHOTGUN = 95;
	final public static int WEAPON_PUNCTURE_SHOTGUN = 3;
	final public static double WEAPON_SPEED_SHOTGUN = 20.0;
	final public static int WEAPON_DISTANCE_SHOTGUN = 250;
	final public static double WEAPON_PROJECTILE_SIZE_SHOTGUN = 0.08;
	
	/* XP */
	final public static int BLUE_GEM_XP = 1;
	final public static int GREEN_GEM_XP = 2;
	final public static int PURPLE_GEM_XP = 5;
	final public static int RED_GEM_XP = 10;
	final public static int YELLOW_GEM_XP = 15;
}
