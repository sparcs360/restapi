package com.sparcs.betapi;

import org.apache.commons.io.Charsets;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

/**
 * 
 * @author Lee Newfeld
 */
public class BetException extends HttpClientErrorException {

	private static final long serialVersionUID = 3740568947973773328L;

	/**
	 * bet_id doesn't identify a currently available bet 
	 */
	public static final BetException INVALID_BET_ID =
			new BetException("Invalid Bet ID");

	/**
	 * stake must be positive
	 */
	public static final BetException INVALID_STAKE =
			new BetException("Invalid Stake");
	
	/**
	 * Constructor
	 * 
	 * @param error Custom error message
	 */
	BetException(String error) {

		super(HttpStatus.I_AM_A_TEAPOT,
			  HttpStatus.I_AM_A_TEAPOT.getReasonPhrase(),
			  String.format("{ \"error\": \"%s\" }", error).getBytes(),
			  Charsets.UTF_8);
	}
}
