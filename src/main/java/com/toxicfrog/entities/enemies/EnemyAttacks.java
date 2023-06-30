package com.toxicfrog.entities.enemies;

import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.projectiles.EnemyShot;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class EnemyAttacks {

	public static void shot(Level level, Vector2D position, double xOffset, double yOffset, double angle, double speed, double distance, Player player, Enemy enemy) {
		new EnemyShot(level, new Vector2D(position.x + xOffset, position.y + yOffset), Utils.getPositionArroundVector(position, angle, distance), speed, distance, player, enemy);
	}
	
	public static void ringShot(Level level, Vector2D position, double xOffset, double yOffset, double speed, double distance, Player player, Enemy enemy) {
		shot(level, position, 0, yOffset, 0, speed, distance, player, enemy);
		shot(level, position, 0, yOffset, 45, speed, distance, player, enemy);
		shot(level, position, 0, yOffset, 90, speed, distance, player, enemy);
		shot(level, position, 0, yOffset, 135, speed, distance, player, enemy);
		shot(level, position, 0, yOffset, 180, speed, distance, player, enemy);
		shot(level, position, 0, yOffset, 225, speed, distance, player, enemy);
		shot(level, position, 0, yOffset, 270, speed, distance, player, enemy);
		shot(level, position, 0, yOffset, 315, speed, distance, player, enemy);
	}
	
	public static void tripleRingShot(Level level, Vector2D position, double xOffset, double yOffset, double speed, double distance, Player player, Enemy enemy) {
		ringShot(level, position, 0, yOffset, speed * 0.75, distance * 0.75, player, enemy);
		ringShot(level, position, 0, yOffset, speed, distance, player, enemy);
		ringShot(level, position, 0, yOffset, speed * 1.25, distance * 1.25, player, enemy);
	}
}