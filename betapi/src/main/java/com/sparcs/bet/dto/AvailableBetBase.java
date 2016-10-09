package com.sparcs.bet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The base class for DTOs representing available bets
 * 
 * @author Lee Newfeld
 */
@SuppressWarnings("serial")
abstract class AvailableBetBase extends BetBase {

	private String eventName;
	private String name;

	/**
	 * Constructor for Faster Jackson
	 */
	protected AvailableBetBase() {
		
		super();
	}
	
	/**
	 * Constructor
	 * @param id The unique id of the Bet
	 * @param eventName The name of the Event this Bet is associated with
	 * @param name The name of the entity being Bet on
	 */
	protected AvailableBetBase(int id, String eventName, String name) {
		
		super(id);
		
		this.eventName = eventName;
		this.name = name;
	}
	
	/**
	 * @return The name of the Event this Bet is associated with
	 */
	@JsonProperty("event")
	public String getEventName() {
		
		return eventName;
	}

	/**
	 * @return The name of the entity being Bet on
	 */
	public String getName() {
		
		return name;
	}
}
