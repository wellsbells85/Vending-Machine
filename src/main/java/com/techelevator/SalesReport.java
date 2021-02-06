package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SalesReport {

	//create a private map on startup that increments value with every sale  
	//overwrite Sales.txt on exit. 
	//activated by pressing 4 (hidden) on the main menu
	//when SalesReport forces a quick overwrite to master Sales.txt
	//it formats the name and creates a separate file by copying Sales.txt
	//prints out unique report with timeStamp in name
//	>Potato Crisps|10
//	>Stackers|3
//	>Grain Waves|0
//	>Cloud Popcorn|50
//	>Moonpie|23
//	>Cowtales|2

	private File salesReport = new File("SalesReport.txt");
	
	public void makeReport() throws IOException {
		salesReport.createNewFile();
	}
	
	public void logWriter(String message) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(salesReport, true ))) {
			writer.println(message);
			writer.close();		
		} catch(IOException e) {
		System.exit(1); //end the program with an irregular error
		}
	}
}
