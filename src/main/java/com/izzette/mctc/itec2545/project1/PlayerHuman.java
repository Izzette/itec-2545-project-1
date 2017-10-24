package com.izzette.mctc.itec2545.project1;

import input.InputUtils;

import java.util.Arrays;
import java.util.LinkedList;

class PlayerHuman extends PlayerA {
	public PlayerHuman () {
	}

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
		System.out.printf ("%s: \"%s\"\n", InputUtils.stringInput ("Gloat below:"));
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
			selection = InputUtils.intInput ("Pick a card, any card:");
			if (selection < cards.length && selection >= 0)
				break;

			System.out.println ("Oops, you missed.  Are you feeling alright?");
		}

		Card selectedCard = cards[selection];
		return hand.remove (selectedCard);
	}
}

// vim: set ts=4 sw=4 noet syn=java:
