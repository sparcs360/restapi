package com.sparcs.betapi;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sparcs.BaseTest;

/**
 * {@link SkyBetService} tests
 *  
 * @author Lee Newfeld
 */
public class SkyBetServiceTest extends BaseTest {

	private static final Logger log = LoggerFactory.getLogger(SkyBetServiceTest.class);

	@Autowired
	private SkyBetService skyBetService;

	/**
	 * Should 
	 *  
	 * @throws Exception
	 */
	@Test
	public void shouldGetResponseFromGetAvailable() throws Exception {

		log.trace("+shouldGetResponseFromGetAvailable");
		
		List<SkyBet> bets = skyBetService.getAvailable();
		assertThat(bets, notNullValue());
		assertThat(bets, hasSize(6));
		
		assertThat(bets.get(0).getId(), is(1));
		assertThat(bets.get(0).getEventName(), is("World Cup 2018"));
		assertThat(bets.get(0).getName(), is("England"));
		assertThat(bets.get(0).getOdds().getNumerator(), is(10));
		assertThat(bets.get(0).getOdds().getDenominator(), is(1));
		assertThat(bets.get(0).getOdds().getDecimalOdds(), is(11.0));
		
		assertThat(bets.get(1).getId(), is(2));
		assertThat(bets.get(1).getEventName(), is("World Cup 2018"));
		assertThat(bets.get(1).getName(), is("Brazil"));
		assertThat(bets.get(1).getOdds().getNumerator(), is(1));
		assertThat(bets.get(1).getOdds().getDenominator(), is(1));
		assertThat(bets.get(1).getOdds().getDecimalOdds(), is(2.0));

		assertThat(bets.get(2).getId(), is(3));
		assertThat(bets.get(2).getEventName(), is("World Cup 2018"));
		assertThat(bets.get(2).getName(), is("Spain"));
		assertThat(bets.get(2).getOdds().getNumerator(), is(3));
		assertThat(bets.get(2).getOdds().getDenominator(), is(1));
		assertThat(bets.get(2).getOdds().getDecimalOdds(), is(4.0));

		assertThat(bets.get(3).getId(), is(4));
		assertThat(bets.get(3).getEventName(), is("Next General Election"));
		assertThat(bets.get(3).getName(), is("Labour"));
		assertThat(bets.get(3).getOdds().getNumerator(), is(7));
		assertThat(bets.get(3).getOdds().getDenominator(), is(4));
		assertThat(bets.get(3).getOdds().getDecimalOdds(), is(2.75));

		assertThat(bets.get(4).getId(), is(5));
		assertThat(bets.get(4).getEventName(), is("Next General Election"));
		assertThat(bets.get(4).getName(), is("Conservatives"));
		assertThat(bets.get(4).getOdds().getNumerator(), is(2));
		assertThat(bets.get(4).getOdds().getDenominator(), is(1));
		assertThat(bets.get(4).getOdds().getDecimalOdds(), is(3.0));

		assertThat(bets.get(5).getId(), is(6));
		assertThat(bets.get(5).getEventName(), is("Next General Election"));
		assertThat(bets.get(5).getName(), is("Liberal Democrats"));
		assertThat(bets.get(5).getOdds().getNumerator(), is(17));
		assertThat(bets.get(5).getOdds().getDenominator(), is(1));
		assertThat(bets.get(5).getOdds().getDecimalOdds(), is(18.0));
        
		log.trace("-shouldGetResponseFromGetAvailable");
	}
	
	@Test
	public void shouldGetReceiptForValidBet() {

		log.trace("+shouldGetReceiptForValidBet");
		
		SkyBet bet = new SkyBet(1, "World Cup 2018", "England", 10, 1);
		SkyBetReceipt receipt = skyBetService.placeBet(bet, 100);
		assertThat(receipt, notNullValue());
		assertThat(receipt.getBet(), is(bet));
		assertThat(receipt.getStake(), is(100));
		assertThat(receipt.getTransactionId(), greaterThan(0));

		log.trace("-shouldGetReceiptForValidBet");
	}

	@Test
	public void shouldntGetReceiptForInvalidBetId() {

		log.trace("+shouldntGetReceiptForInvalidBetId");
		
		SkyBet bet = new SkyBet(999, "World Cup 2018", "England", 10, 1);
		SkyBetReceipt receipt = skyBetService.placeBet(bet, 100);
		assertThat(receipt, nullValue());

		log.trace("-shouldntGetReceiptForInvalidBetId");
	}

	@Test
	public void shouldntGetReceiptForInvalidOdds() {

		log.trace("+shouldntGetReceiptForInvalidOdds");
		
		SkyBet bet = new SkyBet(1, "World Cup 2018", "England", 100, 1);
		SkyBetReceipt receipt = skyBetService.placeBet(bet, 100);
		assertThat(receipt, nullValue());

		log.trace("-shouldntGetReceiptForInvalidOdds");
	}

	@Test
	public void shouldntGetReceiptForZeroStake() {

		log.trace("+shouldntGetReceiptForZeroStake");
		
		SkyBet bet = new SkyBet(1, "World Cup 2018", "England", 100, 1);
		SkyBetReceipt receipt = skyBetService.placeBet(bet, 0);
		assertThat(receipt, nullValue());

		log.trace("-shouldntGetReceiptForZeroStake");
	}

	@Test
	public void shouldntGetReceiptForNegativeStake() {

		log.trace("+shouldntGetReceiptForNegativeStake");
		
		SkyBet bet = new SkyBet(1, "World Cup 2018", "England", 100, 1);
		SkyBetReceipt receipt = skyBetService.placeBet(bet, -100);
		assertThat(receipt, nullValue());

		log.trace("-shouldntGetReceiptForNegativeStake");
	}
}
