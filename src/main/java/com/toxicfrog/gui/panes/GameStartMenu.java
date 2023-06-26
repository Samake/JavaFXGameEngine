package com.toxicfrog.gui.panes;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.core.GameLauncher;
import com.toxicfrog.enums.ENUMS.CHARACTER;
import com.toxicfrog.enums.ENUMS.GAMESTATE;
import com.toxicfrog.enums.ENUMS.WEAPON;
import com.toxicfrog.gui.GameScene;
import com.toxicfrog.gui.helper.GUIHelper;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameStartMenu extends VBox {
	
	private Button startGameButton;
	private Button char1Button;
	private Button char2Button;
	private Button char3Button;
	private Button char4Button;
	private Button rifleButton;
	private Button pistolButton;
	private Button shotgunButton;
	private Button wandButton;
	
    private CHARACTER character = null;
    private WEAPON weapon = null;
    
	public GameStartMenu(GameScene gameScene) {
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10));
		setSpacing(10);
		
		addBackGroundImage();
		
		HBox chooseCharacterLabelBox = new HBox();
        chooseCharacterLabelBox.setAlignment(Pos.CENTER);
        chooseCharacterLabelBox.setPadding(new Insets(10));
        chooseCharacterLabelBox.setSpacing(10);
		
		Label chooseCharacterLabel = new Label("Wähle deinen Character");
        chooseCharacterLabel.getStyleClass().add("gui-label-bold");

        chooseCharacterLabelBox.getChildren().add(chooseCharacterLabel);
        
        HBox characterButtonBox = new HBox();
        characterButtonBox.setAlignment(Pos.CENTER);
        characterButtonBox.setPadding(new Insets(10));
        characterButtonBox.setSpacing(10);
	    
        char1Button = GUIHelper.createChooseButton(Resources.CHAR_01_ICON, "Char 01", Settings.WINDOW_HEIGHT * 0.15);
        char2Button = GUIHelper.createChooseButton(Resources.CHAR_02_ICON, "Char 02", Settings.WINDOW_HEIGHT * 0.15);
        char3Button = GUIHelper.createChooseButton(Resources.CHAR_03_ICON, "Char 03", Settings.WINDOW_HEIGHT * 0.15);
        char4Button = GUIHelper.createChooseButton(Resources.CHAR_04_ICON, "Char 04", Settings.WINDOW_HEIGHT * 0.15);
        
        char1Button.setOnMouseClicked(event -> {
            char1Button.getStyleClass().clear();
            char1Button.getStyleClass().add("image-button-box-selected");
            
            char2Button.getStyleClass().clear();
            char2Button.getStyleClass().add("image-button-box");
            
            char3Button.getStyleClass().clear();
            char3Button.getStyleClass().add("image-button-box");
            
            char4Button.getStyleClass().clear();
            char4Button.getStyleClass().add("image-button-box");
            
            character = CHARACTER.CHAR_01;
        });
        
        char2Button.setOnMouseClicked(event -> {
            char1Button.getStyleClass().clear();
            char1Button.getStyleClass().add("image-button-box");
            
            char2Button.getStyleClass().clear();
            char2Button.getStyleClass().add("image-button-box-selected");
            
            char3Button.getStyleClass().clear();
            char3Button.getStyleClass().add("image-button-box");
            
            char4Button.getStyleClass().clear();
            char4Button.getStyleClass().add("image-button-box");
            
            character = CHARACTER.CHAR_02;
        });
        
        char3Button.setOnMouseClicked(event -> {
            char1Button.getStyleClass().clear();
            char1Button.getStyleClass().add("image-button-box");
            
            char2Button.getStyleClass().clear();
            char2Button.getStyleClass().add("image-button-box");
            
            char3Button.getStyleClass().clear();
            char3Button.getStyleClass().add("image-button-box-selected");
            
            char4Button.getStyleClass().clear();
            char4Button.getStyleClass().add("image-button-box");
            
            character = CHARACTER.CHAR_03;
        });
        
        char4Button.setOnMouseClicked(event -> {
            char1Button.getStyleClass().clear();
            char1Button.getStyleClass().add("image-button-box");
            
            char2Button.getStyleClass().clear();
            char2Button.getStyleClass().add("image-button-box");
            
            char3Button.getStyleClass().clear();
            char3Button.getStyleClass().add("image-button-box");
            
            char4Button.getStyleClass().clear();
            char4Button.getStyleClass().add("image-button-box-selected");
            
            character = CHARACTER.CHAR_04;
        });
        
        characterButtonBox.getChildren().addAll(char1Button, char2Button, char3Button, char4Button);
        
        HBox chooseWeaponLabelBox = new HBox();
        chooseWeaponLabelBox.setAlignment(Pos.CENTER);
        chooseWeaponLabelBox.setPadding(new Insets(10));
        chooseWeaponLabelBox.setSpacing(10);
        
        Label chooseWeaponLabel = new Label("Wähle deine Waffe");
        chooseWeaponLabel.getStyleClass().add("gui-label-bold");

        chooseWeaponLabelBox.getChildren().add(chooseWeaponLabel);
        
        HBox weaponButtonBox = new HBox();
        weaponButtonBox.setAlignment(Pos.CENTER);
        weaponButtonBox.setPadding(new Insets(10));
        weaponButtonBox.setSpacing(10);
        
        rifleButton = GUIHelper.createChooseButton(Resources.RIFLE_ICON, "Sniper", Settings.WINDOW_HEIGHT * 0.15);
        pistolButton = GUIHelper.createChooseButton(Resources.PISTOL_ICON, "Pistole", Settings.WINDOW_HEIGHT * 0.15);
        shotgunButton = GUIHelper.createChooseButton(Resources.SHOTGUN_ICON, "Shotgun", Settings.WINDOW_HEIGHT * 0.15);
        wandButton = GUIHelper.createChooseButton(Resources.WAND_ICON, "Wand", Settings.WINDOW_HEIGHT * 0.15);
        
        rifleButton.setOnMouseClicked(event -> {
        	rifleButton.getStyleClass().clear();
        	rifleButton.getStyleClass().add("image-button-box-selected");
            
        	pistolButton.getStyleClass().clear();
        	pistolButton.getStyleClass().add("image-button-box");
            
        	shotgunButton.getStyleClass().clear();
        	shotgunButton.getStyleClass().add("image-button-box");
        	
        	wandButton.getStyleClass().clear();
        	wandButton.getStyleClass().add("image-button-box");
        	
        	weapon = WEAPON.RIFLE;
        });
        
        pistolButton.setOnMouseClicked(event -> {
        	rifleButton.getStyleClass().clear();
        	rifleButton.getStyleClass().add("image-button-box");
            
        	pistolButton.getStyleClass().clear();
        	pistolButton.getStyleClass().add("image-button-box-selected");
            
        	shotgunButton.getStyleClass().clear();
        	shotgunButton.getStyleClass().add("image-button-box");
        	
        	wandButton.getStyleClass().clear();
        	wandButton.getStyleClass().add("image-button-box");
        	
        	weapon = WEAPON.PISTOL;
        });
        
        shotgunButton.setOnMouseClicked(event -> {
        	rifleButton.getStyleClass().clear();
        	rifleButton.getStyleClass().add("image-button-box");
            
        	pistolButton.getStyleClass().clear();
        	pistolButton.getStyleClass().add("image-button-box");
            
        	shotgunButton.getStyleClass().clear();
        	shotgunButton.getStyleClass().add("image-button-box-selected");
        	
        	wandButton.getStyleClass().clear();
        	wandButton.getStyleClass().add("image-button-box");
        	
        	weapon = WEAPON.SHOTGUN;
        });
        
        wandButton.setOnMouseClicked(event -> {
        	rifleButton.getStyleClass().clear();
        	rifleButton.getStyleClass().add("image-button-box");
            
        	pistolButton.getStyleClass().clear();
        	pistolButton.getStyleClass().add("image-button-box");
            
        	shotgunButton.getStyleClass().clear();
        	shotgunButton.getStyleClass().add("image-button-box");
        	
        	wandButton.getStyleClass().clear();
        	wandButton.getStyleClass().add("image-button-box-selected");
        	
        	weapon = WEAPON.WAND;
        });
        
        weaponButtonBox.getChildren().addAll(rifleButton, pistolButton, shotgunButton, wandButton);
        
        HBox gameStartButtonBox = new HBox();
        gameStartButtonBox.setAlignment(Pos.CENTER);
        gameStartButtonBox.setPadding(new Insets(10));
        gameStartButtonBox.setSpacing(10);
        
        startGameButton = new Button("Starten");
        startGameButton.getStyleClass().add("button-green");
        
        Button cancelButton = new Button("Abbrechen");
        cancelButton.getStyleClass().add("button-red");
        
        startGameButton.setOnAction(e -> {
        	GameLauncher.startGameWithSettings(character, weapon);
            reset();
        });
        
        cancelButton.setOnAction(e -> {
            reset();
            gameScene.setState(GAMESTATE.MAIN);
        });
        
        gameStartButtonBox.getChildren().addAll(startGameButton, cancelButton);
        
        getChildren().addAll(chooseCharacterLabelBox, characterButtonBox, chooseWeaponLabelBox, weaponButtonBox, gameStartButtonBox);
        
	    Log.print("GameStartMenu were initialized!");
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
	
	public void reset() {
		character = null;
        weapon = null;
        
        char1Button.getStyleClass().clear();
		char1Button.getStyleClass().add("image-button-box");
		
		char2Button.getStyleClass().clear();
		char2Button.getStyleClass().add("image-button-box");
		
		char3Button.getStyleClass().clear();
		char3Button.getStyleClass().add("image-button-box");
		
		char4Button.getStyleClass().clear();
		char4Button.getStyleClass().add("image-button-box");
		
		rifleButton.getStyleClass().clear();
		rifleButton.getStyleClass().add("image-button-box");
		
		pistolButton.getStyleClass().clear();
		pistolButton.getStyleClass().add("image-button-box");
		
		shotgunButton.getStyleClass().clear();
		shotgunButton.getStyleClass().add("image-button-box");
	}
	
	public void update() {
		if (startGameButton != null) {
    		if (character == null || weapon == null) {
    			startGameButton.setDisable(true);
    		} else {
    			startGameButton.setDisable(false);
    		}
    	}
	}
}
