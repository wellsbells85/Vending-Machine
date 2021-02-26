package com.techelevator;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemsTest {
	
//	private VendingMachineItems vend;
//	private Map<String , VendingMachineItems> productMap = new LinkedHashMap<>();
//	
//	
//	@Before
//	public void setup() {
//		vend = new VendingMachineItems();
//		vend.initializeVendingMachine();
//	}
//	
//	@Test
//	public void initializeVendingMachine_creates_a_LinkedHashMap_with_correct_slotLocation_data_inside() {
//		//Arrange
//		String slotA1 = "A1";
//		String slotB3 = "B3";
//		String slotC4 = "C4";
//		String slotD2 = "D2";
//				
//		//Act
//		String resultSlotA1 = vend.getSlotLocation(slotA1);
//		String resultSlotB3 = vend.getSlotLocation(slotB3);
//		String resultSlotC4 = vend.getSlotLocation(slotC4);
//		String resultSlotD2 = vend.getSlotLocation(slotD2);
//				
//		//Assert
//		Assert.assertEquals("A1", resultSlotA1);
//		Assert.assertEquals("B3", resultSlotB3);
//		Assert.assertEquals("C4", resultSlotC4);
//		Assert.assertEquals("D2", resultSlotD2);
//	}
//	
//	@Test
//	public void initializeVendingMachine_creates_a_LinkedHashMap_with_correct_productName_data_inside() {
//		//Arrange
//		String slotA1 = "A1";
//		String slotB3 = "B3";
//		String slotC4 = "C4";
//		String slotD2 = "D2";
//				
//		//Act
//		String resultSlotA1 = vend.getProductName(slotA1);
//		String resultSlotB3 = vend.getProductName(slotB3);
//		String resultSlotC4 = vend.getProductName(slotC4);
//		String resultSlotD2 = vend.getProductName(slotD2);
//				
//		//Assert
//		Assert.assertEquals("Potato Crisps", resultSlotA1);
//		Assert.assertEquals("Wonka Bar", resultSlotB3);
//		Assert.assertEquals("Heavy", resultSlotC4);
//		Assert.assertEquals("Little League Chew", resultSlotD2);
//	}
//	
//	@Test
//	public void initializeVendingMachine_creates_a_LinkedHashMap_with_correct_price_data_inside() {
//		//Arrange
//		String slotA1 = "A1";
//		String slotB3 = "B3";
//		String slotC4 = "C4";
//		String slotD2 = "D2";
//				
//		//Act
//		String resultSlotA1 = vend.getPrice(slotA1);
//		String resultSlotB3 = vend.getPrice(slotB3);
//		String resultSlotC4 = vend.getPrice(slotC4);
//		String resultSlotD2 = vend.getPrice(slotD2);
//				
//		//Assert
//		Assert.assertEquals("3.05", resultSlotA1);
//		Assert.assertEquals("1.50", resultSlotB3);
//		Assert.assertEquals("1.50", resultSlotC4);
//		Assert.assertEquals("0.95", resultSlotD2);
//	}
//	
//	@Test
//	public void initializeVendingMachine_creates_a_LinkedHashMap_with_correct_category_data_inside() {
//		//Arrange
//		String slotA1 = "A1";
//		String slotB3 = "B3";
//		String slotC4 = "C4";
//		String slotD2 = "D2";
//				
//		//Act
//		String resultSlotA1 = vend.getCategory(slotA1);
//		String resultSlotB3 = vend.getCategory(slotB3);
//		String resultSlotC4 = vend.getCategory(slotC4);
//		String resultSlotD2 = vend.getCategory(slotD2);
//				
//		//Assert
//		Assert.assertEquals("Chip", resultSlotA1);
//		Assert.assertEquals("Candy", resultSlotB3);
//		Assert.assertEquals("Drink", resultSlotC4);
//		Assert.assertEquals("Gum", resultSlotD2);
//	}
//	
//	@Test
//	public void getCategoryMessage_returns_the_correct_message_based_on_input() {
//		//Arrange
//		String inputChip = "A1";
//		String inputCandy = "B1";
//		String inputDrink = "C1";
//		String inputGum = "D1";
//		
//		//Act
//		String resultChip = vend.getCategoryMessage(inputChip);
//		String resultCandy = vend.getCategoryMessage(inputCandy);
//		String resultDrink = vend.getCategoryMessage(inputDrink);
//		String resultGum = vend.getCategoryMessage(inputGum);
//		
//		//Assert
//		Assert.assertEquals("\"Crunch Crunch, Yum!\"", resultChip);
//		Assert.assertEquals("\"Munch Munch, Yum!\"", resultCandy);
//		Assert.assertEquals("\"Glug Glug, Yum!\"", resultDrink);
//		Assert.assertEquals("\"Chew Chew, Yum!\"", resultGum);
//	}
//	
//	@Test
//	public void getCategoryMessage_returns_an_empty_String_if_input_is_an_empty_string() {
//		//Arrange
//		String input = "";
//		
//		//Act
//		String result = vend.getCategoryMessage(input);
//		
//		//Assert
//		Assert.assertEquals("", result);
//	}
//	
//	@Test
//	public void getCategoryMessage_returns_an_empty_String_if_input_is_null() {
//		//Arrange
//		String input = null;
//				
//		//Act
//		String result = vend.getCategoryMessage(input);
//				
//		//Assert
//		Assert.assertEquals("", result);
//	}
//	
//	@Test
//	public void getProductData_returns_the_correct_message_based_on_input() {
//		//Arrange
//		String inputA1 = "A1";
//		String inputB2 = "B2";
//		String inputC3 = "C3";
//		String inputD4 = "D4";
//		
//		//Act
//		String resultA1 = vend.getProductData(inputA1);
//		String resultB2 = vend.getProductData(inputB2);
//		String resultC3 = vend.getProductData(inputC3);
//		String resultD4 = vend.getProductData(inputD4);
//		
//		//Assert
//		Assert.assertEquals("A1    Potato Crisps       $3.05 ", resultA1);
//		Assert.assertEquals("B2    Cowtales            $1.50 ", resultB2);
//		Assert.assertEquals("C3    Mountain Melter     $1.50 ", resultC3);
//		Assert.assertEquals("D4    Triplemint          $0.75 ", resultD4);
//	}
//	
}
