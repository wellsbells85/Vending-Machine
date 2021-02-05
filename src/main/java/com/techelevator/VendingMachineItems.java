package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class VendingMachineItems {
	
	private String slotLocation;
	private String productName;
	private String price;
	private String category;
	private String productData;
	private String productsList = "";
	
	private Map<String , VendingMachineItems> productMap = new LinkedHashMap<>();
		
	public VendingMachineItems() {
		
	}
	
	public VendingMachineItems(String slotLocation, String productName, String price, String category) {
		this.slotLocation = slotLocation;
		this.productName = productName;
		this.price = price;
		this.category = category;
	}
	
	public String getSlotLocation() {
		return slotLocation;
	}
	
	public String getSlotLocation(String input) {
		String slotLoc = productMap.get(input).getSlotLocation();
		return slotLoc;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getProductName(String input) {
		String prodName = productMap.get(input).getProductName();
		return prodName;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getPrice(String input) {
		String pri = productMap.get(input).getPrice();
		return pri;
	}
	
	public String getCategory() {
		return category;
	}
	
	public String getCategory(String input) {
		String cat = productMap.get(input).getCategory();
		return cat;
	}
	
	//method to return String "Message according to the README based on the category"
	public String getCategoryMessage(String input) {
		if (getCategory(input).equals("Chip")) {
			return "Crunch Crunch, Yum!";		
		} else if (getCategory(input).equals("Candy")) {
			return "Munch Munch, Yum!";		
		}else if (getCategory(input).equals("Drink")) {
			return "Glug Glug, Yum!";			
		}else if (getCategory(input).equals("Gum")) {
			return "Chew Chew, Yum!";
		} else return "";		
	} //end method
		
	//method to return String "Slot Name $Price"
	public String getProductData(String input) {
		String productData = productMap.get(input).getSlotLocation() + " " + productMap.get(input).getProductName() + "\t\t " + "$" + productMap.get(input).getPrice() + "\n "; ///
		return productData;
	} //end method	
	
	//method to produce entire list of products
	public String displayProducts() {
		String productList = "";
		for (String key : productMap.keySet()) {
			productList += getProductData(key) + "\n";
		} return productList;	
	} //end method

	//method to produce map of Vending Machine Items from the "vendingmachine.csv" file
	public void initializeVendingMachine() {
		
		File vendFile = new File("vendingmachine.csv");
		String[] data;
		
		try (Scanner scanner = new Scanner(vendFile)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();			
				data = line.split("\\|");				
				VendingMachineItems vendItem = new VendingMachineItems(data[0], data[1], data[2], data[3]);				
				String key = data[0];	
				productMap.put(key, vendItem);
			}			
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong");
			System.exit(1);
		}
	} //end method	
} //end class
