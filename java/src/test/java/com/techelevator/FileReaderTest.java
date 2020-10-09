package com.techelevator;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import java.math.BigDecimal;

import org.junit.Test;

public class FileReaderTest {

	@Test
	public void name() {
		FileReader test = new FileReader("vendingmachine.csv");
		Map<String, Selection> testMap = test.createInventoryMap();
		assertEquals("Potato Crisps", testMap.get("A1").getItem().getName());
		assertEquals("Cowtales", testMap.get("B2").getItem().getName());
		assertEquals("Mountain Melter", testMap.get("C3").getItem().getName());
		assertEquals("Triplemint", testMap.get("D4").getItem().getName());
	}
	
	@Test
	public void price() {
		FileReader test = new FileReader("vendingmachine.csv");
		Map<String, Selection> testMap = test.createInventoryMap();
		assertEquals(BigDecimal.valueOf(3.05), testMap.get("A1").getItem().getPrice());
		assertEquals(BigDecimal.valueOf(1.50), testMap.get("B2").getItem().getPrice());
		assertEquals(BigDecimal.valueOf(1.50), testMap.get("C3").getItem().getPrice());
		assertEquals(BigDecimal.valueOf(0.75), testMap.get("D4").getItem().getPrice());
	}

}
