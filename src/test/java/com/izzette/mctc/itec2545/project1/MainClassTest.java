package com.izzette.mctc.itec2545.project1;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test MainClass.
 */
public class MainClassTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public MainClassTest (String testName) {
		super (testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite (MainClassTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testMainClass() {
		assertTrue (true);
	}
}

// vim: set ts=4 sw=4 noet syn=java:
