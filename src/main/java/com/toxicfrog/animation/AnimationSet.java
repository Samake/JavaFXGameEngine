package com.toxicfrog.animation;

import com.toxicfrog.enums.ENUMS.ANIMATION;
import com.toxicfrog.settings.Path;

public class AnimationSet {
	
	public String animationID;
	public double speed = 250;
	public double speedModifier = 1;
	
	public Animation idle;
	public Animation death;
	public Animation fall;
	public Animation hit;
	public Animation jumpEnd;
	public Animation jumpStart;
	public Animation roll;
	public Animation walk;
	public Animation fly;
	
	public Animation current;
	
	public AnimationSet(String animationID, double speed) {
		this.animationID = animationID;
		this.speed = speed;
		
		if (animationID != null && !animationID.isEmpty()) {
			loadAnimations(animationID);
		}
	}

	private void loadAnimations(String animationID) {
		if (animationID.contains("enemy_03")) {
			fly = new Animation(animationID + "fly", Path.TEXTURE_PATH + animationID + "/fly", 6, speed, true);
			idle = fly;
			death = fly;
			fall = fly;
			hit = fly;
			jumpEnd = fly;
			jumpStart = fly;
			roll = fly;
			walk = fly;
			
			current = fly;
		} else {
			idle = new Animation(animationID + "/idle", Path.TEXTURE_PATH + animationID + "/idle", 6, speed, true);
			death = new Animation(animationID + "/death", Path.TEXTURE_PATH + animationID + "/death", 10, speed, false);
			fall = new Animation(animationID + "/fall", Path.TEXTURE_PATH + animationID + "/fall", 5, speed, true);
			hit = new Animation(animationID + "/hit", Path.TEXTURE_PATH + animationID + "/hit", 3, speed, false);
			jumpEnd = new Animation(animationID + "/jumpEnd", Path.TEXTURE_PATH + animationID + "/jumpEnd", 3, speed, false);
			jumpStart = new Animation(animationID + "/jumpStart", Path.TEXTURE_PATH + animationID + "/jumpStart", 2, speed, false);
			roll = new Animation(animationID + "/roll", Path.TEXTURE_PATH + animationID + "/roll", 5, speed, true);
			walk = new Animation(animationID + "/walk", Path.TEXTURE_PATH + animationID + "/walk", 8, speed, true);
			fly = idle;
			
			current = idle;
		}
	}
	
	public void update(double delta) {
		if (current != null) {
			current.speedModifier = speedModifier;
			current.update(delta);
		}
	}
	
	public void setCurrentAnimation(ANIMATION animation) {
		switch(animation) {
			case IDLE:
				current = idle;
				break;
			case DEATH:
				current = death;		
				break;
			case FALL:
				current = fall;
				break;
			case HIT:
				current = hit;
				break;
			case JUMPEND:
				current = jumpEnd;
				break;
			case JUMPSTART:
				current = jumpStart;
				break;
			case ROLL:
				current = roll;
				break;
			case WALK:
				current = walk;
				break;
			case FLY:
				current = fly;
				break;
			default:
				current = idle;
				break;
		}
	}
	
	public double getDuration() {
		return current.duration;
	}
}
