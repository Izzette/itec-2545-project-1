package com.izzette.mctc.itec2545.project1;

import java.util.Comparator;

enum CardRankE {
	TWO("Two", 2),
	THREE("Three", 3),
	FOUR("Four", 4),
	FIVE("Five", 5),
	SIX("Six", 6),
	SEVEN("Seven", 7),
	EIGHT("Eight", 8),
	NINE("Nine", 9),
	TEN("Ten", 10),
	JACK("Jack", 11),
	QUEEN("Queen", 12),
	KING("King", 13),
	ACE("Ace", 14);

	public final String name;
	public final int order;

	CardRankE (String name, int order) {
		this.name = name;
		this.order = order;
	}
}

// vim: set ts=4 sw=4 noet syn=java:
