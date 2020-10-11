package com.techelevator;
import com.techelevator.Item;

public class Selection { 

	private String name;
	private Item item;

	
	public Selection(String name, Item item) { 
		this.name = name;
		this.item = item;
	}
	
	public String getSelectionName() {
		return name;
	}
	
	public Item getItem() {
		return item;
	}
	
}
