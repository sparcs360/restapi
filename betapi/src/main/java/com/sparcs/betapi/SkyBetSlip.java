package com.sparcs.betapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sparcs.betapi.SkyBet.Odds;

/**
 * Represents the taking of a {@link SkyBet bet}.
 * 
 * @author Lee Newfeld
 */
@JsonPropertyOrder(alphabetic=true)
public class SkyBetSlip {

	private int betId;
	private SkyBet.Odds odds;
	private int stake;

	/**
	 * Default Constructor
	 */
	SkyBetSlip() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param betId The unique identifier of the {@link SkyBet bet} being taken.
	 * @param odds The requested odds.
	 * @param stake The amount to put at risk.
	 */
	SkyBetSlip(int betId, SkyBet.Odds odds, int stake) {

		this.betId = betId;
		this.odds = odds;
		this.stake = stake;
	}

	/**
	 * Constructor
	 * 
	 * @param bet A {@link SkyBet} to place the bet on.
	 * @param stake The amount to put at risk.
	 */
	public SkyBetSlip(SkyBet bet, int stake) {

		this(bet.getId(), bet.getOdds(), stake);
	}

	/**
	 * @return The unique identifier of the {@link SkyBet bet} being taken.
	 */
	@JsonProperty("bet_id") 
	public int getBetId() {
		
		return betId;
	}

	/**
	 * @return The {@link Odds} for this Bet.
	 */
	public SkyBet.Odds getOdds() {
		
		return odds;
	}
	
	/**
	 * @return The amount at risk.
	 */
	public int getStake() {
		
		return stake;
	}

	@Override
	public String toString() {
		return "SkyBetSlip [betId=" + betId + ", odds=" + odds + ", stake=" + stake + "]";
	}
}
