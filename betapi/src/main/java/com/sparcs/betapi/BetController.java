package com.sparcs.betapi;

import java.util.List;

/**
 * Controller for the Betting API
 * 
 * @author Lee Newfeld
 */
public interface BetController {

	/**
	 * Error message returned if the {@link BetSlip} argument of
	 * {@link #placeBet(BetSlip)} can't be deserialised from the JSON
	 * provided by the caller.
	 */
	public static final String ERROR_BAD_SLIP =
			"Your betting slip isn't readable.  " +
			"Example: { \"bet_id\": 1, \"odds\": 11.0, \"stake\": 100}";

	/**
	 * Get the list of available {@link Bet}s.
	 * @return
	 */
	public List<Bet> getAvailable();
	
	/**
	 * Place a bet...
	 *  
	 * @param slip Your {@link BetSlip betting slip}
	 * @return A digital receipt 
	 */
	BetReceipt placeBet(BetSlip slip);
}
