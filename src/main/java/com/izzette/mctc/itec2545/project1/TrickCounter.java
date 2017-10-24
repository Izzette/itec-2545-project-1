package com.izzette.mctc.itec2545.project1;

import java.util.List;
import java.util.ArrayList;

import com.izzette.mctc.itec2545.project1.Trick;

class TrickCounter {
	private List<TrickPlayerPair> trickPlayerPairs = new ArrayList<> ();

	public void add (int playerId, Trick trick) {
		trickPlayerPairs.add (new TrickPlayerPair (playerId, trick));
	}

	public int size () {
		return trickPlayerPairs.size ();
	}

	public int whoTook (int n) {
		return trickPlayerPairs.get (n).playerId;
	}

	public int whoTookLast () {
		return whoTook (size () - 1);
	}

	static public class TrickPlayerPair {
		public final int playerId;
		public final Trick trick;

		public TrickPlayerPair (int playerId, Trick trick) {
			this.playerId = playerId;
			this.trick = trick;
		}
	}
}

// vim: set ts=4 sw=4 noet syn=java:
