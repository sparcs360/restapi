package com.sparcs.betapi.internal;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;

import com.sparcs.betapi.Bet;
import com.sparcs.betapi.BetController;
import com.sparcs.betapi.BetReceipt;
import com.sparcs.betapi.BetSlip;
import com.sparcs.betapi.SkyBetReceipt;
import com.sparcs.betapi.SkyBetService;
import com.sparcs.betapi.SkyBetSlip;

/**
 * Implementation of {@link BetController}.
 * 
 * @author Lee Newfeld
 */
@RestController
class BetControllerImpl implements BetController {

	private static final Logger log = LoggerFactory.getLogger(BetControllerImpl.class);

	@Autowired
	private SkyBetService skyBetService;
	
	/**
	 * Default constructor
	 */
	BetControllerImpl() {
	}

	/**
	 * <p>Handler for {@link HttpStatusCodeException}s thrown by this Controller
	 * or the {@link SkyBetService} it uses.</p>
	 * <p>Simply forwards the caught Exception to the caller.</p>
	 *  
	 * @param e The caught exception
	 * @return
	 */
	@ExceptionHandler({ HttpStatusCodeException.class })
	public ResponseEntity<String> handleHttpStatusCodeException(HttpStatusCodeException e) {

	    return new ResponseEntity<String>(e.getResponseBodyAsString(), e.getStatusCode());
	}

	/* (non-Javadoc)
	 * @see com.sparcs.betapi.BetController#getAvailable()
	 */
	@Override
	@RequestMapping(
		path="/available",
		method={RequestMethod.GET},
		produces={MediaType.APPLICATION_JSON_UTF8_VALUE}
	)
	public @ResponseBody List<Bet> getAvailable() {

		// Convert available bets from Sky to our betting format
		return skyBetService.getAvailable()
			.stream()
			.map(sb -> new Bet(sb))	// Use a mapper instead?
			.collect(Collectors.toList());
	}

	/* (non-Javadoc)
	 * @see com.sparcs.betapi.BetController#placeBet()
	 */
	@Override
	@RequestMapping(
		path="/bets",
		method={RequestMethod.POST},
		consumes={MediaType.APPLICATION_JSON_UTF8_VALUE},
		produces={MediaType.APPLICATION_JSON_UTF8_VALUE}
	)
	public @ResponseBody BetReceipt placeBet(@RequestBody BetSlip slip) {

		log.trace("slip={}", slip);
		
		// Place the bet (with Sky)
		SkyBetSlip skySlip = new SkyBetSlip(slip);

		SkyBetReceipt receipt = skyBetService.placeBet(skySlip);
		return new BetReceipt(receipt);
	}
}
