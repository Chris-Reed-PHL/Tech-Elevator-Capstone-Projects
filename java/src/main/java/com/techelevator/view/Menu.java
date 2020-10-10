package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.FileReader;
import com.techelevator.Selection;
import com.techelevator.MakeChange;
public class Menu {

	private PrintWriter out;
	private Scanner in;
	private MakeChange makechange = new MakeChange();
	
	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];//because zero based
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}
	
	

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	
	public void selectProduct() {
		FileReader reader = new FileReader("vendingmachine.csv");
		Map.Entry<String, Selection> options =  reader.createInventoryMap();//why can't we access our map?
		System.out.println("Please make your selection >>> ");
		String userSelection = in.nextLine();
		
		if(userSelection != options.getKey()){
			System.out.println("You mad! Product does not exist.");

		}else if(userSelection.equals(options.getKey()) && options.getValue().getStock() == 0){
			System.out.println("Sowi! Product is sold out.");
		}else if(userSelection.equals(options.getKey()) && options.getValue().getStock() > 0) {
			options.getValue().dispenseItem();
			makechange.subtractCost(options.getValue().getItem().getPrice());
			System.out.println("Here's your " + options.getValue().getItem().getName() + "it was $" + options.getValue().getItem().getPrice());
			System.out.println( options.getValue().getItem().getSound() +  "Your balance remaining is $" + makechange.getMachineBalance());


		}
	}
	
//	public Object getChoiceFromPurchaseOptions(Object[] options) {
//		
//		Object choice = null;
//		while (choice == null) 
//			displayPurchaseOptions(options);
//			choice = getChoiceFromPurchaseInput(options);
//		
//		return choice;
//	}
//	
//	private Object getChoiceFromPurchaseInput(Object [] options){
//		
//		Object choice = null;
//		String userInput = in.nextLine();
//		try {
//			int selectedOption = Integer.valueOf(userInput);
//			if (selectedOption > 0 && selectedOption <= options.length) {
//				choice = options[selectedOption - 1];//because zero based
//			}
//		} catch (NumberFormatException e) {
//			// eat the exception, an error message will be displayed below since choice will be null
//		}
//		if (choice == null) {
//			out.println("\n*** " + userInput + " is not a valid option ***\n");
//		}
//		return choice;
//	}
//	
//	private void displayPurchaseOptions(Object[] options) {
//		out.println();
//		for (int i = 0; i < options.length; i++) {
//			int optionNum = i + 1;
//			out.println(optionNum + ") " + options[i]);
//		}
//		out.print("\nPlease choose an option >>> ");
//		out.flush();
//		
//	}
}

	