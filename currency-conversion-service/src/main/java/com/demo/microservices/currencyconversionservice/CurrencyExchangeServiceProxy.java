package com.demo.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name="currency-exchange-service", url="localhost:8000")
//now we are using ribbon client so no need to hard code port here 
// @FeignClient(name="currency-exchange-service") now using zuul api gateway
 @FeignClient(name="netflix-zuul-api-gateway-server")
 @RibbonClient(name="currency-exchange-service")
//ribbon used to call multiple instance of service called dynamic balancing
//its calling currency-exchange-service on multiple port configure in properties file
public interface CurrencyExchangeServiceProxy {
	/*
	 * Feign Client Implementation
	 * It simplifiies the communication between microservices(Restful services)
	 */
	//@GetMapping("currency-exchange/from/{from}/to/{to}")
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean retrievedExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}
