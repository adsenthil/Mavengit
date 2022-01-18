package org.adactin;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Data {
	@DataProvider(name="login")
	
	private Object[][] getData(){
		Object[][] obj=new Object[][] {
			
				{"senthil","2245"},
				{"Kumar","2644"},
				{"sfbdbjh","31321"},
				{"adsenthil","mugavari"}
			};
			return obj;
	}

}
