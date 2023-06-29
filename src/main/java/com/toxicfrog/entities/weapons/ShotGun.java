package com.toxicfrog.entities.weapons;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.cache.SoundCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.projectiles.ShotGunShot;
import com.toxicfrog.enums.ENUMS.SOUNDTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.sound.SoundManager;
import com.toxicfrog.utils.Vector2D;

public class ShotGun extends Weapon {

	public ShotGun(Level level, Player player) {
		super(level, "weapons/shotgun.png", 0.3, player);
		
		boundingBox.widthScale = 0.6;
		boundingBox.heightScale = 0.3;
		boundingBox.yOffset = height * 0.05;
		
		damage = CoreBalance.WEAPON_DAMAGE_SHOTGUN;
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
		SoundManager.playSound(SoundCache.getSound(Resources.EFFECT_SHOTGUN_SHOT), InternalSettings.VOLUME_SHOTGUN_SHOT, false, SOUNDTYPE.SOUND);
		
		new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mousePosition.x + 35, input.mousePosition.y - 35), player, this, damage);
		new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mousePosition.x + 15, input.mousePosition.y - 15), player, this, damage);
		new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mousePosition.x, input.mousePosition.y), player, this, damage);
		new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mousePosition.x - 15, input.mousePosition.y + 15), player, this, damage);
		new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mousePosition.x - 35, input.mousePosition.y + 35), player, this, damage);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
