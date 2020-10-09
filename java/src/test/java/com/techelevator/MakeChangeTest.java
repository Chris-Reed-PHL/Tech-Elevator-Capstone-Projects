package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MakeChangeTest {
	
	@Test
	public void one_dollar_equals_four_quarters() {
		MakeChange test = new MakeChange();
		String output = test.dispenseChange(1.00);
		assertEquals("Your change is: " + 0 + " nickel(s), " + 0 + " dime(s), and " + 4 + " quarter(s).", output);
	}
	
	@Test
	public void one_dollar_ten_cents_equals_four_quarters_one_dime() {
		MakeChange test = new MakeChange();
		String output = test.dispenseChange(1.10);
		assertEquals("Your change is: " + 0 + " nickel(s), " + 1 + " dime(s), and " + 4 + " quarter(s).", output);
	}
	
	@Test
	public void one_dollar_sixty_five_cents_equals_six_quarters_one_dime_one_nickel() {
		MakeChange test = new MakeChange();
		String output = test.dispenseChange(1.65);
		assertEquals("Your change is: " + 1 + " nickel(s), " + 1 + " dime(s), and " + 6 + " quarter(s).", output);
	}
	
	@Test
	public void fifteen_cents_equals_1_dime_1_nickel() {
		MakeChange test = new MakeChange();
		String output = test.dispenseChange(0.15);
		assertEquals("Your change is: " + 1 + " nickel(s), " + 1 + " dime(s), and " + 0 + " quarter(s).", output);
	}

}
