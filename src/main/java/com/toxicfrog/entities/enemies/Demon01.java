package com.toxicfrog.entities.enemies;

import com.toxicfrog.balancing.CoreBalance;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class Demon01 extends Enemy {

	public Demon01(Level level, Vector2D position, double rotation, Player player) {
		super(level, "enemies/enemy_01", position, rotation, CoreBalance.DEMON_01_SCALE, Utils.randomDouble(CoreBalance.DEMON_01_SPEED - 0.25, CoreBalance.DEMON_01_SPEED + 0.25), player);

		boundingBox.widthScale = 0.25;
		boundingBox.heightScale = 0.35;
		boundingBox.yOffset = height * 0.2;
		
		difficult = 1;
		maxLife = CoreBalance.DEMON_01_LIFE;
		life = maxLife;
		damage = CoreBalance.DEMON_01_DAMAGE;
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
