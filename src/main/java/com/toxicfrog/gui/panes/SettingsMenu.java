package com.toxicfrog.gui.panes;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class SettingsMenu extends VBox {
	
	public SettingsMenu() {
		setAlignment(Pos.CENTER);
		setPadding(new Insets(10));
		setSpacing(10);
		
	    addBackGroundImage();

	    Log.print("SettingsMenu were initialized!");
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
}
