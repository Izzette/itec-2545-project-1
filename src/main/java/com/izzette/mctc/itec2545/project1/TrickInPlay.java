package com.izzette.mctc.itec2545.project1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.izzette.mctc.itec2545.project1.CardSuitE;
import com.izzette.mctc.itec2545.project1.Trick;

class TrickInPlay {
	public final CardSuitE suitLead;

	private int playerLast;
	private Card[] cards;

	public TrickInPlay (Card cardLead, int playerIdLead, int playersInPool) {
		this.suitLead = cardLead.suit;
		this.playerLast = playerIdLead;

		this.cards = new Card[playersInPool];
		for (int i = 0; this.cards.length > i; ++i)
			this.cards[i] = null;

		this.cards[playerIdLead] = cardLead;
	}

	public List<Card> getCards () {
		return Collections.unmodifiableList (Arrays.asList (cards));
	}

	public void playCard (Card card) throws TrickCompletedException {
		if (isComplete ())
			throw new TrickCompletedException ();

		cards[progressPlayer ()] = card;
	}

	public boolean isComplete () {
		return (null != cards[getPlayerNext ()]);
	}

	public Trick finishPlay () throws TrickNotCompletedException {
		if (!isComplete ())
			throw new TrickNotCompletedException ();

		return new Trick (cards.clone ());
	}

	private int progressPlayer () {
		return (playerLast = getPlayerNext ());
	}

	private int getPlayerNext () {
		return (playerLast + 1) % cards.length;
	}

	static public class TrickCompletedException extends Exception {
		static private final long serialVersionUID = 1;
	}

	static public class TrickNotCompletedException extends Exception {
		static private final long serialVersionUID = 1;
	}
}

// vim: set ts=4 sw=4 noet syn=java:
