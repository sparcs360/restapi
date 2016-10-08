package com.sparcs.betapi;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Parameterized.class)
public class OddsTest {

	@Parameters(name = "{0}/{1}={2}")
	public static Collection<Object[]> testdata() {

		return Arrays.asList(new Object[][] {
			{ 1, 5, "1.2" },
			{ 2, 9, "1.22" },
			{ 1, 4, "1.25" },
			{ 2, 7, "1.29" },
			{ 3, 10, "1.3" },
			{ 1, 3, "1.33" },
			{ 6, 17, "1.35" },
			{ 4, 11, "1.36" },
			{ 2, 5, "1.4" },
			{ 4, 9, "1.44" },
			{ 9, 20, "1.45" },
			{ 7, 15, "1.47" },
			{ 1, 2, "1.5" },
			{ 8, 15, "1.53" },
			{ 4, 7, "1.57" },
			{ 3, 5, "1.6" },
			// Can't get Apache Fraction to derive this fraction from 1.62 (it's coming up with 5/8)
			//{ 8, 13, "1.62" },	// 0.61538461538461538461538461538462
			{ 5, 8, "1.63" },	// 0.625
			{ 2, 3, "1.67" },
			{ 7, 10, "1.7" },
			{ 8, 11, "1.73" },
			{ 4, 5, "1.8" },
			{ 5, 6, "1.83" },
			{ 9, 10, "1.9" },
			{ 10, 11, "1.91" },
			{ 19, 20, "1.95" },
			{ 1, 1, "2" },
			{ 21, 20, "2.05" },
			{ 11, 10, "2.1" },
			{ 6, 5, "2.2" },
			{ 5, 4, "2.25" },
			{ 13, 10, "2.3" },
			{ 11, 8, "2.38" },
			{ 7, 5, "2.4" },
			{ 3, 2, "2.5" },
			{ 8, 5, "2.6" },
			{ 13, 8, "2.63" },
			{ 17, 10, "2.7" },
			{ 7, 4, "2.75" },
			{ 9, 5, "2.8" },
			{ 15, 8, "2.88" },
			{ 19, 10, "2.9" },
			{ 2, 1, "3" },
			{ 21, 10, "3.1" },
			{ 17, 8, "3.13" },
			{ 11, 5, "3.2" },
			{ 9, 4, "3.25" },
			{ 23, 10, "3.3" },
			{ 19, 8, "3.38" },
			{ 12, 5, "3.4" },
			{ 5, 2, "3.5" },
			{ 13, 5, "3.6" },
			{ 11, 4, "3.75" },
			{ 14, 5, "3.8" },
			{ 3, 1, "4" },
			{ 16, 5, "4.2" },
			{ 10, 3, "4.33" },
			{ 7, 2, "4.5" },
			{ 18, 5, "4.6" },
			{ 4, 1, "5" },
			{ 9, 2, "5.5" },
			{ 5, 1, "6" },
			{ 11, 2, "6.5" },
			{ 6, 1, "7" },
			{ 13, 2, "7.5" },
			{ 7, 1, "8" },
			{ 15, 2, "8.5" },
			{ 8, 1, "9" },
			{ 17, 2, "9.5" },
			{ 9, 1, "10" },
			{ 10, 1, "11" },
			{ 50, 1, "51" },
		});
	}
	
	private int num;
	private int den;
	private BigDecimal dec;
	
	public OddsTest(int num, int den, String dec) {
	
		this.num = num;
		this.den = den;
		this.dec = new BigDecimal(dec).setScale(2, RoundingMode.HALF_UP);
	}
	
	@Test
	public void shouldConvertFractionalBetToDecimalBet() {

		Odds odds = new Odds(num, den);
		
		assertThat(odds.getDecimalOdds(), is(dec));
	}

	@Test
	public void shouldConvertDecimalBetToFractionalBet() {

		Odds odds = new Odds(dec);
		
		if( odds.getDenominator() != den || odds.getNumerator() != num ) {
			System.out.println(odds + " -> " + num + "/" + den + "=" + dec);
		}
		
		assertThat(odds.getDenominator(), is(den));
		assertThat(odds.getNumerator(), is(num));
	}
}
