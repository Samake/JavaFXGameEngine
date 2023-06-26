package com.toxicfrog.input;

import java.util.ArrayList;
import java.util.List;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.gui.GameScene;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.utils.Vector2D;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class Input {
	
	public GameScene gameScene;
	public Vector2D zero = new Vector2D();
	public Vector2D middle = new Vector2D((double) (Settings.WINDOW_WIDTH * 0.5), (double) (Settings.WINDOW_HEIGHT * 0.5));
	public Vector2D mousePosition = new Vector2D();
	public Vector2D mouseWorldPosition = new Vector2D();
	public Vector2D mouseScreenOffset = new Vector2D();
	
	private List<KeyCode> pressedKeys = new ArrayList<KeyCode>();
	private List<KeyCode> releasedKeys = new ArrayList<KeyCode>();
	
	private boolean leftMouseClicked = false;
	private boolean rightMouseClicked = false;
	
	public Input(GameScene gameScene) {
		gameScene.setOnKeyPressed(event -> {
		    KeyCode keyCode = event.getCode();
		    
		    //System.err.println(event.getCode());

		    if (!pressedKeys.contains(keyCode)) {
		    	pressedKeys.add(keyCode);
		    }
		    
		    if (releasedKeys.contains(keyCode)) {
		    	releasedKeys.remove(keyCode);
		    }
		});

		gameScene.setOnKeyReleased(event -> {
		    KeyCode keyCode = event.getCode();

		    if (pressedKeys.contains(keyCode)) {
		    	pressedKeys.remove(keyCode);
		    }
		    
		    if (!releasedKeys.contains(keyCode)) {
		    	releasedKeys.add(keyCode);
		    }
		});
		
		gameScene.setOnMouseClicked(event -> {
			mousePosition.x = event.getX();
			mousePosition.y = event.getY();
			
			if (event.getButton().equals(MouseButton.PRIMARY)) {
				leftMouseClicked = true;
			}
			
			if (event.getButton().equals(MouseButton.SECONDARY)) {
				rightMouseClicked = true;
			}
		});

		gameScene.setOnMouseMoved(event -> {
			mousePosition.x = event.getX();
			mousePosition.y = event.getY();
		});
		
		Log.print("Input wurde initialisiert...");
	}
	
	public boolean isKeyPressed(KeyCode keyCode) {
		return pressedKeys.contains(keyCode);
	}
	
	
	public boolean isKeyClicked(KeyCode keyCode) {
		if (releasedKeys.contains(keyCode)) {
			releasedKeys.remove(keyCode);
			return true;
		}
		
		return false;
	}
	
	public boolean isLeftMouseClicked() {
		if (leftMouseClicked) {
			leftMouseClicked = false;
			return true;
		} 
		
		return false;
	}
	
	public boolean isRightMouseClicked() {
		if (rightMouseClicked) {
			rightMouseClicked = false;
			return true;
		} 
		
		return false;
	}

	public void update(Camera camera) {
		if (camera != null) {
			mouseScreenOffset.x = middle.x + mousePosition.x;
			mouseScreenOffset.y = middle.y + mousePosition.y;
			mouseWorldPosition.x = camera.position.x + mousePosition.x;
			mouseWorldPosition.y = camera.position.y + mousePosition.y;
		}
		
	}
}
