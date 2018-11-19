package com.demo.microservices.limitsservice.model;

public class LimitConfugiration {

	private Integer maximum;
	private Integer minimum;
	
	public LimitConfugiration(Integer maximum, Integer minimum) {
		super();
		this.maximum = maximum;
		this.minimum = minimum;
	}
	
	public LimitConfugiration(){}

	public Integer getMaximum() {
		return maximum;
	}

	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}

	public Integer getMinimum() {
		return minimum;
	}

	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}
	
	
	
	
}
