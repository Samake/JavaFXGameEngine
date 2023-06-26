package com.toxicfrog.gui.panes;

import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

public class GameView extends BorderPane  {

	public Canvas canvas;

	public GameView() {

		canvas = new Canvas(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);

		Log.print("Canvas were initialized!");
		
		getChildren().addAll(canvas);
		
		Log.print("GameView were initialized!");
	}
}
