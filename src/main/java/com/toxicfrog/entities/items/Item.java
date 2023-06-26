package com.toxicfrog.entities.items;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Object;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class Item extends Object {

	public Item(Level level, String imagePath, Vector2D position, double rotation, double scale, boolean renderShadows) {
		super(level, ENTITYTYPE.ITEM, imagePath, null, position, rotation, scale, 0, renderShadows);
		
		hasCollission = false;
	}
	
	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
	}
}
