package com.sparcs.bet.api;

import java.util.List;

import com.sparcs.bet.dto.DecimalBet;
import com.sparcs.bet.dto.DecimalBetReceipt;
import com.sparcs.bet.dto.DecimalBetSlip;

/**
 * Controller for the Betting API
 * 
 * @author Lee Newfeld
 */
public interface BetController {

	/**
	 * Error message returned if the {@link DecimalBetSlip} argument of
	 * {@link #placeBet(DecimalBetSlip)} can't be deserialised from the JSON
	 * provided by the caller.
	 */
	public static final String ERROR_BAD_SLIP =
			"Your betting slip isn't readable.  " +
			"Example: { \"bet_id\": 1, \"odds\": 11.0, \"stake\": 100}";

	/**
	 * Get information on API usage.
	 * 
	 * @return The README!
	 */
	public String getReadMe();
	
	/**
	 * Get the list of available {@link DecimalBet}s.
	 * @return
	 */
	public List<DecimalBet> getAvailable();
	
	/**
	 * Place a bet...
	 *  
	 * @param slip Your {@link DecimalBetSlip betting slip}
	 * @return A digital receipt 
	 */
	DecimalBetReceipt placeBet(DecimalBetSlip slip);
}
