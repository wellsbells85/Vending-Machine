package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

public class Console {
	
	private PrintWriter out;
	private Scanner in;

	public Console(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}
	
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMainMenu(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}
	
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
	
	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}
	
	private void displayMainMenu(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			if(i == 3) {
				out.println(options[i]);
			} else {
				int optionNum = i + 1;
				out.println(optionNum + ") " + options[i]);
			}
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
		
	public String selectProducts(String products) {
		out.println("Code       Name           Price");
		out.println("===============================");
		out.println(products);
		out.println();
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
		return in.nextLine();
	}
	
	public void displayInventoryProducts(String products) {
		out.println("Code       Name           Price  Quantity");
		out.println("==========================================");
		out.println(products);
		out.flush();
	}
	
	public String displayFeedMoney() {
		out.print("Would you like to add $1, $2, $5, or $10? ");
		out.flush();
		return in.nextLine();
	}
	
	public void displayFeedMoneyReturn(Map<String, String[]> input) {
		for(String key: input.keySet()) {
			out.print(key);
			out.println();
			out.flush();
		}		
	}
	
	public void displayChange(Map<String, String[]> input) {
		for(String key: input.keySet()) {
			out.print(key);
			out.println("\n");
			out.flush();
		}	 
	}
	
	public void displaySale(String products) {
		System.out.println();
		System.out.println(products);
		System.out.println();
	} 
	
	public void displayValidation() {
		System.out.println("");
		System.out.println("Invalid Selection");
		System.out.println("");
	}

	public void displayNoFunds() {
		System.out.println("");
		System.out.println("Insufficient Funds");
		System.out.println("");
	}
	
	public void displaySoldOut() {
		System.out.println("");
		System.out.println("SOLD OUT");
		System.out.println("");
	}
	
	public void message(String message) {
		System.out.println(message);
	}
	
	public void displayClosing() {
		System.out.println("Thank You, Have a Nice Day!");
	} 
	
	public void displayBlank() {
		System.out.println("");
	}
} //end class
