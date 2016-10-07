package com.sparcs.betapi;

import java.util.List;

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
	 * @param bet The {@link SkyBet} being taken
	 * @param stake The amount to wager
	 * @return A digital receipt 
	 */
	SkyBetReceipt placeBet(SkyBet bet, int stake);

}