package com.sparcs.bet.dto;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparcs.bet.BaseTest;

/**
 * {@link SkyBet} tests
 *  
 * @author Lee Newfeld
 */
public class SkyBetTest extends BaseTest {

	private static final String JSON_VALID = "{ \"bet_id\": 1, \"event\": \"World Cup 2018\", \"name\": \"England\", \"odds\": { \"numerator\": 10, \"denominator\": 1 } }";

	@Test
	public void shouldSerialiseToExpectedJson() throws JsonProcessingException {

		SkyBet bet = new SkyBet(1, "World Cup 2018", "England", 10, 1);
		String actualJson = prettyPrint(bet);
		JSONAssert.assertEquals(JSON_VALID, actualJson, true);
	}
	
	@Test
	public void shouldDeserialiseFromExpectedJson() throws Exception {
		
		SkyBet bet = jsonMapper.readValue(JSON_VALID, SkyBet.class);
		assertThat(bet, notNullValue());
		assertThat(bet.getBetId(), is(1));
		assertThat(bet.getEventName(), is("World Cup 2018"));
		assertThat(bet.getName(), is("England"));
		assertThat(bet.getOdds().getNumerator(), is(10));
		assertThat(bet.getOdds().getDenominator(), is(1));
	}
}
