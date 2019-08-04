package poker_zookeeper.game.player;

import java.util.ArrayList;
import java.util.List;

import poker_zookeeper.game.deck.card.Card;
import poker_zookeeper.game.deck.card.Naipe;

public abstract class PlayerScoreHelper {

	public static List<Card> listOfCards = new ArrayList<>();

	public void refreshListOfCards(final Player player) {
		PlayerScoreHelper.listOfCards.addAll(player.getDeck());
		PlayerScoreHelper.listOfCards.addAll(player.getTable().getListOfCards());
	}

	public static boolean isFlush() {
		if (PlayerScoreHelper.listOfCards.size() < 5) {
			return false;
		}
		int copas = 0;
		int paus = 0;
		int espadas = 0;
		int ouros = 0;
		for (Card card : PlayerScoreHelper.listOfCards) {
			Naipe naipe = card.getNaipe();
			switch (naipe) {
				case COPAS :
					copas++;
					break;
				case OUROS :
					ouros++;
					break;
				case ESPADAS :
					espadas++;
					break;
				default :
					paus++;
			}
		}
		return copas >= 5 || paus >= 5 || espadas >= 5 || ouros >= 5;
	}

	public int isStraight() {
		boolean[] cardsPosition = new boolean[14];

		PlayerScoreHelper.listOfCards.stream().map(p -> p.getNumericValue()).forEach(p -> {
			cardsPosition[p - 1] = true;
			if (p == 14) {
				cardsPosition[0] = true;
			}
		});
		int cont = 0;
		int biggest = 0;
		for (int i = 0; i < 14; i++) {
			if (cont >= 5) {
				biggest = i;
			}
			if (cardsPosition[i]) {
				cont++;
			} else {
				cont = 0;
			}
		}

		return biggest;
	}
}
