package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductSelector {
	
	private String slotLocation;
	private String productName;
	private String price;
	private String category;
	private int inventoryCount;
	private boolean validate;
	private Map<String , Integer> inventoryMap = new LinkedHashMap<>();

	public ProductSelector() {
		
	}
	
	public int getInventoryCount(String input) {
		return inventoryMap.get(input);
	}
	
	public boolean validateSlot(String input) {
		boolean validate = false;
		for(String key : inventoryMap.keySet()) {
			if(key.equalsIgnoreCase(input)) {
				validate = true;
			} 
		} return validate;
	}
	
	//method to decrement inventory correctly
	public int adjustInventory(String input) {
		for(String key : inventoryMap.keySet()) {
			if(key.equalsIgnoreCase(input) && getInventoryCount(input) > 0) {
				inventoryCount--;
			} 
		} return inventoryCount;
	} //end method	
	
	//method to create initialize an inventory map of slot location with 5 items each
	public void initializeInventory() {
		File vendFile = new File("vendingmachine.csv");
		String[] data;
		try (Scanner scanner = new Scanner(vendFile)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				data = line.split("\\|");
				String key = data[0]; //pull first item from string array as the slot location = key
				inventoryMap.put(key, 5); // initialize an array with 5 items each
			}
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong");
			System.exit(1);
		}
	} //end method
	
} //end class


	// must display available items (call MainMenu.displayProducts) 
	// return errors on invalid or out stock items
	// send data to Accounting() class to do all the math
	// return results & messages from Accounting Class & the VendingMachineClass
	// send data to VendoMatic800 to decrement the Map value by 1
	// if the sale is successful, must call Audit class
	// ***to check for existence use Map.containsKey()
	// ***to check for stock use Map.getValue()
	// finish transaction will tell the Accounting Class to run makeChane() method
	

