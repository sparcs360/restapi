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
public class FractionalBetReceipt extends ReceiptBase {

	private static final long serialVersionUID = 5888789369984676552L;
	
	private FractionalBet bet;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private FractionalBetReceipt() {
	}
	
	/**
	 * Constructor for a specific receipt
	 * 
	 * @param bet
	 * @param stake
	 * @param transactionId
	 */
	FractionalBetReceipt(FractionalBet bet, int stake, int transactionId) {

		super(stake, transactionId);
		
		this.bet = bet;
	}

	/**
	 * @return The {@link FractionalBet bet} that was taken.
	 */
	@JsonUnwrapped
	public FractionalBet getBet() {
		
		return bet;
	}
	
	@Override
	public String toString() {
		
		return "FractionalBetReceipt [bet=" + bet + ", stake=" + getStake() + ", transactionId=" + getTransactionId() + "]";
	}
}
