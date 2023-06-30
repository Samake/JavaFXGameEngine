package com.toxicfrog.gui.panes;

import com.toxicfrog.camera.Camera;
import com.toxicfrog.core.GameLauncher;
import com.toxicfrog.enums.ENUMS.GAMESTATE;
import com.toxicfrog.game.Game;
import com.toxicfrog.gui.GameScene;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class PauseGUI extends Popup {

	private GameScene gameScene;
	private VBox guiPane = new VBox();
	
	public PauseGUI(GameScene gameScene) {
		
		this.gameScene = gameScene;
		
		double popupWidth = Settings.WINDOW_WIDTH;
        double popupHeight = Settings.WINDOW_HEIGHT;

        setWidth(popupWidth);
        setHeight(popupHeight);

        double popupX = GameLauncher.primaryStage.getX() + (Settings.WINDOW_WIDTH - popupWidth) / 2;
        double popupY = GameLauncher.primaryStage.getY() + (Settings.WINDOW_HEIGHT - popupHeight) / 2;
        
        setX(popupX);
        setY(popupY);
        
        double xGap = Settings.WINDOW_WIDTH * 0.02;
        double yGap = Settings.WINDOW_WIDTH * 0.02;
        
		guiPane.setPrefSize(popupWidth - (xGap * 2), popupHeight - (yGap * 2));
		guiPane.setMaxSize(popupWidth - (xGap * 2), popupHeight - (yGap * 2));
		guiPane.setMinSize(popupWidth - (xGap * 2), popupHeight - (yGap * 2));
		
		Label test = new Label("PAUSE");
		test.getStyleClass().add("gui-label");
		
		Button mainMenuButton = new Button("Hauptmenü");
		mainMenuButton.getStyleClass().add("button-orange");

	    mainMenuButton.setOnAction(event -> {
	    	gameScene.setState(GAMESTATE.MAIN);
	    });
		
		guiPane.getChildren().addAll(test, mainMenuButton);
		
		getContent().add(guiPane);
        
        Log.print("PauseGUI were initialized!");
	}
	
	public void update(Game game, Camera camera) {
		if (game != null) {
			if (game.level != null) {
				if (game.level.player != null) {

				}
			}
		}
	}
}
