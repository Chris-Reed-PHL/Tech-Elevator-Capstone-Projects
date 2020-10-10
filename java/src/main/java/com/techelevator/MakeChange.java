package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MakeChange {

	//instance variables
	private BigDecimal machineBalance = new BigDecimal(0);
	private double currentBalance = 0.00;
	private int nickels;
	private int dimes;
	private int quarters;

	public BigDecimal getMachineBalance() {
		return machineBalance;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public int getNickels() {
		return nickels;
	}
	public int getDimes() {
		return dimes;
	}
	public int getQuarters() {
		return quarters;
	}

	//method for adding money
	//take user input and add integer value of string to machine balance

	public BigDecimal addMoney(double moneyIn) {
		currentBalance += moneyIn;
		machineBalance = new BigDecimal(currentBalance).setScale(2, RoundingMode.HALF_UP);
		return machineBalance;
	}

	//method for making change
	//take remaining machine balance and return nickels, dimes, and quarters
	//penny math 

	public String dispenseChange(double currentBalance) {
		currentBalance *= 100;
		quarters = (int) (currentBalance / 25);
		currentBalance = (currentBalance % 25);
		dimes = (int) (currentBalance / 10);
		currentBalance = (currentBalance % 10);
		nickels = (int) (currentBalance / 5);
		return "Your change is: " + nickels + " nickel(s), " + dimes + " dime(s), and " + quarters + " quarter(s).";
	}

	public BigDecimal subtractCost(BigDecimal cost) {
		currentBalance -= cost.doubleValue();
		machineBalance = new BigDecimal(currentBalance).setScale(2, RoundingMode.HALF_UP);
		return machineBalance;
	}

	public BigDecimal clearMachineBalance(double currentBalance) {

		currentBalance = 0.00;
		machineBalance = new BigDecimal(currentBalance);
		return machineBalance;
	}
}
