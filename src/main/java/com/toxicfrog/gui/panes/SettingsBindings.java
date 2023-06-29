package com.toxicfrog.gui.panes;

import com.toxicfrog.logging.Log;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

public class SettingsBindings extends Tab {

	private VBox contentPane = new VBox();
	
	public SettingsBindings(String title) {
		super(title);
		
		contentPane.setAlignment(Pos.TOP_LEFT);
		
		setContent(contentPane);
		
		Log.print("SettingsBindings were initialized!");
	}
}
