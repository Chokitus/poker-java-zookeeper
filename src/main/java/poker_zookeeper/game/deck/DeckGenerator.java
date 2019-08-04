package poker_zookeeper.game.deck;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import poker_zookeeper.game.deck.card.Card;
import poker_zookeeper.game.exception.PokerException;
import poker_zookeeper.game.player.Player;

public class DeckGenerator {
	@Getter
	@Setter(AccessLevel.PRIVATE)
	private Player dealer;

	private static DeckGenerator instance;

	@Getter
	private final Deque<Player> listOfPlayers = new LinkedList<>();

	private final List<Integer> generatedCards = new ArrayList<>();

	private final Random rng = new Random();

	private DeckGenerator(final Player dealer) {
		this.dealer = dealer;
	}

	public static DeckGenerator getInstance(final Player dealer) throws PokerException {
		if (DeckGenerator.instance == null) {
			if (dealer == null) {
				throw new PokerException("Primeira chamada de DeckGenerator: envie um dealer");
			}
			DeckGenerator.instance = new DeckGenerator(dealer);
		}
		return DeckGenerator.instance;
	}

	public void setDealer(final Player dealer) {
		if (dealer == null) {
			this.dealer = dealer;
		}
	}

	public void genCardsToPlayers() throws PokerException {
		while (!this.listOfPlayers.isEmpty()) {
			Player player = this.listOfPlayers.pop();
			Card card = this.getNewCard();
			Card card2 = this.getNewCard();
			player.addHand(card, card2);
		}
	}

	private Card getNewCard() {
		int card = this.rng.nextInt(52) + 1;
		while (this.generatedCards.contains(card)) {
			card = this.rng.nextInt(52) + 1;
		}
		this.generatedCards.add(card);
		return Card.getCard(card);
	}

	public void addPlayer(final Player player) {
		this.listOfPlayers.push(player);
	}

	public void fetchPlayers() {
		this.listOfPlayers.push(this.dealer);
		/**
		 * Busca no ZooKeeper os Players
		 */
	}

	public void getCardsToTable() {
		this.dealer.getTable().addCard(this.getNewCard());
		this.dealer.getTable().addCard(this.getNewCard());
	}
}
