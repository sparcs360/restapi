package com.sparcs.bet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application
 * 
 * @author Lee Newfeld
 */
@SpringBootApplication
public class BetApplication {

	private static final Logger log = LoggerFactory.getLogger(BetApplication.class);

	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(BetApplication.class);
		
		log.trace("+run()");
		app.run(args);
		log.trace("-run()");
	}
}
