package com.techelevator;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;    
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditWriter {
	
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
	
	/*
	 * Feed Money - DONE
	 * Give Change - DONE
	 * Purchase - DONE
	 * 
	 * We will need a method that will initialize a new empty log file at the start of each run, or we can have a master log file like the Sales Report will.
	 */
}	
	
	




