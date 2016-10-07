package com.sparcs.betapi.internal;

import java.util.List;
import java.util.Objects;

import org.apache.commons.io.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import com.sparcs.betapi.SkyBet;
import com.sparcs.betapi.SkyBetReceipt;
import com.sparcs.betapi.SkyBetService;
import com.sparcs.betapi.SkyBetSlip;

/**
 * Implementation of {@link SkyBetService}.
 * 
 * @author Lee Newfeld
 */
@Service
class SkyBetServiceImpl implements SkyBetService {

	private static final Logger log = LoggerFactory.getLogger(SkyBetServiceImpl.class);

	private static final String URL_BASE = "http://skybettechtestapi.herokuapp.com";
	private static final String URL_AVAILABLE = URL_BASE + "/available";
	private static final String URL_BETS = URL_BASE + "/bets";

    @Autowired
    private RestOperations restClient;

    /* (non-Javadoc)
	 * @see com.sparcs.betapi.SkyBetService#getAvailable()
	 */
    @Override
	public List<SkyBet> getAvailable() {

		// Get the master list of Bets from Sky... 
		ResponseEntity<List<SkyBet>> response =
		        restClient.exchange(URL_AVAILABLE,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<SkyBet>>() {});
    	log.trace(response.toString());

    	// Expecting HTTP 200 on success
		// TODO: What to do about other 2xx statuses?
    	if( response.getStatusCode() != HttpStatus.OK ) {

    	}

		return response.getBody();
    }

    /* (non-Javadoc)
	 * @see com.sparcs.betapi.SkyBetService#placeBet(com.sparcs.betapi.SkyBet, int)
	 */
    @Override
	public SkyBetReceipt placeBet(SkyBet bet, int stake) {
    	
    	// Validate arguments
    	Objects.requireNonNull(bet,  "bet");
    	// Don't allow zero stakes through because the Sky API will take a
    	// bet with zero stake (which we don't want).  Negative stakes are
    	// correctly handled by the Sky API.
    	if( stake == 0 ) {

    		throw new HttpClientErrorException(
    				HttpStatus.I_AM_A_TEAPOT,
    				HttpStatus.I_AM_A_TEAPOT.getReasonPhrase(),
    				"{ \"error\": \"Invalid Stake\" }".getBytes(),
    				Charsets.UTF_8);
    	}

    	// Create a betting slip
    	SkyBetSlip slip = new SkyBetSlip(bet, stake);
    	log.trace(slip.toString());

    	// POST the slip to SkyBet
    	ResponseEntity<SkyBetReceipt> response =
    			restClient.postForEntity(URL_BETS, slip, SkyBetReceipt.class);
    	log.trace(response.toString());
    	
    	// Expecting HTTP 201 on success
		// TODO: What to do about other 2xx statuses?
    	if( response.getStatusCode() != HttpStatus.CREATED ) {
    		
    	}
    	
		return response.getBody();
    }
}
