package com.techelevator;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountantTest {
	
	private Accountant account;
	private Map<String, BigDecimal> priceMap = new LinkedHashMap<>();
	private BigDecimal currentMoney;
	
	@Before
	public void setup() {
		account = new Accountant();
		account.initializePrices();
		this.currentMoney = new BigDecimal("2.95");
		
	}

	@Test
	public void initializePrices_creates_a_LinkedHashMap_with_correct_price_data_inside() {
		//Arrange
		String slotA1 = "A1";
		String slotB3 = "B3";
		String slotC4 = "C4";
		String slotD2 = "D2";
		BigDecimal expectedA1Price = new BigDecimal("3.05");
		BigDecimal expectedB3Price = new BigDecimal("1.50");
		BigDecimal expectedC4Price = new BigDecimal("1.50");
		BigDecimal expectedD2Price = new BigDecimal("0.95");
				
		//Act
		BigDecimal resultSlotA1 = account.getPrice(slotA1);
		BigDecimal resultSlotB3 = account.getPrice(slotB3);
		BigDecimal resultSlotC4 = account.getPrice(slotC4);
		BigDecimal resultSlotD2 = account.getPrice(slotD2);
				
		//Assert
		Assert.assertEquals(expectedA1Price, resultSlotA1);
		Assert.assertEquals(expectedB3Price, resultSlotB3);
		Assert.assertEquals(expectedC4Price, resultSlotC4);
		Assert.assertEquals(expectedD2Price, resultSlotD2);
	}
	
	@Test
	public void displayCurrentMoney_return_correct_format_and_answer() {
		//Arrange
		String expectedResult = "Current Money Provided: $0.00";
		
		//Act
		String result = account.displayCurrentMoney(); 
		
		//Assert
		Assert.assertEquals(expectedResult, result);
	}
	
}
