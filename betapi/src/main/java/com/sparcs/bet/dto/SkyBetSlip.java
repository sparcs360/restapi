package com.sparcs.bet.dto;

/**
 * Represents the taking of a {@link SkyBet bet}.
 * 
 * @author Lee Newfeld
 */
public class SkyBetSlip extends BetBase {

	private static final long serialVersionUID = -7847002685472216046L;
	
	private Odds odds;
	private int stake;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private SkyBetSlip() {
		
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param betId The unique identifier of the {@link SkyBet bet} being taken.
	 * @param odds The requested odds.
	 * @param stake The amount to put at risk.
	 */
	public SkyBetSlip(int betId, Odds odds, int stake) {

		super(betId);

		this.odds = odds;
		this.stake = stake;
	}

	/**
	 * Constructor
	 * @param slip
	 */
	public SkyBetSlip(BetSlip slip) {
		
		this(slip.getBetId(), new Odds(slip.getOdds()), slip.getStake());
	}

	/**
	 * @return The {@link Odds} for this Bet.
	 */
	public Odds getOdds() {
		
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
		return "SkyBetSlip [betId=" + getBetId() + ", odds=" + odds + ", stake=" + stake + "]";
	}
}
