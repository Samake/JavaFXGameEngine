package com.toxicfrog.gui.panes.settings;

import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.sound.SoundManager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SettingsSound extends Tab {

	private VBox contentPane = new VBox();
	
	public SettingsSound(String title) {
		super(title);
		
		contentPane.setAlignment(Pos.TOP_LEFT);
		
		addMaxSoundsBox();
		addMasterVolumeBox();
		addMusicVolumeBox();
		
		setContent(contentPane);
		
		Log.print("SettingsSound were initialized!");
	}
	
	private void addMaxSoundsBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.setPadding(new Insets(10));
		labelBox.setSpacing(10);
		labelBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Label label = new Label("Maximale Sounds");
		label.getStyleClass().add("gui-label");
		
		labelBox.getChildren().addAll(label);
		
		HBox changeBox = new HBox();
		changeBox.setAlignment(Pos.CENTER_LEFT);
		changeBox.setPadding(new Insets(10));
		changeBox.setSpacing(10);
		changeBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		ComboBox<Integer> dropdown = new ComboBox<Integer>();

		dropdown.getItems().add(16);
		dropdown.getItems().add(32);
		dropdown.getItems().add(64);
		
		if (Settings.MAXSOUNDS == 16) {
			dropdown.getSelectionModel().select(0);
		}
		
		if (Settings.MAXSOUNDS == 32) {
			dropdown.getSelectionModel().select(1);
		}
		
		if (Settings.MAXSOUNDS == 64) {
			dropdown.getSelectionModel().select(2);
		}
	
		dropdown.setOnAction(event -> {
			Settings.MAXSOUNDS = dropdown.getValue();
		});
		
		changeBox.getChildren().addAll(dropdown);
		
		box.getChildren().addAll(labelBox, changeBox);
		
		contentPane.getChildren().add(box);
	}
	
	private void addMasterVolumeBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.setPadding(new Insets(10));
		labelBox.setSpacing(10);
		labelBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Label label = new Label("Lautstärke Allgemein");
		label.getStyleClass().add("gui-label");
		
		labelBox.getChildren().addAll(label);
		
		HBox changeBox = new HBox();
		changeBox.setAlignment(Pos.CENTER_LEFT);
		changeBox.setPadding(new Insets(10));
		changeBox.setSpacing(10);
		changeBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Slider slider = new Slider();
		slider.setMin(0.0);
        slider.setMax(1.0);
        slider.setValue(Settings.MASTERVOLUME);
        slider.setMajorTickUnit(0.05);
        
        slider.valueProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				Settings.MASTERVOLUME = newValue.doubleValue();
				SoundManager.updateVolume();
			}
        });

		changeBox.getChildren().addAll(slider);
		
		box.getChildren().addAll(labelBox, changeBox);
		
		contentPane.getChildren().add(box);
	}
	
	private void addMusicVolumeBox() {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.setSpacing(10);
		
		HBox labelBox = new HBox();
		labelBox.setAlignment(Pos.CENTER_RIGHT);
		labelBox.setPadding(new Insets(10));
		labelBox.setSpacing(10);
		labelBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Label label = new Label("Lautstärke Musik");
		label.getStyleClass().add("gui-label");
		
		labelBox.getChildren().addAll(label);
		
		HBox changeBox = new HBox();
		changeBox.setAlignment(Pos.CENTER_LEFT);
		changeBox.setPadding(new Insets(10));
		changeBox.setSpacing(10);
		changeBox.setPrefWidth(Settings.WINDOW_WIDTH * 0.3);
		
		Slider slider = new Slider();
		slider.setMin(0.0);
        slider.setMax(1.0);
        slider.setValue(Settings.MUSICVOLUME);
        slider.setMajorTickUnit(0.05);
        
        slider.valueProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				Settings.MUSICVOLUME = newValue.doubleValue();
				SoundManager.updateVolume();
			}
        });

		changeBox.getChildren().addAll(slider);
		
		box.getChildren().addAll(labelBox, changeBox);
		
		contentPane.getChildren().add(box);
	}
}
