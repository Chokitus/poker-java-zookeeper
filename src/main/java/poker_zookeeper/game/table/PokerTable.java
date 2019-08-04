package poker_zookeeper.game.table;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import poker_zookeeper.game.deck.card.Card;

public class PokerTable {
	@Getter
	private final List<Card> listOfCards = new ArrayList<>();
	private int bets = 0;

	public PokerTable() {
		// TODO Auto-generated constructor stub
	}

	public void addBet(final int bet) {
		this.bets += bet;
	}

	public void addCard(final Card newCard) {
		this.listOfCards.add(newCard);
	}

}
