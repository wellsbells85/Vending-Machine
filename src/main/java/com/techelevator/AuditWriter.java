package com.techelevator;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;    
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditWriter {
	
	private Map<String, Integer> salesMap = new HashMap<>();
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
	private LocalDateTime time = LocalDateTime.now();
	
	public void logWriter(String message) {
		try (PrintWriter writer = new PrintWriter(new FileWriter("Log.txt", true ))) { // changed this to true so it adds to the list in the file
			writer.println(dateFormatter.format(time) + " " + message);
			writer.close();		
		} catch(IOException e) {
		System.exit(1); //end the program with an irregular error
		}
	}
	
	public Map<String, Integer> getSalesMap() {
		return salesMap;
	} 
	
	public void setSalesMap(Map<String, Integer> salesMap) {
		this.salesMap = salesMap;
	}
	
	public void salesReportInitializer() {
		File salesReport = new File("vendingmachine.csv");
		try(Scanner fileScanner = new Scanner(salesReport)){
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] lineData = line.split("\\|");
				salesMap.put(lineData[1] , 0);
			}
		} catch(Exception e) { //end try-with-resources writer
			System.out.println("\nThe program was unable to write your file. Sorry.");
			System.exit(1); //end the program with an irregular error
		} //end try-catch	
	}
	
	public void salesMapMaker() {
		File salesReport = new File("SalesReportMaster.txt");
		try(Scanner fileScanner = new Scanner(salesReport)) {
			if(salesReport.length() == 0) {
				salesReportInitializer();
			} //if master copy is blank then initialize it
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] productCount = line.split("\\|");
				String product = productCount[0];
				Integer count = Integer.parseInt(productCount[1]);
				salesMap.put(product, count);
			}
		} catch(Exception e) { //end try-with-resources writer
			System.out.println("\nThe program was unable to write your file. Sorry.");
			System.exit(1); //end the program with an irregular error
		} //end try-catch	
	} //end salesArray method
	
	public void salesMapEditor(String product) {
		Integer count = getSalesMap().get(product);
		count = count + 1;
		salesMap.put(product, count);		
	} //end editor method
	
	public void masterReportWriter()  {
		try (PrintWriter writer = new PrintWriter(new FileWriter("SalesReportMaster.txt", false))) {
			for(String key : salesMap.keySet()) {
				String count = String.valueOf(salesMap.get(key));
				String output = key + "|" + count;
				writer.println(output);
			}	writer.close();		
		} catch(IOException e) {
			System.out.println("\nThe program was unable to write your file. Sorry.");
			System.exit(1); //end the program with an irregular error
		}
	} //end masterCopyWriter method	
	
} //end class	




