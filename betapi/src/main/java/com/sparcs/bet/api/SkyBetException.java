package com.sparcs.bet.api;

import org.apache.commons.io.Charsets;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Mimics HTTP 418 exceptions thrown by the Sky API's
 * <code>/bets</code> URI.
 * Thrown by {@link SkyBetServiceImpl} when plugging gaps in that API. 
 * 
 * @author Lee Newfeld
 */
public class SkyBetException extends HttpClientErrorException {

	private static final long serialVersionUID = 3740568947973773328L;

	/**
	 * bet_id doesn't identify a currently available bet 
	 */
	public static final SkyBetException INVALID_BET_ID =
			new SkyBetException("Invalid Bet ID");

	/**
	 * stake must be positive
	 */
	public static final SkyBetException INVALID_STAKE =
			new SkyBetException("Invalid Stake");
	
	/**
	 * Constructor
	 * 
	 * @param error Custom error message
	 */
	SkyBetException(String error) {

		super(HttpStatus.I_AM_A_TEAPOT,
			  HttpStatus.I_AM_A_TEAPOT.getReasonPhrase(),
			  String.format("{ \"error\": \"%s\" }", error).getBytes(),
			  Charsets.UTF_8);
	}
}
