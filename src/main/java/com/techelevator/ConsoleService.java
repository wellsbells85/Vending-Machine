package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class ConsoleService {
	
	private PrintWriter out;
	private Scanner in;

	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}
	
	/**
	* Calls displayMenu to print out menus, then
	* compares user input to the list of menu options.
	* Allows the MainMenu to go to that user option.
	* 
	* @param options
	* @return Object menuOption
	*/
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenu(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}
	
	/**
	* Prints out the received menu options.
	* Prints out prompt for user to choose an option.
	*
	* @param options
	*/
	private void displayMenu(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			if(i == 3) {
				out.print(options[i]);
			} else {
				int optionNum = i + 1;
				out.println(optionNum + ") " + options[i]);
			}
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
	
	/**
	* Compares the options list received from MainMenu
	* to captured user input. Validates and returns the
	* selection.
	* 
	* @param options
	* @return Object menuOption
	*/
	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}
		
	/**
	* Prints out the list of products.
	* String is formatted properly in VendingMachineItem.java.
	* Prints out prompt to enter a product code and
	* accepts the user input.
	* 
	* @param products
	* @return String userInput
	*/
	public String selectProducts(String products) {
		out.println("Code       Name           Price");
		out.println("===============================");
		out.println(products);
		out.println("\n");
		out.print(System.lineSeparator() + "Please enter product code >>> ");
		out.flush();
		return in.nextLine();
	}
	
	/**
	* Displays feed money prompt and accepts user input.
	* 
	* @return String userInput
	*/
	public String displayFeedMoney() {
		out.print("Would you like to add $1, $2, $5, or $10? ");
		out.flush();
		return in.nextLine();
	}
	
	/**
	* Prints out errors is any exist while feeding money.
	* Map is created in Accountant.java.
	* The key is the message itself, the String[] array contains 
	* data used by the Report and Log Writers
	* 
	* @param input
	*/
	public void displayFeedMoneyReturn(Map<String, String[]> input) {
		for(String key: input.keySet()) {
			out.print(key);
			out.println();
			out.flush();
		}		
	}
	
	/**
	* Prints out correct change message.
	* Map is created in Accountant.java.
	* The key is the message itself, the String[] array contains 
	* data used by the Report and Log Writers
	* 
	* @param input
	*/
	public void displayChange(Map<String, String[]> input) {
		for(String key: input.keySet()) {
			out.print(key);
			out.println("\n");
			out.flush();
		}	 
	}
	
	/**
	* Displays updated Products List with the corresponding
	* inventory count. Displays SOLD OUT is count = 0.
	* String is formatted properly in VendingMachineItem.java.
	* 
	* @param products
	*/
	public void displayInventoryProducts(String products) {
		out.println("Code       Name           Price  Quantity");
		out.println("==========================================");
		out.println(products);
		out.flush();
	}
	
	/**
	* Prints out the list of products.
	* String is formatted properly in VendingMachineItem.java.
	* 
	* @param products
	*/
	public void displaySale(String products) {
		out.println();
		out.println(products);
		out.println();
		out.flush();
	} 
	
	/**
	* Print out any message received.
	* 
	* @param message
	*/
	public void message(String message) {
		out.println(message);
		out.flush();
	}
	
	/**
	* Print Invalid Selection error.
	*/
	public void displayValidation() {
		out.println("");
		out.println("Invalid Selection");
		out.println("");
		out.flush();
	}

	/**
	* Print Insufficient Funds error.
	*/
	public void displayNoFunds() {
		out.println("");
		out.println("Insufficient Funds");
		out.println("");
		out.flush();
	}
	
	/**
	* Print SOLD OUT error.
	*/
	public void displaySoldOut() {
		out.println("");
		out.println("SOLD OUT");
		out.println("");
		out.flush();
	}
	
	/**
	* Print out closing statement.
	*/
	public void displayClosing() {
		out.println("Thank You, Have a Nice Day!");
		out.flush();
	} 
	
} //end class
