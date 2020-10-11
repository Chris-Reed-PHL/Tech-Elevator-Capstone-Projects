package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import org.junit.Test;

public class MainInventoryTest {
	
	@Test
	public void stock_inventory_yeilds_correct_name_price_stock_etc() {
		FileReader test = new FileReader("vendingmachine.csv");
		Map<String, Selection> testMap = test.createInventoryMap();
		BigDecimal expected = new BigDecimal(1.25).setScale(2, RoundingMode.HALF_UP);
		assertEquals("Cowtales", testMap.get("B2").getItem().getName());
		assertEquals(5, testMap.get("A4").getItem().getStock());
		assertEquals(expected, testMap.get("C1").getItem().getPrice());
	}

}
