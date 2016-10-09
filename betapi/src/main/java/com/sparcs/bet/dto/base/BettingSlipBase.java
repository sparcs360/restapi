package com.sparcs.bet.dto.base;

/**
 * The base class for DTOs representing a betting slip
 * 
 * @author Lee Newfeld
 */
@SuppressWarnings("serial")
public abstract class BettingSlipBase extends BetBase {

	private int stake;

	/**
	 * Constructor for Faster Jackson
	 */
	protected BettingSlipBase() {
		
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param betId The unique identifier of the {@link FractionalBet bet} being taken.
	 * @param stake The amount to put at risk.
	 */
	protected BettingSlipBase(int betId, int stake) {

		super(betId);

		this.stake = stake;
	}

	/**
	 * @return The amount at risk.
	 */
	public int getStake() {
		
		return stake;
	}
}
