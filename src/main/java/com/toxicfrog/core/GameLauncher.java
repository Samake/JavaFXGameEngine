package com.toxicfrog.core;

import com.toxicfrog.cache.ImageCache;
import com.toxicfrog.enums.ENUMS.CHARACTER;
import com.toxicfrog.enums.ENUMS.GAMESTATE;
import com.toxicfrog.enums.ENUMS.WEAPON;
import com.toxicfrog.game.Game;
import com.toxicfrog.gui.GameScene;
import com.toxicfrog.logging.Log;
import com.toxicfrog.loot.LootManager;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.sound.SoundManager;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameLauncher extends Application {

	public static Stage primaryStage;
	
	private long lastFrameTime;
    private double targetFPS = 60.0;
    private double targetDelta = 1.0 / targetFPS;
    private double deltaCount = 0.0;
    private int fpsCount = 0;

    private static String title = "";
    private static GameScene scene;
    private static Game game;
    
    public static int FPS = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	GameLauncher.primaryStage = primaryStage;
    	
    	System.setProperty("prism.order", "sw");
    	System.setProperty("prism.forceGPU", "true");

    	primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("Alt+F4"));
    	primaryStage.setFullScreenExitHint("");
    	
    	title = InternalSettings.TITLE + " - " + InternalSettings.VERSION;
        primaryStage.setTitle(title);
        
        Log.print(title + " were started!");
        
        Settings.load();
        LootManager.init();
        
        if (Settings.WINDOW_FULLSCREEN) {
        	primaryStage.setFullScreen(true);
        }

        primaryStage.initStyle(StageStyle.UNDECORATED);

        scene = new GameScene(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        startGameLoop();
    }

    private void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
            	
            	double deltaTime = (currentNanoTime - lastFrameTime) / 1_000_000_000.0;
            	
            	deltaCount += deltaTime;
            	fpsCount++;
            	
            	if (deltaCount >= 1) {
            		FPS = fpsCount;
            		deltaCount = 0;
            		fpsCount = 0;
            	}
            	
                if (deltaTime >= targetDelta) {
                    update(deltaTime / targetDelta);
                    lastFrameTime = currentNanoTime;
                }
                
                render();
            }
        };

        gameLoop.start();
    }
    
    private void update(double delta) {
    	if (scene != null) {
    		scene.update(game, delta);
    		
	    	if (game != null) {
	    		game.update(scene, delta);
	    	}
	    	
	    	if (game != null && scene.state.equals(GAMESTATE.INGAME)) {
	    		scene.setCursor(ImageCache.getCursor());
	    	} else {
	    		scene.setCursor(Cursor.DEFAULT);
	    	}
    	}
    }

    private void render() {
    	if (scene != null) {
	    	if (game != null) {
	    		game.render(scene);
	    	}
    	}
    }
    
    public static void startGameWithSettings(CHARACTER character, WEAPON weapon) {
    	if (game == null) {
    		game = new Game(character, weapon, scene);
    		scene.setState(GAMESTATE.INGAME);
    	}
    }
    
    public static void startGame() {
    	scene.setState(GAMESTATE.STARTGAME);
    }
    
    public static void resumeGame() {
    	if (game != null) {
    		scene.setState(GAMESTATE.INGAME);
    	}
    }
    
    public static void stopGame() {
    	if (game != null) {
    		game.stop();
    		game = null;
    	}
    }

    public static void openSettings() {
    	if (scene != null) {
    		scene.setState(GAMESTATE.SETTINGS);
    	}
    }

    public static void exitGame() {
    	scene.destroy();
    	SoundManager.destroy();
    	
    	Log.print(title + " was closed!");
    	
        Platform.exit();
        System.exit(0);
    }
}
