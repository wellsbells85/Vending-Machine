package com.techelevator;

import java.util.Scanner;

	//when press 3, run default SalesReport to save it, then exit(0); will exit the program with no errors
	//displayProducts();  will collect the data from the VendingMachineItems or possibly VendoMatic800 Class
	//will also redirect user to the PurchaseMenu Class
	//does not allow user interaction, must run automatically

public class VendoMatic800 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String userPromptInput;
		
		VendingMachineItems vend = new VendingMachineItems();
		vend.initializeVendingMachine();
		
		System.out.println("(1) Display Vending Machine Items");
		System.out.println("(2) Purchase");
		System.out.println("(3) Exit");
		
		userPromptInput = scanner.nextLine();
		
		if(userPromptInput.equals("1") ) {
			System.out.println(vend.displayProducts());
		} else if (userPromptInput.equals("2") ) {
			
		} else if (userPromptInput.equals("3") ) {
			//call Sales Report
			
			System.exit(0); //exit the program regularly
		}
		
		
		
		
		
		//when program starts, tell Audit.java to create it's map based off the last Sales.txt
		//VendoMatic800 will call VendingMachineItems, will create Map, and Initialize Integer values to 5, only at start of program
		//can be in a method, or can be a simple initialization of the map
		//****if don't use a method now, then we need Setters & Getters to move this data around****
		//create dispenseProduct(get data from SelectProductMenu Class) method that simply removes 1 item from map. 
		
		
		
		
	}

}
