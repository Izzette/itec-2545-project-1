package com.izzette.mctc.itec2545.project1;

import com.izzette.mctc.itec2545.project1.CardSuitE;
import com.izzette.mctc.itec2545.project1.CardRankE;

class Card {
	public final CardSuitE suit;
	public final CardRankE rank;

	Card (CardSuitE suit, CardRankE rank) {
		this.suit = suit;
		this.rank = rank;
	}

	@Override
	public String toString () {
		return String.format ("%s of %ss", rank.name, suit.name);
	}

	static public abstract class CardAcceptor {
		public abstract boolean accept (Card card);

		public boolean reject (Card card) {
			return !accept (card);
		}
	}
}

// vim: set ts=4 sw=4 noet syn=java:
