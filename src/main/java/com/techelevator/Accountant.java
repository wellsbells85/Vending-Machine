package com.techelevator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class Accountant {
	
	private BigDecimal currentMoney = BigDecimal.ZERO;

	public BigDecimal getCurrentMoney() {
		return currentMoney;
	}
	
	/**
	* Creates formatted string of the current balance.
	* 
	* @return String currentMoneyDisplay
	*/
	public String displayCurrentMoney() {
		BigDecimal current = getCurrentMoney();
		String currentMoneyDisplay = "Current Money Provided:"
				+ String.format("%1$8s", NumberFormat.getCurrencyInstance().format(current));
		return currentMoneyDisplay;
	}
	
	/**
	* Checks if the user has enough funds for a purchase.
	* 
	* @param selectionPrice
	* @return boolean
	*/
	public boolean checkFunds(String selectionPrice) {
		BigDecimal price = new BigDecimal(selectionPrice);
		if(getCurrentMoney().compareTo(price) < 0) {
			return false;
		} return true;
	}
	
	/**
	* Validates if a user put in a valid amount of money.
	* Uses the String key as error messages. If valid the key
	* is " ". Invalid inputs return the original balances, while
	* valid inputs return an updated starting and current balance.
	* 
	* @param input
	* @return Map<String, String[]> logMap
	*/
	public Map<String, String[]> feedMoney(String input) {
		Map<String, String[]> logMap= new HashMap<>();
		String[] money = new String[2];
		String result;
		BigDecimal startingMoney = getCurrentMoney();
		if (input == null || input.equals("")) {
			result = "\nPlease Retry Bill Entry\n";
			logMap.put(result, money);
			return logMap;
		} else {
			try {
				BigDecimal entry = new BigDecimal(input);
				if (entry.compareTo(BigDecimal.ONE) != 0 && entry.compareTo(BigDecimal.valueOf(2)) != 0
						&& entry.compareTo(BigDecimal.valueOf(5)) != 0 && entry.compareTo(BigDecimal.TEN) != 0) {
					result = "\nPlease Insert Valid Bill\n";
					logMap.put(result, money);
					return logMap;
				} else {
					currentMoney = getCurrentMoney().add(entry);
					money[0] = startingMoney.toString();
					money[1] = currentMoney.toString();
					result = " ";
					logMap.put(result, money);
					return logMap;
				}
			} catch (NumberFormatException e) {
				result = "\nPlease Retry Bill Entry\n";
				logMap.put(result, money);
				return logMap;
			}
		}
	} // end method

	/**
	* Accepts the data necessary to create a purchase String[] to be 
	* sent to the Report Writer. Updates the current balance. Stores 
	* all relevant data, and returns to MainMenu to be passed along.
	* 
	* @param slot, name, selectionPrice
	* @return Map<String, String[]> logMap
	*/
	public Map<String, String[]> purchase(String slot, String name, String selectionPrice) {
		Map<String, String[]> logMap = new HashMap<>();
		String[] data = new String[4];
		String result;
		String startingMoney = getCurrentMoney().toString();
		BigDecimal price = new BigDecimal(selectionPrice);
		currentMoney = getCurrentMoney().subtract(price);
		result = " ";
		data[0] = name;
		data[1] = slot;
		data[2] = startingMoney;
		data[3] = currentMoney.toString();
		logMap.put(result, data);
		//purchaseLog(slot, name, price, startingMoney, currentMoney);
		return logMap;
	}// end purchase()

	/**
	* Creates a string for the correct amount of change given.
	* Stores that as the key in a map. The String[] contains the
	* starting and ending(always == 0) balances. This is returned
	* to MainMenu to be passed on to ReportWriter.
	* 
	* @return Map<String, String[]> logMap
	*/
	public Map<String, String[]> makeChange() {
		Map<String, String[]> logMap= new HashMap<>();
		String[] money = new String[2];
		String result;
		BigDecimal startingMoney = getCurrentMoney();
		BigDecimal currentFromBig = getCurrentMoney().multiply(BigDecimal.valueOf(100));
		int current = currentFromBig.intValueExact();
		int dime = 10;
		int quarter = 25;
		int remainder = 0;
		int numberOfQuarters = 0;
		int numberOfDimes = 0;

		if (current == 0) {
			result = "No Change Necessary";	
		} else {
			remainder = current % quarter;
			numberOfQuarters = current / quarter;
			current = current - numberOfQuarters * quarter;
			if (remainder == 0) {
				if (numberOfQuarters == 1) {
					result = "Change is 1 Quarter";
				} else {
					result = "Change is " + numberOfQuarters + " Quarters";
				} // end all possible quarter only return statements
			} else {
				remainder = current % dime;
				numberOfDimes = current / dime;
				current = current - numberOfDimes * dime;
				if (remainder == 0) {
					if (numberOfQuarters == 0 && numberOfDimes == 1) {
						result = "Change is 1 Dime";
					} else if (numberOfQuarters == 0 && numberOfDimes == 2) {
						result = "Change is 2 Dimes";
					} else if (numberOfQuarters == 1 && numberOfDimes == 1) {
						result = "Change is 1 Quarter and 1 Dime";
					} else if (numberOfQuarters == 1 && numberOfDimes == 2) {
						result = "Change is 1 Quarter and 2 Dimes";
					} else if (numberOfQuarters > 1 && numberOfDimes == 1) {
						result = "Change is " + numberOfQuarters + " Quarters and 1 Dime";
					} else {
						result = "Change is " + numberOfQuarters + " Quarters and 2 Dimes";
					}  // end all possible quarter and dime return situations
				} else {
					if (numberOfQuarters == 0 && numberOfDimes == 0) {
						result = "Change is 1 Nickel";
					} else if (numberOfQuarters == 1 && numberOfDimes == 0) {
						result = "Change is 1 Quarter and 1 Nickel";
					} else if (numberOfQuarters > 1 && numberOfDimes == 0) {
						result = "Change is " + numberOfQuarters + " Quarters and 1 Nickel";
					} else if (numberOfQuarters == 0 && numberOfDimes == 1) {
						result = "Change is 1 Dime and 1 Nickel";
					} else if (numberOfQuarters == 1 && numberOfDimes == 1) {
						result = "Change is 1 Quarter, 1 Dime, and 1 Nickel";
					} else {
						result = "Change is " + numberOfQuarters + " Quarters, 1 Dime, and 1 Nickel";
					} 
				} // end third if-else for nickels
			} // end second if-else for dimes
		} // end first if-else for quarters
		this.currentMoney = BigDecimal.ZERO;
		money[0] = startingMoney.toString();
		money[1] = currentMoney.toString();
		logMap.put(result, money);
		return logMap;
	}
} // end class
