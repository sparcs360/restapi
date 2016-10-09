package com.sparcs.betapi;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparcs.BaseTest;

/**
 * {@link SkyBetSlip} tests
 *  
 * @author Lee Newfeld
 */
public class SkyBetSlipTest extends BaseTest {

	private static final String JSON = "{ \"bet_id\": 1, \"odds\": { \"numerator\": 10, \"denominator\": 1 }, \"stake\": 100}";

	@Test
	public void shouldSerialiseToExpectedJson() throws JsonProcessingException {

		SkyBetSlip slip = new SkyBetSlip(1, new Odds(10, 1), 100);
		String actualJson = prettyPrint(slip);
		JSONAssert.assertEquals(JSON, actualJson, true);
	}
	
	@Test
	public void shouldDeserialiseFromExpectedJson() throws Exception {
		
		SkyBetSlip slip = jsonMapper.readValue(JSON, SkyBetSlip.class);
		assertThat(slip, notNullValue());
		assertThat(slip.getBetId(), is(1));
		assertThat(slip.getOdds().getNumerator(), is(10));
		assertThat(slip.getOdds().getDenominator(), is(1));
		assertThat(slip.getStake(), is(100));
	}
}
