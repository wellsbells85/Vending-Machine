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
	
	Map<String, Integer> salesMap = new HashMap<>();
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
	
	public void salesMapMaker(File sales) {
		File salesReport = new File("SalesReportMaster.txt");
		try(Scanner fileScanner = new Scanner(sales)) {
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] productCount = line.split("\\|");
				salesMap.put(productCount[0], Integer.parseInt(productCount[1]));
			}
		} catch(Exception e) { //end try-with-resources writer
			System.out.println("\nThe program was unable to write your file. Sorry.");
			System.exit(1); //end the program with an irregular error
		} //end try-catch	
	} //end salesArray method
	
	public void salesMapEditor(String product) {
		for(String key : salesMap.keySet() ) {
			if(product.equals(key)) {
				int count = salesMap.get(key);
				count++;
				salesMap.put(key, count);
				break;
			} //end increment sales
		} //end for-each
	} //end editor method
	
	
	public void masterCopyWriter(String product)  {
		
//		
//				PrintWriter writer = new PrintWriter(new FileWriter( false )); 
//					if(product.equals(productCount[0])) {
//						int count = Integer.parseInt(productCount[1]);
//						count++;
//						productCount[1] = String.valueOf(count);
//						writer.println(productCount[0] + "|" + productCount[0]);
//					} else writer.println(line);
//				writer.close();
//			} // end while looper		
//		} catch(Exception e) { //end try-with-resources writer
//			System.out.println("\nThe program was unable to write your file. Sorry.");
//			System.exit(1); //end the program with an irregular error
//		} //end try-catch	
	} //end masterCopyWriter method	
} //end class	




