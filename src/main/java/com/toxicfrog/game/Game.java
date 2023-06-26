package com.toxicfrog.game;

import com.toxicfrog.enums.ENUMS.CHARACTER;
import com.toxicfrog.enums.ENUMS.GAMESTATE;
import com.toxicfrog.enums.ENUMS.WEAPON;
import com.toxicfrog.gui.GameScene;
import com.toxicfrog.level.Level;
import com.toxicfrog.logging.Log;
import com.toxicfrog.rendering.Renderer;

public class Game {

	public Level level;
	public Renderer renderer;
	
	public Game(CHARACTER character, WEAPON weapon) {
		Log.print("New game started. Character: " + character.toString() + ", Weapon: " + weapon.toString());
		
		level = new Level(4096, 4096, character, weapon, 1000*60*10);
		renderer = new Renderer();
	}
	
	public void update(GameScene scene, double delta) {
		if (level != null) {
			if (scene.state.equals(GAMESTATE.INGAME)) {
				//scene.gameView.canvas.setWidth(level.width);
				//scene.gameView.canvas.setHeight(level.height);
				
				level.update(scene, delta);
			}
		}
    }

	public void render(GameScene scene) {
		if (renderer != null && level != null && scene != null) {
			if (scene.state.equals(GAMESTATE.INGAME)) {
				renderer.render(scene.gameView.canvas, scene.camera, level);
			}
		}
    }

	public void stop() {
		if (level != null) {
			level.stop();
		}
		
		if (renderer != null) {
			renderer = null;
		}
	}
}
