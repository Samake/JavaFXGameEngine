package com.toxicfrog.entities.enemies;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class Demon02 extends Enemy {

	public Demon02(Level level, Vector2D position, double rotation, Player player) {
		super(level, "enemies/enemy_04", position, rotation, CoreBalance.DEMON_02_SCALE, Utils.randomDouble(CoreBalance.DEMON_02_SPEED - 0.25, CoreBalance.DEMON_02_SPEED + 0.25), player);

		boundingBox.widthScale = 0.275;
		boundingBox.heightScale = 0.325;
		boundingBox.yOffset = height * 0.2;
		
		difficult = 2;
		maxLife = CoreBalance.DEMON_02_LIFE;
		life = maxLife;
		damage = CoreBalance.DEMON_02_DAMAGE;
	}

	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		if (!isDeath) {
			
		}
	}
	
	@Override
	public void hit(double damage, Vector2D projVelo) {
		super.hit(damage, projVelo);

	}
	
	@Override
	public void kill() {
		super.kill();
		
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
