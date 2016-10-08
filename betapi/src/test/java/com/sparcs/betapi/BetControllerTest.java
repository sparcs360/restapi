package com.sparcs.betapi;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.sparcs.BaseTest;

/**
 * {@link BetControllerImpl} tests
 *  
 * @author Lee Newfeld
 */
public class BetControllerTest extends BaseTest {

	@Test
	public void shouldGetResponseFromGetAvailable() throws Exception {

        mvc.perform(get("/available"))
           //.andDo(print())
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
		BetSlip slip = new BetSlip(1, new BigDecimal(11), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
								 .content(prettyPrint(slip)))
		   .andDo(print())
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
	public void shouldntGetReceiptWhenBetSlipIsUnreadable() throws Exception {

//		log.trace("+shouldntGetReceiptWhenBetSlipIsUnreadable");
		
		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
								 .content("xxx: \"yyy\""))
		   .andDo(print())
		   .andExpect(status().isBadRequest())
		   ;

//		log.trace("-shouldntGetReceiptWhenBetSlipIsUnreadable");
	}

	@Test
	public void shouldntGetReceiptForNonExistentBetId() throws Exception {

//		log.trace("+shouldntGetReceiptForNonExistentBetId");
		
		// No such bet with Id #999
		BetSlip slip = new BetSlip(999, new BigDecimal(11), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(print())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Bet ID")))
		   ;

//		log.trace("-shouldntGetReceiptForNonExistentBetId");
	}

	@Test
	public void shouldntGetReceiptForZeroBetId() throws Exception {

//		log.trace("+shouldntGetReceiptForZeroBetId");

		// No such bet with Id #0
		BetSlip slip = new BetSlip(0, new BigDecimal(11), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(print())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Bet ID")))
		   ;

//		log.trace("-shouldntGetReceiptForZeroBetId");
	}

	@Test
	public void shouldntGetReceiptForNegativeBetId() throws Exception {

//		log.trace("+shouldntGetReceiptForNegativeBetId");

		// No such bet with Id #-1
		BetSlip slip = new BetSlip(-1, new BigDecimal(11), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(print())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Bet ID")))
		   ;

//		log.trace("-shouldntGetReceiptForNegativeBetId");
	}

	@Test
	public void shouldntGetReceiptForInvalidOdds() throws Exception {

//		log.trace("+shouldntGetReceiptForInvalidOdds");
		
		// Odds don't match current odds
		BetSlip slip = new BetSlip(1, new BigDecimal(101), 100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(print())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Incorrect Odds")))
		   ;

//		log.trace("-shouldntGetReceiptForInvalidOdds");
	}

	@Test
	public void shouldntGetReceiptForZeroStake() throws Exception {

//		log.trace("+shouldntGetReceiptForZeroStake");
		
		BetSlip slip = new BetSlip(1, new BigDecimal(11), 0);

    	// Note: BetControllerImpl doesn't allow zero stakes but the Sky API it delegates to does.
		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(print())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Stake")))
		   ;

//		log.trace("-shouldntGetReceiptForZeroStake");
	}

	@Test
	public void shouldntGetReceiptForNegativeStake() throws Exception {

//		log.trace("+shouldntGetReceiptForNegativeStake");
		
		BetSlip slip = new BetSlip(1, new BigDecimal(11), -100);

		mvc.perform(post("/bets").contentType(MediaType.APPLICATION_JSON_UTF8)
				 .content(prettyPrint(slip)))
		   .andDo(print())
		   .andExpect(status().isIAmATeapot())
		   .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		   .andExpect(jsonPath("$.error", is("Invalid Stake")))
		   ;

//		log.trace("-shouldntGetReceiptForNegativeStake");
	}
}
