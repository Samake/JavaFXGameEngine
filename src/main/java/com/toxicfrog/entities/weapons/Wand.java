package com.toxicfrog.entities.weapons;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.cache.SoundCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.projectiles.WandShot;
import com.toxicfrog.enums.ENUMS.SOUNDTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.sound.SoundManager;
import com.toxicfrog.utils.Vector2D;

public class Wand extends Weapon {

	public Wand(Level level, Player player) {
		super(level, "weapons/wand.png", 0.3, player);
		
		boundingBox.widthScale = 0.5;
		boundingBox.heightScale = 0.15;
		boundingBox.yOffset = height * 0.0;
		
		damage = CoreBalance.WEAPON_DAMAGE_WAND;
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
		SoundManager.playSound(SoundCache.getSound(Resources.EFFECT_GUN_SHOT), InternalSettings.VOLUME_GUN_SHOT, false, SOUNDTYPE.SOUND);
		
		new WandShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mouseWorldPosition.x, input.mouseWorldPosition.y), player, this, damage);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
