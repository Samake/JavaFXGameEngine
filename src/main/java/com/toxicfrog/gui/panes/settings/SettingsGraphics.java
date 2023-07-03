package com.toxicfrog.gui.panes.settings;

import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsGraphics extends Tab {

	private VBox contentPane = new VBox();
	
	public SettingsGraphics(String title) {
		super(title);
		
		contentPane.setAlignment(Pos.TOP_LEFT);
		
		addResolutionBox();
		addFullScreenBox();
		addShadowBox();
		
		setContent(contentPane);
		
		Log.print("SettingsGraphics were initialized!");
	}

	private void addResolutionBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.setPadding(new Insets(10));
		labelBox.setSpacing(10);
		labelBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Label label = new Label("Auflösung");
		label.getStyleClass().add("gui-label");
		
		labelBox.getChildren().addAll(label);
		
		HBox changeBox = new HBox();
		changeBox.setAlignment(Pos.CENTER_LEFT);
		changeBox.setPadding(new Insets(10));
		changeBox.setSpacing(10);
		changeBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		ComboBox<String> dropdown = new ComboBox<String>();
		String resolution1 = "1280x720";
		String resolution2 = "1920x1080";
		
		dropdown.getItems().add(resolution1);
		dropdown.getItems().add(resolution2);
		
		if (resolution1.contains(String.valueOf(Settings.WINDOW_WIDTH))) {
			dropdown.getSelectionModel().select(0);
		}
		
		if (resolution2.contains(String.valueOf(Settings.WINDOW_WIDTH))) {
			dropdown.getSelectionModel().select(1);
		}
		
		dropdown.setOnAction(event -> {
		    changeResolution(dropdown.getValue());
		});
		
		changeBox.getChildren().addAll(dropdown);
		
		box.getChildren().addAll(labelBox, changeBox);
		
		contentPane.getChildren().add(box);
	}
	
	private void addFullScreenBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.setPadding(new Insets(10));
		labelBox.setSpacing(10);
		labelBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Label label = new Label("Vollbild");
		label.getStyleClass().add("gui-label");
		
		labelBox.getChildren().addAll(label);
		
		HBox changeBox = new HBox();
		changeBox.setAlignment(Pos.CENTER_LEFT);
		changeBox.setPadding(new Insets(10));
		changeBox.setSpacing(10);
		changeBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		CheckBox checkBox = new CheckBox();
		checkBox.getStyleClass().add("check-box");
		
		checkBox.setSelected(Settings.WINDOW_FULLSCREEN);
		
		checkBox.setOnAction(event -> {
			Settings.WINDOW_FULLSCREEN = checkBox.isSelected();
		});

		changeBox.getChildren().addAll(checkBox);
		
		box.getChildren().addAll(labelBox, changeBox);
		
		contentPane.getChildren().add(box);
	}
	
	private void addShadowBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.setPadding(new Insets(10));
		labelBox.setSpacing(10);
		labelBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Label label = new Label("Schatten");
		label.getStyleClass().add("gui-label");
		
		labelBox.getChildren().addAll(label);
		
		HBox changeBox = new HBox();
		changeBox.setAlignment(Pos.CENTER_LEFT);
		changeBox.setPadding(new Insets(10));
		changeBox.setSpacing(10);
		changeBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		CheckBox checkBox = new CheckBox();
		checkBox.getStyleClass().add("check-box");
		
		checkBox.setSelected(Settings.RENDER_SHADOWS);
		
		checkBox.setOnAction(event -> {
			Settings.RENDER_SHADOWS = checkBox.isSelected();
		});

		changeBox.getChildren().addAll(checkBox);
		
		box.getChildren().addAll(labelBox, changeBox);
		
		contentPane.getChildren().add(box);
	}

	private void changeResolution(String resolutionString) {

	}
}
