package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Item {
	
	public Candy(String name, BigDecimal price) { //constructor calls super constructor
		super(name, price);
	}
	
	@Override
	public String getSound() { //override sound method for candy sound
		return "Munch Munch, Yum!";

	}
}
