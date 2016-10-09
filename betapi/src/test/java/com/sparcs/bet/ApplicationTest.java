package com.sparcs.bet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest extends BaseTest {

	@Test
	public void shouldLoadContext() {
		
		log.trace("+shouldLoadContext");

		log.trace("-shouldLoadContext");
	}
}
