package com.sparcs.bet.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The base class for DTOs representing a digital receipt
 * 
 * @author Lee Newfeld
 */
@SuppressWarnings("serial")
abstract class ReceiptBase implements Serializable {

	private int stake;
	private int transactionId;

	/**
	 * Constructor for Faster Jackson
	 */
	protected ReceiptBase() {
	}

	/**
	 * 
	 * @param stake The amount at risk.
	 * @param transactionId The unique transaction Id for this wager.
	 */
	protected ReceiptBase(int stake, int transactionId) {
		
		this.stake = stake;
		this.transactionId = transactionId;
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
}
