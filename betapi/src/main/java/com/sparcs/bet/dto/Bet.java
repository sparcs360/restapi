package com.sparcs.bet.dto;

import java.math.BigDecimal;

/**
 * Represents a Bet that a Customer can place a wager on.
 * 
 * @author Lee Newfeld
 */
public final class Bet extends AvailableBetBase {

	private static final long serialVersionUID = -2952590949726157518L;
	
	private BigDecimal odds;
	
	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private Bet() {
		
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param id The unique id of the Bet
	 * @param eventName The name of the Event this Bet is associated with
	 * @param name The name of the entity being Bet on
	 * @param odds The odds offered.
	 */
	Bet(int id, String eventName, String name, BigDecimal odds) {
		
		super(id, eventName, name);
		
		this.odds = odds;
	}

	/**
	 * @param skyBet A {@link SkyBet} to base this {@link Bet} on.
	 */
	public Bet(SkyBet skyBet) {

		super(skyBet.getBetId(), skyBet.getEventName(), skyBet.getName());
		
		odds = skyBet.getOdds().getDecimalOdds();
	}

	/**
	 * @return The odds offered.
	 */
	public BigDecimal getOdds() {
		
		return odds;
	}
	
	@Override
	public String toString() {
		return "SkyBet [id=" + getBetId() +
			   ", eventName=" + getEventName() +
			   ", name=" + getName() +
			   ", odds=" + odds + "]";
	}
}
