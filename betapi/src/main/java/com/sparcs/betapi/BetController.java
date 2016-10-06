package com.sparcs.betapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for the Betting API
 * 
 * @author Lee Newfeld
 */
@RestController
public class BetController {

	/**
	 * Get the list of available {@link Bet}s.
	 * @return
	 */
	@RequestMapping("/available")
	@ResponseBody()
	public List<Bet> getAvailable() {

		List<Bet> available = new ArrayList<>();
		
		available.add(new Bet(1, "World Cup 2018", "England", (double)10/1+1));
		available.add(new Bet(2, "World Cup 2018", "Brazil", (double)1/1+1));
		available.add(new Bet(3, "World Cup 2018", "Spain", (double)3/1+1));
		available.add(new Bet(4, "Next General Election", "Labour", (double)7/4+1));
		available.add(new Bet(5, "Next General Election", "Conservatives", (double)2/1+1));
		available.add(new Bet(6, "Next General Election", "Liberal Democrats", (double)17/1+1));
		
		return available;
	}
}
