package com.sample.microservices.currencyexchange.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.currencyexchange.bean.CurrencyExchange;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment env;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retreiveCurrencyExchange(@PathVariable String from,@PathVariable String to) {
		CurrencyExchange currencyExchange = new CurrencyExchange(1000l,from,to,BigDecimal.valueOf(64.2));
		currencyExchange.setEnvironment(env.getProperty("local.server.port"));
		return currencyExchange;
	}
}
