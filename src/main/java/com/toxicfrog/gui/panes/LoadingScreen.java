package com.toxicfrog.gui.panes;

import java.util.Map.Entry;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.cache.Resources;
import com.toxicfrog.cache.SoundCache;
import com.toxicfrog.enums.ENUMS.GAMESTATE;
import com.toxicfrog.gui.GameScene;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.Path;
import com.toxicfrog.settings.Settings;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LoadingScreen extends StackPane {
	
	private ProgressBar progressBar;
	private Label progressText;
	private int sleepTime = 0;
	
	public LoadingScreen() {
		
		getStyleClass().add("loading-pane");
		
		setAlignment(Pos.CENTER);
		
		VBox contentPane = new VBox();
		contentPane.setAlignment(Pos.CENTER);
		contentPane.setPadding(new Insets(10));
		contentPane.setSpacing(10);
		
		Image logo = ImageCache.getImage(Resources.LOGO, null);
		ImageView logoView = new ImageView(logo);
		logoView.setFitWidth(Settings.WINDOW_HEIGHT * 0.3);
		logoView.setFitHeight(Settings.WINDOW_HEIGHT * 0.3);

        progressText = new Label();
        progressText.getStyleClass().add("loading-label");
        
        progressBar = new ProgressBar();
        progressBar.setPrefWidth(Settings.WINDOW_WIDTH * 0.8);
        progressBar.getStyleClass().add("loading-bar");
        
        contentPane.getChildren().addAll(logoView, progressText, progressBar);

        getChildren().add(contentPane);
        
        Log.print("LoadingScreen were initialized!");
	}

	public void loadResources(GameScene gameScene) {
		Resources.load();
        
        Task<Void> loadingTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
            	int i = 0;
            	
            	Log.print("Loadings Fonts ...");
 
            	updateMessage("FONT: " + Path.FONT_PATH + "Hardpixel.OTF");
        		
            	Resources.FONT_DEFAULT  = Font.loadFont(Path.FONT_PATH + "Hardpixel.OTF", 20);
        		
        		i++;
        		
            	updateProgress(i, Resources.getResourceCount());
            	
            	Thread.sleep(sleepTime * 2);
            	
            	Log.print("1 Font were loaded!");
            	
            	Log.print("Loading textures ...");
            	
            	for (Entry<String, double[]> texturesPathEntry : Resources.texturesPaths.entrySet()) {
        			if (texturesPathEntry != null) {
        				updateMessage("TEXTURE: " + texturesPathEntry.getKey());
        				
        				ImageCache.getImage(texturesPathEntry.getKey(), texturesPathEntry.getValue());
        				
        				i++;
        				
        				updateProgress(i, Resources.getResourceCount());
        				
        				Thread.sleep(sleepTime);
        			}
        		}
            	
            	Log.print(Resources.texturesPaths.size() + " texures were loaded!");
            	
            	Log.print("Loading sounds ...");
        		
        		for (String soundPath : Resources.soundPaths) {
        			if (soundPath != null) {
        				updateMessage("SOUND: " + soundPath);
        				
        				SoundCache.getSound(soundPath);
        				
        				i++;
        				
        				updateProgress(i, Resources.getResourceCount());
        				
        				Thread.sleep(sleepTime * 2);
        			}
        		}
        		
        		Log.print(Resources.soundPaths.size() + " sounds were loaded!");
            	
                return null;
            }
        };

        progressBar.progressProperty().bind(loadingTask.progressProperty());
        progressText.textProperty().bind(loadingTask.messageProperty());

        loadingTask.setOnSucceeded(event -> {
        	Log.print("Resources were loaded!");
        	
        	gameScene.setState(GAMESTATE.MAIN);
        });

        new Thread(loadingTask).start();
	}
	
}
