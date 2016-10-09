package com.sparcs.bet.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents a betting slip
 * 
 * @author Lee Newfeld
 */
public final class BetSlip extends BetBase {

	private static final long serialVersionUID = -8986042172060371020L;
	
	private BigDecimal odds;
	private int stake;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private BetSlip() {
		
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param betId The unique identifier of the {@link Bet bet} being taken.
	 * @param odds The requested odds.
	 * @param stake The amount to put at risk.
	 */
	public BetSlip(int betId, BigDecimal odds, int stake) {

		super(betId);
		
		this.odds = odds;
		this.stake = stake;
	}

	/**
	 * @return The requested odds
	 */
	public BigDecimal getOdds() {
		
		return odds;
	}
	
	/**
	 * @return The amount at risk
	 */
	public int getStake() {
		
		return stake;
	}

	/**
	 * @return true if a value for all mandatory properties have been provided.
	 */
	@JsonIgnore
	public boolean isComplete() {

		return odds != null;
	}

	@Override
	public String toString() {
		return "BetSlip [betId=" + getBetId() + ", odds=" + odds + ", stake=" + stake + "]";
	}
}
