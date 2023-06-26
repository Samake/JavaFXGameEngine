package com.toxicfrog.entities;

import com.toxicfrog.animation.Animation;
import com.toxicfrog.animation.AnimationSet;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.input.Input;
import com.toxicfrog.level.Level;
import com.toxicfrog.logging.Log;
import com.toxicfrog.physics.BoundingBox;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.utils.Vector2D;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

public class Entity {

	public Level level;
	public ENTITYTYPE type;
	
	public Vector2D position;
	public Vector2D movement = new Vector2D();
	public Vector2D velocity = new Vector2D();
	public double rotation;
	public double scale = 1;
	public double speed = 1;
	public double defaultSpeed = 1;
	public double width = 0;
	public double height = 0;
	public String animationSetPath;
	public AnimationSet animationSet;
	public Animation animation;
	public String imagePath;
	public Image image;
	public boolean renderShadows = true;
	public BoundingBox boundingBox = new BoundingBox();
	
	public boolean isWalking = false;
	public boolean isJumping = false;
	public boolean isRunning = false;
	public boolean isRolling = false;
	public boolean isDeath = false;
	public boolean isFlipped = false;
	
	public boolean isRendered = true;

	public Entity(Level level, ENTITYTYPE type, String imagePath, String animationSetPath, Vector2D position, double rotation, double scale, double speed, boolean renderShadows) {
		this.level = level;
		this.type = type;
		this.imagePath = imagePath;
		this.animationSetPath = animationSetPath;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.speed = speed;
		this.defaultSpeed = speed;
		this.renderShadows = renderShadows;
		
		width = ((InternalSettings.DEFAULT_TEXTURE_SIZE * scale) * InternalSettings.TEXTURE_ZOOM) * InternalSettings.WINDOW_ZOOM;
		height = ((InternalSettings.DEFAULT_TEXTURE_SIZE * scale) * InternalSettings.TEXTURE_ZOOM) * InternalSettings.WINDOW_ZOOM;
		
		if (Settings.DEBUG_LOG) {
			Log.print("Entity " + this + " were created!");
		}
	}
	
	public void update(Input input, Camera camera, double delta) {
		speed = defaultSpeed * delta;

		if (animation != null) {
			animation.update(delta);
		}
		
		if (animationSet != null) {
			animationSet.update(delta);
		}
		
		if (boundingBox != null) {
			boundingBox.update(position, width, height);
		}
	}
	
	public boolean checkCollission(Entity entity, ENTITYTYPE type) {
		if (entity != null) {
			if (entity.type.equals(type)) {
				if (entity.boundingBox != null && boundingBox != null) {
					Shape intersect = Shape.intersect(boundingBox, entity.boundingBox);

					if (intersect.getBoundsInParent().getWidth() > 0) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public void destroy() {
		if (Settings.DEBUG_LOG) {
			Log.print("Entity " + this + " was deleted!");
		}
		
		level.removeEntity(this);
	}
}
