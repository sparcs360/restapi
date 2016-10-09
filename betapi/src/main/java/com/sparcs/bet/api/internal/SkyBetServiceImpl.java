package com.sparcs.bet.api.internal;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.sparcs.bet.api.SkyBetException;
import com.sparcs.bet.api.SkyBetService;
import com.sparcs.bet.dto.SkyBet;
import com.sparcs.bet.dto.SkyBetReceipt;
import com.sparcs.bet.dto.SkyBetSlip;

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

		return response.getBody();
    }

    /* (non-Javadoc)
	 * @see com.sparcs.betapi.SkyBetService#placeBet(com.sparcs.betapi.SkyBet, int)
	 */
    @Override
	public SkyBetReceipt placeBet(SkyBetSlip slip) {

    	log.trace("slip={}", slip.toString());

    	// Sky API throws a HTTP 500 if bet_id <= 0.  Let's trap that condition
		// and return a more relevant error
		if( slip.getBetId() < 1 ) {
			
			throw SkyBetException.INVALID_BET_ID;
		}
    	// Sky API will allow a bet with zero stake to be taken.  We don't want
		// that.
    	if( slip.getStake() == 0 ) {

    		throw SkyBetException.INVALID_STAKE;
    	}

    	// POST the slip to SkyBet
    	ResponseEntity<SkyBetReceipt> response =
    			restClient.postForEntity(URL_BETS, slip, SkyBetReceipt.class);
    	log.trace(response.toString());
    	
		return response.getBody();
    }
}
