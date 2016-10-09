package com.sparcs.bet.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.sparcs.bet.api.BetController;
import com.sparcs.bet.dto.base.ReceiptBase;

/**
 * A digital receipt obtained by successfully
 * {@link BetController#placeBet(BetSlip) placing a bet}.
 * 
 * @author Lee Newfeld
 */
public class DecimalBetReceipt extends ReceiptBase {

	private static final long serialVersionUID = 9212709336909641200L;
	
	private DecimalBet bet;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private DecimalBetReceipt() {
	}
	
	/**
	 * Constructor for a specific receipt
	 * 
	 * @param bet The {@link DecimalBet bet} that was taken.
	 * @param stake The amount at risk.
	 * @param transactionId The unique transaction Id for this wager.
	 */
	public DecimalBetReceipt(DecimalBet bet, int stake, int transactionId) {

		super(stake, transactionId);
		
		this.bet = bet;
	}

	/**
	 * Construct a receipt with decimal odds based on a receipt with
	 * fractional odds.
	 * @param receipt
	 */
	public DecimalBetReceipt(FractionalBetReceipt receipt) {

		super(receipt.getStake(), receipt.getTransactionId());

		bet = new DecimalBet(receipt.getBet());
	}

	/**
	 * @return The {@link DecimalBet bet} that was taken.
	 */
	@JsonUnwrapped
	public DecimalBet getBet() {
		
		return bet;
	}
	
	@Override
	public String toString() {
		
		return "DecimalBetReceipt [bet=" + bet + ", stake=" + getStake() + ", transactionId=" + getTransactionId() + "]";
	}
}
