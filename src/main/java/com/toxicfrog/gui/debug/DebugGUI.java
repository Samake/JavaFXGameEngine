package com.toxicfrog.gui.debug;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.core.GameLauncher;
import com.toxicfrog.game.Game;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.sound.SoundManager;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Popup;

public class DebugGUI extends Popup {

	private VBox guiPane = new VBox();
	
	private Text fps;
	private Text levelEntities;
	private Text renderedEntities;
	private Text sounds;
	private Text dangerLevel;
	private Text currenEmenies;
	private Text killCount;
	
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
		
		Text fpsText = new Text("FPS: ");
		fpsText.getStyleClass().add("text-label");
		
		fps = new Text("0");
		fps.getStyleClass().add("text-label-green");
		
		TextFlow fpsFullText = new TextFlow();
		fpsFullText.getChildren().addAll(fpsText, fps);
		
		Text levelEntitiesText = new Text("Entities: ");
		levelEntitiesText.getStyleClass().add("text-label");
		
		levelEntities = new Text("0");
		levelEntities.getStyleClass().add("text-label-blue");
		
		TextFlow levelEntitiesFullText = new TextFlow();
		levelEntitiesFullText.getChildren().addAll(levelEntitiesText, levelEntities);

		Text renderedEntitiesText = new Text("Rendered: ");
		renderedEntitiesText.getStyleClass().add("text-label");
		
		renderedEntities = new Text("0");
		renderedEntities.getStyleClass().add("text-label-blue");
		
		TextFlow renderedEntitiesFullText = new TextFlow();
		renderedEntitiesFullText.getChildren().addAll(renderedEntitiesText, renderedEntities);
		
		Text soundsText = new Text("Sounds: ");
		soundsText.getStyleClass().add("text-label");
		
		sounds = new Text("0");
		sounds.getStyleClass().add("text-label-blue");
		
		TextFlow soundsFullText = new TextFlow();
		soundsFullText.getChildren().addAll(soundsText, sounds);
		
		Text dangerLevelText = new Text("Dangerlevel: ");
		dangerLevelText.getStyleClass().add("text-label");
		
		dangerLevel = new Text("0");
		dangerLevel.getStyleClass().add("text-label-blue");
		
		TextFlow dangerLevelFullText = new TextFlow();
		dangerLevelFullText.getChildren().addAll(dangerLevelText, dangerLevel);
		
		Text currenEmeniesText = new Text("Current enemies: ");
		currenEmeniesText.getStyleClass().add("text-label");
		
		currenEmenies = new Text("0");
		currenEmenies.getStyleClass().add("text-label-blue");
		
		TextFlow currenEmeniesFullText = new TextFlow();
		currenEmeniesFullText.getChildren().addAll(currenEmeniesText, currenEmenies);
		
		Text killCountText = new Text("Enemies killed: ");
		killCountText.getStyleClass().add("text-label");
		
		killCount = new Text("0");
		killCount.getStyleClass().add("text-label-blue");
		
		TextFlow killCountFullText = new TextFlow();
		killCountFullText.getChildren().addAll(killCountText, killCount);
		
		double xPos = Settings.WINDOW_WIDTH * 0.015;
		double yPos = Settings.WINDOW_HEIGHT * 0.25;
		
		VBox.setMargin(debugBox, new Insets(yPos, 0, 0, xPos));
		
		debugBox.getChildren().addAll(fpsFullText, levelEntitiesFullText, renderedEntitiesFullText, soundsFullText, dangerLevelFullText, currenEmeniesFullText, killCountFullText);
		
		guiPane.getChildren().addAll(debugBox);
	}
	
	public void update(Game game, Camera camera) {
		if (game != null) {
			if (game.level != null) {
				
				if (fps != null) {
					
					if (GameLauncher.FPS > 30) {
						fps.getStyleClass().clear();
						fps.getStyleClass().add("text-label-green");
					} else {
						fps.getStyleClass().clear();
						fps.getStyleClass().add("text-label-red");
					}
					
					fps.setText(String.valueOf(GameLauncher.FPS));
				}
				
				if (levelEntities != null) {
					int entityCount = game.level.entities.size() + game.level.textEntities.size();
					levelEntities.setText(String.valueOf(entityCount));
				}
		
				if (renderedEntities != null) {
					int renderedCount = game.level.renderList.size() + game.level.renderListText.size();
					renderedEntities.setText(String.valueOf(renderedCount));
				}

				if (sounds != null) {
					sounds.setText(String.valueOf(SoundManager.soundCount));
				}
				
				if (dangerLevel != null) {
					dangerLevel.setText(String.valueOf(game.level.enemySpawner.dangerLevel));
				}
				
				if (currenEmenies != null) {
					currenEmenies.setText(String.valueOf(game.level.currentEmenyCount));
				}
				
				if (killCount != null) {
					killCount.setText(String.valueOf(game.level.statistic.killCount));
				}
			}
		}
	}
}
