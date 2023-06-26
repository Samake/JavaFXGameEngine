package com.toxicfrog.entities.text;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Object;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.utils.Vector2D;

import javafx.scene.paint.Color;

public class TextEntity extends Object {

	public String text;
	public Color color;
	public float alpha = 1.0f;
	
	public TextEntity(Level level, String text, Vector2D position, Color color, double speed) {
		super(level, ENTITYTYPE.TEXT, null, null, position, 0, 1.0f, speed, false);

		this.text = text;
		this.color = color;
		
		hasCollission = false;
		
		level.addTextEntity(this);
	}

	@Override
	public void update(Input input, Camera camera, double delta) {
		super.update(input, camera, delta);
		
		double decreaseValue = 0.01 * speed * delta;
		
		position.y -= decreaseValue * 100 * delta;
		alpha -= decreaseValue;
		
		if (alpha <= 0) {
			alpha = 0;
			destroy();
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
		
		level.removeTextEntity(this);
	}
}
