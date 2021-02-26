package com.techelevator;

import java.io.File;
import java.io.IOException;

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
		
		MainMenu mainMenu = new MainMenu();
		mainMenu.runMainMenu();
				
	} //end main 
} //end class
