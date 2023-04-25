package multiplayer2;
import main.MainMenu;

import java.io.Serializable;

public class Data2 implements Serializable {

	private static final long serialVersionUID = 1L;

	public int clientScore;
	public int enemyScore;

	public int gameState;

	public Data2(MainMenu game) {

		clientScore = game.gp.pacman.score;
		gameState = game.gp.gameState;
		enemyScore = 0;

	}
}