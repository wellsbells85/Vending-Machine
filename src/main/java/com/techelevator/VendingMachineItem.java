package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class VendingMachineItem {

	private String slot;
	private String name;
	private String price;
	private String category;
	private String message;
	private LinkedHashMap<String, LinkedHashMap<String, LinkedList<VendingMachineItem>>> inventoryMap = new LinkedHashMap<>();
	private LinkedHashMap<String, LinkedList<VendingMachineItem>> productMap = new LinkedHashMap<>();
	private LinkedList<VendingMachineItem> productData = new LinkedList<>();

	public VendingMachineItem() {

	}

	public VendingMachineItem(String slot, String name, String price, String category, String message) {
		this.slot = slot;
		this.name = name;
		this.price = price;
		this.category = category;
		this.message = message;
	}

	public LinkedHashMap<String, LinkedHashMap<String, LinkedList<VendingMachineItem>>> getInventoryMap() {
		return inventoryMap;
	}

	public String getSlot() {
		return slot;
	}

	public String getName() {
		return name;
	}
	
	/**
	* References productData to get the Product Name
	* based on it's slot. It's done this way because 
	* the List itself depletes as Items are sold, 
	* causing a NullPointerException when count == 0.
	* 
	* @param slot
	* @return String name
	*/
	public String getName(String slot) {
		String name = "";
		for (VendingMachineItem product : productData) {
			if(product.getSlot().equals(slot)) {
				name = product.getName();
			}
		} return name;
	}

	public String getPrice() {
		return price;
	}
	
	/**
	* References productData to get the Product Price
	* based on it's slot. It's done this way because 
	* the List itself depletes as Items are sold, 
	* causing a NullPointerException when count == 0.
	* 
	* @param slot
	* @return String price
	*/
	public String getPrice(String slot) {
		String price = "";
		for (VendingMachineItem product : productData) {
			if(product.getSlot().equals(slot)) {
				price = product.getPrice();
			}	
		} return price;
	}

	public String getCategory() {
		return category;
	}

	/**
	* Determines if a slot is a valid input by
	* checking the inventoryMap KeySet.
	* 
	* @param input
	* @return boolean
	*/
	public boolean validateSlot(String input) {
		if(inventoryMap.containsKey(input)) {
			return true;
		} return false;
	} 
	
	/**
	* Checks the actual count of items in the InventoryMap.
	*/
	public int getCount(String slot) {
		return getInventoryMap().get(slot).get(slot).size();
	}

	/**
	* Checks the actual count of items in the InventoryMap.
	* If there is a sellable item, it pops it off the map.
	*/
	public void setCount(String slot) {
		if (getCount(slot) > 0) {
			this.inventoryMap.get(slot).get(slot).pollLast();
		}
	} // end adjustInventory()

	/**
	* Compares item categories to stored messages,
	* in order to return correct string.
	* 
	* @param category
	* @return String message
	*/
	public String getMessage(String category) {
		if (getCategory().equals("Chip")) {
			return "\"Crunch Crunch, Yum!\"";
		} else if (getCategory().equals("Candy")) {
			return "\"Munch Munch, Yum!\"";
		} else if (getCategory().equals("Drink")) {
			return "\"Glug Glug, Yum!\"";
		} else {
			return "\"Chew Chew, Yum!\"";
		} 
	} // end getMessage()

	/**
	* Checks inventory items and creates a formatted
	* string of all items with their count. If the count is 
	* ZERO it displays SOLD OUT to the user.
	* 
	* return String productsAndInventory
	*/
	public String displayItemsAndInventory() {
		String count = "";
		String productAndInventory = "";
		for (VendingMachineItem product : productData) {
			if(getCount(product.getSlot()) == 0){
				count = " SOLD OUT";
			} else {
				count = "    " + String.valueOf(getCount(product.getSlot()));
			}
			productAndInventory += " " + String.format("%1$-5s", product.getSlot())
					+ String.format("%1$-20s", product.getName()) + "$" 
					+ String.format("%1$-5s", product.getPrice())
					+ String.format("%1$-8s", count) + "\n";
		} // end for-while
		return productAndInventory;
	} // end method

	/**
	* Creates a formatted string of all products. 
	* 
	* @return String productList
	*/
	public String displayProducts() {
		String productList = "";
		for (VendingMachineItem product : productData) {
			productList += " " + String.format("%1$-5s", product.getSlot())
					+ String.format("%1$-20s", product.getName()) 
					+ "$" + String.format("%1$-5s", product.getPrice()) + "\n";
		}
		return productList;
	} // end displayProducts()
	
	/**
	* Creates a formatted string of a product that was
	* recently purchased.
	* 
	* @return String productInfo
	*/
	public String getSoldProduct(String slot) {
		String productInfo = "";
		for (VendingMachineItem product : productData) {
			if (slot.equals(product.getSlot())) {
				productInfo += " " + String.format("%1$-5s", product.getSlot())
						+ String.format("%1$-20s", product.getName()) + "$"
						+ String.format("%1$-5s", product.getPrice()) + "   "
						+ product.getMessage(product.getCategory());
			} // end if statement
		} // end for-while
		return productInfo;
	} // end getSoldProduct()

	/**
	* Scans .csv file to get data use to initialize the inventoryMap. 
	* This method is only called the when the MainMenu class is first 
	* called. If all items are sold out, it won't replenish until the
	* program is restarted. Creates FIVE of each item.
	*/
	public void initializeVendingMachineList() {
		File vendFile = new File("vendingmachine.csv");
		String[] data;
		try (Scanner scanner = new Scanner(vendFile)) {
			while (scanner.hasNextLine()) {
				LinkedList<VendingMachineItem> products = new LinkedList<>();
				String line = scanner.nextLine();
				data = line.split("\\|");
				this.slot = data[0];
				this.name = data[1];
				this.price = data[2];
				this.category = data[3];
				this.message = getMessage(this.category);
				VendingMachineItem vendItem = new VendingMachineItem(slot, name, price, category, message);
				this.productData.add(vendItem); // create list that has all data of each item
				for (int i = 0; i < 5; i++) {
					products.add(vendItem); // add 5 items each to the products list, 1 as place holder
				}
				this.productMap.put(slot, products);
				this.inventoryMap.put(slot, this.productMap);
			} // end while loop
		} catch (FileNotFoundException e) {
			System.out.println("Something went wrong");
			System.exit(1); // end irregularly
		} // end try-catch
	} // end initializeVendingMachineList()
} // end class
