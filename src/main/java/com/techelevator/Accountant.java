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
	private BigDecimal change = BigDecimal.ZERO;
	private Map<String, BigDecimal> priceMap = new LinkedHashMap<>();

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
		
		Scanner userInput = new Scanner(System.in);
		System.out.print("\nWould you like to add $1, $2, $5, or $10? ");
		String input = userInput.nextLine();
		BigDecimal entry = new BigDecimal(input);

		if (entry.compareTo(BigDecimal.ONE) != 0 && entry.compareTo(BigDecimal.valueOf(2)) != 0
				&& entry.compareTo(BigDecimal.valueOf(5)) != 0 && entry.compareTo(BigDecimal.TEN) != 0) {
			System.out.println("Please enter a valid selection choice.");
		} else {
			currentMoney = currentMoney.add(entry);
		} return currentMoney;
	}

	public boolean purchase(String slotSelection) {
		BigDecimal current = getCurrentMoney();
		BigDecimal selectionPrice = priceMap.get(slotSelection);
		
		if (priceMap.containsKey(slotSelection)) {
			if (current.compareTo(selectionPrice) < 0) {
				return false;
			} else {
				this.currentMoney = current.subtract(selectionPrice);
				return true;
			}
		} else return false;
		
	}
	public String makeChange() {
		BigDecimal current = getCurrentMoney();
		BigDecimal nickel = BigDecimal.valueOf(.05);
		BigDecimal dime = BigDecimal.valueOf(.1);
		BigDecimal quarter = BigDecimal.valueOf(.25);
		BigDecimal[] numberOfNickels;
		BigDecimal[] numberOfDimes;
		BigDecimal[] numberOfQuarters;
		BigDecimal remainder = BigDecimal.valueOf(0);
		String returnStatement = "";
		
		numberOfQuarters = (current.divideAndRemainder(quarter));
		remainder = numberOfQuarters[1];
		current = numberOfQuarters[1];
		
		if (remainder.compareTo(BigDecimal.ZERO) == 0) {
			returnStatement = "Change is " + numberOfQuarters[0] + " Quarters.";
		
		} else {
			numberOfDimes = current.divideAndRemainder(dime);
			remainder = numberOfDimes[1];
			current = numberOfDimes[1];
			if (remainder.compareTo(BigDecimal.ZERO) == 0) {
				returnStatement = "Change is " + numberOfQuarters[0] + " Quarters, and " + numberOfDimes[0] + " Dimes.";
			
			} else {
				numberOfNickels = current.divideAndRemainder(nickel);
				remainder = numberOfNickels[1];
				returnStatement = "Change is " + numberOfQuarters[0] + " Quarters, and " + numberOfDimes[0] + " Dimes, and " + numberOfNickels[0] + " Nickels."; 
			}
		}
//		writeAudit(); method call
		this.currentMoney = BigDecimal.ZERO;
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
			}

		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong");
			System.exit(1);
		}
	}

}
