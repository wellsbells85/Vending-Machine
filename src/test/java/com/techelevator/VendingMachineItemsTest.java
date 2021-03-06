package com.techelevator;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemsTest {
	
	private VendingMachineItem vend;
	private LinkedHashMap<String, LinkedHashMap<String, LinkedList<VendingMachineItem>>> inventoryMap = new LinkedHashMap<>();
	
	
	@Before
	public void setup() {
		vend = new VendingMachineItem();
		vend.initializeVendingMachineList();
	}
	
	@Test
	public void getName_returns_correct_item_names() {
		Assert.assertEquals("", vend.getName("BB"));
		Assert.assertEquals("", vend.getName(null));
		Assert.assertEquals("Potato Crisps", vend.getName("A1"));
		Assert.assertEquals("Stackers", vend.getName("A2"));
		Assert.assertEquals("Grain Waves", vend.getName("A3"));
		Assert.assertEquals("Cloud Popcorn", vend.getName("A4"));
		Assert.assertEquals("Moonpie", vend.getName("B1"));
		Assert.assertEquals("Cowtales", vend.getName("B2"));
		Assert.assertEquals("Wonka Bar", vend.getName("B3"));
		Assert.assertEquals("Crunchie", vend.getName("B4"));
		Assert.assertEquals("Cola", vend.getName("C1"));
		Assert.assertEquals("Dr. Salt", vend.getName("C2"));
		Assert.assertEquals("Mountain Melter", vend.getName("C3"));
		Assert.assertEquals("Heavy", vend.getName("C4"));
		Assert.assertEquals("U-Chews", vend.getName("D1"));
		Assert.assertEquals("Little League Chew", vend.getName("D2"));
		Assert.assertEquals("Chiclets", vend.getName("D3"));
		Assert.assertEquals("Triplemint", vend.getName("D4"));		
	}
	
	@Test
	public void getPrice_returns_correct_prices() {
		Assert.assertEquals("", vend.getName("BB"));
		Assert.assertEquals("", vend.getName(null));
		Assert.assertEquals("3.05", vend.getPrice("A1"));
		Assert.assertEquals("1.45", vend.getPrice("A2"));
		Assert.assertEquals("2.75", vend.getPrice("A3"));
		Assert.assertEquals("3.65", vend.getPrice("A4"));
		Assert.assertEquals("1.80", vend.getPrice("B1"));
		Assert.assertEquals("1.50", vend.getPrice("B2"));
		Assert.assertEquals("1.50", vend.getPrice("B3"));
		Assert.assertEquals("1.75", vend.getPrice("B4"));
		Assert.assertEquals("1.25", vend.getPrice("C1"));
		Assert.assertEquals("1.50", vend.getPrice("C2"));
		Assert.assertEquals("1.50", vend.getPrice("C3"));
		Assert.assertEquals("1.50", vend.getPrice("C4"));
		Assert.assertEquals("0.85", vend.getPrice("D1"));
		Assert.assertEquals("0.95", vend.getPrice("D2"));
		Assert.assertEquals("0.75", vend.getPrice("D3"));
		Assert.assertEquals("0.75", vend.getPrice("D4"));
	}
	
	@Test
	public void getCount_and_setCount_do_not_allow_negative_inventory() {
		Assert.assertEquals(5, vend.getCount("A1"));
		vend.setCount("A1");
		Assert.assertEquals(4, vend.getCount("A1"));
		vend.setCount("A1");
		Assert.assertEquals(3, vend.getCount("A1"));
		vend.setCount("A1");
		Assert.assertEquals(2, vend.getCount("A1"));
		vend.setCount("A1");
		Assert.assertEquals(1, vend.getCount("A1"));
		vend.setCount("A1");
		Assert.assertEquals(0, vend.getCount("A1"));
		vend.setCount("A1");
		Assert.assertEquals(0, vend.getCount("A1"));
		vend.setCount("A1");
		Assert.assertEquals(0, vend.getCount("A1"));
	}
	
	@Test
	public void key_validate_returns_valid_booleans() {
		Assert.assertTrue(vend.validateSlot("A1"));
		Assert.assertTrue(vend.validateSlot("A2"));
		Assert.assertTrue(vend.validateSlot("A3"));
		Assert.assertTrue(vend.validateSlot("A4"));
		Assert.assertTrue(vend.validateSlot("B1"));
		Assert.assertTrue(vend.validateSlot("B2"));
		Assert.assertTrue(vend.validateSlot("B3"));
		Assert.assertTrue(vend.validateSlot("B4"));
		Assert.assertTrue(vend.validateSlot("C1"));
		Assert.assertTrue(vend.validateSlot("C2"));
		Assert.assertTrue(vend.validateSlot("C3"));
		Assert.assertTrue(vend.validateSlot("C4"));
		Assert.assertTrue(vend.validateSlot("D1"));
		Assert.assertTrue(vend.validateSlot("D2"));
		Assert.assertTrue(vend.validateSlot("D3"));
		Assert.assertTrue(vend.validateSlot("D4"));
		Assert.assertFalse(vend.validateSlot(null));
		Assert.assertFalse(vend.validateSlot(""));
		Assert.assertFalse(vend.validateSlot("123"));
	}
	
	@Test
	public void displayItemsAndInventory_is_correct_beginning_and_end() {
		Assert.assertEquals(
		" A1   Potato Crisps       $3.05     5   \n A2   Stackers            $1.45     5   \n"+
		" A3   Grain Waves         $2.75     5   \n A4   Cloud Popcorn       $3.65     5   \n"+
		" B1   Moonpie             $1.80     5   \n B2   Cowtales            $1.50     5   \n"+
		" B3   Wonka Bar           $1.50     5   \n B4   Crunchie            $1.75     5   \n"+
		" C1   Cola                $1.25     5   \n C2   Dr. Salt            $1.50     5   \n"+
		" C3   Mountain Melter     $1.50     5   \n C4   Heavy               $1.50     5   \n"+
		" D1   U-Chews             $0.85     5   \n D2   Little League Chew  $0.95     5   \n"+
		" D3   Chiclets            $0.75     5   \n D4   Triplemint          $0.75     5   \n",
		vend.displayItemsAndInventory());		
		for(int i = 0; i < 5; i++) {
			vend.setCount("A1");
			vend.setCount("A2");
			vend.setCount("A3");
			vend.setCount("A4");
			vend.setCount("B1");
			vend.setCount("B2");
			vend.setCount("B3");
			vend.setCount("B4");
			vend.setCount("C1");
			vend.setCount("C2");
			vend.setCount("C3");
			vend.setCount("C4");
			vend.setCount("D1");
			vend.setCount("D2");
			vend.setCount("D3");
			vend.setCount("D4");
		}
		Assert.assertEquals(
				" A1   Potato Crisps       $3.05  SOLD OUT\n A2   Stackers            $1.45  SOLD OUT\n"+
				" A3   Grain Waves         $2.75  SOLD OUT\n A4   Cloud Popcorn       $3.65  SOLD OUT\n"+
				" B1   Moonpie             $1.80  SOLD OUT\n B2   Cowtales            $1.50  SOLD OUT\n"+
				" B3   Wonka Bar           $1.50  SOLD OUT\n B4   Crunchie            $1.75  SOLD OUT\n"+
				" C1   Cola                $1.25  SOLD OUT\n C2   Dr. Salt            $1.50  SOLD OUT\n"+
				" C3   Mountain Melter     $1.50  SOLD OUT\n C4   Heavy               $1.50  SOLD OUT\n"+
				" D1   U-Chews             $0.85  SOLD OUT\n D2   Little League Chew  $0.95  SOLD OUT\n"+
				" D3   Chiclets            $0.75  SOLD OUT\n D4   Triplemint          $0.75  SOLD OUT\n",
				vend.displayItemsAndInventory());		
	}
	
	@Test
	public void displayProducts_is_showing_correct_information() {
		 Assert.assertEquals(
		 " A1   Potato Crisps       $3.05 \n A2   Stackers            $1.45 \n"+   
		 " A3   Grain Waves         $2.75 \n A4   Cloud Popcorn       $3.65 \n"+  
		 " B1   Moonpie             $1.80 \n B2   Cowtales            $1.50 \n"+  
		 " B3   Wonka Bar           $1.50 \n B4   Crunchie            $1.75 \n"+  
		 " C1   Cola                $1.25 \n C2   Dr. Salt            $1.50 \n"+   
		 " C3   Mountain Melter     $1.50 \n C4   Heavy               $1.50 \n"+   
		 " D1   U-Chews             $0.85 \n D2   Little League Chew  $0.95 \n"+   
		 " D3   Chiclets            $0.75 \n D4   Triplemint          $0.75 \n",
		 vend.displayProducts());
	}
	
	@Test
	public void getSoldProduct_displays_correctly_for_all_four_categories() {
		String a1 = vend.getSoldProduct("A1");
		String b1 = vend.getSoldProduct("B1");
		String c1 = vend.getSoldProduct("C1");
		String d1 = vend.getSoldProduct("D1");
		Assert.assertEquals(" A1   Potato Crisps       $3.05    \"Crunch Crunch, Yum!\"", a1);
		Assert.assertEquals(" B1   Moonpie             $1.80    \"Munch Munch, Yum!\"", b1);
		Assert.assertEquals(" C1   Cola                $1.25    \"Glug Glug, Yum!\"", c1);
		Assert.assertEquals(" D1   U-Chews             $0.85    \"Chew Chew, Yum!\"", d1);
	}
	
	
}
