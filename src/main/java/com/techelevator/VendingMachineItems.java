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

	private Map<String, VendingMachineItems> productMap = new LinkedHashMap<>();

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
		String itemSlotLocation = productMap.get(input).getSlotLocation();
		return itemSlotLocation;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductName(String input) {
		String itemProductName = productMap.get(input).getProductName();
		return itemProductName;
	}

	public String getPrice() {
		return price;
	}

	public String getPrice(String input) {
		String itemPrice = productMap.get(input).getPrice();
		return itemPrice;
	}

	public String getCategory() {
		return category;
	}

	public String getCategory(String input) {
		String itemCategory = productMap.get(input).getCategory();
		return itemCategory;
	}

	// method to return String "Message according to the README based on the
	// category"
	public String getCategoryMessage(String input) {
		if (input == null || input.equals("")) {
			return "";
		} else 
			if (getCategory(input).equals("Chip")) {
			return "\"Crunch Crunch, Yum!\"";
		} else if (getCategory(input).equals("Candy")) {
			return "\"Munch Munch, Yum!\"";
		} else if (getCategory(input).equals("Drink")) {
			return "\"Glug Glug, Yum!\"";
		} else if (getCategory(input).equals("Gum")) {
			return "\"Chew Chew, Yum!\"";
		} else
			return "";

	} // end method

	// method to return String "Slot Name $Price"
	public String getProductData(String input) {
		String productData = String.format("%1$-6s", productMap.get(input).getSlotLocation())
				+ String.format("%1$-20s", productMap.get(input).getProductName()) + "$"
				+ String.format("%1$-5s", productMap.get(input).getPrice()); ///
		return productData;
	} // end method

	// method to produce entire list of products
	public String displayProducts() {
		String productList = "Code       Name           Price\n";
		productList += "===============================\n";
		for (String key : productMap.keySet()) {
			productList += getProductData(key) + "\n";
		}
		return productList;
	} // end method

	// method to produce map of Vending Machine Items from the "vendingmachine.csv"
	// file
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
	} // end method
} // end class
