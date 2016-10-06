package com.sparcs.betapi;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

/**
 * A service for consuming the SkyBet API at
 * http://skybettechtestapi.herokuapp.com/
 * 
 * @author Lee Newfeld
 */
@Service
public class SkyBetService {

//	private static final Logger log = LoggerFactory.getLogger(SkyBetService.class);

	private static final String URL_BASE = "http://skybettechtestapi.herokuapp.com";
	private static final String URL_AVAILABLE = URL_BASE + "/available";

    @Autowired
    private RestOperations restClient;

    /**
     * @return The list of available {@link SkyBet bets}.
     */
    public List<SkyBet> getAvailable() {

		// Get the master list of Bets from Sky... 
		ResponseEntity<List<SkyBet>> availableSkyBetsResponse =
		        restClient.exchange(URL_AVAILABLE,
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<SkyBet>>() {});
		return availableSkyBetsResponse.getBody();
    }
}
