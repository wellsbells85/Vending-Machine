package com.techelevator;

public class VendoMatic800 {

	public static void main(String[] args) {
		VendingMachineItems vend = new VendingMachineItems();
		
		vend.initializeVendingMachine();
		
		System.out.println(vend.displayProducts());
		
		//when program starts, tell Audit.java to create it's map based off the last Sales.txt
		//VendoMatic800 will call VendingMachineItems, will create Map, and Initialize Integer values to 5, only at start of program
		//can be in a method, or can be a simple initialization of the map
		//****if don't use a method now, then we need Setters & Getters to move this data around****
		//create dispenseProduct(get data from SelectProductMenu Class) method that simply removes 1 item from map. 
		
		
		
		
	}

}
