package com.sparcs.bet.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Represents a betting slip
 * 
 * @author Lee Newfeld
 */
@JsonPropertyOrder(alphabetic=true)
public class BetSlip implements Serializable {

	private static final long serialVersionUID = -8986042172060371020L;
	
	private int betId;
	private BigDecimal odds;
	private int stake;

	/**
	 * Default Constructor
	 */
	BetSlip() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param betId The unique identifier of the {@link Bet bet} being taken.
	 * @param odds The requested odds.
	 * @param stake The amount to put at risk.
	 */
	public BetSlip(int betId, BigDecimal odds, int stake) {

		super();
		
		this.betId = betId;
		this.odds = odds;
		this.stake = stake;
	}

	@JsonProperty("bet_id")
	public int getBetId() {
		
		return betId;
	}
	
	public BigDecimal getOdds() {
		
		return odds;
	}
	
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
		return "BetSlip [betId=" + betId + ", odds=" + odds + ", stake=" + stake + "]";
	}
}
