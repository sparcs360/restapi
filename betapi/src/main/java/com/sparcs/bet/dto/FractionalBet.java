package com.sparcs.bet.dto;

import com.sparcs.bet.dto.base.AvailableBetBase;

/**
 * Represents an available bet expressed using fractional odds.
 * 
 * @author Lee Newfeld
 */
public final class FractionalBet extends AvailableBetBase {

	private static final long serialVersionUID = 1115565796101994073L;
	
	private Odds odds;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private FractionalBet() {
	}

	/**
	 * 
	 * @param id The unique id of the Bet
	 * @param eventName The name of the Event this Bet is associated with
	 * @param name The name of the entity being Bet on
	 * @param numerator The numerator element of the fractional odds
	 * @param denominator The denominator element of the fractional odds
	 */
	FractionalBet(int id, String eventName, String name, int numerator, int denominator) {

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
		return "FractionalBet [id=" + getBetId() +
			   ", eventName=" + getEventName() +
			   ", name=" + getName() +
			   ", odds.numerator=" + odds.getNumerator() +
			   ", odds.demoninator=" + odds.getDenominator() + "]";
	}
}
