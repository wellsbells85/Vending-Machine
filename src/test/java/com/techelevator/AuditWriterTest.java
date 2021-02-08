package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuditWriterTest {

	private AuditWriter aw;
	
	@Before
	public void setup() {
		aw = new AuditWriter();
	}
//	salesReportInitializer
//	getSalesMap
	// these are the only two methods that might be testable, but I can't think of a way to confirm the data is in the map because we don't have access to it from here
	//we would have to write 2 new methods that would only be for the purpose of testing

	
//	@Test
//	public void () {
//		//Arrange
//		
//		
//		//Act
//		
//		
//		//Assert
//		
//	}
	
}
