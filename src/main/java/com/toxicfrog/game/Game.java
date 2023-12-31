package com.toxicfrog.game;

import com.toxicfrog.balancing.CoreBalance;
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

	public Game(CHARACTER character, WEAPON weapon, GameScene gameScene) {

		Log.print("New game started. Character: " + character.toString() + ", Weapon: " + weapon.toString());
		
		long duration = (long) (1000*60*CoreBalance.GAME_ROUND_LENGTH);
		
		level = new Level(4096, 4096, character, weapon, duration, gameScene);
		renderer = new Renderer();
	}
	
	public void update(GameScene scene, double delta) {
		if (level != null) {
			if (scene.state.equals(GAMESTATE.INGAME) || scene.state.equals(GAMESTATE.ENDGAME)) {
				level.update(delta);
			} else {
				level.pause();
			}
		}
    }

	public void render(GameScene scene) {
		if (renderer != null && level != null && scene != null) {
			if (scene.state.equals(GAMESTATE.INGAME) || scene.state.equals(GAMESTATE.ENDGAME)) {
				renderer.render(scene.gameView.canvas, scene.camera, level);
			}
		}
    }

	public void stop() {
		if (level != null) {
			level.destroy();
		}
		
		if (renderer != null) {
			renderer = null;
		}
	}
}
