package com.sparcs.bet.dto;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparcs.bet.BaseTest;

/**
 * {@link SkyBetReceipt} tests
 *  
 * @author Lee Newfeld
 */
public class SkyBetReceiptTest extends BaseTest {

	private static final String JSON_VALID =
			"{ \"bet_id\": 1, \"event\": \"World Cup 2018\", " +
			"\"name\": \"England\", \"odds\": { \"numerator\": 10, \"denominator\": 1 }, " +
			"\"stake\": 100, \"transaction_id\": 12345 }";

	@Test
	public void shouldSerialiseToExpectedJson() throws JsonProcessingException {

		SkyBet bet = new SkyBet(1, "World Cup 2018", "England", 10, 1);
		SkyBetReceipt receipt = new SkyBetReceipt(bet, 100, 12345);
		String actualJson = prettyPrint(receipt);
		JSONAssert.assertEquals(JSON_VALID, actualJson, true);
	}
	
	@Test
	public void shouldDeserialiseFromExpectedJson() throws Exception {
		
		SkyBetReceipt receipt = jsonMapper.readValue(JSON_VALID, SkyBetReceipt.class);
		assertThat(receipt, notNullValue());
		assertThat(receipt.getBet().getBetId(), is(1));
		assertThat(receipt.getBet().getEventName(), is("World Cup 2018"));
		assertThat(receipt.getBet().getName(), is("England"));
		assertThat(receipt.getBet().getOdds().getNumerator(), is(10));
		assertThat(receipt.getBet().getOdds().getDenominator(), is(1));
		assertThat(receipt.getStake(), is(100));
		assertThat(receipt.getTransactionId(), is(12345));
	}
}
