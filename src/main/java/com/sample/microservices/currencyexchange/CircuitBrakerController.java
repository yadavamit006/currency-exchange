package com.sample.microservices.currencyexchange;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBrakerController {
	
	Logger logger = LoggerFactory.getLogger(CircuitBrakerController.class);
	

	@GetMapping("/sample-api")
	//@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
	//@CircuitBreaker(name = "sample-api", fallbackMethod = "hardcodedResponse")
	//@RateLimiter(name="default",fallbackMethod = "rateLimitingFallback")
	//@Bulkhead(name="sample-api")
	//10s => 10000 calls to the sample api
	public String sampleApi() {
		logger.info("Sample api call received");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", 
//					String.class);
//		return forEntity.getBody();
		return "sample-api";
	}

	public String rateLimitingFallback(Throwable d) {
		return "Too Many Requests - Retry After 1 Minute";
	}
	
	public String hardcodedResponse(Exception exception) {
		return "fallback-response";
	}

}
