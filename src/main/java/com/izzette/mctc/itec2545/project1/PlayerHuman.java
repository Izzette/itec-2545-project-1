package com.izzette.mctc.itec2545.project1;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

class PlayerHuman extends PlayerA {
	private Scanner inputScanner = new Scanner (System.in);

	@Override
	public Card lead() throws PlayerA.CardNotInHandException {
		System.out.println ("Select a card to lead with.");
		return selectCard ();
	}

	@Override
	public Card play (CardSuitE suitLead)
			throws PlayerA.CheatedException, PlayerA.CardNotInHandException {
		System.out.println ("Select a card to play (don't cheat now).");
		Card card = selectCard ();

		if (card.suit != suitLead)
			for (Card otherCard : hand.cards)
				if (otherCard.suit == suitLead)
					throw new CheatedException ();

		return card ;
	}

	@Override
	public void gloat () {
		System.out.print ("Gloat here: ");
		System.out.printf ("%s: \"%s\"\n", this, inputScanner.nextLine ());
	}

	@Override
	public String toString () {
		return "You";
	}

	private Card selectCard () throws CardNotInHandException {
		Object[] cardObjects = hand.cards.toArray ();
		Card[] cards = new Card[cardObjects.length];
		System.arraycopy (cardObjects, 0, cards, 0, cards.length);

		if (0 == cards.length)
			throw new CardNotInHandException ();

		System.out.println ("Cards in hand:");
		for (int i = 0; cards.length > i; ++i)
			System.out.printf ("%d) %s\n", i, cards[i]);

		int selection = -1;
		for (;;) {
			System.out.printf ("Pick a card, any card (%s%d): ",
					(1 < cards.length ? "0-" : ""), cards.length - 1);
			String selectionString = inputScanner.nextLine ();
			try {
				selection = Integer.parseInt (selectionString);
			} catch (NumberFormatException e) {
				System.out.println ("Hmm, let's try that again ...");
				continue;
			}

			if (selection >= cards.length || selection < 0) {
				System.out.println ("Oops, you missed.  Are you feeling alright?");
				continue;
			}

			break;
		}

		Card selectedCard = cards[selection];
		return hand.remove (selectedCard);
	}
}

// vim: set ts=4 sw=4 noet syn=java:
