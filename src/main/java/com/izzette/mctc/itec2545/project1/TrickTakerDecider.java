package com.izzette.mctc.itec2545.project1;

import com.izzette.mctc.itec2545.project1.Card;
import com.izzette.mctc.itec2545.project1.Trick;
import com.izzette.mctc.itec2545.project1.CardSuitE;
import com.izzette.mctc.itec2545.project1.CardRankE;

class TrickTakerDecider {
	private CardSuitE suitLead;
	private Trick trick;

	public TrickTakerDecider (CardSuitE suitLead, Trick trick) {
		this.suitLead = suitLead;
		this.trick = trick;
	}

	public int decide () throws SuitLeadNotInTrickException {
		int playerId = -1;
		for (int i = 0; trick.cards.length > i; ++i) {
			Card card = trick.cards[i];

			if (suitLead != card.suit)
				continue;

			if (0 > playerId || trick.cards[playerId].rank.order < card.rank.order)
				playerId = i;
		}

		if (0 > playerId)
			throw new SuitLeadNotInTrickException ();

		return playerId;
	}

	static public class SuitLeadNotInTrickException extends Exception {
		static private final long serialVersionUID = 1;
	}
}

// vim: set ts=4 sw=4 noet syn=java:
