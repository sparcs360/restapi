package com.sparcs.bet.api;

import java.util.List;

import com.sparcs.bet.dto.FractionalBet;
import com.sparcs.bet.dto.FractionalBetReceipt;
import com.sparcs.bet.dto.FractionalBetSlip;

/**
 * A service for consuming the SkyBet API at
 * http://skybettechtestapi.herokuapp.com/
 * 
 * @author Lee Newfeld
 */
public interface SkyBetService {

	/**
	 * @return The list of {@link FractionalBet bets} available from Sky Bet.
	 */
	List<FractionalBet> getAvailable();

	/**
	 * Place a bet...
	 *  
	 * @param slip A {@link FractionalBetSlip} describing the bet you wish to place
	 * @return A digital receipt (if the bet was accepted)
	 */
	FractionalBetReceipt placeBet(FractionalBetSlip slip);
}
