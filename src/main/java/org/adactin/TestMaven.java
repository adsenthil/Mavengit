package org.adactin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestMaven extends BaseClass {



	
	@Test(dataProvider="login")
	private void test(String user, String pass) throws InterruptedException {
		System.out.println("User"+user);
		System.out.println("pass"+pass);
		
			SoftAssert softAssert = new SoftAssert();
			browserLaunch("chrome");
			launcherUrl("https://adactinhotelapp.com/");
			implicitWait(20);

			Login l = new Login();
			fillTextbox(l.getuserName(), user);
			fillTextbox(l.getpassWord(), pass);
			l.getlogin().click();
			softAssert.assertTrue(getCurrentUrl().contains("SearchHotel"), "Check login");
			
			Thread.sleep(5000);
			softAssert.assertAll();
			browserQuit();
		}
	
	@DataProvider(name="login",indices= {1,2,3})
	private Object[][] getData() throws IOException{
		return getExcelDataForDataProvider();
	}

// public static void main(String[] args) throws IOException {
//	Object[][] data=getExcelDataForDataProvider();
//	System.out.println(data[1][1]);
//	for(Object[]x:data) {
//		for (Object y : x) {
//			System.out.println(y);
//		}
//	}
//}
}
