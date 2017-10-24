package com.izzette.mctc.itec2545.project1;

import com.izzette.mctc.itec2545.project1.PlayerA;

class PlayerPool {
	private PlayerA[] players;

	PlayerPool (PlayerA[] players) {
		this.players = players;
	}

	public int size () {
		return players.length;
	}

	public PlayerA getPlayer (int playerId) {
		return players[playerId];
	}

	public void dealTo (int playerId, Card[] drawn) {
		players[playerId].addCards (drawn);
	}
}

// vim: set ts=4 sw=4 noet syn=java:
