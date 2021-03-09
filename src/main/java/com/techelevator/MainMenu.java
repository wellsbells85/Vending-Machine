package com.techelevator;

import java.math.BigDecimal;
import java.util.Map;

public class MainMenu {

	private static final String MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MENU_OPTION_PURCHASE_MENU = "Purchase:";
	private static final String MENU_OPTION_EXIT = "Exit";
	private static final String MENU_OPTION_HIDDEN = "";
	private static final String[] MAIN_MENU = { MENU_OPTION_DISPLAY_ITEMS, MENU_OPTION_PURCHASE_MENU, MENU_OPTION_EXIT , MENU_OPTION_HIDDEN };
	private static final String MENU_OPTION_FEED_MONEY = "Feed Money:";
	private static final String MENU_OPTION_SELECT_PRODUCT = "Select Product:";
	private static final String MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction:";
	private static final String[] PURCHASE_MENU = { MENU_OPTION_FEED_MONEY, MENU_OPTION_SELECT_PRODUCT, MENU_OPTION_FINISH_TRANSACTION };
	
	private Accountant account;
	private VendingMachineItem vend;
	private ConsoleService console;
	
	public MainMenu(ConsoleService console, VendingMachineItem vend, Accountant account) {
		this.account = account;
		this.vend = vend;
		this.console = console;
	}
	
	/**
	* Boots up the machine
	* Creates Vending Machine List with 5 Items each.
	* Initializes first Sales Report if it doesn't already exist.
	* Initializes first Log file if it doesn't already exist.
	*/
	public void initialize() {
		vend.initializeVendingMachineList();
		ReportWriter.masterReportWriter("", BigDecimal.ZERO);
		LogWriter.createLog();
	}

	/**
	* Creates main menu screen.
	*/
	public void runMainMenu() {
		while (true) {
			String choice = (String)console.getChoiceFromOptions(MAIN_MENU);
			if(MENU_OPTION_DISPLAY_ITEMS.equals(choice)) {
				console.displayInventoryProducts(vend.displayItemsAndInventory());
			} else if(MENU_OPTION_PURCHASE_MENU.equals(choice)) {
				runPurchaseMenu();
			} else if(MENU_OPTION_EXIT.equals(choice)){
				exitProgram();
			} else if(MENU_OPTION_HIDDEN.equals(choice)) {
				ReportWriter.timeStampedReport();
			}
		}
	} //end runMainMenu()
	
	/**
	* Creates purchase menu screen.
	*/
	public void runPurchaseMenu() {
		while (true) {
			console.message(account.displayCurrentMoney());
			String choice = (String)console.getChoiceFromOptions(PURCHASE_MENU);
			if(MENU_OPTION_FEED_MONEY.equals(choice)) {
				feedMoney();
			} else if(MENU_OPTION_SELECT_PRODUCT.equals(choice)) {
				makePurchase();
			} else if(MENU_OPTION_FINISH_TRANSACTION.equals(choice)) {
				finishTransaction();
			}
		}
	} //end runPurchaseMenu()
	
	
	/**
	* Controls input of money. Takes user input.
	* Sends information to Accountant. 
	* Displays errors and/or new balance.
	*/
	public void feedMoney() {
		String currency = console.displayFeedMoney();
		Map<String, String[]> result = account.feedMoney(currency);
		LogWriter.feedMoneyLog(result);
		console.displayFeedMoneyReturn(result);
	}
	
	/**
	* Controls sale of product. Takes user slot input.
	* Sends information to VendingMachineItem to validate location. 
	* If valid slot, sends info to Accountant to validate balance.
	* If both are valid it initiates a purchase, sends data to Log
	* and Report Writers to record permanently.
	* Displays errors if any arise.
	*/
	public void makePurchase() {
		String slot = console.selectProducts(vend.displayProducts()).toUpperCase();
		if (vend.validateSlot(slot) == false) {
			console.displayValidation();
		} else if (vend.getCount(slot) == 0) {
			console.displaySoldOut();
		} else if (vend.getCount(slot) > 0 && !account.checkFunds(vend.getPrice(slot))) {
			console.displayNoFunds();
		} else if (vend.getCount(slot) > 0 && account.checkFunds(vend.getPrice(slot))) {
			Map<String, String[]> result = account.purchase(slot, vend.getName(slot), vend.getPrice(slot));
			vend.setCount(slot);
			console.displaySale(vend.getSoldProduct(slot));
			LogWriter.purchaseLog(result);
			ReportWriter.masterReportWriter(vend.getName(slot), new BigDecimal(vend.getPrice(slot)));
		} //end if-else if-else if
	}
	
	/**
	* Makes change for user. Prints record in Log.
	*/
	public void finishTransaction() {
		Map<String, String[]> result = account.makeChange();
		console.displayChange(result);
		LogWriter.makeChangeLog(result);
		runMainMenu();
	}
		
	/**
	* Closes program.
	*/
	public void exitProgram() {
		console.displayClosing();
		System.exit(0);
	}
	
}
