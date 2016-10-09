package com.sparcs.bet.dto;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparcs.bet.BaseTest;

/**
 * {@link DecimalBetReceipt} tests
 *  
 * @author Lee Newfeld
 */
public class DecimalBetReceiptTest extends BaseTest {

	private static final String JSON_VALID =
			"{ \"bet_id\": 1, \"event\": \"World Cup 2018\", " +
			"\"name\": \"England\", \"odds\": 11.0, " +
			"\"stake\": 100, \"transaction_id\": 12345 }";

	@Test
	public void shouldSerialiseToExpectedJson() throws JsonProcessingException {

		DecimalBet bet = new DecimalBet(1, "World Cup 2018", "England", new BigDecimal(11));
		DecimalBetReceipt receipt = new DecimalBetReceipt(bet, 100, 12345);
		String actualJson = prettyPrint(receipt);
		JSONAssert.assertEquals(JSON_VALID, actualJson, true);
	}
	
	@Test
	public void shouldDeserialiseFromExpectedJson() throws Exception {
		
		DecimalBetReceipt receipt = jsonMapper.readValue(JSON_VALID, DecimalBetReceipt.class);
		assertThat(receipt, notNullValue());
		assertThat(receipt.getBet().getBetId(), is(1));
		assertThat(receipt.getBet().getEventName(), is("World Cup 2018"));
		assertThat(receipt.getBet().getName(), is("England"));
		assertThat(receipt.getBet().getOdds().doubleValue(), is(11.0));
		assertThat(receipt.getStake(), is(100));
		assertThat(receipt.getTransactionId(), is(12345));
	}
}
