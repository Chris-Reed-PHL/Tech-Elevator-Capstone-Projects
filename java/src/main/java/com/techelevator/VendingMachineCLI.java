package com.techelevator;
import com.techelevator.view.Menu;
import com.techelevator.MakeChange;
import com.techelevator.Selection;
import java.util.Map;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

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
					choice = (String) menu.getToPurchaseMenu();
					if (choice.equals(menu.getPurchaseMenuOptionFeedMoney())) {
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
					} else if (choice.equals(menu.getPurchaseMenuOptionSelectProduct())) {
						FileReader options = new FileReader("vendingmachine.csv");
						choice = (String) menu.getChoiceFromPurchaseOptions(options.createInventoryMap());
						System.out.println("Your remaining balance is $" + makechange.subtractCost(options.createInventoryMap().get(choice).getItem().getPrice()));
					} else if (choice.equals(menu.getPurchaseMenuOptionFinishTransaction())) {
						//change dispenses, balance returns to zero
						System.out.println(makechange.dispenseChange(makechange.getCurrentBalance()));
						System.out.println("Current Money Provided: $" + makechange.clearMachineBalance(makechange.getCurrentBalance()));
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
