package com.toxicfrog.gui.panes.pause;

import com.toxicfrog.logging.Log;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class PauseTitle extends VBox {

	public PauseTitle() {
		setAlignment(Pos.CENTER);
	    setPadding(new Insets(10));
	    setSpacing(10);
	    
		getStyleClass().add("menu-box");
		
		Label title = new Label("PAUSE");
		title.getStyleClass().add("gui-label-red-bold");
		
		getChildren().addAll(title);
		
		Log.print("PauseTitle were initialized!");
	}
}
