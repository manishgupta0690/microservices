package com.demo.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableFeignClients("com.demo.microservices")
@RestController
public class CurrencyConversionController {
	
	//http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1000
	@Autowired
	Environment environment;
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity){
		Map<String,String> uriVariable = new HashMap<>();
		uriVariable.put("from", from);
		uriVariable.put("to", to);
	 ResponseEntity<CurrencyConversionBean> responseEntity =	
			 new RestTemplate().getForEntity(environment.getProperty("currency-exchange-service-url"), CurrencyConversionBean.class,uriVariable);
	
	 CurrencyConversionBean response = responseEntity.getBody();	
	 return new CurrencyConversionBean(
			 response.getId(), from, to, response.getConversionMultiple(), quantity, quantity.multiply(
					 response.getConversionMultiple()), response.getPort());
	}
	
	
	
	/*
	 * Using feign client to call other microservice
	 */
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,@PathVariable BigDecimal quantity){
	 CurrencyConversionBean response = proxy.retrievedExchangeValue(from, to);
	 return new CurrencyConversionBean(
			 response.getId(), from, to, response.getConversionMultiple(), quantity, quantity.multiply(
					 response.getConversionMultiple()), response.getPort());
	}

}
