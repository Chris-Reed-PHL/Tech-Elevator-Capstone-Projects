package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class ItemTest {
	
	@Test
	public void getSound_should_return_correct_sound() {
		Candy candy = new Candy("Crunch Bar", BigDecimal.valueOf(2.50));
		Gum gum = new Gum("Juicy Froot", BigDecimal.valueOf(.99));
		Drink drink = new Drink("Pupsi", BigDecimal.valueOf(2.99));
		Chip chip = new Chip("Leis", BigDecimal.valueOf(4.00));
		assertEquals("Munch Munch, Yum!", candy.getSound());
		assertEquals("Chew Chew, Yum!", gum.getSound());
		assertEquals("Glug Glug, Yum!", drink.getSound());
		assertEquals("Crunch Crunch, Yum!", chip.getSound());
	}
	
	@Test
	public void reduceStock_should_change_stock_from_5_to_4_etc() {
		Item item = new Candy("Soda Tabs", BigDecimal.valueOf(1.89));
		item.reduceStock();
		assertEquals(4, item.getStock());
		item.reduceStock();
		assertEquals(3, item.getStock());
	}

}
