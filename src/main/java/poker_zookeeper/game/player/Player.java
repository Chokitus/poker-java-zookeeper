package poker_zookeeper.game.player;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import poker_zookeeper.game.deck.DeckGenerator;
import poker_zookeeper.game.deck.card.Card;
import poker_zookeeper.game.exception.PokerException;
import poker_zookeeper.game.table.PokerTable;

public class Player {
	@Getter
	private final List<Card> deck = new ArrayList<>();

	@Setter(AccessLevel.PRIVATE)
	private float fichas;

	private DeckGenerator deckGenerator;

	@Getter
	private PokerTable table;

	public Player() throws PokerException {
		this.setFichas(1000);
		this.table = new PokerTable();
		this.deckGenerator = DeckGenerator.getInstance(this);
	}

	public void addHand(final Card card, final Card card2) throws PokerException {
		if (!this.deck.isEmpty()) {
			throw new PokerException("Mão não vazia");
		}
		this.deck.add(card);
		this.deck.add(card2);
	}

	public void distributeCards() throws PokerException {
		this.deckGenerator.fetchPlayers();
		this.deckGenerator.genCardsToPlayers();
	}

	public void betAndwaitForBets() {
		this.bet(200);
		/**
		 * Faz a sincronização, além do próprio dealer apostar primeiro
		 */
	}

	private void bet(final int bet) {
		this.table.addBet(bet);
		this.fichas -= bet;
	}

	public void setTable() {
		this.deckGenerator.getCardsToTable();
	}

	public int getScore() {

	}

}
