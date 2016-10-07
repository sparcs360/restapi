package com.sparcs.betapi;

import java.util.List;

/**
 * Controller for the Betting API
 * 
 * @author Lee Newfeld
 */
public interface BetController {

	/**
	 * Get the list of available {@link Bet}s.
	 * @return
	 */
	public List<Bet> getAvailable();
}
