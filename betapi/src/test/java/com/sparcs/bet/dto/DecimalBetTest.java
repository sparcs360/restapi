package com.sparcs.bet.dto;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparcs.bet.BaseTest;

/**
 * {@link DecimalBet} tests
 *  
 * @author Lee Newfeld
 */
public class DecimalBetTest extends BaseTest {

	private static final String JSON_VALID = "{ \"bet_id\": 1, \"event\": \"World Cup 2018\", \"name\": \"England\", \"odds\": 11.0}";

	@Test
	public void shouldSerialiseToExpectedJson() throws JsonProcessingException {

		DecimalBet bet = new DecimalBet(1, "World Cup 2018", "England", new BigDecimal(11));
		String actualJson = prettyPrint(bet);
		JSONAssert.assertEquals(JSON_VALID, actualJson, true);
	}
	
	@Test
	public void shouldDeserialiseFromExpectedJson() throws Exception {
		
		DecimalBet bet = jsonMapper.readValue(JSON_VALID, DecimalBet.class);
		assertThat(bet, notNullValue());
		assertThat(bet.getBetId(), is(1));
		assertThat(bet.getEventName(), is("World Cup 2018"));
		assertThat(bet.getName(), is("England"));
		assertThat(bet.getOdds().doubleValue(), is(11.0));
	}
}
