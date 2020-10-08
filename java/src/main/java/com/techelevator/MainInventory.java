package com.techelevator;
import java.util.Map;

import com.techelevator.VendoInventory;


public class MainInventory {

	private Map<String , Selection> inventoryMap;
	private VendoInventory inventoryReader =  new VendoInventory("vendingmachine.csv");
	
	public void stockInventory() {
		
		this.inventoryMap = inventoryReader.getInventoryMap();
		
	}

	public  Map<String , Selection> getInventoryMap() {
		return inventoryMap;
	}
}
