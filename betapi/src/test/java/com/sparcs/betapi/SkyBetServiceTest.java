package com.sparcs.betapi;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.sparcs.BaseTest;

/**
 * {@link SkyBetServiceImpl} tests
 *  
 * @author Lee Newfeld
 */
public class SkyBetServiceTest extends BaseTest {

	@Autowired
	private SkyBetService skyBetService;

	@Test
	public void shouldGetResponseFromGetAvailable() throws Exception {

		List<SkyBet> bets = skyBetService.getAvailable();
		assertThat(bets, notNullValue());
		assertThat(bets, hasSize(6));
		
		assertThat(bets.get(0).getId(), is(1));
		assertThat(bets.get(0).getEventName(), is("World Cup 2018"));
		assertThat(bets.get(0).getName(), is("England"));
		assertThat(bets.get(0).getOdds().getNumerator(), is(10));
		assertThat(bets.get(0).getOdds().getDenominator(), is(1));
		assertThat(bets.get(0).getOdds().getDecimalOdds().doubleValue(), is(11.0));
		
		assertThat(bets.get(1).getId(), is(2));
		assertThat(bets.get(1).getEventName(), is("World Cup 2018"));
		assertThat(bets.get(1).getName(), is("Brazil"));
		assertThat(bets.get(1).getOdds().getNumerator(), is(1));
		assertThat(bets.get(1).getOdds().getDenominator(), is(1));
		assertThat(bets.get(1).getOdds().getDecimalOdds().doubleValue(), is(2.0));

		assertThat(bets.get(2).getId(), is(3));
		assertThat(bets.get(2).getEventName(), is("World Cup 2018"));
		assertThat(bets.get(2).getName(), is("Spain"));
		assertThat(bets.get(2).getOdds().getNumerator(), is(3));
		assertThat(bets.get(2).getOdds().getDenominator(), is(1));
		assertThat(bets.get(2).getOdds().getDecimalOdds().doubleValue(), is(4.0));

		assertThat(bets.get(3).getId(), is(4));
		assertThat(bets.get(3).getEventName(), is("Next General Election"));
		assertThat(bets.get(3).getName(), is("Labour"));
		assertThat(bets.get(3).getOdds().getNumerator(), is(7));
		assertThat(bets.get(3).getOdds().getDenominator(), is(4));
		assertThat(bets.get(3).getOdds().getDecimalOdds().doubleValue(), is(2.75));

		assertThat(bets.get(4).getId(), is(5));
		assertThat(bets.get(4).getEventName(), is("Next General Election"));
		assertThat(bets.get(4).getName(), is("Conservatives"));
		assertThat(bets.get(4).getOdds().getNumerator(), is(2));
		assertThat(bets.get(4).getOdds().getDenominator(), is(1));
		assertThat(bets.get(4).getOdds().getDecimalOdds().doubleValue(), is(3.0));

		assertThat(bets.get(5).getId(), is(6));
		assertThat(bets.get(5).getEventName(), is("Next General Election"));
		assertThat(bets.get(5).getName(), is("Liberal Democrats"));
		assertThat(bets.get(5).getOdds().getNumerator(), is(17));
		assertThat(bets.get(5).getOdds().getDenominator(), is(1));
		assertThat(bets.get(5).getOdds().getDecimalOdds().doubleValue(), is(18.0));
	}

	@Test
	public void shouldGetReceiptForValidBet() {

		SkyBetSlip slip = new SkyBetSlip(1, new Odds(10, 1), 100);
		SkyBetReceipt receipt = skyBetService.placeBet(slip);
		
		assertThat(receipt, notNullValue());
		assertThat(receipt.getBet().getId(), is(slip.getBetId()));
		assertThat(receipt.getBet().getOdds(), is(slip.getOdds()));
		assertThat(receipt.getStake(), is(slip.getStake()));
		assertThat(receipt.getTransactionId(), greaterThan(0));
	}

	@Rule
	public ExpectedException shouldntGetReceiptForZeroStakeException = ExpectedException.none();
	@Test
	public void shouldntGetReceiptForZeroStake() {

		SkyBetSlip slip = new SkyBetSlip(1, new Odds(10, 1), 0);
		
    	// Note: The Sky API allows a zero stake.
		//       SkyBetServiceImpl traps this condition and returns a 418.
		shouldntGetReceiptForZeroStakeException.expect(HttpClientErrorException.class);
		shouldntGetReceiptForZeroStakeException.expect(hasStatusCode(HttpStatus.I_AM_A_TEAPOT));
		shouldntGetReceiptForZeroStakeException.expect(jsonBodyMatches("{ \"error\": \"Invalid Stake\" }"));

		skyBetService.placeBet(slip);
	}

	@Rule
	public ExpectedException shouldntGetReceiptForNonExistentBetIdException = ExpectedException.none();
	@Test
	public void shouldntGetReceiptForNonExistentBetId() {

		// No such bet with Id #999
		SkyBetSlip slip = new SkyBetSlip(999, new Odds(10, 1), 100);

		shouldntGetReceiptForNonExistentBetIdException.expect(HttpClientErrorException.class);
		shouldntGetReceiptForNonExistentBetIdException.expect(hasStatusCode(HttpStatus.I_AM_A_TEAPOT));
		shouldntGetReceiptForNonExistentBetIdException.expect(jsonBodyMatches("{ \"error\": \"Invalid Bet ID\" }"));

		skyBetService.placeBet(slip);
	}

