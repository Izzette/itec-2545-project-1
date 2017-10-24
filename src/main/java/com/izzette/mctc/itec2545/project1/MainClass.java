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

		AgramGame game = new AgramGame (playerPool, 0);

		PlayerA winner;
		try {
			winner = game.play ();
		} catch (PlayerA.CheatedException | AgramGame.DoublePlayException e) {
			System.out.println ("Somebody cheated!");
			e.printStackTrace ();
			return;
		}

		System.out.printf ("%s won!\n", winner);
		winner.gloat ();
	}
}

// vim: set ts=4 sw=4 noet syn=java:
