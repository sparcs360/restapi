package com.sparcs.betapi;

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

	private static final Logger log = LoggerFactory.getLogger(SkyBetSlipTest.class);


	@Test
	public void shouldSerialiseToExpectedJson() throws JsonProcessingException {

		log.trace("+shouldSerialiseToExpectedJson");

		SkyBetSlip slip = new SkyBetSlip(1, new SkyBet.Odds(10, 1), 100);
		String actualJson = prettyPrint(slip);
		JSONAssert.assertEquals("{ \"bet_id\": 1, \"odds\": { \"numerator\": 10, \"denominator\": 1 }, \"stake\": 100}", actualJson, true);

		log.trace("-shouldSerialiseToExpectedJson");
	}
}
