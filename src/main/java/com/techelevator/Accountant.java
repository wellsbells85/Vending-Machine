package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;

public class Accountant {

	// uses BigDecimal
	// inputs are Strings when used in correct format, outputs are BigDecimal
	// Objects, that
	// when change is given call the Audit class
	// currentMoneyProvided variable
	private BigDecimal currentMoney = BigDecimal.ZERO;
	private BigDecimal price = BigDecimal.ZERO;
	private Map<String, BigDecimal> priceMap = new LinkedHashMap<>();
	private Map<String , String> nameMap = new LinkedHashMap<>();
	private AuditWriter aw = new AuditWriter();

	public BigDecimal getCurrentMoney() {
		return currentMoney;
	}

	public BigDecimal getPrice(String key) {
		this.price = priceMap.get(key);
		return price;
	}

	public String displayCurrentMoney() {
		BigDecimal current = getCurrentMoney();
		String currentMoneyDisplay = "Current Money Provided: " + NumberFormat.getCurrencyInstance().format(current);
		return currentMoneyDisplay;
	}

	public BigDecimal feedMoney() {
		BigDecimal startingMoney = getCurrentMoney();

		Scanner userInput = new Scanner(System.in);
		System.out.print("\nWould you like to add $1, $2, $5, or $10? ");
		String input = userInput.nextLine();
		
		
		if (input == null || input.equals("")) {
			return currentMoney;
		} else {
			try { 
				BigDecimal entry = new BigDecimal(input);
				if (entry.compareTo(BigDecimal.ONE) != 0 && entry.compareTo(BigDecimal.valueOf(2)) != 0
					&& entry.compareTo(BigDecimal.valueOf(5)) != 0 && entry.compareTo(BigDecimal.TEN) != 0) {
				System.out.println("\nPlease enter a valid selection choice.");
				} else {
					currentMoney = currentMoney.add(entry);
				}	
			} catch(NumberFormatException e) {
				System.out.println("\nPlease enter a valid selection choice.");
			}
		}
		String feedMoney = String.format("%1$21s", "FEED MONEY:"); 
		aw.logWriter(feedMoney + String.format("%1$7s", NumberFormat.getCurrencyInstance().format(startingMoney)) 
			+ String.format("%1$7s", NumberFormat.getCurrencyInstance().format(currentMoney)));
		return currentMoney;
	} //end method

	public boolean purchase(String slotSelection) {
		BigDecimal startingMoney = getCurrentMoney();
		BigDecimal current = getCurrentMoney();
		BigDecimal selectionPrice = priceMap.get(slotSelection);

		if (priceMap.containsKey(slotSelection)) {
			if (current.compareTo(selectionPrice) < 0) {
				return false;
			} else {
				this.currentMoney = current.subtract(selectionPrice);
				String name = nameMap.get(slotSelection);
				aw.getOldSales();
				aw.setNewSales(selectionPrice);
				aw.salesMapEditor(name);
				aw.logWriter( String.format("%1$18s" , name) + " " + slotSelection  
					+ String.format("%1$7s", NumberFormat.getCurrencyInstance().format(startingMoney)) 
					+ String.format("%1$7s", NumberFormat.getCurrencyInstance().format(currentMoney)));
				return true;
			}
		} else return false;
	} //end method

	public String makeChange() {
		BigDecimal startingMoney = getCurrentMoney();
		String returnStatement = "";
		BigDecimal currentFromBig = getCurrentMoney().multiply(BigDecimal.valueOf(100));
		int current = currentFromBig.intValueExact();
		int nickel = 5;
		int dime = 10;
		int quarter = 25;
		int remainder = 0;
		int numberOfQuarters = 0;
		int numberOfDimes = 0;
		int numberOfNickels = 0;

		if (current == 0) {
			return "No change necessary.";
		} else {
			remainder = current % quarter;
			numberOfQuarters = current / quarter;
			current = current - numberOfQuarters * quarter;
			if (remainder == 0) {
				returnStatement = "Change is " + current + " Quarters.";
			} else {
				remainder = current % dime;
				numberOfDimes = current / dime;
				current = current - numberOfDimes * dime;
				if (remainder == 0) {
					returnStatement = "Change is " + numberOfQuarters + " Quarters, and " + numberOfDimes + " Dimes.";
				} else {
					numberOfNickels = current / nickel;
					returnStatement = "Change is " + numberOfQuarters + " Quarters, and " + numberOfDimes
					+ " Dimes, and " + numberOfNickels + " Nickels.";
				} //end third if-else for nickels
			} //end second if-else for dimes
		}//end first if-else for quarters
		this.currentMoney = BigDecimal.ZERO;
		String giveChange = String.format("%1$21s", "GIVE CHANGE:");
		aw.logWriter(giveChange + String.format("%1$7s", NumberFormat.getCurrencyInstance().format(startingMoney))
			+ String.format("%1$7s", NumberFormat.getCurrencyInstance().format(currentMoney)));
		return returnStatement;
	}
	
	public void initializePrices() {

		File vendFile = new File("vendingmachine.csv");
		String[] data;

		try (Scanner scanner = new Scanner(vendFile)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();

				data = line.split("\\|");

				BigDecimal price = new BigDecimal(data[2]);

				String key = data[0];
				priceMap.put(key, price);
				nameMap.put(key, data[1]);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong");
			System.exit(1);
		} //end try-catch
	} //end method
} //end class
