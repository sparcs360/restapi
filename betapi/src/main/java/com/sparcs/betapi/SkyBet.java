package com.sparcs.betapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an object in the array returned by http://skybettechtestapi.herokuapp.com/available
 * 
 * @author Lee Newfeld
 */
public class SkyBet {

	private int id;
	private String eventName;
	private String name;
	private Odds odds;
	
	/**
	 * Represents the 'odds' substructure
	 *  
	 * @author Lee Newfeld
	 */
	public static class Odds {
		
		private int numerator;
		private int denominator;

		/**
		 * Default Construtor
		 */
		Odds() {
		}
		
		/**
		 * Construction with specific fractional odds
		 * 
		 * @param numerator
		 * @param denominator
		 */
		public Odds(int numerator, int denominator) {

			this.numerator = numerator;
			this.denominator = denominator;
		}

		/**
		 * @return The numerator element of the fractional odds
		 */
		public int getNumerator() {

			return numerator;
		}

		/**
		 * @return The denominator element of the fractional odds
		 */
		public int getDenominator() {
			
			return denominator;
		}

		/**
		 * @return The odds in decimal format
		 */
		@JsonIgnore
		public double getDecimalOdds() {
			
			if( denominator == 0 ) {
				return Double.POSITIVE_INFINITY;
			}
			
			return (double)numerator / denominator + 1;
		}

		@Override
		public String toString() {
			return "Odds [numerator=" + numerator + ", denominator=" + denominator + "]";
		}

		@Override
		public int hashCode() {
			
			final int prime = 31;
			int result = 1;
			result = prime * result + denominator;
			result = prime * result + numerator;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Odds other = (Odds) obj;
			if (denominator != other.denominator)
				return false;
			if (numerator != other.numerator)
				return false;
			return true;
		}
	}

	/**
	 * Default Constructor
	 */
	SkyBet() {
	}

	/**
	 * 
	 * @param id
	 * @param eventName
	 * @param name
	 * @param numerator
	 * @param denominator
	 */
	public SkyBet(int id, String eventName, String name, int numerator, int denominator) {

		this.id = id;
		this.eventName = eventName;
		this.name = name;
		this.odds = new Odds(numerator, denominator);
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
	 * @return The {@link Odds} for this Bet.
	 */
	public Odds getOdds() {
		
		return odds;
	}
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((odds == null) ? 0 : odds.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkyBet other = (SkyBet) obj;
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (odds == null) {
			if (other.odds != null)
				return false;
		} else if (!odds.equals(other.odds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SkyBet [id=" + id + ", eventName=" + eventName + ", name=" + name + ", odds.numerator=" + odds.getNumerator()
				+ ", odds.demoninator=" + odds.getDenominator() + "]";
	}
}
