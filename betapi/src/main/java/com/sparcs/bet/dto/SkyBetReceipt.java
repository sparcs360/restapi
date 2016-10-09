package com.sparcs.bet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * A digital receipt obtained by placing a {@link SkyBet bet} with Sky Bet
 *  
 * @author Lee Newfeld
 */
public class SkyBetReceipt {

	private SkyBet bet;
	private int stake;
	private int transactionId;

	/**
	 * Default Constructor
	 */
	SkyBetReceipt() {
	}
	
	/**
	 * Constructor for a specific receipt
	 * 
	 * @param bet
	 * @param stake
	 * @param transactionId
	 */
	SkyBetReceipt(SkyBet bet, int stake, int transactionId) {

		this.bet = bet;
		this.stake = stake;
		this.transactionId = transactionId;
	}

	/**
	 * @return The {@link SkyBet bet} that was taken.
	 */
	@JsonUnwrapped
	public SkyBet getBet() {
		
		return bet;
	}
	
	/**
	 * @return The amount at risk.
	 */
	public int getStake() {
		
		return stake;
	}
	
	/**
	 * @return The unique transaction Id for this wager.
	 */
	@JsonProperty("transaction_id")
	public int getTransactionId() {
		
		return transactionId;
	}

	@Override
	public String toString() {
		
		return "SkyBetReceipt [bet=" + bet + ", stake=" + stake + ", transactionId=" + transactionId + "]";
	}
}
