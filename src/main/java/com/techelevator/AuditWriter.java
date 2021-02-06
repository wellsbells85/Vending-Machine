package com.techelevator;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;    
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditWriter {
	
	 
	public void logWriter(String message) {
		try (PrintWriter writer = new PrintWriter(new FileWriter("Log.txt", false ))) {
			writer.println(message);
			writer.close();		
		} catch(IOException e) {
		System.exit(1); //end the program with an irregular error
		}
	}
	
}	
	
	




