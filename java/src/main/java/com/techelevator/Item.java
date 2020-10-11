package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Item {

	private String name;
	private BigDecimal price;
	private int stock = 5;
	
	public Item(String name, BigDecimal price) { //constructor
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}
	
	public int getStock() {
		return stock;
	}

	public BigDecimal getPrice() {
		return price.setScale(2, RoundingMode.HALF_UP);
	}
	
	public abstract String getSound();
	
	public void reduceStock() {
		if (stock > 0) {
			stock -= 1;
		}
	}
	
}
