package com.techelevator;

import java.math.BigDecimal;

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
		return price;
	}
	
	
	public abstract String getSound(); //each product has its own method for getSound()
	
}
