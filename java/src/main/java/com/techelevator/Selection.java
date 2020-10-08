package com.techelevator;
import com.techelevator.Item;

public class Selection {

	private String name;
	private int stock;
	private Item item;
	private int dispenseAmount = 0;
	
	public Selection(String name, Item item) {
		this.name = name;
		this.stock = 5;
		this.item = item;
	}
	
	public void dispenseItem() {
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
