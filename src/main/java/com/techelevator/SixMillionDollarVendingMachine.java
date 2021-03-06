package com.techelevator;

public class SixMillionDollarVendingMachine {

	public static void main(String[] args) {	
		MainMenu mainMenu = new MainMenu(new Console(System.in, System.out), new VendingMachineItem(), new Accountant());
		mainMenu.initialize();
		mainMenu.runMainMenu();				
	} //end main 
} //end class
