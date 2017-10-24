package com.izzette.mctc.itec2545.project1;

import java.util.Arrays;

import com.izzette.mctc.itec2545.project1.Card;

class Trick {
	public final Card[] cards;

	public Trick (Card[] cards) {
		this.cards = cards.clone ();
	}

	@Override
	public String toString() {
		return Arrays.toString (cards);
	}
}

// vim: set ts=4 sw=4 noet syn=java:
