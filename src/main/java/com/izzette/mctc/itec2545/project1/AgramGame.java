package com.izzette.mctc.itec2545.project1;

import java.util.NoSuchElementException;

import com.izzette.mctc.itec2545.project1.Card;
import com.izzette.mctc.itec2545.project1.CardSuitE;
import com.izzette.mctc.itec2545.project1.Deck;
import com.izzette.mctc.itec2545.project1.PlayerA;
import com.izzette.mctc.itec2545.project1.PlayerPool;
import com.izzette.mctc.itec2545.project1.Trick;
import com.izzette.mctc.itec2545.project1.TrickInPlay;
import com.izzette.mctc.itec2545.project1.TrickTakerDecider;
import static com.izzette.mctc.itec2545.project1.TrickInPlay.TrickCompletedException;

class AgramGame {
	private Deck deck;
	private PlayerPool playerPool;
	private int leadingPlayerId;

	private TrickCounter trickCounter = new TrickCounter ();

	public AgramGame (PlayerPool playerPool, int startingPlayerId) throws GameRuleException {
		this.deck = new Deck ();
		this.playerPool = playerPool;
		this.leadingPlayerId = startingPlayerId;

		removeCards ();
		this.deck.shuffle ();

		try {
			dealGame (startingPlayerId);
		} catch (GameRuleException e) {
			System.out.println (
					"Hmm, seems like there's more players " +
					"than cards that need to be dealt.");
			throw e;
		}
	}

	public PlayerA play ()
			throws PlayerA.CheatedException, DoublePlayException, GameRuleException {
		do playTrick (); while (6 > trickCounter.size ());

		System.out.println ("Six tricks played!");

		return playerPool.getPlayer (trickCounter.whoTookLast ());
	}

	private void playTrick ()
			throws PlayerA.CheatedException, DoublePlayException, GameRuleException {
		PlayerA leadingPlayer = playerPool.getPlayer (leadingPlayerId);

		Card cardLead;
		try {
			cardLead = leadingPlayer.lead ();
		} catch (PlayerA.CardNotInHandException e) {
			throw new DoublePlayException ();
		}

		System.out.printf ("%s lead with %s.\n", leadingPlayer, cardLead);

		TrickInPlay trickInPlay = new TrickInPlay (
				cardLead, leadingPlayerId, playerPool.size ());

		for (int playerId = (leadingPlayerId + 1) % playerPool.size ();
				leadingPlayerId != playerId;
				playerId = (playerId + 1) % playerPool.size ()) {
			PlayerA nextPlayer = playerPool.getPlayer (playerId);

			Card card;
			try {
				card = nextPlayer.play (trickInPlay.suitLead);
			} catch (PlayerA.CardNotInHandException e) {
				throw new DoublePlayException ();
			}

			try {
				trickInPlay.playCard (card);
			} catch (TrickCompletedException e) {
				throw new GameRuleException ();
			}

			System.out.printf ("%s followed with %s.\n", nextPlayer, card);
		}

		Trick trick;
		try {
			trick = trickInPlay.finishPlay ();
		} catch (TrickInPlay.TrickNotCompletedException e) {
			throw new GameRuleException ();
		}

		CardSuitE cardSuit = cardLead.suit;
		TrickTakerDecider trickTakerDecider = new TrickTakerDecider (cardSuit, trick);
		try {
			leadingPlayerId = trickTakerDecider.decide ();
		} catch (TrickTakerDecider.SuitLeadNotInTrickException e) {
			throw new GameRuleException ();
		}

		System.out.printf ("%s won the trick: %s.\n",
				playerPool.getPlayer (leadingPlayerId),
				trick);

		trickCounter.add (leadingPlayerId, trick);

		// Give a little spacing ...
		System.out.println ();
	}

	private void removeCards () {
		deck.removeCards(new Card.CardAcceptor () {
			@Override
			public boolean accept (Card card) {
				switch (card.rank) {
					case TWO:
					case JACK:
					case QUEEN:
					case KING:
						return true;
					case ACE:
						return (CardSuitE.SPADE == card.suit);
					default:
						return false;
				}
			}
		});
	}

	private void dealGame (int dealerPlayerId) throws GameRuleException {
		PlayerA dealer = playerPool.getPlayer (dealerPlayerId);
		try {
			dealer.deal (playerPool, deck);
		} catch (NoSuchElementException e) {
			throw new GameRuleException ();
		}
		System.out.printf ("%s dealt 6 cards to each player.\n", dealer);
	}

	static public class GameRuleException extends Exception {
		static private final long serialVersionUID = 1;
	}

	static public class DoublePlayException extends GameRuleException {
		static private final long serialVersionUID = 1;
	}
}

// vim: set ts=4 sw=4 noet syn=java:
