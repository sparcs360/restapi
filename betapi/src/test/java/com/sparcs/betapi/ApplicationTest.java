package com.sparcs.betapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sparcs.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest extends BaseTest {

	@Test
	public void contextLoads() {
		
		log.trace("+contextLoads");

		log.trace("-contextLoads");
	}
}
