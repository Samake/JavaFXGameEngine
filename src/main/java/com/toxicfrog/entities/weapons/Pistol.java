package com.toxicfrog.entities.weapons;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.cache.SoundCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.projectiles.PistolShot;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.sound.SoundManager;
import com.toxicfrog.utils.Vector2D;

public class Pistol extends Weapon {

	public Pistol(Level level, Player player) {
		super(level, "weapons/pistol.png", 0.3, player);
		
		boundingBox.widthScale = 0.4;
		boundingBox.heightScale = 0.25;
		boundingBox.yOffset = height * 0.05;
		
		damage = CoreBalance.WEAPON_DAMAGE_PISTOL;
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		if (player != null && !player.isDeath) {
			if (input.isLeftMouseClicked()) {
				shot(input);
			}
		}
	}
	
	private void shot(Input input) {
		SoundManager.playSound(SoundCache.getSound(Resources.EFFECT_GUN_SHOT), InternalSettings.VOLUME_GUN_SHOT, false);
		
		new PistolShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mouseWorldPosition.x, input.mouseWorldPosition.y), player, this, damage);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
