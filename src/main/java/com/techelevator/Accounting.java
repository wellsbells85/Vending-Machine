package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;

public class Accounting {

	// uses BigDecimal
	// inputs are Strings when used in correct format, outputs are BigDecimal
	// Objects, that
	// when change is given call the Audit class
	// currentMoneyProvided variable

// in case we want to format currency:	
//	public static String currencyFormat(BigDecimal n) {
//	    return NumberFormat.getCurrencyInstance().format(n);
//	}

	private BigDecimal currentMoney; // = new BigDecimal("5");
	private BigDecimal price;
	private Map<String, BigDecimal> priceMap = new LinkedHashMap<>();

	public String getCurrentMoney() {
		return "Current Money Provided: " + NumberFormat.getCurrencyInstance().format(currentMoney);
	}

	public void getPrice(String key) {
		this.price = priceMap.get(key);
	}

	public String feedMoney() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Would you like to add $1, $2, $5, or $10?");

		BigDecimal entry = new BigDecimal(userInput.nextLine());

		if (entry.compareTo(BigDecimal.ONE) != 0 && entry.compareTo(BigDecimal.valueOf(2)) != 0
				&& entry.compareTo(BigDecimal.valueOf(5)) != 0 && entry.compareTo(BigDecimal.TEN) != 0) {
			System.out.println("Please enter a valid selection choice.");
		} else
			currentMoney.add(entry);

		return getCurrentMoney();
	}

	public String makeChange() {
		BigDecimal current = currentMoney;
		BigDecimal nickel = BigDecimal.valueOf(.05);
		BigDecimal dime = BigDecimal.valueOf(.10);
		BigDecimal quarter = BigDecimal.valueOf(.25);

		return getCurrentMoney();
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