	@Rule
	public ExpectedException shouldntGetReceiptForZeroBetIdException = ExpectedException.none();
	@Test
	public void shouldntGetReceiptForZeroBetId() {

		// No such bet with Id #0
		SkyBetSlip slip = new SkyBetSlip(0, new Odds(10, 1), 100);
		
    	// Note: The Sky API throws an HTTP 500 if bet_id <= 0.
		//       SkyBetServiceImpl traps this condition and returns a 418.
		shouldntGetReceiptForZeroBetIdException.expect(HttpClientErrorException.class);
		shouldntGetReceiptForZeroBetIdException.expect(hasStatusCode(HttpStatus.I_AM_A_TEAPOT));
		shouldntGetReceiptForZeroBetIdException.expect(jsonBodyMatches("{ \"error\": \"Invalid Bet ID\" }"));

		skyBetService.placeBet(slip);
	}

	@Rule
	public ExpectedException shouldntGetReceiptForNegativeBetIdException = ExpectedException.none();
	@Test
	public void shouldntGetReceiptForNegativeBetId() {

		// No such bet with Id #-1
		SkyBetSlip slip = new SkyBetSlip(-1, new Odds(10, 1), 100);
		
    	// Note: The Sky API throws an HTTP 500 if bet_id <= 0.
		//       SkyBetServiceImpl traps this condition and returns a 418.
		shouldntGetReceiptForNegativeBetIdException.expect(HttpClientErrorException.class);
		shouldntGetReceiptForNegativeBetIdException.expect(hasStatusCode(HttpStatus.I_AM_A_TEAPOT));
		shouldntGetReceiptForNegativeBetIdException.expect(jsonBodyMatches("{ \"error\": \"Invalid Bet ID\" }"));

		skyBetService.placeBet(slip);
	}

	@Rule
	public ExpectedException shouldntGetReceiptForInvalidOddsException = ExpectedException.none();
	@Test
	public void shouldntGetReceiptForInvalidOdds() {

		// Odds don't match current odds
		SkyBetSlip slip = new SkyBetSlip(1, new Odds(100, 1), 100);

		shouldntGetReceiptForInvalidOddsException.expect(HttpClientErrorException.class);
		shouldntGetReceiptForInvalidOddsException.expect(hasStatusCode(HttpStatus.I_AM_A_TEAPOT));
		shouldntGetReceiptForInvalidOddsException.expect(jsonBodyMatches("{ \"error\": \"Incorrect Odds\" }"));

		skyBetService.placeBet(slip);
	}

	@Rule
	public ExpectedException shouldntGetReceiptForNegativeStakeException = ExpectedException.none();
	@Test
	public void shouldntGetReceiptForNegativeStake() {

		SkyBetSlip slip = new SkyBetSlip(1, new Odds(10, 1), -100);
		
		shouldntGetReceiptForNegativeStakeException.expect(HttpClientErrorException.class);
		shouldntGetReceiptForNegativeStakeException.expect(hasStatusCode(HttpStatus.I_AM_A_TEAPOT));
		shouldntGetReceiptForNegativeStakeException.expect(jsonBodyMatches("{ \"error\": \"Invalid Stake\" }"));

		// Negative stake isn't allowed
		skyBetService.placeBet(slip);
	}
	
	//---
	
	/**
	 * Factory for creating {@link HttpStatusCodeExceptionStatusCodeMatcher}.
	 * 
	 * @param statusCode The {@link HttpStatus} we're expecting
	 * @return The matcher
	 */
    @Factory
    private static HttpStatusCodeExceptionStatusCodeMatcher hasStatusCode(final HttpStatus statusCode) {
    	
        return new HttpStatusCodeExceptionStatusCodeMatcher(statusCode);
    }
	
	/**
	 * A Hamcrest Matcher for checking {@link HttpStatusCodeException#getStatusCode()}.
	 * 
	 * @author Lee Newfeld
	 */
	public static class HttpStatusCodeExceptionStatusCodeMatcher extends TypeSafeMatcher<HttpStatusCodeException> {

		private HttpStatus statusCode;
		
		public HttpStatusCodeExceptionStatusCodeMatcher(HttpStatus statusCode) {
			
			this.statusCode = statusCode;
		}

		@Override
		public void describeTo(Description description) {

			description.appendText("status code")
					   .appendValue(statusCode);
		}

		@Override
		protected boolean matchesSafely(HttpStatusCodeException other) {

			return other.getStatusCode() == statusCode;
		}

	}

	/**
	 * Factory for creating {@link HttpStatusCodeExceptionJsonBodyMatcher}.
	 * 
	 * @param body The json we're expecting in the 
	 * @return The matcher
	 */
    @Factory
    private static HttpStatusCodeExceptionJsonBodyMatcher jsonBodyMatches(final String body) {
    	
        return new HttpStatusCodeExceptionJsonBodyMatcher(body);
    }
	
	/**
	 * A Hamcrest Matcher for checking that a specific Json response is returned
	 * in {@link HttpStatusCodeException#getResponseBodyAsString()}.
	 * 
	 * @author Lee Newfeld
	 */
	public static class HttpStatusCodeExceptionJsonBodyMatcher extends TypeSafeMatcher<HttpStatusCodeException> {

		private String body;
		
		public HttpStatusCodeExceptionJsonBodyMatcher(String body) {
			
			this.body = body;
		}

		@Override
		public void describeTo(Description description) {

			description.appendText("body ")
					   .appendValue(body);
		}

		@Override
		protected boolean matchesSafely(HttpStatusCodeException item) {

			String otherBody = item.getResponseBodyAsString();

			return JSONCompare.compareJSON(
					body, otherBody, JSONCompareMode.STRICT).passed();
		}
		
		@Override
		protected void describeMismatchSafely(HttpStatusCodeException item, Description mismatchDescription) {

			mismatchDescription.appendText("doesn't match ")
							   .appendValue(item.getResponseBodyAsString());
		}
	}
}
