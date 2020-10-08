package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendoInventory {
	
	private  String path = "vendingmachine.csv";
	
	public VendoInventory(String path) {
		
		this.path = path;
	}
	
	public  List<String> createInventoryList() {
		List<String> lines = new ArrayList<String>();
		File fileToStockFrom = new File(path);
		
		try(Scanner readFile = new Scanner(fileToStockFrom)) {
			while(readFile.hasNextLine()) {
				lines.add(readFile.nextLine());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: " + e);
		}	
		return lines;
	}
	
	//public List<String [] > createInventoryArray(){
//		
//		List<String []> inventoryList = new ArrayList<String []>();
//		
//			for(String line : createInventoryList()){
//				inventoryList.add(line.split("\\|"));
//			}
//		
//		return inventoryList;
//		
//		
//	}
	
	public Map<String, Selection> getInventoryMap(){
		List<String> lines = createInventoryList();
		
		
		return createInventoryMap(lines);
		
		
	}
	
	public Map<String, Selection> createInventoryMap(List<String> lines) {
		Map<String, Selection> inventoryMap = new HashMap<String, Selection>();
		
		for (String line : lines) {
			String[] itemInfo = line.split("\\|");
			
			if(itemInfo[3].equals("Candy")) {
				inventoryMap.put(itemInfo[0], new Selection(itemInfo[0], new Candy(itemInfo[1], BigDecimal.valueOf(Double.valueOf(itemInfo[2])))));
			}
			
			if(itemInfo[3].equals("Chip")) {
				inventoryMap.put(itemInfo[0], new Selection(itemInfo[0], new Chip(itemInfo[1], BigDecimal.valueOf(Double.valueOf(itemInfo[2])))));
			}
			
			if(itemInfo[3].equals("Drink")) {
				inventoryMap.put(itemInfo[0], new Selection(itemInfo[0], new Drink(itemInfo[1], BigDecimal.valueOf(Double.valueOf(itemInfo[2])))));
			}
			
			if(itemInfo[3].equals("Gum")) {
				inventoryMap.put(itemInfo[0], new Selection(itemInfo[0], new Gum(itemInfo[1], BigDecimal.valueOf(Double.valueOf(itemInfo[2])))));
			}
			
			}
		
		return inventoryMap;	
	}
	
}
