package com.sparcs.bet.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.sparcs.bet.api.BetController;

/**
 * A digital receipt obtained by successfully
 * {@link BetController#placeBet(BetSlip) placing a bet}.
 * 
 * @author Lee Newfeld
 */
public class BetReceipt extends ReceiptBase {

	private static final long serialVersionUID = 9212709336909641200L;
	
	private Bet bet;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private BetReceipt() {
	}
	
	/**
	 * Constructor for a specific receipt
	 * 
	 * @param bet
	 * @param stake
	 * @param transactionId
	 */
	public BetReceipt(Bet bet, int stake, int transactionId) {

		super(stake, transactionId);
		
		this.bet = bet;
	}

	public BetReceipt(SkyBetReceipt receipt) {

		super(receipt.getStake(), receipt.getTransactionId());

		bet = new Bet(receipt.getBet());
	}

	/**
	 * @return The {@link Bet bet} that was taken.
	 */
	@JsonUnwrapped
	public Bet getBet() {
		
		return bet;
	}
	
	@Override
	public String toString() {
		
		return "BetReceipt [bet=" + bet + ", stake=" + getStake() + ", transactionId=" + getTransactionId() + "]";
	}
}
