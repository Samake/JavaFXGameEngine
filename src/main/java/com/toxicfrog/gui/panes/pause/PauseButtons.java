package com.toxicfrog.gui.panes.pause;

import com.toxicfrog.enums.ENUMS.GAMESTATE;
import com.toxicfrog.gui.GameScene;
import com.toxicfrog.logging.Log;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class PauseButtons extends HBox {

	public PauseButtons(GameScene gameScene) {
		setAlignment(Pos.CENTER);
	    setPadding(new Insets(10));
	    setSpacing(10);
	    
		getStyleClass().add("menu-box");
		
		Button mainMenuButton = new Button("Hauptmenü");
		mainMenuButton.getStyleClass().add("button-orange");

	    mainMenuButton.setOnAction(event -> {
	    	gameScene.setState(GAMESTATE.MAIN);
	    });
	    
	    Button backButton = new Button("Zurück");
	    backButton.getStyleClass().add("button-yellow");

	    backButton.setOnAction(event -> {
	    	gameScene.setState(GAMESTATE.INGAME);
	    });
		
		getChildren().addAll(mainMenuButton, backButton);
		
		Log.print("PauseButtons were initialized!");
	}
}
