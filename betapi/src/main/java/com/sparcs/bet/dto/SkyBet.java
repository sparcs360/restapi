package com.sparcs.bet.dto;

/**
 * Represents an object in the array returned by http://skybettechtestapi.herokuapp.com/available
 * 
 * @author Lee Newfeld
 */
public final class SkyBet extends AvailableBetBase {

	private static final long serialVersionUID = 1115565796101994073L;
	
	private Odds odds;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private SkyBet() {
	}

	/**
	 * 
	 * @param id The unique id of the Bet
	 * @param eventName The name of the Event this Bet is associated with
	 * @param name The name of the entity being Bet on
	 * @param numerator The numerator element of the fractional odds
	 * @param denominator The denominator element of the fractional odds
	 */
	SkyBet(int id, String eventName, String name, int numerator, int denominator) {

		super(id, eventName, name);

		this.odds = new Odds(numerator, denominator);
	}

	/**
	 * @return The {@link Odds} for this Bet.
	 */
	public Odds getOdds() {
		
		return odds;
	}

	@Override
	public String toString() {
		return "SkyBet [id=" + getBetId() +
			   ", eventName=" + getEventName() +
			   ", name=" + getName() +
			   ", odds.numerator=" + odds.getNumerator() +
			   ", odds.demoninator=" + odds.getDenominator() + "]";
	}
}
