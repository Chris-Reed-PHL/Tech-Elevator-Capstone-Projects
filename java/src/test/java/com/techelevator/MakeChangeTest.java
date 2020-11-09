package com.techelevator;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
	
	@Test
	public void add_money_five_dollars() {
		MakeChange test = new MakeChange();
		BigDecimal expected = new BigDecimal(5).setScale(2, RoundingMode.HALF_UP);
		BigDecimal output = test.addMoney(5.00).setScale(2, RoundingMode.HALF_UP);
		assertEquals(expected, output);
	}
	
	@Test
	public void clear_balance_from_five() {
		MakeChange test = new MakeChange();
		BigDecimal output = test.clearMachineBalance(5.00);
		BigDecimal expected = new BigDecimal(0);
		assertEquals(expected, output);
	}
	
	@Test
	public void subtractCost_should_return_correct_balance() {
		MakeChange test = new MakeChange();
		test.addMoney(2);
		BigDecimal output = test.subtractCost(BigDecimal.valueOf(1));
		BigDecimal expected = new BigDecimal(1).setScale(2);
		assertEquals(expected, output);
	}

}
