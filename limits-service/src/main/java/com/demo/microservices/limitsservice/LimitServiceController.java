package com.demo.microservices.limitsservice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.demo.microservices.limitsservice.model.Confugiration;
import com.demo.microservices.limitsservice.model.LimitConfugiration;

@RestController
public class LimitServiceController {

	@Autowired
	private Confugiration confugiration;
	
	/*@Value("${limits-service.minimum}")
	private String minLimits;
	*/
	
	@GetMapping(path="/limits")
	public LimitConfugiration retrievedLimitConfiguration(){
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return new LimitConfugiration(confugiration.getMaximum(), confugiration.getMinimum());
	}
}
