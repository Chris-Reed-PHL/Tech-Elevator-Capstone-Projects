package com.techelevator;

import java.util.Map;
import com.techelevator.FileReader;

public class MainInventory {

	private Map<String , Selection> inventoryMap;
	private FileReader inventoryReader =  new FileReader("vendingmachine.csv");
	
	public void stockInventory() {
		this.inventoryMap = inventoryReader.createInventoryMap();
	}

	public  Map<String , Selection> getInventoryMap() {
		return inventoryMap;
	}
}
