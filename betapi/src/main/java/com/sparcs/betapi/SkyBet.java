package com.sparcs.betapi;

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

//		/**
//		 * Constructor
//		 * 
//		 * @param numerator
//		 * @param demoninator
//		 */
//		public Odds(int numerator, int demoninator) {
//			
//			super();
//			
//			this.numerator = numerator;
//			this.demoninator = demoninator;
//		}

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
		public double getDecimalOdds() {
			
			if( denominator == 0 ) {
				return Double.POSITIVE_INFINITY;
			}
			
			return (double)numerator / denominator + 1;
		}
	}

//	/**
//	 * Constructor
//	 * 
//	 * @param id
//	 * @param eventName
//	 * @param name
//	 * @param numerator
//	 * @param denominator
//	 */
//	public SkyBet(int id, String eventName, String name, int numerator, int denominator) {
//		
//		super();
//		
//		this.id = id;
//		this.eventName = eventName;
//		this.name = name;
//		this.odds = new Odds(numerator, denominator);
//	}

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
	public String toString() {
		return "SkyBet [id=" + id + ", eventName=" + eventName + ", name=" + name + ", odds.numerator=" + odds.getNumerator()
				+ ", odds.demoninator=" + odds.getDenominator() + "]";
	}
}
