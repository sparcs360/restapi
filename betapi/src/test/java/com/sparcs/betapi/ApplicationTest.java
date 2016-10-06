package com.sparcs.betapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sparcs.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest extends BaseTest {

	private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);

	@Test
	public void contextLoads() {
		
		log.trace("+contextLoads");

		log.trace("-contextLoads");
	}
}