package com.toxicfrog.entities.weapons;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Object;
import com.toxicfrog.entities.characters.Player;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Utils;
import com.toxicfrog.utils.Vector2D;

public class Weapon extends Object {

	protected Player player;
	public int damage = 1;
	
	private double swingValue = 0;
	private double swingAmount = 0;

	public Weapon(Level level, String imagePath, double scale, Player player) {
		super(level, ENTITYTYPE.WEAPON, imagePath, null, new Vector2D(), 0, scale, 0, false);
		
		this.player = player;
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		if (player != null && !player.isDeath) {
			isFlipped = player.isFlipped;
			swingValue = (swingValue + 0.1 * delta)%360;
			swingAmount = (float) Math.sin(swingValue) * 5 * delta;
			
			if (isFlipped) {
				position.x = player.position.x - (player.width * 0.075) + swingAmount;
				rotation = Utils.getAngleBetweenPoints(position, input.mouseWorldPosition, -180);
			} else {
				position.x = player.position.x + (player.width * 0.075) + swingAmount;
				rotation = Utils.getAngleBetweenPoints(position, input.mouseWorldPosition, 0);
			}
			
			position.y = player.position.y + (player.height * 0.325);
		} else {
			destroy();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
