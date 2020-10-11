package com.techelevator;

import static org.junit.Assert.assertEquals;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
		assertEquals(BigDecimal.valueOf(3.05).setScale(2, RoundingMode.HALF_UP), testMap.get("A1").getItem().getPrice());
		assertEquals(BigDecimal.valueOf(1.50).setScale(2, RoundingMode.HALF_UP), testMap.get("B2").getItem().getPrice());
		assertEquals(BigDecimal.valueOf(1.50).setScale(2, RoundingMode.HALF_UP), testMap.get("C3").getItem().getPrice());
		assertEquals(BigDecimal.valueOf(0.75).setScale(2, RoundingMode.HALF_UP), testMap.get("D4").getItem().getPrice());
	}

}
