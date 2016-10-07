package com.sparcs.betapi.internal;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sparcs.betapi.Bet;
import com.sparcs.betapi.BetController;
import com.sparcs.betapi.SkyBetService;

/**
 * Implementation of {@link BetController}.
 * 
 * @author Lee Newfeld
 */
@RestController
class BetControllerImpl implements BetController {

	@Autowired
	private SkyBetService skyBetService;
	
	/**
	 * Default constructor
	 */
	BetControllerImpl() {
	}
	
	/* (non-Javadoc)
	 * @see com.sparcs.betapi.BetController#getAvailable()
	 */
	@Override
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
