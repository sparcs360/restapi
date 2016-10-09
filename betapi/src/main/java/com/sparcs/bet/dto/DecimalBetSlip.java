package com.sparcs.bet.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparcs.bet.dto.base.BettingSlipBase;

/**
 * Represents a betting slip with decimal odds
 * 
 * @author Lee Newfeld
 */
public final class DecimalBetSlip extends BettingSlipBase {

	private static final long serialVersionUID = -8986042172060371020L;
	
	private BigDecimal odds;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private DecimalBetSlip() {
		
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param betId The unique identifier of the {@link DecimalBet bet} being taken.
	 * @param odds The requested odds.
	 * @param stake The amount to put at risk.
	 */
	public DecimalBetSlip(int betId, BigDecimal odds, int stake) {

		super(betId, stake);
		
		this.odds = odds;
	}

	/**
	 * @return The requested odds
	 */
	public BigDecimal getOdds() {
		
		return odds;
	}

	/**
	 * @return true if all mandatory properties have a valid value.
	 */
	@JsonIgnore
	public boolean isComplete() {

		return odds != null;
	}

	@Override
	public String toString() {
		return "DecimalBetSlip [betId=" + getBetId() + ", odds=" + odds + ", stake=" + getStake() + "]";
	}
}
