package com.toxicfrog.gui.panes;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.core.GameLauncher;
import com.toxicfrog.game.Game;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.settings.Settings;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class MainMenu extends VBox {
	
	private Button startButton;
	private Button resumeButton;
	private Button stopButton;
	private Button settingsButton;
	private Button exitButton;
	
	public MainMenu() {
	    addBackGroundImage();
	    VBox titleBox = addTitle();
	    VBox buttonBox = addMenuButtons();

	    this.getChildren().addAll(titleBox, buttonBox);
	    
	    Log.print("MainMenu were initialized!");
	}
	
	private void addBackGroundImage() {
		Image backgroundImage = ImageCache.getImage(Resources.BG_01, null);
	    
	    BackgroundImage bImg = new BackgroundImage(backgroundImage,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundRepeat.NO_REPEAT,
	            BackgroundPosition.CENTER,
	            new BackgroundSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT + 35, false, false, false, false));

	    Background backGround = new Background(bImg);

	    this.setBackground(backGround);
	}
	
	private VBox addTitle() {
		VBox titleBox = new VBox();
		
		Label title = new Label(InternalSettings.TITLE);
		title.getStyleClass().add("game-title");
		title.setMaxWidth(Settings.WINDOW_WIDTH);
		
		Label version = new Label(InternalSettings.VERSION);
		version.getStyleClass().add("game-Version");
		version.setMaxWidth(Settings.WINDOW_WIDTH);
		
		titleBox.getChildren().addAll(title, version);
		
		VBox.setMargin(title, new Insets(Settings.WINDOW_HEIGHT * 0.15, 0, 0, 0));
		
		return titleBox;
	}

	private VBox addMenuButtons() {
		VBox buttonBox = new VBox();
	    startButton = new Button("Neues Spiel starten");
	    startButton.getStyleClass().add("button-green");
	    
	    resumeButton = new Button("Spiel fortsetzen");
	    resumeButton.getStyleClass().add("button-yellow");
	    
	    stopButton = new Button("Spiel beenden");
	    stopButton.getStyleClass().add("button-orange");

	    settingsButton = new Button("Einstellungen");
	    settingsButton.getStyleClass().add("button-blue");

	    exitButton = new Button("Beenden");
	    exitButton.getStyleClass().add("button-red");
	    
	    startButton.setOnAction(event -> {
	    	GameLauncher.startGame();
	    });
	    
	    resumeButton.setOnAction(event -> {
	    	GameLauncher.resumeGame();
	    });
	    
	    stopButton.setOnAction(event -> {
	    	GameLauncher.stopGame();
	    });

	    settingsButton.setOnAction(event -> {
	    	GameLauncher.openSettings();
	    });

	    exitButton.setOnAction(event -> {
	    	GameLauncher.exitGame();
	    });

	    buttonBox.getChildren().addAll(startButton, resumeButton, stopButton, settingsButton, exitButton);

	    buttonBox.setPadding(new Insets(10));
	    buttonBox.setPrefSize(Settings.WINDOW_WIDTH * 0.33, Settings.WINDOW_HEIGHT * 0.33);
	    
	    startButton.setMaxWidth(Settings.WINDOW_WIDTH * 0.33);
	    resumeButton.setMaxWidth(Settings.WINDOW_WIDTH * 0.33);
	    stopButton.setMaxWidth(Settings.WINDOW_WIDTH * 0.33);
	    settingsButton.setMaxWidth(Settings.WINDOW_WIDTH * 0.33);
	    exitButton.setMaxWidth(Settings.WINDOW_WIDTH * 0.33);

	    VBox.setMargin(startButton, new Insets(Settings.WINDOW_HEIGHT * 0.075, 0, 0, Settings.WINDOW_WIDTH * 0.33));
	    VBox.setMargin(resumeButton, new Insets(10, 0, 0, Settings.WINDOW_WIDTH * 0.33));
	    VBox.setMargin(stopButton, new Insets(10, 0, 0, Settings.WINDOW_WIDTH * 0.33));
	    VBox.setMargin(settingsButton, new Insets(10, 0, 0, Settings.WINDOW_WIDTH * 0.33));
	    VBox.setMargin(exitButton, new Insets(10, 0, 0, Settings.WINDOW_WIDTH * 0.33));

	    return buttonBox;
	}

	public void update(Game game) {
		if (game != null) {
			if (!startButton.isDisabled()) {
				startButton.setDisable(true);
			}
			
			if (resumeButton.isDisabled()) {
				resumeButton.setDisable(false);
			}
			
			if (stopButton.isDisabled()) {
				stopButton.setDisable(false);
			}
		} else {
			if (startButton.isDisabled()) {
				startButton.setDisable(false);
			}
			
			if (!resumeButton.isDisabled()) {
				resumeButton.setDisable(true);
			}
			
			if (!stopButton.isDisabled()) {
				stopButton.setDisable(true);
			}
		}
	}
}
