package com.sparcs.betapi;

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
	private double odds;
	
	/**
	 * Constructor
	 * 
	 * @param id The unique id of the Bet
	 * @param eventName The name of the Event this Bet is associated with
	 * @param name The name of the entity being Bet on
	 * @param odds The odds offered.
	 */
	public Bet(int id, String eventName, String name, double odds) {
		
		super();
		
		this.id = id;
		this.eventName = eventName;
		this.name = name;
		this.odds = odds;
	}

	/**
	 * @param sb A {@link SkyBet} to base this {@link Bet} on.
	 */
	public Bet(SkyBet sb) {

		super();
		
		id = sb.getId();
		eventName = sb.getEventName();
		name = sb.getName();
		odds = sb.getOdds().getDecimalOdds();
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
	public double getOdds() {
		return odds;
	}
	
	@Override
	public String toString() {
		return "SkyBet [id=" + id + ", eventName=" + eventName + ", name=" + name + ", odds=" + odds + "]";
	}
}