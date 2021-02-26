package com.techelevator;

public class Console {
	
	public void displayMainMenu(String currentMoney) {
		System.out.println(currentMoney + "\n");
		System.out.println("(1) Display Vending Machine Items");
		System.out.println("(2) Purchase");
		System.out.println("(3) Exit");
	}
	
	public void displayMoneyMenu(String currentMoney) {
		System.out.println(currentMoney + "\n");
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.print("(3) Finish Transaction\n");
	}
	
	public void displayPurchaseMenu(String products, String currentMoney) {
		System.out.println(products + "\n");
		System.out.println(currentMoney + "\n");
		System.out.print("Enter Product Code: "); 		
	}
	
	//imported from VendingMachineItems
	public void displayProducts(String products) {
		System.out.println("");
		System.out.println(products);
		System.out.println("");
	} // end displayProducts()
		
	public void displayBasicHeader() {
		System.out.println("Code       Name           Price");
		System.out.println("===============================");
	}
	
	public void displayQuantityHeader() {
		System.out.println("Code       Name           Price  Quantity");
		System.out.println("==========================================");
	}
	
	public void displayFeedMoney() {
		System.out.print("Would you like to add $1, $2, $5, or $10? ");
	}
	
	public void displayValidation() {
		System.out.println("");
		System.out.println("Invalid selection");
		System.out.println("");
	}

	public void displayFunds() {
		System.out.println("");
		System.out.println("Insufficient Funds");
		System.out.println("");
	}
	
	public void displaySoldOut() {
		System.out.println("");
		System.out.println("SOLD OUT");
		System.out.println("");
	}
	
	public void displayClosing() {
		System.out.println("Thank You! Have a nice Day.");
	} 
	
	public void displayBlank() {
		System.out.println("");
	}
} //end class
