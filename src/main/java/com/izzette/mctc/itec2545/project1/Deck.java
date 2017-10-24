package com.izzette.mctc.itec2545.project1;

import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import com.izzette.mctc.itec2545.project1.Card;
import com.izzette.mctc.itec2545.project1.CardSuitE;
import com.izzette.mctc.itec2545.project1.CardRankE;

class Deck {
	private LinkedList<Card> cardsInDeck = new LinkedList<> ();

	public Deck () {
		for (CardSuitE cardSuit : CardSuitE.values()) {
			for (CardRankE cardRank : CardRankE.values()) {
				Card card = new Card (cardSuit, cardRank);
				cardsInDeck.add (card);
			}
		}
	}

	public void shuffle () {
		Collections.shuffle (cardsInDeck);
	}

	public Card[] draw (int numberOfCards) {
		Card[] drawn = new Card[numberOfCards];
		for (int i = 0; numberOfCards > i; ++i)
			drawn[i] = cardsInDeck.pop ();

		return drawn;
	}

	public List<Card> removeCards (Card.CardAcceptor criteria) {
		List<Card> removed = new LinkedList<> ();

		Iterator<Card> deckIterator = cardsInDeck.iterator ();
		while (deckIterator.hasNext ()) {
			Card card = deckIterator.next ();
			if (criteria.accept (card)) {
				deckIterator.remove ();
				removed.add (card);
			}
		}

		return removed;
	}
}

// vim: set ts=4 sw=4 noet syn=java:
