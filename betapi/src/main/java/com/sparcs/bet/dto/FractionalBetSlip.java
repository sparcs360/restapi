package com.sparcs.bet.dto;

import com.sparcs.bet.dto.base.BettingSlipBase;

/**
 * Represents a betting slip with fractional odds.
 * 
 * @author Lee Newfeld
 */
public final class FractionalBetSlip extends BettingSlipBase {

	private static final long serialVersionUID = -7847002685472216046L;
	
	private Odds odds;
	
	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private FractionalBetSlip() {
		
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param betId The unique identifier of the {@link FractionalBet bet} being taken.
	 * @param odds The requested odds.
	 * @param stake The amount to put at risk.
	 */
	public FractionalBetSlip(int betId, Odds odds, int stake) {

		super(betId, stake);

		this.odds = odds;
	}

	/**
	 * Construct a betting slip with fractional odds based on a betting slip
	 * with decimal odds.
	 *  
	 * @param slip The {@link DecimalBetSlip} to base this betting slip on.
	 */
	public FractionalBetSlip(DecimalBetSlip slip) {
		
		this(slip.getBetId(), new Odds(slip.getOdds()), slip.getStake());
	}

	/**
	 * @return The {@link Odds} for this Bet.
	 */
	public Odds getOdds() {
		
		return odds;
	}


	@Override
	public String toString() {
		return "FractionalBetSlip [betId=" + getBetId() + ", odds=" + odds + ", stake=" + getStake() + "]";
	}
}
