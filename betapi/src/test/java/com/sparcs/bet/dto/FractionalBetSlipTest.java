package com.sparcs.bet.dto;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparcs.bet.BaseTest;

/**
 * {@link FractionalBetSlip} tests
 *  
 * @author Lee Newfeld
 */
public class FractionalBetSlipTest extends BaseTest {

	private static final String JSON = "{ \"bet_id\": 1, \"odds\": { \"numerator\": 10, \"denominator\": 1 }, \"stake\": 100}";

	@Test
	public void shouldSerialiseToExpectedJson() throws JsonProcessingException {

		FractionalBetSlip slip = new FractionalBetSlip(1, new Odds(10, 1), 100);
		String actualJson = prettyPrint(slip);
		JSONAssert.assertEquals(JSON, actualJson, true);
	}
	
	@Test
	public void shouldDeserialiseFromExpectedJson() throws Exception {
		
		FractionalBetSlip slip = jsonMapper.readValue(JSON, FractionalBetSlip.class);
		assertThat(slip, notNullValue());
		assertThat(slip.getBetId(), is(1));
		assertThat(slip.getOdds().getNumerator(), is(10));
		assertThat(slip.getOdds().getDenominator(), is(1));
		assertThat(slip.getStake(), is(100));
	}
}
