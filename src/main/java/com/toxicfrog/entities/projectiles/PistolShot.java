package com.toxicfrog.entities.projectiles;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.weapons.Weapon;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class PistolShot extends PlayerProjectile {

	public PistolShot(Level level, Vector2D position, Vector2D targetPosition, Player player, Weapon weapon, int damage) {
		super(level, "extras/bullet.png", position, targetPosition, CoreBalance.WEAPON_PROJECTILE_SIZE_PISTOL, CoreBalance.WEAPON_SPEED_PISTOL, CoreBalance.WEAPON_DISTANCE_PISTOL, player, weapon, damage);
		
		boundingBox.widthScale = 0.5;
		boundingBox.heightScale = 0.5;
		boundingBox.yOffset = height * 0.0;
		
		puncture = CoreBalance.WEAPON_PUNCTURE_PISTOL;
	}

	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);

	}
	
	@Override
	public void destroy() {
		super.destroy();

	}
}
