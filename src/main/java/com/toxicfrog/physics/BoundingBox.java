package com.toxicfrog.physics;

import com.toxicfrog.utils.Vector2D;

import javafx.scene.shape.Rectangle;

public class BoundingBox extends Rectangle {

	public double widthScale = 1.0;
	public double heightScale = 1.0;
	public double xOffset = 0.0;
	public double yOffset = 0.0;
	public double sortHeight = 0;
	
	public BoundingBox() {
		super();
	}
	
	public BoundingBox(double widthScale, double heightScale) {
		super();
		
		this.widthScale = widthScale;
		this.heightScale = heightScale;
	}
	
	public void update(Vector2D position, double width, double height) {
		setWidth(width * widthScale);
		setHeight(height * heightScale);
		setX(position.x - (getWidth() / 2) + xOffset);
		setY(position.y - (getHeight() / 2) + yOffset);
		
		sortHeight = getY() + getHeight();
	}
}
