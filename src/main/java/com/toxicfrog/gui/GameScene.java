package com.toxicfrog.gui;

import com.toxicfrog.cache.Resources;
import com.toxicfrog.cache.SoundCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.core.GameLauncher;
import com.toxicfrog.enums.ENUMS.GAMESTATE;
import com.toxicfrog.game.Game;
import com.toxicfrog.gui.debug.DebugGUI;
import com.toxicfrog.gui.panes.GameGUI;
import com.toxicfrog.gui.panes.GameStartMenu;
import com.toxicfrog.gui.panes.GameView;
import com.toxicfrog.gui.panes.LoadingScreen;
import com.toxicfrog.gui.panes.MainMenu;
import com.toxicfrog.gui.panes.SettingsMenu;
import com.toxicfrog.gui.panes.SplashScreen;
import com.toxicfrog.input.Input;
import com.toxicfrog.logging.Log;
import com.toxicfrog.settings.InternalSettings;
import com.toxicfrog.settings.Path;
import com.toxicfrog.settings.Settings;
import com.toxicfrog.sound.SoundManager;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

public class GameScene extends Scene {
	

	public GAMESTATE state;
	public Input input;
	
	public LoadingScreen loadingScreen;
	public static MainMenu mainMenu;
	public GameStartMenu gameStartMenu;
	public SettingsMenu settingsMenu;
	public GameView gameView;
	public GameGUI gameGui;
	public DebugGUI debugGui;
	
	public Camera camera;
	
	private MediaPlayer menuSound;
	private MediaPlayer ingameSound;

	public GameScene(double width, double height) {
		super(new SplashScreen(), width, height);
		
		loadCSSStyle();

		loadingScreen = new LoadingScreen();
		mainMenu = new MainMenu();
		gameStartMenu = new GameStartMenu(this);
		settingsMenu = new SettingsMenu();
		gameView = new GameView();
		gameGui = new GameGUI();
		
		debugGui = new DebugGUI();
		
		input = new Input(this);
		camera = new Camera();
        
        initSound();
        
        Log.print("GameScene were initialized!");
        
        setState(GAMESTATE.LOADING);
        loadingScreen.loadResources(this);
	}

	private void loadCSSStyle() {
		getStylesheets().add(Path.STYLES_PATH + "game.css");
		
		Log.print("CSS styles were loaded!");
	}
	
	private void initSound() {
		menuSound = SoundManager.playSound(SoundCache.getSound(Resources.MUSIC_02), InternalSettings.VOLUME_MUSIC * Settings.MUSICVOLUME, true);
		menuSound.stop();
        ingameSound = SoundManager.playSound(SoundCache.getSound(Resources.MUSIC_01), InternalSettings.VOLUME_MUSIC * Settings.MUSICVOLUME, true);
        ingameSound.stop();
	}
	
	public void update(Game game, double delta) {
		if (input != null) {
			if (!state.equals(GAMESTATE.INGAME)) {
				if (gameGui != null) {
					if (gameGui.isShowing()) {
						gameGui.hide();
					}
				}
				
				if (debugGui != null) {
					if (debugGui.isShowing()) {
						debugGui.hide();
					}
				}
				
				if (menuSound != null) {
					if (!menuSound.getStatus().equals(Status.PLAYING)) {
						menuSound.play();
					}
				}
				
				if (ingameSound != null) {
					if (ingameSound.getStatus().equals(Status.PLAYING)) {
						ingameSound.pause();
					}
				}
				
				if (state.equals(GAMESTATE.STARTGAME)) {
					if (gameStartMenu != null) {
						gameStartMenu.update();
					}
				}
			} else {
				if (input != null) {
					input.update(camera);
				}
				
				if (gameGui != null) {
					gameGui.update(game, camera);
					
					if (!gameGui.isShowing()) {
						gameGui.show(GameLauncher.primaryStage);
					}
				}
				
				if (debugGui != null) {
					if (Settings.DEBUG_GUI) {
						debugGui.update(game, camera);
						
						if (!debugGui.isShowing()) {
							debugGui.show(GameLauncher.primaryStage);
						}
					} else {
						if (debugGui.isShowing()) {
							debugGui.hide();
						}
					}

				}
				
				if (menuSound != null) {
					if (menuSound.getStatus().equals(Status.PLAYING)) {
						menuSound.pause();
					}
				}
				
				if (ingameSound != null) {
					if (!ingameSound.getStatus().equals(Status.PLAYING)) {
						ingameSound.play();
					}
				}
			}
			
			if (input.isKeyClicked(KeyCode.ESCAPE)) {
				if (state.equals(GAMESTATE.SETTINGS)) {
					setState(GAMESTATE.MAIN);
				}
				
				if (state.equals(GAMESTATE.STARTGAME)) {
					setState(GAMESTATE.MAIN);
				}
				
				if (state.equals(GAMESTATE.INGAME)) {
					setState(GAMESTATE.MAIN);
				}
			}
			
			if (input.isKeyClicked(KeyCode.F5)) {
				Settings.DEBUG_GUI = !Settings.DEBUG_GUI;
			}
		}
		
		if (mainMenu != null) {
			mainMenu.update(game);
		}
	}

	public void setState(GAMESTATE state) {
		if (Settings.DEBUG_LOG) {
			Log.print("GAMESTATE old:" +  this.state + ", new: " + state.toString());
		}

		this.state = state;
		
		switch(state) {
			case LOADING:
				setRoot(loadingScreen);
				break;
			case MAIN:
				setRoot(mainMenu);
				break;
			case SETTINGS:
				setRoot(settingsMenu);
				break;
			case STARTGAME:
				setRoot(gameStartMenu);
				break;
			case INGAME:
				setRoot(gameView);
				break;
			case ENDGAME:
				setRoot(null);
				break;
			case SHOP:
				setRoot(null);
				break;
			case TASKS:
				setRoot(null);
				break;
			case UNLOCKS:
				setRoot(null);
				break;
		}
	}
}
