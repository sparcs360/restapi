package com.sparcs.bet.api;

import java.util.List;

import com.sparcs.bet.dto.SkyBet;
import com.sparcs.bet.dto.SkyBetReceipt;
import com.sparcs.bet.dto.SkyBetSlip;

/**
 * A service for consuming the SkyBet API at
 * http://skybettechtestapi.herokuapp.com/
 * 
 * @author Lee Newfeld
 */
public interface SkyBetService {

	/**
	 * @return The list of {@link SkyBet bets} available from Sky Bet.
	 */
	List<SkyBet> getAvailable();

	/**
	 * Place a bet...
	 *  
	 * @param slip A {@link SkyBetSlip} describing the bet you wish to place
	 * @return A digital receipt (if the bet was accepted)
	 */
	SkyBetReceipt placeBet(SkyBetSlip slip);
}
