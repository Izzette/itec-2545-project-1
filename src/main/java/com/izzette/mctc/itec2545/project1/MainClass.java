package com.izzette.mctc.itec2545.project1;

import com.izzette.mctc.itec2545.project1.PlayerA;
import com.izzette.mctc.itec2545.project1.PlayerComputer;
import com.izzette.mctc.itec2545.project1.PlayerHuman;

/**
 * Project 1 main class.
 */
public class MainClass extends Object {
	/**
	 * Project 1 entry point.
	 *
	 * @param args command line arguments
	 */
	public static void main (String args[]) {
		int numberOfComputers;
		try {
			numberOfComputers = Integer.parseInt (args[0]);
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			System.out.println ("Usage: <number of computers>");
			System.exit (1);
			return;
		}

		if (0 >= numberOfComputers)
			System.out.println ("There must be at least one computer opponent");

		int playerId = 0;
		PlayerA[] players = new PlayerA[numberOfComputers + 1];

		players[playerId++] = new PlayerHuman ();

		for (int computerNumber = 0;
				numberOfComputers > computerNumber;
				++computerNumber, ++playerId)
			players[playerId] = new PlayerComputer (computerNumber);

		PlayerPool playerPool = new PlayerPool (players);

		System.out.printf ("You're playing Agram against %d computer%s, it's your deal.\n",
				numberOfComputers, (1 < numberOfComputers ? "s" : ""));
		System.out.println ("Let's play!");
		System.out.println ();

		AgramGame game;
		try {
			game = new AgramGame (playerPool, 0);
		} catch (AgramGame.GameRuleException e) {
			System.exit (1);
			return;
		}

		PlayerA winner;
		try {
			winner = game.play ();
		} catch (PlayerA.CheatedException e) {
			System.out.println ("You cheated!");
			System.out.println ("Cheaters always lose!");
			System.exit (1);
			return;
		} catch (AgramGame.GameRuleException e) {
			System.out.println ("Somebody doesn't know the rules ...");
			System.out.println (e.toString ());
			System.out.println (e.getMessage ());
			e.printStackTrace ();
			System.exit (1);
			return;
		}

		System.out.printf ("%s won!\n", winner);
		winner.gloat ();
	}
}

// vim: set ts=4 sw=4 noet syn=java:
