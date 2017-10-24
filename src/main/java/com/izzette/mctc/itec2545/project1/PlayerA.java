package com.izzette.mctc.itec2545.project1;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import com.izzette.mctc.itec2545.project1.Card;
import com.izzette.mctc.itec2545.project1.PlayerPool;
import com.izzette.mctc.itec2545.project1.TrickInPlay;

abstract class PlayerA {
	protected Hand hand = new Hand ();
	protected Collection<Trick> tricks = new LinkedList<> ();

	public abstract Card lead () throws CardNotInHandException;
	public abstract Card play (CardSuitE suitLead) throws CheatedException, CardNotInHandException;
	public abstract void gloat ();

	public void deal (PlayerPool playerPool, Deck deck) {
		for (int i = 0; 2 > i; ++i)
			for (int playerId = 0; playerPool.size () > playerId; ++playerId)
				playerPool.dealTo (playerId, deck.draw (3));
	}

	public void addCards (Card[] drawn) {
		hand.addCards (drawn);
	}

	public void takeTrick (Trick trick) {
		tricks.add (trick);
	}

	static protected class Hand {
		public Collection<Card> cards = new HashSet<> ();

		public void addCards (Card[] drawn) {
			cards.addAll (Arrays.asList (drawn));
		}

		public Card remove (Card selection) throws CardNotInHandException {
			if (!cards.remove (selection))
				throw new CardNotInHandException ();

			return selection;
		}
	}

	static public class CardNotInHandException extends Exception {
		static private final long serialVersionUID = 1;
	}

	static public class CheatedException extends Exception {
		static private final long serialVersionUID = 1;
	}
}

// vim: set ts=4 sw=4 noet syn=java:
