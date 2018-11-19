package com.demo.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchanageValue retrievedExchangeValue(@PathVariable String from, @PathVariable String to){
		ExchanageValue exchangeValue  = exchangeValueRepository.findByFromAndTo(from, to);
		//ExchanageValue exchangeValue = new ExchanageValue(1000L, from, to, new BigDecimal("65"));
		exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")) );
		return exchangeValue;
	}

}
