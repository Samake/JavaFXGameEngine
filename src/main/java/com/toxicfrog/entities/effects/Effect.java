package com.toxicfrog.entities.effects;

import com.toxicfrog.animation.Animation;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Object;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.settings.Path;
import com.toxicfrog.utils.Vector2D;

public class Effect extends Object {

	public String name;
	public int duration;
	
	private long lastTick = System.currentTimeMillis();

	public Effect(Level level, String animationSetPath, int imageCount, String name, Vector2D position, double scale, double speed, int duration) {
		super(level, ENTITYTYPE.EFFECT, null, animationSetPath, position, 0, scale, speed, false);

		this.name = name;
		this.duration = duration;
		
		hasCollission = false;
		
		if (animationSetPath != null && !animationSetPath.isEmpty()) {
			animation = new Animation(name, Path.TEXTURE_PATH + animationSetPath, imageCount, speed, true);
		}
		
		if (animation != null) {
			level.addEntity(this);
			lastTick = System.currentTimeMillis();
		} else {
			destroy();
		}
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		if (animation != null) {
			long currentTick = System.currentTimeMillis();
			
			if (currentTick > lastTick + (duration * delta)) {
				destroy();
			}
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}