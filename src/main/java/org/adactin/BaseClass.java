package org.adactin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	public static WebDriver browserLaunch(String browserName) {
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		}
		return driver;

	}

	public static void launcherUrl(String url) {

		driver.get(url);
		driver.manage().window().maximize();

	}

	public static void implicitWait(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	public static void fillTextbox(WebElement e, String value) {
		System.out.println(value);
		e.sendKeys(value);
	}

	public static void btnClick(WebElement e) {
		e.click();
	}

	public static void browserQuit() {

		driver.quit();
	}

	public static String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public static String getAttribute(WebElement e) {
		String value = e.getAttribute("value");
		return value;

	}

	public static void moveToElement(WebElement e) {
		Actions actions = new Actions(driver);
		actions.moveToElement(e).perform();

	}

	public static void dragAndDrop(WebElement src, WebElement des) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(src, des).perform();
	}

	public static void selectByIndex(WebElement e, int index) {
		Select s = new Select(e);
		s.selectByIndex(index);

	}

	public static void selectByString(WebElement e, String i) {
		Select s = new Select(e);
		List<WebElement> options = s.getOptions();

		for (WebElement l : options) {
			String text = l.getText();
			String trim = text.trim();

			String trim2 = i.trim();
			System.out.println(text);
			System.out.println(i);
			if (trim.contains(trim2)) {
				System.out.println("equals");
				s.selectByVisibleText(text);
			}

		}

	}

	public static WebElement findWebElement(String locatorName, String locatorValue) {
		WebElement e = null;
		if (locatorName.equals("id")) {
			e = driver.findElement(By.id(locatorValue));
		} else if (locatorName.equals("name")) {
			e = driver.findElement(By.name(locatorValue));
		} else if (locatorName.equals("xpath")) {
			e = driver.findElement(By.xpath(locatorValue));
		}
		return e;
	}
	public static WebElement findWebElementUntillClickable(String locatorName, String locatorValue) {
		//WebElement e1 = null;
		if (locatorName.equals("id")) {
			WebDriverWait wait = new WebDriverWait(driver, 10); 
			WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
			return e1;
			
		} else if (locatorName.equals("name")) {
			WebDriverWait wait = new WebDriverWait(driver, 10); 
			WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
			return e1;

		} else if (locatorName.equals("xpath")) {
			WebDriverWait wait = new WebDriverWait(driver, 10); 
			WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			return e1;

		}
		return null;
		
		
	}

	public static WebElement findWebElements(String locatorName, String locatorValue) {
		WebElement e1 = null;
		if (locatorName.equals("id")) {
			List<WebElement> findElements = driver.findElements(By.id(locatorValue));
			for (WebElement e11 : findElements) {
				return e11;
			}
		} else if (locatorName.equals("name")) {
			List<WebElement> findElements = driver.findElements(By.name(locatorValue));

			for (WebElement e11 : findElements) {
				return e11;
			}
		} else if (locatorName.equals("xpath")) {
			System.out.println("xpath");
			List<WebElement> f = driver.findElements(By.xpath(locatorValue));
			// Cancel Some Bookings

			for (int i = 0; i < f.size(); i++) {
				WebElement webElement = f.get(i);
				String attribute = webElement.getAttribute("value");
				System.out.println(attribute);
				if (attribute.equals("Cancel D84W6X8H55")) {
					Actions actions = new Actions(driver);
					actions.moveToElement(webElement).click().build().perform();
					driver.switchTo().alert().accept();
				}
			}
		}
		return e1;
	}

	public static List<WebElement> findTable(String locatorName, String locatorValue) {

		if (locatorName.equals("tagName")) {
			List<WebElement> e1 = driver.findElements(By.tagName(locatorValue));
			for (WebElement webElement : e1) {
				String text = webElement.getText();
				System.out.println(text);

			}

			return (e1);
		} else if (locatorName.equals("tr")) {
			List<WebElement> e2 = driver.findElements(By.tagName(locatorValue));
			WebElement webElement = e2.get(0);
			String text = webElement.getText();
			System.out.println(text);

			return (e2);
		} else if (locatorName.equals("th")) {
			List<WebElement> e3 = driver.findElements(By.tagName(locatorValue));
			WebElement webElement = e3.get(0);
			String text = webElement.getText();
			System.out.println(text);

			return (e3);
		} else if (locatorName.equals("td")) {
			List<WebElement> e3 = driver.findElements(By.tagName(locatorValue));
			WebElement webElement = e3.get(0);
			String text = webElement.getText();
			System.out.println(text);

			return (e3);
		}

		return null;

	}

	public static void openInNewWindow() throws AWTException, InterruptedException {
		Actions actions = new Actions(driver);
		actions.contextClick().perform();
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_DOWN);
		r.keyRelease(KeyEvent.VK_DOWN);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
	}

	public static void windowHandles(int winId) {
		Set<String> set = driver.getWindowHandles();
		ArrayList<Object> list = new ArrayList<>();

		list.addAll(set);
		Object object = list.get(winId);
		driver.switchTo().window((String) object);

	}

	public static void javaScriptScroll(WebElement e) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", e);

	}

	public static void fillTextbox_UsingJscript(WebElement e, String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('value','" + text + "')", e);

	}

	public static void printTable(WebElement e) {
		String text = e.getText();
		System.out.println(text);
	}

	public static void screenShot(String storeLocation) throws IOException {
		TakesScreenshot tk2 = (TakesScreenshot) driver;
		File src2 = tk2.getScreenshotAs(OutputType.FILE);
		System.out.println(src2);
		long time = System.currentTimeMillis();

		File des2 = new File(storeLocation + time + ".png");
		FileUtils.copyFile(src2, des2);
		System.out.println("done");

	}

	public static String getExcelData2(int rowNo, int cellNo) throws IOException {
		File loc = new File(
				"C:\\Users\\Senthil\\eclipse-workspace\\selinium new\\TestMaven11AdactinTestng\\File\\MECH_IV-Year_D_AttendanceReport_05_08_2021.xls");
		FileInputStream st = new FileInputStream(loc);
		Workbook w = new HSSFWorkbook(st);
		Sheet sheet = w.getSheet("Adactin");
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);

		int type = cell.getCellType();
		System.out.println(type);
		// type 1=String
		// type 0=Numbers,Date
		String value;
		if (type == 1) {
			value = cell.getStringCellValue();
			System.out.println(value);
			return value;
		} else {
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
				value = sf.format(date);
				System.out.println(value);
				return value;
			} else {
				double num = cell.getNumericCellValue();
				long ln = (long) num;
				value = String.valueOf(ln);
				System.out.println(value);
				return value;
			}
		}

	}

	public static String getExcelData2(String fileName, String sheetName, int rowNo, int cellNo) throws IOException {
		File loc = new File(fileName + ".xls");
		FileInputStream st = new FileInputStream(loc);
		Workbook w = new HSSFWorkbook(st);
		Sheet sheet = w.getSheet(sheetName);
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);
		@SuppressWarnings("deprecation")
		int type = cell.getCellType();
		System.out.println(type);
		// type 1=String
		// type 0=Numbers,Date
		String value = null;
		if (type == 1) {
			value = cell.getStringCellValue();
			System.out.println(value);
		} else {
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy");
				value = sf.format(date);
				System.out.println(value);
			} else {
				double num = cell.getNumericCellValue();
				long ln = (long) num;
				value = String.valueOf(ln);
				System.out.println(value);
			}
		}
		return value;

	}
	public static Object[][] getExcelDataForDataProvider() throws IOException {
		File loc = new File(System.getProperty("user.dir")+"/File/1.xls");
		FileInputStream st = new FileInputStream(loc);
		Workbook w = new HSSFWorkbook(st);
		Sheet s = w.getSheet("Adactin");
		Row r = s.getRow(1);
		int rowCount = s.getPhysicalNumberOfRows();
		int cellCount = r.getPhysicalNumberOfCells();
		Object[][] obj=new Object[rowCount][cellCount];
		for (int i = 0; i < rowCount; i++) {
			Row row = s.getRow(i);
			for (int j = 0; j < cellCount; j++) {
				Cell cell = row.getCell(j);
			
		int type = cell.getCellType();
		// type 1=String
		// type 0=Numbers,Date
		String value=null;
		if (type == 1) {
			value = cell.getStringCellValue();
//			System.out.println(value);
			//return value;
		} else {
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
				value = sf.format(date);
//				System.out.println(value);
				//return value;
			} else {
				double num = cell.getNumericCellValue();
				long ln = (long) num;
				value = String.valueOf(ln);
//				System.out.println(value);
				//return value;
			}
		}
     obj[i][j]=value;
			}
		}
		return obj;
	}

	public static String getExcelData2Date(int rowNo, int cellNo) throws IOException {
		File loc = new File(
				"E:\\1.xls");
		FileInputStream st = new FileInputStream(loc);
		Workbook w = new HSSFWorkbook(st);
		Sheet sheet = w.getSheet("Adactin");
		Row row = sheet.getRow(rowNo);
		Cell cell = row.getCell(cellNo);

		int type = cell.getCellType();
		System.out.println(type);
		// type 1=String
		// type 0=Numbers,Date
		String value = null;
		value = cell.getStringCellValue();
		System.out.println(value);
		return value;
	}

	public static void writeExcel(String fileLocation, String sheetName, String value) throws IOException {
		File loc = new File(fileLocation);
		Workbook w = new HSSFWorkbook();
		// Create Sheet
		Sheet sheet = w.createSheet(sheetName);
		// Create row
		Row row = sheet.createRow(5);
		// create cell
		Cell cell = row.createCell(4);
		cell.setCellValue(value);
		FileOutputStream fo = new FileOutputStream(loc);
		w.write(fo);
//		Row row1 = sheet.createRow(1);
//		// create cell
//		Cell cell1 = row1.createCell(4);
//		
//		cell.setCellValue("Booking ID");
//		FileOutputStream fo1 = new FileOutputStream(loc);
//		w.write(fo1);
//		

	}
	public static void writeExistingExcel(String fileLocation, String sheetName, String value) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(fileLocation);
		 HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
		 HSSFSheet sheet = workbook.getSheet(sheetName);
		 HSSFRow row = sheet.createRow(1);
		 HSSFCell cell = row.createCell(4);
		 cell.setCellValue("Booking ID");
		 FileOutputStream fileOutputStream = new FileOutputStream(fileLocation);
		 workbook.write(fileOutputStream);
		 
		 
		 

	}
	public static void writeTextFile(String fileLocation,String value) throws IOException {
		File f = new File(fileLocation);
		f.createNewFile();
		FileUtils.write(f,value, false);
		FileUtils.write(f, value, true);
		FileUtils.write(f, " Booking ID", true);
		FileUtils.write(f, " Welcome to  Class C\n", true);
		FileUtils.write(f, " Welcome to  Class D\n", true);
		FileUtils.write(f, " Welcome to  Class E\n", true);
		FileUtils.write(f, " Welcome to  Class F\n", true);
		FileUtils.write(f, " Welcome to  Class G\n", true);
		FileUtils.write(f, " Welcome to  Class  H\n", true);

		List<String> readLines = FileUtils.readLines(f);
		int size = readLines.size();
		System.out.println("Number of Rows " + size);
		System.out.println("----------Number of times String Class-------");
		int b=0;
		for (int i = 0; i < size; i++) {
			
			{
				String string = readLines.get(i);
				if(string.contains("Class"))
					++b;
				
			}
		}
		System.out.println(b);
	}


	
}
