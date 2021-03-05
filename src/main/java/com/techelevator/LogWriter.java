package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LogWriter {
	
	private static DateTimeFormatter logFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

	public static void createLog() {
		File logName = new File("Log.txt"); 
		try{
			logName.createNewFile(); //write the log file
			} catch (IOException e) { //			
		} 
	}
	
	public static void feedMoneyLog(Map<String, String[]> input) {
		if(input.containsKey(" ")){
			String[] money = input.get(" ");
			String start = money[0];
			String current = money[1];
			logWriter(String.format("%1$21s", "FEED MONEY:") 
				+ String.format("%16s", NumberFormat.getCurrencyInstance().format(new BigDecimal(start))
				+ String.format("%8s", NumberFormat.getCurrencyInstance().format(new BigDecimal(current)))));
		} 
	} //end feedMoneyLog()
	
	public static void makeChangeLog(Map<String, String[]> input) {
		for(String key: input.keySet()) {
			String[] money = input.get(key);
			String start = money[0];
			String current = money[1];
			logWriter(String.format("%1$21s", "GIVE CHANGE:") 
				+ String.format("%16s", NumberFormat.getCurrencyInstance().format(new BigDecimal(start))
				+ String.format("%8s", NumberFormat.getCurrencyInstance().format(new BigDecimal(current)))));	
		} 
	} //end makeChangeLog()
	
	public static void purchaseLog(Map<String, String[]> input) {
		for(String key: input.keySet()) {
			String[] data = input.get(key);
			String name = data[0];
			String slot = data[1];
			String start = data[2];
			String current = data[3];
			logWriter(String.format("%1$21s", name + " " + slot)
				+ String.format("%1$8s", NumberFormat.getCurrencyInstance().format(new BigDecimal(start)))
				+ String.format("%1$8s", NumberFormat.getCurrencyInstance().format(new BigDecimal(current))));
		} //end for loop
	} //end purchaseLog()

	public static void logWriter(String message) {
		LocalDateTime time = LocalDateTime.now();
		try (PrintWriter writer = new PrintWriter(new FileWriter("Log.txt", true ))) { // changed this to true so it adds to the list in the file
			writer.printf(logFormat.format(time) + " " + message + "\n");
			writer.close();		
		} catch(IOException e) {
			System.out.println("\nProgram was unable to write to Log.");
			System.exit(1); //end the program with an irregular error
		}
	} //end logWriter()
	
}
