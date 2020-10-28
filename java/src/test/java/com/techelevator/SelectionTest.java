package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class SelectionTest {
	
	@Test
	public void getSelectionName_should_return_name() {
		Item item = new Candy("Sweet Hearts", BigDecimal.valueOf(2.67));
		Selection selection = new Selection("A6", item);
		assertEquals("A6", selection.getSelectionName());
	}

}
