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
import java.math.BigDecimal;

public class AuditWriter {
	
	private Map<String, Integer> salesMap = new HashMap<>();
	private BigDecimal totalSales;
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
	
	public void salesReportInitializer() {
		File salesReport = new File("vendingmachine.csv");
		try(Scanner fileScanner = new Scanner(salesReport)){
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] lineData = line.split("\\|");
				salesMap.put(lineData[1] , 0); //intialize first sales report with names and zero products sold
			}
		} catch(Exception e) { //end try-with-resources writer
			System.out.println("\nThe program was unable to write your file. Sorry.");
			System.exit(1); //end the program with an irregular error
		} //end try-catch	
	} 
	
	public Map<String, Integer> getSalesMap() {
		File salesReport = new File("SalesReportMaster.txt");
		try(Scanner fileScanner = new Scanner(salesReport)) {
			if(salesReport.length() == 0) {
				salesReportInitializer();
			} //if master copy is blank then initialize it
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				if(line.matches(".*[\\|].*")) {
					String[] productCount = line.split("\\|");
					String productName = productCount[0];
					String countString = productCount[1];
					Integer count = (Integer)Integer.parseInt(countString);
					salesMap.put(productName, count);
				} else { //if statement to only pull pipe delimited data
					String[] salesHistory = line.split("$");
					if(salesHistory.length == 1) {
						BigDecimal totals = BigDecimal.ZERO;
						setTotalSales(totals); // there will be no value after the $ the first time the file is read, this avoids a NullPointerException later
					} else {
						String price = salesHistory[1];
						BigDecimal oldSales = new BigDecimal(price);
						setTotalSales(oldSales);				
					}
				}			
			}
		} catch(Exception e) { //end try-with-resources writer
			System.out.println("\nThe program was unable to write your file. Sorry. SECONDNOTE");
			System.exit(1); //end the program with an irregular error
		} //end try-catch
		return salesMap;
	} //end salesArray method
	
	public void salesMapEditor(String product) {
		this.salesMap = getSalesMap();
		Integer count = salesMap.get(product);
		count++;
		salesMap.put(product, count);
		masterReportWriter();
	} //end editor method
	
	public void setTotalSales(BigDecimal sales) {
		this.totalSales = sales;
	}
	
	public BigDecimal getTotalSales() {
		return totalSales;
	}
	
	
	public void masterReportWriter()  {
		try (PrintWriter writer = new PrintWriter(new FileWriter("SalesReportMaster.txt", false))) {
			for(String key : salesMap.keySet()) {
				String count = String.valueOf(salesMap.get(key));
				String output = key + "|" + count;
				writer.println(output);
			}	
			writer.printf("%1$10s", "TOTAL: $");
			writer.printf(getTotalSales().toString() );
			writer.close();		
		} catch(IOException e) {
			System.out.println("\nThe program was unable to write your file. Sorry! NOTE");
			System.exit(1); //end the program with an irregular error
		}
	} //end masterReportWriter method	
	
//	public void dateStampedReportWriter()  {
//		String fileName = dateFormatter.format(time) + ""
//		
//		try (PrintWriter writer = new PrintWriter(new FileWriter(datedFileName, false ))) {
//			for(String key : salesMap.keySet()) {
//				String count = String.valueOf(salesMap.get(key));
//				String output = key + "|" + count;
//				writer.println(output);
//			}	writer.close();		
//		} catch(IOException e) {
//			System.out.println("\nThe program was unable to write your file. Sorry.");
//			System.exit(1); //end the program with an irregular error
//		}
//	} //end masterCopyWriter method	
	
} //end class	




