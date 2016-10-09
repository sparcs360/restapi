package com.sparcs.bet.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import org.apache.commons.math3.fraction.Fraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Represents the 'odds' substructure
 *  
 * @author Lee Newfeld
 */
@JsonPropertyOrder(alphabetic=true)
public class Odds {

	private int numerator;
	private int denominator;

	/**
	 * Constructor for Faster Jackson
	 */
	@SuppressWarnings("unused")
	private Odds() {
	}
	
	/**
	 * Construction with specific fractional odds
	 * 
	 * @param numerator The numerator element of the fractional odds
	 * @param denominator The denominator element of the fractional odds
	 */
	public Odds(int numerator, int denominator) {

		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * Construction from decimal odds
	 * 
	 * @param odds Decimal odds
	 */
	public Odds(BigDecimal odds) {

		Objects.requireNonNull(odds, "odds cannot be null");

		Fraction fraction = new Fraction(odds.doubleValue() - 1, 0.005, 100);
		numerator = fraction.getNumerator();
		denominator = fraction.getDenominator();
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
	public BigDecimal getDecimalOdds() {
		
		if( denominator == 0 ) {
			return BigDecimal.ZERO;
		}

		return new BigDecimal(numerator)
				.divide(new BigDecimal(denominator), 2, RoundingMode.HALF_UP)
				.add(BigDecimal.ONE)
				;
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
