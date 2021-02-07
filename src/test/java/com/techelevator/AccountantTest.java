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
	
	/*
	 * When running these tests, the tester should select 5, 2, 10, and 5 for the feedMoney prompts in the console.
	 */
	
	@Before
	public void setup() {
		account = new Accountant();
		account.initializePrices();		
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
	public void feedMoney_adds_the_correct_amount_of_money_to_currentMoney() {
		//Arrange
		BigDecimal expectedResult = new BigDecimal("5");
		account.feedMoney(); // tester should select 5
		
		//Act
		BigDecimal result = account.getCurrentMoney(); 
		
		//Assert
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void displayCurrentMoney_return_correct_format_and_answer_when_nothing_has_been_added() {
		//Arrange
		String expectedResult = "Current Money Provided: $0.00";
		
		
		//Act
		String result = account.displayCurrentMoney(); 
		
		//Assert
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void displayCurrentMoney_return_correct_format_and_answer_when_2_dollars_have_been_added() {
		//Arrange
		String expectedResult = "Current Money Provided: $2.00";
		account.feedMoney(); // tester should select 2
		
		//Act
		String result = account.displayCurrentMoney(); 
		
		//Assert
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void purchase_returns_false_if_there_are_not_enough_funds_for_the_item() {
		//Arrange
		String slotA3 = "A3";
		String slotB1 = "B1";
		String slotC2 = "C2";
		String slotD4 = "D4";
		
		//Act
		boolean resultA3 = account.purchase(slotA3); 
		boolean resultB1 = account.purchase(slotB1); 
		boolean resultC2 = account.purchase(slotC2); 
		boolean resultD4 = account.purchase(slotD4); 
		
		//Assert
		Assert.assertFalse("Purchase shuld have returned false with insufficient funds.", resultA3);
		Assert.assertFalse("Purchase shuld have returned false with insufficient funds.", resultB1);
		Assert.assertFalse("Purchase shuld have returned false with insufficient funds.", resultC2);
		Assert.assertFalse("Purchase shuld have returned false with insufficient funds.", resultD4);
	}
	
	@Test
	public void purchase_returns_true_if_there_is_enough_funds_for_the_item() {
		//Arrange
		String slotA3 = "A3";
		String slotB1 = "B1";
		String slotC2 = "C2";
		String slotD4 = "D4";
		account.feedMoney(); //tester should select 10
		
		//Act
		boolean resultA3 = account.purchase(slotA3); 
		boolean resultB1 = account.purchase(slotB1); 
		boolean resultC2 = account.purchase(slotC2); 
		boolean resultD4 = account.purchase(slotD4); 
		
		//Assert
		Assert.assertTrue("Purchase shuld have returned true with sufficient funds.a", resultA3);
		Assert.assertTrue("Purchase shuld have returned true with sufficient funds.b", resultB1);
		Assert.assertTrue("Purchase shuld have returned true with sufficient funds.c", resultC2);
		Assert.assertTrue("Purchase shuld have returned true with sufficient funds.d", resultD4);
	}
	
	@Test
	public void makeChange_returns_no_change_necessary_when_current_money_is_0() {
		//Arrange
		String expectedResult = "No change necessary.";
		
		//Act
		String result = account.makeChange(); 
		
		//Assert
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void makeChange_returns_correct_format_and_answer_when_change_is_made() {
		//Arrange
		String slotSelection = "A2";
		String expectedResult = "Change is 14 Quarters, and 0 Dimes, and 1 Nickels.";
		account.feedMoney(); // tester should select 5
		
		//Act
		account.purchase(slotSelection);
		String result = account.makeChange(); 
		
		//Assert
		Assert.assertEquals(expectedResult, result);
	}
}
