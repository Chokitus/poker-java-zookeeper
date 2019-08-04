package poker_zookeeper.game;

import poker_zookeeper.game.exception.PokerException;
import poker_zookeeper.game.player.Player;

/**
 * Hello world!
 *
 */
public class PokerGame {
	public static void main(final String[] args) throws PokerException {
		Player player = new Player();
		player.distributeCards();
		player.betAndwaitForBets();
		player.setTable();
	}
}
