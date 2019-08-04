package poker_zookeeper.game.deck.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Card {
	private Naipe naipe;
	private char valor;

	private Card(final Naipe naipe, final char valor) {
		this.setNaipe(naipe);
		this.setValor(valor);
	}

	public static Card getCard(final int card) {
		Naipe naipe = Naipe.getNaipe((int) Math.ceil(card / 13.0));
		char valor = Card.parseValor((int) Math.ceil(card / 4.0));
		return new Card(naipe, valor);
	}

	private static char parseValor(final int valor) {
		switch (valor) {
			case 1 :
				return 'A';
			case 11 :
				return 'J';
			case 12 :
				return 'Q';
			case 13 :
				return 'K';
			default :
				return Character.forDigit(valor, 10);
		}
	}

	public int getNumericValue() {
		switch (this.valor) {
			case 'A' :
				return 14;
			case 'J' :
				return 11;
			case 'Q' :
				return 12;
			case 'K' :
				return 13;
			default :
				return Character.getNumericValue(this.valor);
		}
	}

	@Override
	public String toString() {
		return new StringBuilder().append(this.getValor()).append(this.getNaipe().toString()).toString();
	}
}
