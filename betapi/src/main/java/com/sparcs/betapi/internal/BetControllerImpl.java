package com.sparcs.betapi.internal;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

	/**
	 * Exception thrown if {@link BetSlip} is only partially "filled in" (i.e.,
	 * the Json provided by the caller didn't deserialise to a complete object - e.g.,
	 * The <code>bet_id</code> and <code>stake</code> are present, but the
	 * <code>odds</code> are missing). 
	 */
	private static final HttpMessageNotReadableException INCOMPLETE_SLIP_EXCEPTION =
			new HttpMessageNotReadableException("Slip is incomplete");

	@Autowired
	private SkyBetService skyBetService;
	
	/**
	 * Only let Spring instantiate these
	 */
	private BetControllerImpl() {
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
	
	/**
	 * <p>Handler for {@link HttpMessageNotReadableException}s thrown by Spring
	 * if the {@link RequestBody} can't be deserialised.</p>
	 *  
	 * @param e The caught exception
	 * @return A 400 with a descriptive body
	 */
	@ExceptionHandler({HttpMessageNotReadableException.class})
	public ResponseEntity<String> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException e) {

		String body = String.format(
			"%s\n%s",
			ERROR_BAD_SLIP, e.getMostSpecificCause().getMessage()
		);

		return ResponseEntity
				.badRequest()
				.contentType(MediaType.TEXT_PLAIN)
				.body(body)
				;
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
		
		// Treat empty or partially completed slips as a
		// "Message Not Readable" problem
		if( !slip.isComplete() ) {
			
			throw INCOMPLETE_SLIP_EXCEPTION;
		}
		
		// Create a Sky betting slip based on ours
		SkyBetSlip skySlip = new SkyBetSlip(slip);

		// Place the bet with Sky
		SkyBetReceipt receipt = skyBetService.placeBet(skySlip);
		
		// Return our receipt based on Sky's
		return new BetReceipt(receipt);
	}
}
