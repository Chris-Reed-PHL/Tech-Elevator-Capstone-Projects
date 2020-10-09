package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileReader {
	
private  String path = "vendingmachine.csv";
	
	public FileReader(String path) {
		this.path = path;
	}
	
	public Map<String, Selection> createInventoryMap(){
		List<String> lines = getMapInfo();
		return populateInventoryMap(lines);
	}
	
	public  List<String> getMapInfo() {
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
	
	public Map<String, Selection> populateInventoryMap(List<String> lines) {
		Map<String, Selection> inventoryMap = new HashMap<String, Selection>();
		
		for (String line : lines) {
			
			if(line == null) {
				continue;
			}
			
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
