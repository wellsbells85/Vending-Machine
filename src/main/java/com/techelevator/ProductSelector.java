package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductSelector {
	
	private int inventoryCount;

	private Map<String , Integer> inventoryMap = new LinkedHashMap<>();
	private Map<String , String> nameMap = new LinkedHashMap<>();
	private Map<String , String> priceMap = new LinkedHashMap<>();

	public ProductSelector() {
		
	}
	
	public int getInventoryCount(String input) {
		return inventoryMap.get(input);
	}
	
	public String displayInventory(String input) {
		if(getInventoryCount(input) == 0) {
			return "SOLD OUT";
		} else {
			return Integer.toString(getInventoryCount(input));		
		}
	} //end method 
	
	public boolean validateSlot(String input) {
		for(String key : inventoryMap.keySet()) {
			if(inventoryMap.containsKey(input)) {
				return true;
			} 
		} return false;
	}
	
	//method to decrement inventory correctly
	public int adjustInventory(String input) {
		inventoryCount = getInventoryCount(input);
		inventoryCount--;
		inventoryMap.put(input, inventoryCount);
		return inventoryCount;
	} //end method	
	
	//method to create initialize an inventory map of slot location with 5 items each
	public void initializeInventory() {
		File vendFile = new File("vendingmachine.csv");
		String[] data;
		try (Scanner scanner = new Scanner(vendFile)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				data = line.split("\\|");
				inventoryMap.put(data[0], 5 ); // initialize an array with 5 items each
				nameMap.put(data[0], data[1]);
				priceMap.put(data[0], data[2]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong");
			System.exit(1);
		}
	} //end method
	
	//method to return all string data with updated inventory
	public String getAllVendingData(String input) {
		String productData = String.format("%1$-6s", input) + String.format("%1$-20s", nameMap.get(input)) 
				+ "$" + String.format("%1$-5s", priceMap.get(input)); ///
		return productData;
	} //end method	

	//quick call method to display all data easily
	public String displayAllVendingData() {
		String productAndInventory = "Code       Name           Price   Quantity\n";
		productAndInventory += "==========================================\n";
		for (String key : inventoryMap.keySet()) {
			productAndInventory += getAllVendingData(key) + "   " + displayInventory(key) + "\n";
		} return productAndInventory;	
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
	

