package com.techelevator;
import com.techelevator.view.Menu;
import com.techelevator.MakeChange;
import com.techelevator.Selection;
import java.util.Map;
import java.util.Scanner;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private static final String FEED_OPTION_1 = "add $1";
	private static final String FEED_OPTION_2 = "add $2";
	private static final String FEED_OPTION_3 = "add $5";
	private static final String FEED_OPTION_4 = "add $10";
	private static final String[] FEED_MENU_OPTIONS = { FEED_OPTION_1, FEED_OPTION_2, FEED_OPTION_3, FEED_OPTION_4};

	private Menu menu;
	private MakeChange makechange = new MakeChange();


	public VendingMachineCLI(Menu menu, MakeChange makechange) {
		this.menu = menu;
		this.makechange = makechange;

	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				FileReader options = new FileReader("vendingmachine.csv");
				for(Map.Entry<String, Selection> option : options.createInventoryMap().entrySet()) {
					System.out.println(option.getKey() + " " + option.getValue().getItem().getName() + " $" + option.getValue().getItem().getPrice() + " Quantity Remaining:" + option.getValue().getStock());


				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// display purchase menu
				while(true) { //too nested??
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						//display feed menu
						choice = (String) menu.getChoiceFromOptions(FEED_MENU_OPTIONS);
						if (choice.equals(FEED_OPTION_1)) {
							//add $1 to customer balance
							makechange.addMoney(1.00);
						} else if (choice.equals(FEED_OPTION_2)) {
							//add $2 to customer balance
							makechange.addMoney(2.00);	
						} else if (choice.equals(FEED_OPTION_3)) {
							//add $5 to customer balance
							makechange.addMoney(5.00);
						} else if (choice.equals(FEED_OPTION_4)) {
							//add $10 to customer balance
							makechange.addMoney(10.00);
						}
						System.out.println("Current Money Provided: $" + makechange.getMachineBalance());
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						//display the map of available items
						FileReader options = new FileReader("vendingmachine.csv");
						for(Map.Entry<String, Selection> option : options.createInventoryMap().entrySet()) {
							System.out.println(option.getKey() + " " + option.getValue().getItem().getName() + " $" + option.getValue().getItem().getPrice() + " Quantity Remaining: " + option.getValue().getStock());

							while(true) {
								
							
							System.out.println("Please make your selection >>> ");
							
							Scanner userInput = new Scanner(System.in);
							 choice = userInput.nextLine();

							if(choice != option.getKey()){
								System.out.println("You mad! Product does not exist.");

							}else if(choice.equals(option.getKey()) && option.getValue().getStock() == 0){
								System.out.println("Sowi! Product is sold out.");
							}else if(choice.equals(option.getKey()) && option.getValue().getStock() > 0) {
								option.getValue().dispenseItem();
								makechange.subtractCost(option.getValue().getItem().getPrice());
								System.out.println("Here's your " + option.getValue().getItem().getName() + "it was $" + option.getValue().getItem().getPrice());
								System.out.println( option.getValue().getItem().getSound() +  "Your balance remaining is $" + makechange.getMachineBalance());


							}
							userInput.close();
						}
						}
						//if product doesn't exist or sold out, return to purchase menu
						//System.out.println("Error! Product does not exist.");
						//System.out.println("Error! Product is sold out.");
						//if valid, print item name, cost, new balance, and message
						//return to purchase menu
						
					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						//change dispenses, balance returns to zero
						System.out.println(makechange.dispenseChange(makechange.getCurrentBalance()));
						System.out.println("Current Money Provided: $" + makechange.getMachineBalance());
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank You, Come Again!");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		MakeChange makechange = new MakeChange();
		VendingMachineCLI cli = new VendingMachineCLI(menu, makechange);
		cli.run();
	}
}
