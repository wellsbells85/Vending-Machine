package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductSelectorTest {
	
	private ProductSelector ps;
	
	@Before
	public void setup() {
		ps = new ProductSelector(); 
	}

	@Test
	public void initializeInventory_fills_map_with_all_items_and_stocks_with_5_each() {
		//Arrange
		String slotA3 = "A3";
		String slotB4 = "B4";
		String slotC3 = "C3";
		String slotD1 = "D1";
		ps.initializeInventory();
		int expectedResult = 5;
		
		//Act
		int resultA3 = ps.getInventoryCount(slotA3);
		int resultB4 = ps.getInventoryCount(slotB4);
		int resultC3 = ps.getInventoryCount(slotC3);
		int resultD1 = ps.getInventoryCount(slotD1);
		
		//Assert
		Assert.assertEquals(expectedResult, resultA3);
		Assert.assertEquals(expectedResult, resultB4);
		Assert.assertEquals(expectedResult, resultC3);
		Assert.assertEquals(expectedResult, resultD1);
	}
	
	@Test
	public void validateSlot_returns_true_when_there_are_items_in_stock() {
		//Arrange
		String slotA3 = "A3";
		String slotB4 = "B4";
		String slotC3 = "C3";
		String slotD1 = "D1";
		ps.initializeInventory();
		
		//Act
		boolean resultA3 = ps.validateSlot(slotA3);
		boolean resultB4 = ps.validateSlot(slotB4);
		boolean resultC3 = ps.validateSlot(slotC3);
		boolean resultD1 = ps.validateSlot(slotD1);
		
		//Assert
		Assert.assertTrue("Should have returned true when items are in stock.", resultA3);
		Assert.assertTrue("Should have returned true when items are in stock.", resultB4);
		Assert.assertTrue("Should have returned true when items are in stock.", resultC3);
		Assert.assertTrue("Should have returned true when items are in stock.", resultD1);
	}
	
	@Test
	public void validateSlot_returns_false_when_the_items_are_not_in_stock() {
		//Arrange
		String slotA3 = "A3";
		String slotB4 = "B4";
		String slotC3 = "C3";
		String slotD1 = "D1";
		
		//Act
		boolean resultA3 = ps.validateSlot(slotA3);
		boolean resultB4 = ps.validateSlot(slotB4);
		boolean resultC3 = ps.validateSlot(slotC3);
		boolean resultD1 = ps.validateSlot(slotD1);
		
		//Assert
		Assert.assertFalse("Should have returned false when items are not in stock.", resultA3);
		Assert.assertFalse("Should have returned false when items are not in stock.", resultB4);
		Assert.assertFalse("Should have returned false when items are not in stock.", resultC3);
		Assert.assertFalse("Should have returned false when items are not in stock.", resultD1);
	}
	
	@Test
	public void adjustInventory_reduces_inventory_by_1() {
		//Arrange
		String slotA3 = "A3";
		String slotB4 = "B4";
		String slotC3 = "C3";
		String slotD1 = "D1";
		ps.initializeInventory();
		int expectedResult = 4;
		
		//Act
		int resultA3 = ps.adjustInventory(slotA3);
		int resultB4 = ps.adjustInventory(slotB4);
		int resultC3 = ps.adjustInventory(slotC3);
		int resultD1 = ps.adjustInventory(slotD1);
		
		//Assert
		Assert.assertEquals(expectedResult, resultA3);
		Assert.assertEquals(expectedResult, resultB4);
		Assert.assertEquals(expectedResult, resultC3);
		Assert.assertEquals(expectedResult, resultD1);
	}
	
	@Test
	public void getAllVendingData_returns_the_correct_information_properly_formatted() {
		//Arrange
		String slotA3 = "A3";
		String slotB4 = "B4";
		String slotC3 = "C3";
		String slotD1 = "D1";
		ps.initializeInventory();
		String expectedResultA3 = "A3    Grain Waves         $2.75 ";
		String expectedResultB4 = "B4    Crunchie            $1.75 ";
		String expectedResultC3 = "C3    Mountain Melter     $1.50 ";
		String expectedResultD1 = "D1    U-Chews             $0.85 ";
		
		//Act
		String resultA3 = ps.getAllVendingData(slotA3);
		String resultB4 = ps.getAllVendingData(slotB4);
		String resultC3 = ps.getAllVendingData(slotC3);
		String resultD1 = ps.getAllVendingData(slotD1);
		
		//Assert
		Assert.assertEquals(expectedResultA3, resultA3);
		Assert.assertEquals(expectedResultB4, resultB4);
		Assert.assertEquals(expectedResultC3, resultC3);
		Assert.assertEquals(expectedResultD1, resultD1);
	}

	@Test
	public void displayInventory_returns_SOLD_OUT_when_no_items_are_in_stock() {
		//Arrange
		String slotA3 = "A3";
		String slotB4 = "B4";
		String slotC3 = "C3";
		String slotD1 = "D1";
		ps.initializeInventory();
		
		ps.adjustInventory(slotA3);
		ps.adjustInventory(slotA3);
		ps.adjustInventory(slotA3);
		ps.adjustInventory(slotA3);
		ps.adjustInventory(slotA3);
		
		ps.adjustInventory(slotB4);
		ps.adjustInventory(slotB4);
		ps.adjustInventory(slotB4);
		ps.adjustInventory(slotB4);
		ps.adjustInventory(slotB4);
		
		ps.adjustInventory(slotC3);
		ps.adjustInventory(slotC3);
		ps.adjustInventory(slotC3);
		ps.adjustInventory(slotC3);
		ps.adjustInventory(slotC3);
		
		ps.adjustInventory(slotD1);
		ps.adjustInventory(slotD1);
		ps.adjustInventory(slotD1);
		ps.adjustInventory(slotD1);
		ps.adjustInventory(slotD1);
		
		String expectedResultA3 = "SOLD OUT";
		String expectedResultB4 = "SOLD OUT";
		String expectedResultC3 = "SOLD OUT";
		String expectedResultD1 = "SOLD OUT";
		
		//Act
		String resultA3 = ps.displayInventory(slotA3);
		String resultB4 = ps.displayInventory(slotB4);
		String resultC3 = ps.displayInventory(slotC3);
		String resultD1 = ps.displayInventory(slotD1);
		
		//Assert
		Assert.assertEquals(expectedResultA3, resultA3);
		Assert.assertEquals(expectedResultB4, resultB4);
		Assert.assertEquals(expectedResultC3, resultC3);
		Assert.assertEquals(expectedResultD1, resultD1);
	}
	
	@Test
	public void displayInventory_returns_correct_number_when_items_are_in_stock() {
		//Arrange
		String slotA3 = "A3";
		String slotB4 = "B4";
		String slotC3 = "C3";
		String slotD1 = "D1";
		ps.initializeInventory();
		
		String expectedResultA3 = "5";
		String expectedResultB4 = "5";
		String expectedResultC3 = "5";
		String expectedResultD1 = "5";
		
		//Act
		String resultA3 = ps.displayInventory(slotA3);
		String resultB4 = ps.displayInventory(slotB4);
		String resultC3 = ps.displayInventory(slotC3);
		String resultD1 = ps.displayInventory(slotD1);
		
		//Assert
		Assert.assertEquals(expectedResultA3, resultA3);
		Assert.assertEquals(expectedResultB4, resultB4);
		Assert.assertEquals(expectedResultC3, resultC3);
		Assert.assertEquals(expectedResultD1, resultD1);
	}
	
}
