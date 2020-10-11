package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import com.techelevator.MakeChange;
import java.util.Scanner;
import com.techelevator.Selection;
public class Menu {

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private PrintWriter out;
	private Scanner in;
	private MakeChange makechange = new MakeChange();

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public String getPurchaseMenuOptionFeedMoney() {
		return PURCHASE_MENU_OPTION_FEED_MONEY;
	}

	public String getPurchaseMenuOptionSelectProduct() {
		return PURCHASE_MENU_OPTION_SELECT_PRODUCT;
	}

	public String getPurchaseMenuOptionFinishTransaction() {
		return PURCHASE_MENU_OPTION_FINISH_TRANSACTION;
	}

	public String[] getPurchaseMenuOptions() {
		return PURCHASE_MENU_OPTIONS;
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
		while(choice == null) {
			choice = getChoiceFromPurchaseInput(options);
		}
		return choice;
	}

	private Object getChoiceFromPurchaseInput(Map<String, Selection> options){
		Object choice = null;
		displayPurchaseOptions(options);
		String userInput = in.nextLine(); 
		while(choice == null) { 
			if(!options.containsKey(userInput)){
				System.out.println("You mad! Product does not exist.");
				choice = (String) getToPurchaseMenu();
			}else if(options.containsKey(userInput) && options.get(userInput).getItem().getStock() == 0){
				System.out.println("Sowi! Product is sold out.");
				choice = (String) getToPurchaseMenu();
			} else if (makechange.getMachineBalance().doubleValue() < options.get(userInput).getItem().getPrice().doubleValue()) {
				System.out.println("Oops, you're poor.");
				choice = (String) getToPurchaseMenu();
			} else if(options.containsKey(userInput) && options.get(userInput).getItem().getStock() > 0) {
				options.get(userInput).getItem().reduceStock();
				System.out.println(options.get(userInput).getItem().getStock());
				System.out.println("Here's your " + options.get(userInput).getItem().getName() + " for $" + options.get(userInput).getItem().getPrice());
				System.out.println(options.get(userInput).getItem().getSound());
				choice = userInput; 
			}
		}
		return choice;
	}

	public Object getToPurchaseMenu() {

		Object choice = null;
		while (choice == null) {
			displayPurchaseMenuOptions();
			choice = getChoiceFromPurchaseUserInput();
		}
		return choice;

	}

	private Object getChoiceFromPurchaseUserInput() {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= PURCHASE_MENU_OPTIONS.length) {
				choice = PURCHASE_MENU_OPTIONS[selectedOption - 1];//because zero based
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayPurchaseMenuOptions() {
		out.println();
		for (int i = 0; i < PURCHASE_MENU_OPTIONS.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + PURCHASE_MENU_OPTIONS[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

	private void displayPurchaseOptions(Map<String, Selection> options) {
		out.println();
		listPurchaseOptions(options);
		out.print("\nPlease choose an option >>> ");
		out.flush();

	}

	public void listPurchaseOptions(Map<String, Selection> options) {
		for(Map.Entry<String, Selection> option : options.entrySet()) {
			System.out.println(option.getKey() + " " + option.getValue().getItem().getName() + " $" + option.getValue().getItem().getPrice() + " Quantity Remaining: " + option.getValue().getItem().getStock());
		}
	}
}

