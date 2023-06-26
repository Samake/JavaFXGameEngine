package com.toxicfrog.gui.debug;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.core.GameLauncher;
import com.toxicfrog.game.Game;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.sound.SoundManager;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class DebugGUI extends Popup {

	private VBox guiPane = new VBox();
	
	private Label fps;
	private Label levelEntities;
	private Label renderedEntities;
	private Label sounds;
	private Label dangerLevel;
	private Label currenEmenies;
	private Label killCount;
	
	public DebugGUI() {
		double popupWidth = Settings.WINDOW_WIDTH;
        double popupHeight = Settings.WINDOW_HEIGHT;

        setWidth(popupWidth);
        setHeight(popupHeight);

        double popupX = GameLauncher.primaryStage.getX() + (Settings.WINDOW_WIDTH - popupWidth) / 2;
        double popupY = GameLauncher.primaryStage.getY() + (Settings.WINDOW_HEIGHT - popupHeight) / 2;
        
        setX(popupX);
        setY(popupY);
        
		guiPane.setPrefSize(popupWidth, popupHeight);
		guiPane.setMaxSize(popupWidth, popupHeight);
		guiPane.setMinSize(popupWidth, popupHeight);
		
		addDebugLabels();
		
		getContent().add(guiPane);
        
        Log.print("DebugGUI were initialized!");
	}

	private void addDebugLabels() {
		VBox debugBox = new VBox();
		debugBox.setPadding(new Insets(5));
		debugBox.setSpacing(5);
		
		fps = new Label("FPS: 0");
		fps.getStyleClass().add("game-popup-label");
		
		levelEntities = new Label("Entities: 0");
		levelEntities.getStyleClass().add("game-popup-label");
		
		renderedEntities = new Label("Rendered: 0");
		renderedEntities.getStyleClass().add("game-popup-label");
		
		sounds = new Label("Sounds: 0");
		sounds.getStyleClass().add("game-popup-label");
		
		dangerLevel = new Label("Gefahrstufe: 0");
		dangerLevel.getStyleClass().add("game-popup-label");
		
		currenEmenies = new Label("Current enemies: 0");
		currenEmenies.getStyleClass().add("game-popup-label");
		
		killCount = new Label("Enemies killed: 0");
		killCount.getStyleClass().add("game-popup-label");
		
		double xPos = Settings.WINDOW_WIDTH * 0.015;
		double yPos = Settings.WINDOW_HEIGHT * 0.25;
		
		VBox.setMargin(debugBox, new Insets(yPos, 0, 0, xPos));
		
		debugBox.getChildren().addAll(fps, levelEntities, renderedEntities, sounds, dangerLevel, currenEmenies, killCount);
		
		guiPane.getChildren().addAll(debugBox);
	}
	
	public void update(Game game, Camera camera) {
		if (game != null) {
			if (game.level != null) {
				
				if (fps != null) {
					fps.setText("FPS: " + GameLauncher.FPS);
				}
				
				if (levelEntities != null) {
					int entityCount = game.level.entities.size() + game.level.textEntities.size();
					levelEntities.setText("Entities: " + entityCount);
				}
		
				if (renderedEntities != null) {
					int renderedCount = game.level.renderList.size() + game.level.renderListText.size();
					renderedEntities.setText("Rendered: " + renderedCount);
				}

				if (sounds != null) {
					sounds.setText("Sounds: " + SoundManager.soundCount);
				}
				
				if (dangerLevel != null) {
					dangerLevel.setText("Gefahrstufe: " + game.level.enemySpawner.dangerLevel);
				}
				
				if (currenEmenies != null) {
					currenEmenies.setText("Aktuelle Gegner: " + game.level.currentEmenyCount);
				}
				
				if (killCount != null) {
					killCount.setText("Gegner getötet: " + game.level.statistic.killCount);
				}
			}
		}
	}
}
