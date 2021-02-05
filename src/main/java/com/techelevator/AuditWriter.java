package com.techelevator;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;    
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditWriter {
	
	String pathName = "FizzBuzz.txt"; 
	File fileName = new File(pathName); 
	
	if(!fileName.exists() ) {
		try {
			fileName.createNewFile(); //write the file
		} catch (IOException e) {		
			System.exit(1); //end with an irregular error
		} // end try-catch
	}	
//	
//		
//	
//
//	try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, false ))) {
//		for(int i = 1; i <= 300; i++) {
//			String textToPrint = FizzBuzz(i);
//			writer.println(textToPrint);
//		}	writer.close();		
//	} catch(IOException e) {
//		System.out.println("\nThe program was unable to write your file. Sorry.");
//		System.exit(1); //end the program with an irregular error
//	}


}

