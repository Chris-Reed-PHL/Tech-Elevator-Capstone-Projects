package com.techelevator;
import com.techelevator.view.Menu;
import com.techelevator.MakeChange;
import com.techelevator.Selection;
import com.techelevator.Log;
import java.io.FileNotFoundException;
import java.util.Map;
import com.techelevator.MainInventory;


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
	private MainInventory inventory = new MainInventory();
	private Log logger = new Log();

	public VendingMachineCLI(Menu menu, MakeChange makechange, MainInventory inventory) {
		this.menu = menu;
		this.makechange = makechange;
		this.inventory = inventory;

	}

	public void run() throws FileNotFoundException {
		while (true) {
			System.out.println("-----------------------------------");
			System.out.println("~*~ WELCOME TO VENDING MACHINE ~*~");
			System.out.println("-----------------------------------");
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				for(Map.Entry<String, Selection> option : inventory.getInventoryMap().entrySet()) {
					System.out.println(option.getKey() + " " + option.getValue().getItem().getName() + " $" + option.getValue().getItem().getPrice() + " Quantity Remaining:" + option.getValue().getItem().getStock());
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
						System.out.println("-----------------------------------");
						System.out.println("Current Money Provided: $" + makechange.getMachineBalance());
						System.out.println("-----------------------------------");
						logger.salesLog("FEED MONEY: $" + makechange.getCurrentBalance() + " $" + makechange.getMachineBalance().doubleValue());
					} else if (choice.equals(menu.getPurchaseMenuOptionSelectProduct())) {
						choice = (String) menu.getChoiceFromPurchaseOptions(inventory.getInventoryMap());
						//Adding a clause for if user wants to Select a product after receiving an out of stock message
						if(inventory.getInventoryMap().get(choice).getItem().getStock() == 0) {
							break;
						}
						else if(makechange.getCurrentBalance() - inventory.getInventoryMap().get(choice).getItem().getPrice().doubleValue() < 0)
						{
							System.out.println("Opps, you poor!");
						}else {
							System.out.println("---------------------------------------");
							System.out.println("Here's your " + inventory.getInventoryMap().get(choice).getItem().getName() + " for $" +
									inventory.getInventoryMap().get(choice).getItem().getPrice());
							System.out.println(inventory.getInventoryMap().get(choice).getItem().getSound());
							inventory.getInventoryMap().get(choice).getItem().reduceStock();

							System.out.println("Your remaining balance is $" + makechange.subtractCost(inventory.getInventoryMap().get(choice).getItem().getPrice()));
							logger.salesLog(inventory.getInventoryMap().get(choice).getItem().getName().toString()+ " " + inventory.getInventoryMap().get(choice).getSelectionName() +
									" $" + inventory.getInventoryMap().get(choice).getItem().getPrice().doubleValue() + " $" + makechange.getMachineBalance().doubleValue());
							System.out.println("---------------------------------------");
							}
					} else if (choice.equals(menu.getPurchaseMenuOptionFinishTransaction())) {
						//change dispenses, balance returns to zero
						System.out.println("---------------------------------------------------------");
						System.out.println(makechange.dispenseChange(makechange.getCurrentBalance()));
						System.out.println("Current Money Provided: $" + makechange.clearMachineBalance(makechange.getCurrentBalance()));
						System.out.println("---------------------------------------------------------");
						logger.salesLog("GIVE CHANGE: $" +  makechange.getCurrentBalance() + " $" + makechange.clearMachineBalance(makechange.getCurrentBalance()).doubleValue());
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("------------------------");
				System.out.println("Thank You, Come Again!");
				System.out.println("------------------------");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		MakeChange makechange = new MakeChange();
		MainInventory inventory = new MainInventory();
		VendingMachineCLI cli = new VendingMachineCLI(menu, makechange, inventory);
		inventory.stockInventory();
		cli.run();
	}
}
