package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

	//when press 3, run default SalesReport to save it, then exit(0); will exit the program with no errors
	//displayProducts();  will collect the data from the VendingMachineItems or possibly VendoMatic800 Class
	//will also redirect user to the PurchaseMenu Class
	//does not allow user interaction, must run automatically

public class VendoMatic800 {

	public static void main(String[] args) {
		
		File reportName = new File("SalesReportMaster.txt"); 
		try{
			reportName.createNewFile(); //write the log file
			} catch (IOException e) { //			
		} 
		File logName = new File("Log.txt"); 
		try{
			logName.createNewFile(); //write the log file
			} catch (IOException e) { //			
		} 
			
		Scanner scanner = new Scanner(System.in);
		Accountant account = new Accountant();
		ProductSelector ps = new ProductSelector();
		VendingMachineItems vend = new VendingMachineItems();
		AuditWriter aw = new AuditWriter();
		Map<String, Integer> salesMap = new HashMap<>();
		
		vend.initializeVendingMachine();
		ps.initializeInventory();
		account.initializePrices();
		aw.salesMapMaker();
		aw.masterReportWriter();
		
		String userInput;
		boolean repeat = true;
		
		while(repeat) {
			System.out.println("\n" + account.displayCurrentMoney() + "\n");
			System.out.println("(1) Display Vending Machine Items");
			System.out.println("(2) Purchase");
			System.out.println("(3) Exit\n");
			userInput = scanner.nextLine();
			
			if(userInput.equals("1") ) {
				System.out.println(ps.displayAllVendingData() );
			} else if (userInput.equals("2") ) {
				boolean repeatAgain = true;
				while(repeatAgain) {
					System.out.println("\n" + account.displayCurrentMoney());
					System.out.println("\n(1) Feed Money");
					System.out.println("(2) Select Product");
					System.out.print("(3) Finish Transaction\n");
					userInput = scanner.nextLine();
					if(userInput.equals("1") ) {
						account.feedMoney();
					} else if(userInput.equals("2") ) {
						System.out.println("\n" + vend.displayProducts() );
						System.out.println("\nEnter Product Code: "); 
						System.out.println("\n" + account.displayCurrentMoney());
						String input = scanner.nextLine().toUpperCase();
						if (ps.validateSlot(input) == false) {
							System.out.println("Please make a valid selection.");
							continue;
						} else if (ps.getInventoryCount(input) == 0) {
							System.out.println("SOLD OUT");
						} else if ((ps.getInventoryCount(input) > 0) && (account.getCurrentMoney().compareTo(account.getPrice(input)) > 0)) {
							account.purchase(input);
							ps.adjustInventory(input);
							//aw.salesMapEditor()    need to pass product name!!!! in paranthesis
							System.out.print(vend.getProductData(input));
							System.out.println("  " + vend.getCategoryMessage(input));
						} 
					} else if(userInput.equals("3") ) {
						System.out.println(account.makeChange());
						repeatAgain = !repeatAgain;
					}
				}	
			} else if (userInput.equals("3") ) {
				System.out.println("Thank You! Have a nice Day.");
				System.exit(0); //exit the program regularly
			} else if (userInput.equals("4" )) {
				//hidden sales report
			} //end outer if-loop
		
		} //end while(repeat) loop
		
		
		//when program starts, tell Audit.java to create it's map based off the last Sales.txt
		//VendoMatic800 will call VendingMachineItems, will create Map, and Initialize Integer values to 5, only at start of program
		//can be in a method, or can be a simple initialization of the map
		//****if don't use a method now, then we need Setters & Getters to move this data around****
		//create dispenseProduct(get data from SelectProductMenu Class) method that simply removes 1 item from map. 			
	} //end main
	

	
} //end class
