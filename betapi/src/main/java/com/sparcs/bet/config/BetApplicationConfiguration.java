package com.sparcs.bet.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Application configuration
 * 
 * @author Lee Newfeld
 */
@Configuration
public class BetApplicationConfiguration {
	
	/**
	 * @return A {@link RestTemplate}, used to consume the
	 * http://skybettechtestapi.herokuapp.com/ API.
	 */
	@Bean
	public RestOperations restTemplate(RestTemplateBuilder builder) {
		
		return builder.build();
	}
}
