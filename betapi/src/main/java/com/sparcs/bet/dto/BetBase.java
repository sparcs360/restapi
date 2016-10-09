package com.sparcs.bet.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The base class for DTOs containing a bet_id
 * 
 * @author Lee Newfeld
 */
@SuppressWarnings("serial")
@JsonPropertyOrder(alphabetic=true)
abstract class BetBase implements Serializable {

	@JsonProperty("bet_id")
	private int id;

	/**
	 * Constructor for Faster Jackson
	 */
	protected BetBase() {
	}

	/**
	 * Constructor
	 * @param id The unique id of the Bet
	 */
	protected BetBase(int id) {
		
		this.id = id;
	}
	
	/**
	 * @return The unique id of the Bet
	 */
	@JsonIgnore
	public int getBetId() {
		
		return id;
	}
}
