package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class VendingMachineItems {

	//getCategoryMessage() method to return String "Message according to the README based on the category"
	// --> DONE - changed to getCategoryMessage() to not overlap with getCategory() getter
	
	//getProductData() method to return String "Slot Name Price(properly formatted if possible)"
	// --> DONE
	
	//****getMethods() for each individual category, then we can combine data later as desired****
	// --> DONE
	
	// I included inventoryCount in the list and made a getter and setter for it.
	// I also made a purchase method that will automatically reduce the inventoryCount by 1 and return "SOLD OUT" if the amount is 0
	
	private String slotLocation;
	private String productName;
	private String price;
	private String category;
//	private int inventoryCount;
	private Map<String , VendingMachineItems> inventoryMap = new LinkedHashMap<>();
	
	public VendingMachineItems() {
		
	}
	
	public VendingMachineItems(String slotLocation, String productName, String price, String category) {
		this.slotLocation = slotLocation;
		this.productName = productName;
		this.price = price;
		this.category = category;
//		this.inventoryCount = 5;
	}
	
	public String getSlotLocation() {
		return slotLocation;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getPrice() {
		return price;
	}
	
	public String getCategory() {
		return category;
	}
	
//	public int getInventoryCount() {
//		return inventoryCount;
//	}
//	
//	public void setInventoryCount(int inventoryCount) {
//		this.inventoryCount = inventoryCount;
//	}
//	
//	// I have this method return an empty String if he purchase is successful so it won't show up anywhere
//	public String purchase() {
//		int currentInventory = getInventoryCount();
//		
//		if (currentInventory > 0) {
//			this.inventoryCount = currentInventory - 1;
//			return "";
//			
//		} else return "SOLD OUT";
//		
//	}
	
	//method to return String "Message according to the README based on the category"
	public String getCategoryMessage() {
		
		String message = "";
		
		if (category.equals("Chip")) {
			message += "Crunch Crunch, Yum!";
			
		} else if (category.equals("Candy")) {
			message += "Munch Munch, Yum!";
			
		}else if (category.equals("Drink")) {
			message += "Glug Glug, Yum!";
			
		}else if (category.equals("Gum")) {
			message += "Chew Chew, Yum!";
		}
		return message;
	}
	
	//method to return String "Slot Name Price(properly formatted)"
	public String getProductData() {
		String productData = "";
		productData += getSlotLocation() + " ";
		productData += getProductName() + " ";
		productData += "$" + getPrice() + " ";
		
		return productData;
	}
	
	// I'm not sure if this will actually make a new item each time or just overwrite the same item each time because the variable name is the same.
	public void initializeVendingMachine() {
		
		File vendFile = new File("vendingmachine.csv");
		String[] data;
		
		try (Scanner scanner = new Scanner(vendFile)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				
				data = line.split("\\|");
				
				VendingMachineItems vendItem = new VendingMachineItems(data[0], data[1], data[2], data[3]);		
			
				String key = data[0];	
				inventoryMap.put(key, vendItem);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong");
			System.exit(1);
		}
	}
	
	public String displayProducts() {
		String contents = "";
		
		for (String key : inventoryMap.keySet()) {
			contents += inventoryMap.get(key).getProductData() + "\n";
		}
		return contents;
	}
	
}
