package com.sparcs.betapi;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.springframework.http.MediaType;

import com.sparcs.BaseTest;

/**
 * {@link BetController} tests
 *  
 * @author Lee Newfeld
 */
public class BetControllerTest extends BaseTest {

	/**
	 * Should 
	 *  
	 * @throws Exception
	 */
	@Test
	public void shouldGetResponseFromGetAvailable() throws Exception {

        mvc.perform(get("/available"))
           .andDo(print())
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
}
