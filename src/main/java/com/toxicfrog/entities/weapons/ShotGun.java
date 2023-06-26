package com.toxicfrog.entities.weapons;

import com.toxicfrog.cache.Resources;
import com.toxicfrog.cache.SoundCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.entities.projectiles.ShotGunShot;
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
		
		damage = 95;
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		if (player != null && !player.isDeath) {
			if (input.isLeftMouseClicked()) {
				shot(input);
				SoundManager.playSound(SoundCache.getSound(Resources.EFFECT_SHOTGUN_SHOT), InternalSettings.VOLUME_SHOTGUN_SHOT, false);
//				new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mouseX + 35, input.mouseY - 35), player, this);
//				new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mouseX + 15, input.mouseY - 15), player, this);
//				new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mouseX, input.mouseY), player, this);
//				new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mouseX - 15, input.mouseY + 15), player, this);
//				new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mouseX - 35, input.mouseY + 35), player, this);
			}
		}
	}
	
	private void shot(Input input) {
		SoundManager.playSound(SoundCache.getSound(Resources.EFFECT_SHOTGUN_SHOT), InternalSettings.VOLUME_SHOTGUN_SHOT, false);
		
		new ShotGunShot(level, new Vector2D(position.x, position.y), new Vector2D(input.mouseWorldPosition.x, input.mouseWorldPosition.y), player, this);
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
