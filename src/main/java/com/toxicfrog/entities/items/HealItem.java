package com.toxicfrog.entities.items;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class HealItem extends Item {
	
	private Player player;
	private float defaultCollectDistance = 200.0f;
	private boolean isCollected = false;
	
	public HealItem(Level level, Player player, Vector2D position) {
		super(level, null, position, 0, 0.1, true);
		
		this.player = player;
		
		image = ImageCache.getImage(Resources.HEART_ICON, null);

		level.addEntity(this);
		
		if (Settings.DEBUG_LOG) {
			Log.print("HealItem " + this + " wurde erstellt! ");
		}
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		if (player != null) {
			double distance = (int) Utils.computeDistanceOfTwoPoints((int) position.x, (int) position.y, (int) player.position.x, (int) player.position.y);
			
			if (distance <= (defaultCollectDistance - (defaultCollectDistance * player.magnify)) * InternalSettings.WINDOW_ZOOM) {
				if (!isCollected) {
					isCollected = true;
				} else {
					defaultSpeed += 0.5 * delta;

					movement.x = player.position.x - position.x;
					movement.y = player.position.y - position.y;

					if (movement.x != 0 || movement.y != 0) {
						double mag = Math.sqrt(movement.x * movement.x + movement.y * movement.y);
						velocity.x = (movement.x / mag) * speed;
						velocity.y = (movement.y / mag) * speed;
					}
					
					position.add(velocity);
				}
			}
			
			if (distance <= width) {
				destroy();
			}
		} else {
			destroy();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		player.heal(25);
	}

}
