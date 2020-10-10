package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import com.techelevator.MakeChange;
import java.util.Scanner;
import com.techelevator.FileReader;
import com.techelevator.Selection;
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

	public Object getChoiceFromPurchaseOptions(Map<String, Selection> options) {

		Object choice = null;
		displayPurchaseOptions(options);
		while(choice == null) {
			choice = getChoiceFromPurchaseInput(options);
		}
		return choice;
	}

	private Object getChoiceFromPurchaseInput(Map<String, Selection> options){
		Object choice = null;
		String userInput = in.nextLine();
		if(!options.containsKey(userInput)){
			System.out.println("You mad! Product does not exist.");
		}else if(options.containsKey(userInput) && options.get(userInput).getStock() == 0){
			System.out.println("Sowi! Product is sold out.");
		}else if (makechange.getMachineBalance().doubleValue() <= 0.00 || makechange.getMachineBalance().doubleValue() < options.get(userInput).getItem().getPrice().doubleValue()){
			System.out.println("Uh Oh! Balance too low."); 
			choice = null; //fix this!!
		}else if(options.containsKey(userInput) && options.get(userInput).getStock() > 0) {
			options.get(userInput).dispenseItem();
			System.out.println("Here's your " + options.get(userInput).getItem().getName() + " for $" + options.get(userInput).getItem().getPrice());
			System.out.println(options.get(userInput).getItem().getSound());
			choice = userInput;
		}
		return choice;
	}

	private void displayPurchaseOptions(Map<String, Selection> options) {
		out.println();
		listPurchaseOptions(options);
		out.print("\nPlease choose an option >>> ");
		out.flush();

	}

	public void listPurchaseOptions(Map<String, Selection> options) {
		FileReader listOfItems = new FileReader("vendingmachine.csv");
		for(Map.Entry<String, Selection> option : listOfItems.createInventoryMap().entrySet()) {
			System.out.println(option.getKey() + " " + option.getValue().getItem().getName() + " $" + option.getValue().getItem().getPrice() + " Quantity Remaining: " + option.getValue().getStock());
		}
	}
}

