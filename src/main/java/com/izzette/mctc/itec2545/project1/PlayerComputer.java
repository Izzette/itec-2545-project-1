package com.izzette.mctc.itec2545.project1;

import com.izzette.mctc.itec2545.project1.Card;
import com.izzette.mctc.itec2545.project1.CardSuitE;
import com.izzette.mctc.itec2545.project1.PlayerA;
import static com.izzette.mctc.itec2545.project1.PlayerA.CardNotInHandException;
import static com.izzette.mctc.itec2545.project1.PlayerA.CheatedException;

class PlayerComputer extends PlayerA {
	private int computerNumber;

	public PlayerComputer (int computerNumber) {
		this.computerNumber = computerNumber;
	}

	@Override
	public Card lead () throws CardNotInHandException {
		for (Card card : hand.cards) {
			hand.cards.remove (card);
			return card;
		}

		throw new CardNotInHandException ();
	}

	@Override
	public Card play (CardSuitE suitLead)
			throws CardNotInHandException {
		Card lastCard = null;
		for (Card card : hand.cards) {
			lastCard = card;
			if (card.suit == suitLead)
				return hand.remove (card);
		}

		if (null == lastCard)
			throw new CardNotInHandException ();

		return hand.remove (lastCard);
	}

	@Override
	public void gloat () {
		System.out.printf ("%s: \"Beep-boop, I win!\"\n", this);
	}

	@Override
	public String toString () {
		return String.format ("Computer %d", computerNumber);
	}
}

// vim: set ts=4 sw=4 noet syn=java:
