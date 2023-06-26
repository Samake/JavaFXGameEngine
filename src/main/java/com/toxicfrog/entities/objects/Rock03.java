package com.toxicfrog.entities.objects;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Object;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class Rock03 extends Object {

	public Rock03(Level level, Vector2D position) {
		super(level, ENTITYTYPE.OBJECT, "environment/rock3_dark.png", null, position, 0, 0.5, 0, false);

		boundingBox.widthScale = 0.6;
		boundingBox.heightScale = 0.4;
		boundingBox.yOffset = height * 0.0;
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
