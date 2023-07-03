package com.toxicfrog.rendering;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.entities.Entity;
import com.toxicfrog.entities.enemies.Enemy;
import com.toxicfrog.entities.text.TextEntity;
import com.toxicfrog.enums.ENUMS.ENTITYTYPE;
import com.toxicfrog.level.Level;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.settings.Settings;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

public class Renderer {

	private GraphicsContext context;
	
	private Color greenColor = Color.rgb(85, 197, 56);
	private Color redColor = Color.rgb(255, 16, 49);
	
	public Renderer() {
		Log.print("Renderer were initialized!");
	}
	
	public void render(Canvas canvas, Camera camera, Level level) {
		if (canvas != null && level != null) {
			context = canvas.getGraphicsContext2D();
			
			context.save();
			
			prepareRender(canvas);
			renderLevel(level, camera);
			
			context.restore();
		}
    }

	private void prepareRender(Canvas canvas) {
		context.setFill( new Color(0, 0, 0, 1.0));
		context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		context.setGlobalBlendMode(BlendMode.SRC_OVER);
	}
	
	private void transformRotationContext(Entity entity, Camera camera) {
        Rotate r = new Rotate(entity.rotation, entity.position.x - camera.position.x, entity.position.y - camera.position.y);
        context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
	
	private void reTransformRotationContext(Entity entity, Camera camera) {
        Rotate r = new Rotate(0, entity.position.x - camera.position.x, entity.position.y - camera.position.y);
        context.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
	
	private void renderLevel(Level level, Camera camera) {
		if (level != null) {
			
			double posX, posY;
			
			if (level.backgroundImage != null) {
				int size = InternalSettings.DEFAULT_TEXTURE_SIZE * 4;
				int count = level.width / size;
				
				for (int i = 0; i < count; i++) {
					for (int j = 0; j < count; j++) {
						posX = (size * i) - camera.position.x;
						posY = (size * j) - camera.position.y;
						
						context.drawImage(level.backgroundImage, posX, posY, size, size);
					}
				}
			}
			
			
			
			for (Entity entity : level.renderList) {
				if (entity != null && entity.isRendered) {
					if (Settings.RENDER_SHADOWS) {
						reTransformRotationContext(entity, camera);
						
						if (entity.renderShadows) {
							posX = (entity.position.x - (entity.width / 2)) - camera.position.x;
							posY = (entity.position.y - (entity.height / 6.5)) - camera.position.y;
							
							context.drawImage(ImageCache.getImage(Resources.SHADOW_TEXTURE, null), posX, posY, entity.width, entity.height);
						}
					}
					
					transformRotationContext(entity, camera);
					
					if (entity.image != null) {
						if (entity.isFlipped) {
							posX = (entity.position.x + (entity.width / 2)) - camera.position.x;
							posY = (entity.position.y - (entity.height / 2)) - camera.position.y;
							
							context.drawImage(entity.image, posX, posY, -entity.width, entity.height);
						} else {
							posX = (entity.position.x - (entity.width / 2)) - camera.position.x;
							posY = (entity.position.y - (entity.height / 2)) - camera.position.y;
							
							context.drawImage(entity.image, posX, posY, entity.width, entity.height);
						}
					}
					
					if (entity.animation != null) {
						if (entity.animation.image != null) {
							if (entity.isFlipped) {
								posX = (entity.position.x + (entity.width / 2)) - camera.position.x;
								posY = (entity.position.y - (entity.height / 2)) - camera.position.y;
								
								context.drawImage(entity.animation.image, posX, posY, -entity.width, entity.height);
							} else {
								posX = (entity.position.x - (entity.width / 2)) - camera.position.x;
								posY = (entity.position.y - (entity.height / 2)) - camera.position.y;
								
								context.drawImage(entity.animation.image, posX, posY, entity.width, entity.height);
							}
						}
					}

					if (entity.animationSet != null) {
						if (entity.animationSet.current.image != null) {
							if (entity.isFlipped) {
								posX = (entity.position.x + (entity.width / 2)) - camera.position.x;
								posY = (entity.position.y - (entity.height / 2)) - camera.position.y;
								
								context.drawImage(entity.animationSet.current.image, posX, posY, -entity.width, entity.height);
							} else {
								posX = (entity.position.x - (entity.width / 2)) - camera.position.x;
								posY = (entity.position.y - (entity.height / 2)) - camera.position.y;
								
								context.drawImage(entity.animationSet.current.image, posX, posY, entity.width, entity.height);
							}
						}
					}
					
					if (entity.type.equals(ENTITYTYPE.ENEMY)) {
						Enemy enemy = (Enemy) entity;
						
						double barWidth = enemy.width * 0.25;
						double barHeight = 10 * InternalSettings.WINDOW_ZOOM;
						
						posX = (enemy.position.x - (barWidth / 2)) - camera.position.x;
						posY = (enemy.position.y - (barHeight * 2)) - camera.position.y;
						
						context.setFill(redColor);
						context.fillRoundRect(posX, posY, barWidth, barHeight, 15, 15);
						
						double healthWidth = (barWidth / enemy.maxLife) * enemy.life;
						
						if (healthWidth > 0) {
							
							if (healthWidth > barWidth) {
								healthWidth = barWidth;
							}
							
							context.setFill(greenColor);
							context.fillRoundRect(posX, posY, healthWidth, barHeight, 15, 15);
						}
					}
					
					if (Settings.RENDER_COLLISSION) {
						if (entity.boundingBox != null) {
							posX = entity.boundingBox.getX() - camera.position.x;
							posY = entity.boundingBox.getY() - camera.position.y;
							
							context.setStroke(Color.RED);
							context.strokeRect(posX, posY, entity.boundingBox.getWidth(), entity.boundingBox.getHeight());
							
							if (entity.type.equals(ENTITYTYPE.ENEMY)) {
								Enemy entityEnemy = (Enemy) entity;
								
								posX = entityEnemy.position.x - camera.position.x;
								posY = entityEnemy.position.y - camera.position.y;
								double tposX = entityEnemy.targetPosition.x - camera.position.x;
								double tposY = entityEnemy.targetPosition.y - camera.position.y;
								
								context.setStroke(Color.YELLOW);
								context.strokeLine(posX, posY, tposX, tposY);
							}
						}
					}
				}
			}
			
			for (TextEntity textEntity : level.renderListText) {
				if (textEntity != null) {
					
					context.setTextAlign(TextAlignment.CENTER);
					context.setTextBaseline(VPos.CENTER);
					context.setFont(Resources.FONT_DEFAULT);
					
					posX = textEntity.position.x - camera.position.x;
					posY = textEntity.position.y - camera.position.y;
					
					context.setFill(Color.color(0.0, 0.0, 0.0, textEntity.alpha));
					context.fillText(textEntity.text, posX - 1, posY - 1);
					
					context.setFill(Color.color(textEntity.color.getRed(), textEntity.color.getGreen(), textEntity.color.getBlue(), textEntity.alpha));
					context.fillText(textEntity.text, posX, posY);
				}
			}
		}
	}
}
