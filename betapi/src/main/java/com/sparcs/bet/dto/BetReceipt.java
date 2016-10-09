package com.sparcs.bet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class BetReceipt {

	private Bet bet;
	private int stake;
	private int transactionId;

	/**
	 * Default Constructor
	 */
	BetReceipt() {
	}
	
	/**
	 * Constructor for a specific receipt
	 * 
	 * @param bet
	 * @param stake
	 * @param transactionId
	 */
	public BetReceipt(Bet bet, int stake, int transactionId) {

		this.bet = bet;
		this.stake = stake;
		this.transactionId = transactionId;
	}

	public BetReceipt(SkyBetReceipt receipt) {

		SkyBet skyBet = receipt.getBet();
		this.bet = new Bet(skyBet);
		this.stake = receipt.getStake();
		this.transactionId = receipt.getTransactionId();
	}

	/**
	 * @return The {@link Bet bet} that was taken.
	 */
	@JsonUnwrapped
	public Bet getBet() {
		
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
		
		return "BetReceipt [bet=" + bet + ", stake=" + stake + ", transactionId=" + transactionId + "]";
	}
}
