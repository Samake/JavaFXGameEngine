package com.toxicfrog.gui.panes;

import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsMain extends Tab {

	private VBox contentPane = new VBox();
	
	public SettingsMain(String title) {
		super(title);
		
		contentPane.setAlignment(Pos.TOP_LEFT);
		
		addDebugLogBox();
		addDebugGUIBox();
		addRenderCollissionBox();
		
		setContent(contentPane);
		
		Log.print("SettingsMain were initialized!");
	}
	
	private void addDebugLogBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.setPadding(new Insets(10));
		labelBox.setSpacing(10);
		labelBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Label label = new Label("Debug Logging");
		label.getStyleClass().add("gui-label");
		
		labelBox.getChildren().addAll(label);
		
		HBox changeBox = new HBox();
		changeBox.setAlignment(Pos.CENTER_LEFT);
		changeBox.setPadding(new Insets(10));
		changeBox.setSpacing(10);
		changeBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		CheckBox checkBox = new CheckBox();
		checkBox.getStyleClass().add("check-box");
		
		checkBox.setSelected(Settings.DEBUG_LOG);
		
		checkBox.setOnAction(event -> {
			Settings.DEBUG_LOG = checkBox.isSelected();
		});

		changeBox.getChildren().addAll(checkBox);
		
		box.getChildren().addAll(labelBox, changeBox);
		
		contentPane.getChildren().add(box);
	}
	
	private void addDebugGUIBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.setPadding(new Insets(10));
		labelBox.setSpacing(10);
		labelBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Label label = new Label("Debug GUI");
		label.getStyleClass().add("gui-label");
		
		labelBox.getChildren().addAll(label);
		
		HBox changeBox = new HBox();
		changeBox.setAlignment(Pos.CENTER_LEFT);
		changeBox.setPadding(new Insets(10));
		changeBox.setSpacing(10);
		changeBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		CheckBox checkBox = new CheckBox();
		checkBox.getStyleClass().add("check-box");
		
		checkBox.setSelected(Settings.DEBUG_GUI);
		
		checkBox.setOnAction(event -> {
			Settings.DEBUG_GUI = checkBox.isSelected();
		});

		changeBox.getChildren().addAll(checkBox);
		
		box.getChildren().addAll(labelBox, changeBox);
		
		contentPane.getChildren().add(box);
	}
	
	private void addRenderCollissionBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.setPadding(new Insets(10));
		labelBox.setSpacing(10);
		labelBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Label label = new Label("Show Collission Boxes");
		label.getStyleClass().add("gui-label");
		
		labelBox.getChildren().addAll(label);
		
		HBox changeBox = new HBox();
		changeBox.setAlignment(Pos.CENTER_LEFT);
		changeBox.setPadding(new Insets(10));
		changeBox.setSpacing(10);
		changeBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		CheckBox checkBox = new CheckBox();
		checkBox.getStyleClass().add("check-box");
		
		checkBox.setSelected(Settings.RENDER_COLLISSION);
		
		checkBox.setOnAction(event -> {
			Settings.RENDER_COLLISSION = checkBox.isSelected();
		});

		changeBox.getChildren().addAll(checkBox);
		
		box.getChildren().addAll(labelBox, changeBox);
		
		contentPane.getChildren().add(box);
	}
}
