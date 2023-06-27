package com.toxicfrog.entities;

import com.toxicfrog.animation.AnimationSet;
import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

import javafx.animation.Interpolator;

public class NPC extends Entity {
	
	public double maxLife = 100;
	public double life = 100;
	public double armor = 0;
	
	public NPC(Level level, ENTITYTYPE type, String imagePath, String animationSetPath, Vector2D position, double rotation, double scale, double speed, boolean renderShadows) {
		super(level, type, imagePath, animationSetPath, position, rotation, scale, speed, renderShadows);
		
		animationSet = new AnimationSet(animationSetPath, CoreBalance.NPC_ANIMATION_DEFAULT);
		
		if (animationSet == null) {
			destroy();
		}
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
	}
	
	public void move(double delta) {
		velocity.clear();
		
		if (movement.x != 0 || movement.y != 0) {
			double mag = Math.sqrt(movement.x * movement.x + movement.y * movement.y);
			velocity.x = (movement.x / mag) * speed;
			velocity.y = (movement.y / mag) * speed;

			position.x = Interpolator.LINEAR.interpolate(position.x, position.x + velocity.x, 1.0 / delta);
			position.y = Interpolator.LINEAR.interpolate(position.y, position.y + velocity.y, 1.0 / delta);
		}
	}
	
	public void hit(double damage, Vector2D projVelo) {
	
	}
	
	public void kill() {
		
	}
	
	@Override
	public void destroy() {
		super.destroy();

	}
}
