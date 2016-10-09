package com.sparcs.bet.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.sparcs.bet.api.SkyBetService;
import com.sparcs.bet.dto.base.ReceiptBase;

/**
 * A digital receipt obtained by successfully
 * {@link SkyBetService#placeBet(SkyBetSlip) placing a bet} with Sky Bet.
 *  
 * @author Lee Newfeld
 */
public class SkyBetReceipt extends ReceiptBase {

	private static final long serialVersionUID = 5888789369984676552L;
	
	private SkyBet bet;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private SkyBetReceipt() {
	}
	
	/**
	 * Constructor for a specific receipt
	 * 
	 * @param bet
	 * @param stake
	 * @param transactionId
	 */
	SkyBetReceipt(SkyBet bet, int stake, int transactionId) {

		super(stake, transactionId);
		
		this.bet = bet;
	}

	/**
	 * @return The {@link SkyBet bet} that was taken.
	 */
	@JsonUnwrapped
	public SkyBet getBet() {
		
		return bet;
	}
	
	@Override
	public String toString() {
		
		return "SkyBetReceipt [bet=" + bet + ", stake=" + getStake() + ", transactionId=" + getTransactionId() + "]";
	}
}
