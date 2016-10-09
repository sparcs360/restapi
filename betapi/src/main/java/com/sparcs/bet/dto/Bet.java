package com.sparcs.bet.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Represents a Bet that a Customer can place a wager on.
 * 
 * @author Lee Newfeld
 */
@JsonPropertyOrder(alphabetic=true)
public class Bet {

	private int id;
	private String eventName;
	private String name;
	private BigDecimal odds;
	
	/**
	 * Constructor
	 * 
	 * @param id The unique id of the Bet
	 * @param eventName The name of the Event this Bet is associated with
	 * @param name The name of the entity being Bet on
	 * @param odds The odds offered.
	 */
	Bet(int id, String eventName, String name, BigDecimal odds) {
		
		super();
		
		this.id = id;
		this.eventName = eventName;
		this.name = name;
		this.odds = odds;
	}

	/**
	 * @param skyBet A {@link SkyBet} to base this {@link Bet} on.
	 */
	public Bet(SkyBet skyBet) {

		super();
		
		id = skyBet.getId();
		eventName = skyBet.getEventName();
		name = skyBet.getName();
		odds = skyBet.getOdds().getDecimalOdds();
	}

	/**
	 * @return The unique id of the Bet
	 */
	@JsonProperty("bet_id")
	public int getId() {
		
		return id;
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

	/**
	 * @return The odds offered.
	 */
	public BigDecimal getOdds() {
		
		return odds;
	}
	
	@Override
	public String toString() {
		return "SkyBet [id=" + id + ", eventName=" + eventName + ", name=" + name + ", odds=" + odds + "]";
	}
}
