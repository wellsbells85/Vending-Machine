package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainMenu {

	Console console = new Console();
	Accountant account = new Accountant();
	VendingMachineItem vend = new VendingMachineItem();
	AuditWriter aw = new AuditWriter();
	Scanner scanner = new Scanner(System.in);
	String userInput;
	boolean repeat = true;
	public void runMainMenu() {
		vend.initializeVendingMachineList();
		account.initializePrices();
		aw.getSalesMap();
		aw.getOldSales();
		aw.setNewSales(BigDecimal.ZERO);
		aw.masterReportWriter();
		while (repeat) {
			console.displayMainMenu(account.displayCurrentMoney());
			userInput = scanner.nextLine();
			if (userInput.equals("1")) {
				console.displayQuantityHeader();
				console.displayProducts(vend.displayItemsAndInventory());
			} else if (userInput.equals("2")) {
				boolean repeatAgain = true;
				while (repeatAgain) {
					console.displayMoneyMenu(account.displayCurrentMoney());
					userInput = scanner.nextLine();
					if (userInput.equals("1")) {
						console.displayFeedMoney();
						account.feedMoney();
						console.displayBlank();
					} else if (userInput.equals("2")) {
						console.displayBasicHeader();
						console.displayPurchaseMenu(vend.displayProducts(), account.displayCurrentMoney());
						String input = scanner.nextLine().toUpperCase();
						if (vend.validateSlot(input) == false) {
							console.displayValidation();
						} else if (vend.getCount(input) == 0) {
							console.displaySoldOut();
						} else if (vend.getCount(input) > 0 && account.getCurrentMoney().compareTo(account.getPrice(input)) > 0) {
							account.purchase(input);
							vend.setCount(input);
							console.displayProducts(vend.getSoldProduct(input));
						} else if (account.getCurrentMoney().compareTo(account.getPrice(input)) < 0) {
							console.displayFunds();
						}
					} else if (userInput.equals("3")) {
						System.out.println(account.makeChange());
						repeatAgain = !repeatAgain;
					}
				}
			} else if (userInput.equals("3")) {
				console.displayClosing();
				System.exit(0); // exit the program regularly
			} else if (userInput.equals("4")) {
				aw.dateStampedReportWriter(); // hidden sales report
			} // end outer if-loop
		} // end while(repeat) loop
	}
}
