package com.sparcs.betapi;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

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
public class BetSlipTest extends BaseTest {

	private static final Logger log = LoggerFactory.getLogger(BetSlipTest.class);

	private static final String JSON = "{ \"bet_id\": 1, \"odds\": 11.0, \"stake\": 100}";

	@Test
	public void shouldSerialiseToExpectedJson() throws JsonProcessingException {

		log.trace("+shouldSerialiseToExpectedJson");

		BetSlip slip = new BetSlip(1, new BigDecimal(11), 100);
		String actualJson = prettyPrint(slip);
		log.trace("actualJson={}", actualJson);
		JSONAssert.assertEquals(JSON, actualJson, true);

		log.trace("-shouldSerialiseToExpectedJson");
	}
	
	@Test
	public void shouldDeserialiseFromExpectedJson() throws Exception {
		
		log.trace("+shouldDeserialiseFromExpectedJson");

		BetSlip slip = jsonMapper.readValue(JSON, BetSlip.class);
		assertThat(slip, notNullValue());
		assertThat(slip.getBetId(), is(1));
		assertThat(slip.getOdds().doubleValue(), is(11.0));
		assertThat(slip.getStake(), is(100));

		log.trace("-shouldDeserialiseFromExpectedJson");
	}
}
