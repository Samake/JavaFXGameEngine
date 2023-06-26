package com.toxicfrog.entities.objects;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Object;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

public class Rock01 extends Object {
	
	public Rock01(Level level, Vector2D position) {
		super(level, ENTITYTYPE.OBJECT, "environment/rock1_dark.png", null, position, 0, 0.5, 0, false);

		boundingBox.widthScale = 0.65;
		boundingBox.heightScale = 0.45;
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
