package com.sparcs.betapi;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Betting API
 * 
 * @author Lee Newfeld
 */
@RestController
public class BetController {

	@Autowired
	private SkyBetService skyBetService;
	
	/**
	 * Get the list of available {@link Bet}s.
	 * @return
	 */
	@RequestMapping("/available")
	@ResponseBody()
	public List<Bet> getAvailable() {

		// Convert available bets from Sky to our betting format
		return skyBetService.getAvailable()
				.stream()
				.map(sb -> new Bet(sb))	// Use a mapper instead?
				.collect(Collectors.toList());
	}
}
