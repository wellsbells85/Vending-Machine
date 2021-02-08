package com.techelevator;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDateTime;    
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class AuditWriter {
	
	private Map<String, Integer> salesMap = new HashMap<>();
	private BigDecimal totalSales = new BigDecimal(0);
	private	BigDecimal oldSales = BigDecimal.ZERO;
	private BigDecimal newSales = BigDecimal.ZERO;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
	private DateTimeFormatter stampDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private DateTimeFormatter stampTime = DateTimeFormatter.ofPattern("HHmmss");
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
				} 	
			}
		} catch(Exception e) { //end try-with-resources writer
			System.out.println("\nThe program was unable to write your file. Sorry. SECONDNOTE");
			System.exit(1); //end the program with an irregular error
		} //end try-catch
		return salesMap;
	} //end salesArray method
	
	public BigDecimal getOldSales() {
		File salesReport = new File("SalesReportMaster.txt");
		try(Scanner fileScanner = new Scanner(salesReport)) {
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				if(!line.matches(".*[\\|].*") && !line.isEmpty()) {
					String[] salesHistory = line.split("\\$");
					if(salesHistory.length == 1) {
						oldSales = new BigDecimal(0);// there will be no value after the $ the first time the file is read, this avoids a NullPointerException later
					} else {
						String price = salesHistory[1];
						oldSales = new BigDecimal(price);				
					}
				} 
			}
		} catch(Exception e) { //end try-with-resources writer
			System.out.println("\nThe program was unable to write your file. Sorry. SECONDNOTE");
			System.exit(1); //end the program with an irregular error
		} return oldSales;//end try-catch
	}
	
	public void salesMapEditor(String product) {
		this.salesMap = getSalesMap();
		Integer count = salesMap.get(product);
		count++;
		salesMap.put(product, count);
		masterReportWriter();
	} //end editor method
	
	public void setNewSales(BigDecimal input) {
		this.newSales = input;
	}
	
	public BigDecimal getNewSales() {
		return newSales;
	}
	
	public BigDecimal getTotalSales() {
		BigDecimal old = getOldSales();
		return old.add(getNewSales());	 
	}
	
	public void masterReportWriter()  {
		try (PrintWriter writer = new PrintWriter(new FileWriter("SalesReportMaster.txt", false))) {
			for(String key : salesMap.keySet()) {
				String count = String.valueOf(salesMap.get(key));
				String output = key + "|" + count;
				writer.println(output);
			}
			writer.println();
			writer.printf("TOTAL SALES: $");
			writer.printf(getTotalSales().toString() );	
		} catch(IOException e) {
			System.out.println("\nThe program was unable to write your file. Sorry! NOTE");
			System.exit(1); //end the program with an irregular error
		}
	} //end masterReportWriter method	
	
	public void dateStampedReportWriter()  {
		String fileName = stampDate.format(time) + "T" + stampTime.format(time) + " SalesReport.txt";
		File reportName = new File(fileName); 
		try{
			reportName.createNewFile(); //write the log file
		} catch (IOException e) { //
			System.out.println("Was unable to create the file.");
			System.exit(1); //end the program with an irregular error
		}
		
		File input = new File("SalesReportMaster.txt");
		try (Scanner fileScanner = new Scanner(input); PrintWriter writer = new PrintWriter(new FileWriter(reportName, true))) {
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				writer.println(line);
			} 
		} catch(Exception e) { //end try-with-resources writer
				System.out.println("\nThere was an unforeseen issue. We must close the application.");
				System.exit(1); //end the program with an irregular error
			}  //end try-catch			
	} //end dateStampedReportWriter method	
	
} //end class	