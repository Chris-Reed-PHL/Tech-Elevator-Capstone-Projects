package com.techelevator.view;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.Selection;
public class Menu {

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private PrintWriter out;
	private Scanner in;



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

	public Object getChoiceFromPurchaseOptions(Map<String, Selection> options) throws FileNotFoundException {

		Object choice = null;
		while(choice == null) {
			choice = getChoiceFromPurchaseInput(options);
		}
		return choice;
	}

	private Object getChoiceFromPurchaseInput(Map<String, Selection> options) throws FileNotFoundException{
		Object choice = null;
		displayPurchaseOptions(options);
		String userInput = in.nextLine(); 
		while(choice == null) { 
			if(!options.containsKey(userInput)){
				System.out.println("-------------------------------------------------------");
				System.out.println("You mad! Product does not exist. Please choose another: ");
				System.out.println("-------------------------------------------------------");
				//choice = (String) getToPurchaseMenu(); NullPointerException
				break;
			}else if(options.containsKey(userInput) && options.get(userInput).getItem().getStock() == 0){
				System.out.println("----------------------------------------------------");
				System.out.println("Sowi! Product is sold out. Please choose another: ");
				System.out.println("----------------------------------------------------");
				//choice = (String) getToPurchaseMenu(); This led to a NullPointerException
				break;
			} else if(options.containsKey(userInput) && options.get(userInput).getItem().getStock() > 0) {
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
			if(option.getValue().getItem().getStock() == 0) {
				System.out.println(option.getKey() + " " + option.getValue().getItem().getName() + " $" + option.getValue().getItem().getPrice() +
						" Quantity Remaining: SOLD OUT");
			}else {
				System.out.println(option.getKey() + " " + option.getValue().getItem().getName() + " $" + option.getValue().getItem().getPrice() +
						" Quantity Remaining: " + option.getValue().getItem().getStock());
			}
		}
	}
}
