package com.toxicfrog.gui;

import com.toxicfrog.cache.Resources;
import com.toxicfrog.cache.SoundCache;
import com.toxicfrog.camera.Camera;
import com.toxicfrog.core.GameLauncher;
import com.toxicfrog.enums.ENUMS.GAMESTATE;
import com.toxicfrog.enums.ENUMS.SOUNDTYPE;
import com.toxicfrog.game.Game;
import com.toxicfrog.gui.debug.DebugGUI;
import com.toxicfrog.gui.panes.EndGameGUI;
import com.toxicfrog.gui.panes.GameGUI;
import com.toxicfrog.gui.panes.GameStartMenu;
import com.toxicfrog.gui.panes.GameView;
import com.toxicfrog.gui.panes.LoadingScreen;
import com.toxicfrog.gui.panes.MainMenu;
import com.toxicfrog.gui.panes.ShopMenu;
import com.toxicfrog.gui.panes.SplashScreen;
import com.toxicfrog.gui.panes.TaskMenu;
import com.toxicfrog.gui.panes.UnlockMenu;
import com.toxicfrog.gui.panes.pause.PauseGUI;
import com.toxicfrog.gui.panes.settings.SettingsMenu;
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
	public ShopMenu shopMenu;
	public TaskMenu taskMenu;
	public UnlockMenu unlockMenu;
	public GameGUI gameGui;
	public PauseGUI pauseGui;
	public EndGameGUI endGameGui;
	public DebugGUI debugGui;
	
	public Camera camera;
	
	private MediaPlayer menuSound;

	public GameScene(double width, double height) {
		super(new SplashScreen(), width, height);
		
		loadCSSStyle();

		loadingScreen = new LoadingScreen();
		mainMenu = new MainMenu();
		gameStartMenu = new GameStartMenu(this);
		settingsMenu = new SettingsMenu();
		gameView = new GameView();
		shopMenu = new ShopMenu();
		taskMenu = new TaskMenu();
		unlockMenu = new UnlockMenu();
		gameGui = new GameGUI();
		pauseGui = new PauseGUI(this);
		endGameGui = new EndGameGUI(this); 
		
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
		menuSound = SoundManager.playSound(SoundCache.getSound(Resources.MUSIC_02), InternalSettings.VOLUME_MUSIC * Settings.MUSICVOLUME, true, SOUNDTYPE.MUSIC);
	}
	
	public void update(Game game, double delta) {
		if (input != null) {
			if (state.equals(GAMESTATE.INGAME)) {
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
			} else {
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
			}
			
			if (state.equals(GAMESTATE.MAIN)) {
				if (mainMenu != null) {
					mainMenu.update(game);
				}
			}

			if (state.equals(GAMESTATE.STARTGAME)) {
				if (gameStartMenu != null) {
					gameStartMenu.update();
				}
			}
			
			if (state.equals(GAMESTATE.PAUSED)) {
				if (pauseGui != null) {
					pauseGui.update(game, camera);
					
					if (!pauseGui.isShowing()) {
						pauseGui.show(GameLauncher.primaryStage);
					}
				}
			} else {
				if (pauseGui != null) {
					if (pauseGui.isShowing()) {
						pauseGui.hide();
					}
				}
			}
			
			if (state.equals(GAMESTATE.ENDGAME)) {
				if (endGameGui != null) {
					endGameGui.update(game, camera);
					
					if (!endGameGui.isShowing()) {
						endGameGui.show(GameLauncher.primaryStage);
					}
				}
			} else {
				if (endGameGui != null) {
					if (endGameGui.isShowing()) {
						endGameGui.hide();
					}
				}
			}
			
			if (state.equals(GAMESTATE.SHOP)) {
				if (shopMenu != null) {
					shopMenu.update();
				}
			}
			
			if (state.equals(GAMESTATE.TASKS)) {
				if (taskMenu != null) {
					taskMenu.update();
				}
			}
			
			if (state.equals(GAMESTATE.UNLOCKS)) {
				if (unlockMenu != null) {
					unlockMenu.update();
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
					setState(GAMESTATE.PAUSED);
				}
				
				if (state.equals(GAMESTATE.PAUSED)) {
					//setState(GAMESTATE.MAIN);
				}
				
				if (state.equals(GAMESTATE.ENDGAME)) {
					//setState(GAMESTATE.MAIN);
				}
				
				if (state.equals(GAMESTATE.SHOP)) {
					setState(GAMESTATE.MAIN);
				}
				
				if (state.equals(GAMESTATE.UNLOCKS)) {
					setState(GAMESTATE.MAIN);
				}
				
				if (state.equals(GAMESTATE.TASKS)) {
					setState(GAMESTATE.MAIN);
				}
			}
			
			if (input.isKeyClicked(KeyCode.F5)) {
				Settings.DEBUG_GUI = !Settings.DEBUG_GUI;
			}
		}
	}

	public void setState(GAMESTATE newState) {
		if (!newState.equals(state)) {
			if (Settings.DEBUG_LOG) {
				Log.print("GAMESTATE old:" +  this.state + ", new: " + state.toString());
			}

			this.state = newState;
			
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
				case PAUSED:
					setRoot(gameView);
					break;
				case ENDGAME:
					setRoot(gameView);
					break;
				case SHOP:
					setRoot(shopMenu);
					break;
				case TASKS:
					setRoot(taskMenu);
					break;
				case UNLOCKS:
					setRoot(unlockMenu);
					break;
				default:
					break;
			}
		}
	}

	public void destroy() {
		if (menuSound != null) {
			SoundManager.removePlayer(menuSound);
			menuSound = null;
		}
	}
}
