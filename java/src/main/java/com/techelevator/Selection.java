package com.techelevator;
import com.techelevator.Item;

public class Selection { //this class stocks items to max every time

	private String name;
	private int stock;
	private Item item;
	private int dispenseAmount = 0;
	
	public Selection(String name, Item item) { //create a selection object
		this.name = name;
		this.stock = 5;
		this.item = item;
	}
	
	public void dispenseItem() { //dispense item if there is enough stock
		if (stock > 0) {
			stock--;
			dispenseAmount++;
		}
	}
	
	public String getSelectionName() {
		return name;
	}
	
	public int getStock() {
		return stock;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getDispenseAmount() {
		return dispenseAmount;
	}
	
	public void resetDispenseAmount() {
		dispenseAmount = 0;
	}
	
}
