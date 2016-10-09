package com.sparcs.bet.api;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.sparcs.bet.BaseTest;
import com.sparcs.bet.dto.DecimalBetSlip;

/**
 * {@link BetControllerImpl} tests
 *  
 * @author Lee Newfeld
 */
public class BetControllerTest extends BaseTest {

	@Test
	public void shouldGetResponseFromGetAvailable() throws Exception {

        mvc.perform(get("/available"))
           .andDo(debugLog())
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
           .andExpect(jsonPath("$", hasSize(6)))
           .andExpect(jsonPath("$[0].bet_id", is(1)))
           .andExpect(jsonPath("$[0].event", is("World Cup 2018")))
           .andExpect(jsonPath("$[0].name", is("England")))
           .andExpect(jsonPath("$[0].odds", is(11.0)))
           .andExpect(jsonPath("$[1].bet_id", is(2)))
           .andExpect(jsonPath("$[1].event", is("World Cup 2018")))
           .andExpect(jsonPath("$[1].name", is("Brazil")))
           .andExpect(jsonPath("$[1].odds", is(2.0)))
           .andExpect(jsonPath("$[2].bet_id", is(3)))
           .andExpect(jsonPath("$[2].event", is("World Cup 2018")))
           .andExpect(jsonPath("$[2].name", is("Spain")))
           .andExpect(jsonPath("$[2].odds", is(4.0)))
           .andExpect(jsonPath("$[3].bet_id", is(4)))
           .andExpect(jsonPath("$[3].event", is("Next General Election")))
           .andExpect(jsonPath("$[3].name", is("Labour")))
           .andExpect(jsonPath("$[3].odds", is(2.75)))
           .andExpect(jsonPath("$[4].bet_id", is(5)))
           .andExpect(jsonPath("$[4].event", is("Next General Election")))
           .andExpect(jsonPath("$[4].name", is("Conservatives")))
           .andExpect(jsonPath("$[4].odds", is(3.0)))
           .andExpect(jsonPath("$[5].bet_id", is(6)))
           .andExpect(jsonPath("$[5].event", is("Next General Election")))
           .andExpect(jsonPath("$[5].name", is("Liberal Democrats")))
           .andExpect(jsonPath("$[5].odds", is(18.0)))
           ;
	}

	@Test
	public void shouldGetReceiptForValidBet() throws Exception {

		// Create valid betting slip
		DecimalBetSlip slip = new DecimalBetSlip(1, new BigDecimal(11), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
								 .content(prettyPrint(slip)))
		   .andDo(debugLog())
           .andExpect(status().isOk())
           .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
           .andExpect(jsonPath("$.bet_id", is(1)))
           .andExpect(jsonPath("$.event", is("World Cup 2018")))
           .andExpect(jsonPath("$.name", is("England")))
           .andExpect(jsonPath("$.odds", is(11.0)))
           .andExpect(jsonPath("$.stake", is(100)))
           .andExpect(jsonPath("$.transaction_id", greaterThan(0)))
		   ;
	}
	
	@Test
	public void shouldntGetReceiptWhenRequestIsEmptyString() throws Exception {

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
								 .content(""))
		   .andDo(debugLog())
		   .andExpect(status().isBadRequest())
		   .andExpect(content().contentType(MediaType.TEXT_PLAIN))
		   .andExpect(content().string(startsWith(BetController.ERROR_BAD_SLIP)))
		   ;
	}

	@Test
	public void shouldntGetReceiptWhenRequestIsEmptyObject() throws Exception {

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
								 .content("{}"))
		   .andDo(debugLog())
		   .andExpect(status().isBadRequest())
		   .andExpect(content().contentType(MediaType.TEXT_PLAIN))
		   .andExpect(content().string(startsWith(BetController.ERROR_BAD_SLIP)))
		   ;
	}

	@Test
	public void shouldntGetReceiptWhenRequestIsPartialObject() throws Exception {

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
								 .content("{ \"bet_id\": 1 }"))
		   .andDo(debugLog())
		   .andExpect(status().isBadRequest())
		   .andExpect(content().contentType(MediaType.TEXT_PLAIN))
		   .andExpect(content().string(startsWith(BetController.ERROR_BAD_SLIP)))
		   ;
	}

	@Test
	public void shouldntGetReceiptWhenRequestIsMalformedObject() throws Exception {

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
								 .content("\"xxx\": \"yyy\""))
		   .andDo(debugLog())
		   .andExpect(status().isBadRequest())
		   .andExpect(content().contentType(MediaType.TEXT_PLAIN))
		   .andExpect(content().string(startsWith(BetController.ERROR_BAD_SLIP)))
		   ;
	}

	@Test
	public void shouldntGetReceiptForNonExistentBetId() throws Exception {

		// No such bet with Id #999
		DecimalBetSlip slip = new DecimalBetSlip(999, new BigDecimal(11), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(debugLog())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Bet ID")))
		   ;
	}

	@Test
	public void shouldntGetReceiptForZeroBetId() throws Exception {

		// No such bet with Id #0
		DecimalBetSlip slip = new DecimalBetSlip(0, new BigDecimal(11), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(debugLog())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Bet ID")))
		   ;
	}

	@Test
	public void shouldntGetReceiptForNegativeBetId() throws Exception {

		// No such bet with Id #-1
		DecimalBetSlip slip = new DecimalBetSlip(-1, new BigDecimal(11), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(debugLog())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Bet ID")))
		   ;
	}

	@Test
	public void shouldntGetReceiptForInvalidOdds() throws Exception {

		// Odds don't match current odds
		DecimalBetSlip slip = new DecimalBetSlip(1, new BigDecimal(101), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(debugLog())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Incorrect Odds")))
		   ;
	}

	@Test
	public void shouldntGetReceiptForZeroStake() throws Exception {

		DecimalBetSlip slip = new DecimalBetSlip(1, new BigDecimal(11), 0);

    	// Note: BetControllerImpl doesn't allow zero stakes but the Sky API it delegates to does.
		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(debugLog())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Stake")))
		   ;
	}

	@Test
	public void shouldntGetReceiptForNegativeStake() throws Exception {

		DecimalBetSlip slip = new DecimalBetSlip(1, new BigDecimal(11), -100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(debugLog())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Stake")))
		   ;
	}
	
	@Test
	public void shouldGetREADME() throws Exception {
		
        mvc.perform(get("/"))
	       .andDo(debugLog())
	       .andExpect(status().isOk())
	       .andExpect(content().contentType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8"))
	       .andExpect(content().string(startsWith("GET /available")))
	       ;
	}
}
