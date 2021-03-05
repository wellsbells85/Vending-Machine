package com.techelevator;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class ReportWriter {

	private static DateTimeFormatter reportDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static DateTimeFormatter reportTime = DateTimeFormatter.ofPattern("HHmmss");
	private static StringBuffer stringData = new StringBuffer();
	private static final String VENDING_FILE = "vendingmachine.csv";
	private static final String REPORT_FILE = "SalesReportMaster.txt";

	public static void createReport() {
		File reportName = new File("SalesReportMaster.txt");
		try {
			reportName.createNewFile(); // write the log file
		} catch (IOException e) { //
		}
	}

	public static void salesReportInitializer() {
		File vendingFile = new File(VENDING_FILE);
		try (Scanner fileScanner = new Scanner(vendingFile);
				PrintWriter writer = new PrintWriter(new FileWriter(REPORT_FILE, true))) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] lineData = line.split("\\|");
				writer.println(lineData[1] + "|0");
			}
			writer.println("");
			writer.printf("TOTAL SALES: $0");
		} catch (IOException e) {
			System.out.println("\nProgram was unable to initialize Sales Report.");
			System.exit(1); // end the program with an irregular error
		} // end try-catch
	} // end salesReportInitializer()

	public static void masterReportWriter(String product, BigDecimal newSales) {
		File salesReport = new File(REPORT_FILE);
		if (salesReport.length() == 0) {
			salesReportInitializer();
		} else {
			boolean fileRead = readReportFile();
			if (fileRead) {
				updateSalesReport(product, newSales);
			}
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE, false))) {
				writer.write(stringData.toString());// writes the edited string buffer to the new file
			} catch (Exception e) {// if an exception occurs
				System.out.println("Error occured while attempting to write to file: " + e.getMessage());
			}
		}
		stringData.setLength(0);
	} // end masterReportWriter()

	public static boolean readReportFile() {
		try (Scanner fileToRead = new Scanner(new File(REPORT_FILE))) {
			for (String line; fileToRead.hasNextLine() && (line = fileToRead.nextLine()) != null;) {
				stringData.append(line).append("\n");
			}
			return true;
		} catch (FileNotFoundException ex) {
			System.out.println("The file " + REPORT_FILE + " could not be found! " + ex.getMessage());
			return false;
		}
	}

	public static void updateSalesReport(String product, BigDecimal newSales) {
		try (Scanner fileToRead = new Scanner(new File(REPORT_FILE))) {
			int startIndex;
			int endIndex;
			for (String line; fileToRead.hasNextLine() && (line = fileToRead.nextLine()) != null;) {
				if (line.matches(".*[\\|].*")) {
					String[] lineData = line.split("\\|");
					if (lineData[0].equals(product)) {
						startIndex = stringData.indexOf(line);
						endIndex = startIndex + line.length();
						Integer count = (Integer) Integer.parseInt(lineData[1]);
						count++;
						line = lineData[0] + "|" + count;
						stringData.replace(startIndex, endIndex, line);
					}
				} else if (!line.matches(".*[\\|].*") && !line.isEmpty()) {
					String[] lineData = line.split("\\$");
					startIndex = stringData.indexOf(line);
					endIndex = startIndex + line.length();
					BigDecimal oldSales = new BigDecimal(lineData[1]);
					BigDecimal totalSales = oldSales.add(newSales);
					line = lineData[0] + "$" + totalSales.toString();
					stringData.replace(startIndex, endIndex, line);
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("The file " + REPORT_FILE + " could not be found! " + ex.getMessage());
		}
	}

	public static void timeStampedReport() {
		LocalDateTime time = LocalDateTime.now();
		String fileName = reportDate.format(time) + "T" + reportTime.format(time) + " SalesReport.txt";
		File reportName = new File(fileName);
		try {
			reportName.createNewFile(); // write the log file
		} catch (IOException e) { //
			System.out.println("Was unable to create the file.");
			System.exit(1); // end the program with an irregular error
		}
		File input = new File(REPORT_FILE);
		try (Scanner fileScanner = new Scanner(input);
				PrintWriter writer = new PrintWriter(new FileWriter(reportName, true))) {
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				writer.println(line);
			} // end while
		} catch (Exception e) { // end try-with-resources writer
			System.out.println("\nThere was an unforeseen issue. We must close the application.");
			System.exit(1); // end the program with an irregular error
		} // end try-catch
	} // end dateStampedReportWriter method

} // end class
