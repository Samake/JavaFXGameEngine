package com.toxicfrog.gui.panes;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.core.GameLauncher;
import com.toxicfrog.game.Game;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class GameGUI extends Popup {

	private VBox guiPane = new VBox();
	
	private ProgressBar lifeBar;
	private ProgressBar energyBar;
	private ProgressBar expBar;
	private Label timeLabel;
	private Label levelLabel;
	private Label dangerLabel;
	
	private boolean debugGUi = false;
	
	public GameGUI() {
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
		
		if (debugGUi)  {
			guiPane.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
		}

		HBox topGuiPane = new HBox();
		topGuiPane.setPrefSize(guiPane.getPrefWidth() * 0.9, guiPane.getPrefHeight() * 0.055);
		topGuiPane.setMaxSize(guiPane.getPrefWidth() * 0.9, guiPane.getPrefHeight() * 0.055);
		topGuiPane.setMinSize(guiPane.getPrefWidth() * 0.9, guiPane.getPrefHeight() * 0.055);
		topGuiPane.setSpacing(10);
		
		if (debugGUi)  {
			topGuiPane.setStyle("-fx-border-color: yellow; -fx-border-width: 2px;");
		}
		
		HBox bottomGuiPane = new HBox();
		bottomGuiPane.setPrefSize(guiPane.getPrefWidth(), guiPane.getPrefHeight() * 0.055);
		bottomGuiPane.setMaxSize(guiPane.getPrefWidth(), guiPane.getPrefHeight() * 0.055);
		bottomGuiPane.setMinSize(guiPane.getPrefWidth(), guiPane.getPrefHeight() * 0.055);
		bottomGuiPane.setSpacing(10);
		
		if (debugGUi)  {
			bottomGuiPane.setStyle("-fx-border-color: yellow; -fx-border-width: 2px;");
		}

		addLifeBox(topGuiPane);
		addEnergyBox(topGuiPane);
		addTimeBox(topGuiPane);
		addDangerBox(topGuiPane);
		addLevelBox(topGuiPane);
		addEXPBox(bottomGuiPane);
		
		VBox.setMargin(guiPane, new Insets(yGap, 0, 0, xGap));
		VBox.setMargin(topGuiPane, new Insets(0, 0, 0, 0));
		VBox.setMargin(bottomGuiPane, new Insets(guiPane.getPrefHeight() - bottomGuiPane.getPrefHeight(), 0, 0, 0));
		
		guiPane.getChildren().addAll(topGuiPane, bottomGuiPane);
		
		getContent().add(guiPane);
        
        Log.print("GameGUI were initialized!");
	}

	private void addLifeBox(HBox topGuiPane) {
		double boxWidth = topGuiPane.getPrefWidth() * 0.2;
		double boxHeigth = topGuiPane.getPrefHeight() * 0.5;
		
		HBox pane = new HBox();
		pane.setSpacing(5);
		pane.setPrefWidth(boxWidth);
		pane.setPrefHeight(boxHeigth);
		pane.setAlignment(Pos.CENTER_LEFT);
		
		if (debugGUi)  {
			pane.setStyle("-fx-border-color: blue; -fx-border-width: 1px;");
		}
		
		ImageView icon = new ImageView(ImageCache.getImage(Resources.HEART_ICON, null));
		icon.setFitWidth(boxHeigth);
		icon.setFitHeight(boxHeigth);
		
		lifeBar = new ProgressBar(0);
		lifeBar.setProgress(0.5);
		lifeBar.getStyleClass().add("life-bar");
		lifeBar.setPrefWidth(boxWidth - boxHeigth);
		lifeBar.setPrefHeight(boxHeigth);
		
		pane.getChildren().addAll(icon, lifeBar);
		
		topGuiPane.getChildren().addAll(pane);
	}
	
	private void addEnergyBox(HBox topGuiPane) {
		double boxWidth = topGuiPane.getPrefWidth() * 0.2;
		double boxHeigth = topGuiPane.getPrefHeight() * 0.5;
		
		HBox pane = new HBox();
		pane.setSpacing(5);
		pane.setPrefWidth(boxWidth);
		pane.setPrefHeight(boxHeigth);
		pane.setAlignment(Pos.CENTER_LEFT);
		
		if (debugGUi)  {
			pane.setStyle("-fx-border-color: blue; -fx-border-width: 1px;");
		}

		ImageView icon = new ImageView(ImageCache.getImage(Resources.ENERGY_ICON, null));
		icon.setFitWidth(boxHeigth);
		icon.setFitHeight(boxHeigth);
		
		energyBar = new ProgressBar(0);
		energyBar.setProgress(0.5);
		energyBar.getStyleClass().add("energy-bar");
		energyBar.setPrefWidth(boxWidth - boxHeigth);
		energyBar.setPrefHeight(boxHeigth);
		
		pane.getChildren().addAll(icon, energyBar);
		
		topGuiPane.getChildren().addAll(pane);
	}
	
	private void addTimeBox(HBox topGuiPane) {
		double boxWidth = topGuiPane.getPrefWidth() * 0.2;
		double boxHeigth = topGuiPane.getPrefHeight() * 0.5;
		
		HBox pane = new HBox();
		pane.setSpacing(5);
		pane.setPrefWidth(boxWidth);
		pane.setPrefHeight(boxHeigth);
		pane.setAlignment(Pos.CENTER);
		
		if (debugGUi)  {
			pane.setStyle("-fx-border-color: blue; -fx-border-width: 1px;");
		}

		ImageView icon = new ImageView(ImageCache.getImage(Resources.CLOCK_ICON, null));
		icon.setFitWidth(boxHeigth);
		icon.setFitHeight(boxHeigth);
		
		timeLabel = new Label("00:00");
		timeLabel.getStyleClass().add("gui-label");
		
		pane.getChildren().addAll(icon, timeLabel);
		
		topGuiPane.getChildren().addAll(pane);
	}
	
	private void addDangerBox(HBox topGuiPane) {
		double boxWidth = topGuiPane.getPrefWidth() * 0.2;
		double boxHeigth = topGuiPane.getPrefHeight() * 0.5;
		
		HBox pane = new HBox();
		pane.setSpacing(5);
		pane.setPrefWidth(boxWidth);
		pane.setPrefHeight(boxHeigth);
		pane.setAlignment(Pos.CENTER);
		
		if (debugGUi)  {
			pane.setStyle("-fx-border-color: blue; -fx-border-width: 1px;");
		}
		
		dangerLabel = new Label("Danger: 0");
		dangerLabel.getStyleClass().add("gui-label");
		
		pane.getChildren().addAll(dangerLabel);
		
		topGuiPane.getChildren().addAll(pane);	
	}
	
	private void addLevelBox(HBox topGuiPane) {
		double boxWidth = topGuiPane.getPrefWidth() * 0.2;
		double boxHeigth = topGuiPane.getPrefHeight() * 0.5;
		
		HBox pane = new HBox();
		pane.setSpacing(5);
		pane.setPrefWidth(boxWidth);
		pane.setPrefHeight(boxHeigth);
		pane.setAlignment(Pos.CENTER_RIGHT);
		
		if (debugGUi)  {
			pane.setStyle("-fx-border-color: blue; -fx-border-width: 1px;");
		}
		
		levelLabel = new Label("Level: 0");
		levelLabel.getStyleClass().add("gui-label");
		
		pane.getChildren().addAll(levelLabel);
		
		topGuiPane.getChildren().addAll(pane);
	}
	
	private void addEXPBox(HBox bottomGuiPane) {
		double boxWidth = bottomGuiPane.getPrefWidth();
		double boxHeigth = bottomGuiPane.getPrefHeight() * 0.35;
		
		HBox pane = new HBox();
		pane.setSpacing(5);
		pane.setPrefWidth(boxWidth);
		pane.setPrefHeight(boxHeigth);
		pane.setAlignment(Pos.CENTER);
		
		if (debugGUi)  {
			pane.setStyle("-fx-border-color: blue; -fx-border-width: 1px;");
		}
		
		expBar = new ProgressBar(0);
		expBar.setProgress(0.5);
		expBar.getStyleClass().add("exp-bar");
		expBar.setPrefWidth(boxWidth);
		expBar.setPrefHeight(boxHeigth);
		
		pane.getChildren().addAll(expBar);
		
        bottomGuiPane.getChildren().addAll(pane);
	}
	
	public void update(Game game, Camera camera) {
		if (game != null) {
			if (game.level != null) {
				if (game.level.player != null) {
					if (lifeBar != null) {
						double lifeProgress = (1.0 / game.level.player.maxLife) * game.level.player.life;
						lifeBar.setProgress(lifeProgress);
					}

					if (energyBar != null) {
						double energyProgress = (1.0 / game.level.player.maxEnergy) * game.level.player.energy;
						energyBar.setProgress(energyProgress);
					}
					
					if (timeLabel != null) {
						timeLabel.setText(game.level.getDuration());
					}
					
					if (dangerLabel != null) {
						dangerLabel.setText("Danger: " + game.level.dangerLevel);
					}
					
					if (levelLabel != null) {
						levelLabel.setText("Level: " + game.level.player.playerLevel);
					}
					
					if (expBar != null) {
						double expProgress = (1.0 / game.level.player.maxExp) * game.level.player.exp;
						expBar.setProgress(expProgress);
					}
				}
			}
		}
	}
}
