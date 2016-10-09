package com.sparcs.bet.dto;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparcs.bet.BaseTest;

/**
 * {@link DecimalBetSlip} tests
 *  
 * @author Lee Newfeld
 */
public class DecimalBetSlipTest extends BaseTest {

	private static final String JSON_VALID = "{ \"bet_id\": 1, \"odds\": 11.0, \"stake\": 100}";
	private static final String JSON_INCOMPLETE = "{ \"bet_id\": 1 }";

	@Test
	public void shouldSerialiseToExpectedJson() throws JsonProcessingException {

		DecimalBetSlip slip = new DecimalBetSlip(1, new BigDecimal(11), 100);
		String actualJson = prettyPrint(slip);
		JSONAssert.assertEquals(JSON_VALID, actualJson, true);
	}
	
	@Test
	public void shouldDeserialiseFromExpectedJson() throws Exception {
		
		DecimalBetSlip slip = jsonMapper.readValue(JSON_VALID, DecimalBetSlip.class);
		assertThat(slip, notNullValue());
		assertThat(slip.getBetId(), is(1));
		assertThat(slip.getOdds().doubleValue(), is(11.0));
		assertThat(slip.getStake(), is(100));
	}

	@Test
	public void shouldBeCompleteWhenDeserialiseFromValidJson() throws Exception {
		
		DecimalBetSlip slip = jsonMapper.readValue(JSON_VALID, DecimalBetSlip.class);
		assertThat(slip.isComplete(), is(true));
	}

	@Test
	public void shouldntBeCompleteWhenDeserialiseFromIncompleteJson() throws Exception {
		
		DecimalBetSlip slip = jsonMapper.readValue(JSON_INCOMPLETE, DecimalBetSlip.class);
		assertThat(slip.isComplete(), is(false));
	}
}
