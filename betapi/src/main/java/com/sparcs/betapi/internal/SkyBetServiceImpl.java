package com.sparcs.betapi.internal;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
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
		ResponseEntity<List<SkyBet>> availableSkyBetsResponse =
		        restClient.exchange(URL_AVAILABLE,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<SkyBet>>() {});
		return availableSkyBetsResponse.getBody();
    }

    /* (non-Javadoc)
	 * @see com.sparcs.betapi.SkyBetService#placeBet(com.sparcs.betapi.SkyBet, int)
	 */
    @Override
	public SkyBetReceipt placeBet(SkyBet bet, int stake) {
    	
    	// Validate arguments
    	Objects.requireNonNull(bet,  "bet");
    	// Allowing zero/negative stakes through because Sky API becomes a teapot...
//    	if( stake <= 0 ) {
//
//    		throw new IllegalArgumentException("stake must be positive");
//    	}

    	// Create a betting slip
    	SkyBetSlip slip = new SkyBetSlip(bet, stake);
    	log.trace(slip.toString());

    	try {
    		
        	// POST the slip to SkyBet
	    	ResponseEntity<SkyBetReceipt> receiptResponse =
	    			restClient.postForEntity(URL_BETS, slip, SkyBetReceipt.class);
	    	log.trace(receiptResponse.toString());
			return receiptResponse.getBody();

    	} catch(HttpClientErrorException e) {
    		
    		// If betId <= 0 then the SkyBet API throws an HTTP 500 instead of a 418
        	log.error(String.format("Failed to POST %s to %s, response=%s",
        							slip,
        							URL_BETS, 
        							e.getResponseBodyAsString()), e);
        	return null;
    	}
    }
}
