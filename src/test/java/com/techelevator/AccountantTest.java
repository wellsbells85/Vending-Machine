package com.techelevator;

import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountantTest {
	
	private Accountant account;
	private Map<String, String[]> result;
	
	@Before
	public void createAccountant() {
		account = new Accountant();
	}
	
	@After
	public void clearAccountant() {
		account.makeChange();
	}
		
	@Test
	public void check_funds_valids_both_paths_correctly() {
		Assert.assertFalse(account.checkFunds("2.00"));
		account.feedMoney("2");
		Assert.assertTrue(account.checkFunds("2.00"));
	}
	
	@Test
	public void display_current_money_functions_correctly() {
		Assert.assertEquals("Current Money Provided:   $0.00", account.displayCurrentMoney());
		account.feedMoney("1");
		Assert.assertEquals("Current Money Provided:   $1.00", account.displayCurrentMoney());
		account.feedMoney("5");
		Assert.assertEquals("Current Money Provided:   $6.00", account.displayCurrentMoney());
		account.feedMoney(null);
		Assert.assertEquals("Current Money Provided:   $6.00", account.displayCurrentMoney());
		account.feedMoney("");
		Assert.assertEquals("Current Money Provided:   $6.00", account.displayCurrentMoney());
		account.feedMoney("ssss");
		Assert.assertEquals("Current Money Provided:   $6.00", account.displayCurrentMoney());
		account.feedMoney("@#$");
		Assert.assertEquals("Current Money Provided:   $6.00", account.displayCurrentMoney());
		
	}
	
	@Test
	public void feed_money_functions_correctly() {
		result = account.feedMoney(null);
		for(String key: result.keySet()) {
			Assert.assertEquals("\nPlease Retry Bill Entry\n", key);
		}
		result = account.feedMoney("");
		for(String key: result.keySet()) {
			Assert.assertEquals("\nPlease Retry Bill Entry\n", key);
		}
		result = account.feedMoney("!#$%@");
		for(String key: result.keySet()) {
			Assert.assertEquals("\nPlease Retry Bill Entry\n", key);
		}
		result = account.feedMoney("abCD");
		for(String key: result.keySet()) {
			Assert.assertEquals("\nPlease Retry Bill Entry\n", key);
		}
		result = account.feedMoney("3");
		for(String key: result.keySet()) {
			Assert.assertEquals("\nPlease Insert Valid Bill\n", key);
		}
		result = account.feedMoney("1");
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals(" ", key);
			Assert.assertEquals("0", money[0]);
			Assert.assertEquals("1", money[1]);
		}
		result = account.feedMoney("2");
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals(" ", key);
			Assert.assertEquals("1", money[0]);
			Assert.assertEquals("3", money[1]);
		}
		result = account.feedMoney("5");
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals(" ", key);
			Assert.assertEquals("3", money[0]);
			Assert.assertEquals("8", money[1]);
		}
		result = account.feedMoney("10");
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals(" ", key);
			Assert.assertEquals("8", money[0]);
			Assert.assertEquals("18", money[1]);
		}
		
	}

	@Test
	public void purchase_returns_a_valid_map() {
		account.feedMoney("10");
		result = account.purchase("ZZ", "testProduct", "9.95");
		for(String key: result.keySet()) {
			String[] information = result.get(key);
			Assert.assertEquals(" ", key);
			Assert.assertEquals("testProduct", information[0]);
			Assert.assertEquals("ZZ", information[1]);
			Assert.assertEquals("10", information[2]);
			Assert.assertEquals("0.05", information[3]);
		}
		
	}

	@Test
	public void make_change_returns_correct_map_for_all_paths() {
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("No Change Necessary", key);
			Assert.assertEquals("0", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".95");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 1 Nickel", key);
			Assert.assertEquals("0.05", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".90");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 1 Dime", key);
			Assert.assertEquals("0.10", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".85");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 1 Dime and 1 Nickel", key);
			Assert.assertEquals("0.15", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".80");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 2 Dimes", key);
			Assert.assertEquals("0.20", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".75");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 1 Quarter", key);
			Assert.assertEquals("0.25", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".70");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 1 Quarter and 1 Nickel", key);
			Assert.assertEquals("0.30", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".65");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 1 Quarter and 1 Dime", key);
			Assert.assertEquals("0.35", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".60");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 1 Quarter, 1 Dime, and 1 Nickel", key);
			Assert.assertEquals("0.40", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".55");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 1 Quarter and 2 Dimes", key);
			Assert.assertEquals("0.45", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".50");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 2 Quarters", key);
			Assert.assertEquals("0.50", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".45");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 2 Quarters and 1 Nickel", key);
			Assert.assertEquals("0.55", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".40");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 2 Quarters and 1 Dime", key);
			Assert.assertEquals("0.60", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".35");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 2 Quarters, 1 Dime, and 1 Nickel", key);
			Assert.assertEquals("0.65", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".30");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 2 Quarters and 2 Dimes", key);
			Assert.assertEquals("0.70", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".25");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 3 Quarters", key);
			Assert.assertEquals("0.75", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".20");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 3 Quarters and 1 Nickel", key);
			Assert.assertEquals("0.80", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".15");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 3 Quarters and 1 Dime", key);
			Assert.assertEquals("0.85", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".10");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 3 Quarters, 1 Dime, and 1 Nickel", key);
			Assert.assertEquals("0.90", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		account.purchase("ZZ", "testProduct", ".05");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 3 Quarters and 2 Dimes", key);
			Assert.assertEquals("0.95", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		account.feedMoney("1");
		result = account.makeChange();
		for(String key: result.keySet()) {
			String[] money = result.get(key);
			Assert.assertEquals("Change is 4 Quarters", key);
			Assert.assertEquals("1", money[0]);
			Assert.assertEquals("0", money[1]);
		}
		
	}
	
	
	
	
	
	
	
}
