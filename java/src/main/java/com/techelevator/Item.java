package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Item {

	private String name;
	private BigDecimal price;
	
	public Item(String name, BigDecimal price) { //constructor
		
		this.name = name;
		this.price = price;
		
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.HALF_UP);
	}
	
	
	public abstract String getSound(); //each product has its own method for getSound()
	
}
