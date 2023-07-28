package com.telliant.core.web;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.paulhammant.ngwebdriver.NgWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverRoot {

	public static WebDriver driver;
	public static WebElement element;
	public static Properties ObjRepo;
	public static Properties config;
	private static String browserName;
	public static NgWebDriver ngWebDriver;
	public ChromeOptions options;
	public static String ele3;
	public static String ele4;
	DesiredCapabilities cap = new DesiredCapabilities();
	

	public static AppiumDriver<MobileElement> appiumDriver;
	
	public DesiredCapabilities getCapabilities() {
		try {
			BufferedReader r = new BufferedReader(new FileReader(""));
			String line = r.readLine();
			while (line != null) {
				String[] c = line.split("=");
				cap.setCapability(c[0], c[1]);
				line = r.readLine();

			}
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cap;

	}

	public AppiumDriver<MobileElement> launchApp() throws MalformedURLException {
		if (config.getProperty("platform_name").equalsIgnoreCase("android")) {
			appiumDriver = new AndroidDriver<MobileElement>(new URL(config.getProperty("appium.server.url")),
					getCapabilities());
		} else if (config.getProperty("platform_name").equalsIgnoreCase("iOS")) {
			appiumDriver = new IOSDriver<MobileElement>(new URL(config.getProperty("appium.server.url")),
					getCapabilities());
		}
		appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return appiumDriver;
	}

	public static boolean clickElement(String locator) {
		try {
			boolean elementIsClickable = getElement(locator).isEnabled();
			while (elementIsClickable) {
				waitForElementVisible(locator);
				waitForElementClickable(locator);
//				JAVASCRIPTEXECUTOR JSE = (JAVASCRIPTEXECUTOR)DRIVER;
//				JSE.EXECUTESCRIPT("ARGUMENTS[0].CLICK()", GETELEMENT(LOCATOR));
				getElement(locator).click();
				return true;
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Element is not enabled");
		}
		return false;
	}

	public static void highlightElement(String locator) {
		for (int i = 0; i < 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute(‘style’, arguments[1]);", getElement(locator),
					"color: solid red; border: 6px solid yellow;");
			js.executeScript("arguments[0].setAttribute(‘style’, arguments[1]);", getElement(locator), "");

		}

	}

	public void type(String locator, String textToType) {
		WebElement ele2 = getElement(locator);
		//scrollToElement(locator);
		try {
			if (ele2.isEnabled()) {
				waitForElementVisible(locator);
				waitForElementClickable(locator);
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click()", getElement(locator));
				ele2.clear();
				ele2.sendKeys(textToType);
				Reporter.log("Entered Text: " + textToType);
			}
		} catch (ElementNotVisibleException e) {
			e.printStackTrace();
		}

	}
	
	
	public static WebElement getElement(String locator) {
		String repolocator = ObjRepo.getProperty(locator).trim();
		String[] tokens = repolocator.split(";");
		String locatorType = tokens[tokens.length - 2];
		String strlocator = tokens[tokens.length - 1];
		WebElement webElement = null;

		try {

			if (locatorType.equalsIgnoreCase("XPATH")) {

				webElement = driver.findElement(By.xpath(strlocator));

			} else if (locatorType.equalsIgnoreCase("ID")) {

				webElement = driver.findElement(By.id(strlocator));

			} else if (locatorType.equalsIgnoreCase("NAME")) {

				webElement = driver.findElement(By.name(strlocator));

			} else if (locatorType.equalsIgnoreCase("CSS")) {

				webElement = driver.findElement(By.cssSelector(strlocator));

			} else if (locatorType.equalsIgnoreCase("LINKTEXT")) {

				webElement = driver.findElement(By.linkText(strlocator));
			}

		} catch (NoSuchElementException e) {

			e.printStackTrace();
			System.out.println(strlocator + " Element not found");
			// Assert.fail(strlocator + " Element not found");

		}

		return webElement;

	}

	public static List<WebElement> getElements(String locator) {
		String repolocator = ObjRepo.getProperty(locator).trim();
		String[] tokens = repolocator.split(";");
		String locatorType = tokens[tokens.length - 2];
		String strlocator = tokens[tokens.length - 1];
		List<WebElement> webElement = null;

		try {

			if (locatorType.equalsIgnoreCase("XPATH")) {

				webElement = driver.findElements(By.xpath(strlocator));

			} else if (locatorType.equalsIgnoreCase("ID")) {

				webElement = driver.findElements(By.id(strlocator));

			} else if (locatorType.equalsIgnoreCase("NAME")) {

				webElement = driver.findElements(By.name(strlocator));

			} else if (locatorType.equalsIgnoreCase("CSS")) {

				webElement = driver.findElements(By.cssSelector(strlocator));

			} else if (locatorType.equalsIgnoreCase("LINKTEXT")) {

				webElement = driver.findElements(By.linkText(strlocator));
			}

		} catch (NoSuchElementException e) {

			e.printStackTrace();
			System.out.println(strlocator + " Element not found");
			// Assert.fail(strlocator + " Element not found");

		}

		return webElement;

	}

	public static void waitForElementClickable2(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);//
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locator)));
	}

	public static void waitForElementClickable(String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(getElement(locator)));
			
	}
	public static void waitForElementVisible(String locator) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
	public static void waitForElementVisibleof(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOf(getElement(locator)));

		}
	
	public static void scrollToElement(String locator) {
		element = getElement(locator);
		try {
			int x = element.getLocation().getX();
			int y = element.getLocation().getY();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("window.scrollBy(" + (x - 200) + "," + (y - 200)
			// + ")");
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			System.err.println("Scrolling to element is not working ");
		}
	}
	
	public static void ScrollElement2() {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
	}
	
	
	  public static void ScrollUp() { 
		  JavascriptExecutor js= (JavascriptExecutor)driver; 
		  js.executeScript("window.scrollBy(0,0)", "") ;
	}

	public static void pressKeyDown(String locator) {
		element = getElement(locator);
		element.sendKeys(Keys.DOWN);
	}

	public void pressKeyEnter(String locator) {
		element = getElement(locator);
		element.sendKeys(Keys.ENTER);
	}

	public static void pressKeyUp(String locator) {
		element = getElement(locator);
		element.sendKeys(Keys.UP);
	}

	public static void moveToTab(String locator) {
		element = getElement(locator);
		element.sendKeys(Keys.chord(Keys.ALT, Keys.TAB));
	}

	public static void pressTab(String locator) {
		element = getElement(locator);
		element.sendKeys(Keys.TAB);
	}

	public static void navigate_forward() {
		driver.navigate().forward();
	}

	public static void navigate_back() {
		driver.navigate().back();
	}

	public static void refresh() {
		driver.navigate().refresh();
	}

	public static boolean checkAlertAccept() {
		try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);
			a.accept();
			return true;
		} catch (Exception e) {
			System.out.println("no alert ");
			return false;

		}
	}

	public static boolean checkAlertDismiss() {
		try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);
			a.dismiss();
			return true;
		} catch (Exception e) {
			System.out.println("no alert ");
			return false;

		}
	}

	public WebDriver launchBrowser() {
		browserName = config.getProperty("browser");
		switch (browserName) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", "./drivers/firefox/geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("Launching Browser: " + browserName);
			break;
		/*
		 * case "chrome": System.setProperty("webdriver.chrome.driver",
		 * "./drivers/chrome/chromedriver.exe"); driver = new ChromeDriver();
		 * System.out.println("Launching " + browserName + " Browser");
		 * Reporting.testReport.get().pass("Launching " + browserName +
		 * " Browser"); break;
		 */
		case "chrome":
			WebDriverManager.chromedriver().setup();
			options = new ChromeOptions();
			Map<String, Object> prefs= new HashMap<String, Object>();
			prefs.put("download.default_directory", System.getProperty("user.dir")+"\\Downloads\\");
			options.setExperimentalOption("prefs", prefs);
			//options.setExperimentalOption("useAutomationExtension", false);
			//options.setExperimentalOption(browserName, ObjRepo);
			driver = new ChromeDriver(options);
			break;
		case "ie":
			System.setProperty("webdriver.edge.driver", "./drivers/edge/msedgedriver.exe");
			driver = new EdgeDriver();
			System.out.println("Launching Browser: " + browserName);
			break;
		}
		return driver;
	}

	public static void launchURL(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
		ngWebDriver.waitForAngularRequestsToFinish();
		//Assert.assertEquals(driver.getCurrentUrl(), url);
	}

	public static void mouseOver(String locator) {
		element = getElement(locator);
		Actions actObj = new Actions(driver);
		actObj.moveToElement(element).build().perform();
	}

	/*
	 * Method to select the dropdown using "select by visible text" Pass Value
	 * as String
	 */
	public static void selectDropdownByVisibleTxt(String locator, String VisibleText) {
		element = getElement(locator);
		Select selObj = new Select(element);
		if (element.isDisplayed()) {
			selObj.selectByVisibleText(VisibleText);
		}
	}

	/*
	 * Method to select the dropdown using "select by index", pass IndexValue as
	 * int
	 */
	public static void selectDropdownByIndex(String locator, int IndexValue) {
		element = getElement(locator);
		Select selObj = new Select(element);
		if (element.isDisplayed()) {
			selObj.selectByIndex(IndexValue);
		}
	}

	/*
	 * Method to select the dropdown using "select by value", pass Value as
	 * String
	 */
	public static void selectDropdownByValue(String locator, String Value) {
		element = getElement(locator);
		Select selObj = new Select(element);
		if (element.isDisplayed()) {
			selObj.selectByValue(Value);
		}
	}

	public static void deselectAllDropdownValue(String locator){
		element= getElement(locator);
		Select selObj= new Select(element);
		if(element.isDisplayed()){
			selObj.deselectAll();
		}
	}

	public static boolean isElementPresent(String locator) {
		element = getElement(locator);
		// @SuppressWarnings("unchecked")
		// Boolean checkIfElementPresent = ((Hashtable<Object, Object>)
		// element).size() != 0;
		Boolean checkIfElementPresent = element.isDisplayed();
		return checkIfElementPresent;
	}

	public static void dragAndDrop(String locator1, String locator2) {
		WebElement source = getElement(locator1);
		WebElement destination = getElement(locator2);
		try {
			Actions actions = new Actions(driver);
			actions.dragAndDrop(source, destination).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getCurrentWindowHandle() {
		return driver.getWindowHandle();
	}

	public static Set<String> getAllWindowHandles() {
		Set<String> handles = driver.getWindowHandles();
		return handles;
	}

	// Method to close the browser window which is currently in focus
	public static void closeBrowser() {
		driver.close();
	}

	// Method to close all the browser windows created by Selenium instance and
	// terminates the WebDriver session
	public static void quitBrowser() {
		driver.quit();
	}

	public static String getText(String locator) {
		return getElement(locator).getText();
	}

	// Method to get element color in Hex format
	public static String getElementColor(String locator) {
		element = getElement(locator);
		try {
			String color = element.getCssValue("color");
			Reporter.log("RGBA color of Element: " + element.getText() + " is " + color, true);
			String hex = Color.fromString(color).asHex();
			Reporter.log("HEX Value of Element: " + hex, true);
			return hex;
		} catch (Exception e) {
			System.err.println("Element color cannot be found");
			return null;
		}

	}

	// Method to get background color of an WebElement
	public static String getElementBackgroundColor(String locator) {
		element = getElement(locator);
		try {
			String color = element.getCssValue("background-color");
			Reporter.log("RGBA Background color of Element: " + element.getText() + " is " + color, true);
			String hex = Color.fromString(color).asHex();
			Reporter.log("HEX value of Background color of Element: " + hex, true);
			return hex;
		} catch (Exception e) {
			System.err.println("Element background color cannot be found");
			return null;
		}

	}

	// @return: zURL of the page currently loaded in the browser
	public static String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	// Method to return Page Title of the Browser
	public static String getPageTitle() {
		return driver.getTitle();
	}
	
	
	public static boolean clickElement1(AppiumDriver<MobileElement> appiumDriver ,String locator) {
		try {
			boolean elementIsClickable = getElement(locator).isEnabled();
			while (elementIsClickable) {
				getElement(locator).click();
				return true;
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Element is not enabled");
		}
		return false;
	}
	
	
	public static boolean clickElement(String locator,String xpathAttached) {
		try {
			boolean elementIsClickable = getElement(locator,xpathAttached).isEnabled();
			while (elementIsClickable) {
				waitForElementVisible(locator,xpathAttached);
				waitForElementClickable(locator,xpathAttached);
//				JAVASCRIPTEXECUTOR JSE = (JAVASCRIPTEXECUTOR)DRIVER;
//				JSE.EXECUTESCRIPT("ARGUMENTS[0].CLICK()", GETELEMENT(LOCATOR));
				getElement(locator,xpathAttached).click();
				return true;
			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Element is not enabled");
		}
		return false;
	}
	
	public static void waitForElementClickable(String locator,String xpathAttached) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(getElement(locator,xpathAttached)));
			
	}
	
	public static void waitForElementVisible(String locator,String xpathAttached) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(getElement(locator,xpathAttached)));
	}
	

	public static WebElement getElement(String locator,String xpathAttached) {
		String repolocator = ObjRepo.getProperty(locator).trim();
		String[] tokens = repolocator.split(";");
		String locatorType = tokens[0];
		String strlocator = tokens[1]+xpathAttached.trim()+tokens[2];
		 //System.out.println(tokens[0]+"  Xpathref "+strlocator);
		WebElement webElement = null;

		try {

			if (locatorType.equalsIgnoreCase("XPATH")) {

				webElement = driver.findElement(By.xpath(strlocator));

			} else if (locatorType.equalsIgnoreCase("ID")) {

				webElement = driver.findElement(By.id(strlocator));

			} else if (locatorType.equalsIgnoreCase("NAME")) {

				webElement = driver.findElement(By.name(strlocator));

			} else if (locatorType.equalsIgnoreCase("CSS")) {

				webElement = driver.findElement(By.cssSelector(strlocator));

			} else if (locatorType.equalsIgnoreCase("LINKTEXT")) {

				webElement = driver.findElement(By.linkText(strlocator));
			}

		} catch (NoSuchElementException e) {

			e.printStackTrace();
			System.out.println(strlocator + " Element not found");
			// Assert.fail(strlocator + " Element not found");

		}

		return webElement;

	}
	
	//Input Types for below randStrGen method 
	  /* 
    len---> Output string Length 
    RandmTyp
    ULN---> Upper case , Lower case & with Number
    UN----> Upper case &  with Number
    LN----> Lower case & with Number
    UL----> Upper case & Lower case
    U-----> Upper case
    L-----> Lower case 
    N-----> Number */
	
	public static String randStrGen(String RandmTyp,int len) {

	    // create a string of uppercase and lowercase characters and numbers
	    String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";
	    String alphaNumeric = null;
	 
	    
	    //combining  Requried  combination
	    
	    	if(RandmTyp=="ULN") {  
	        	  alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
	    	}else if(RandmTyp=="UN") { 
	        	 alphaNumeric = upperAlphabet + numbers; 
	    	}else if(RandmTyp=="LN") {  
	        	 alphaNumeric = lowerAlphabet + numbers; 
	    	}else if(RandmTyp=="UL") {  
	        	 alphaNumeric = upperAlphabet+lowerAlphabet; 
	    	}else if(RandmTyp== "U") {   
	        	 alphaNumeric = upperAlphabet; 
	    	}else if(RandmTyp=="L") {   
	        	 alphaNumeric = lowerAlphabet;
	    	}else if(RandmTyp=="N") {   
	        	 alphaNumeric = numbers; 
	        }
	
	    // create random string builder
	    StringBuilder sb = new StringBuilder();

	    // create an object of Random class
	    Random random = new Random();

	    // specify length of random string
	    int length = len;

	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = random.nextInt(alphaNumeric.length());

	      // get character specified by index
	      // from the string
	      char randomChar = alphaNumeric.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	    }
	    String randomString = sb.toString();
	    
	    return randomString;
	}
	
	
	  public static boolean verifyElementIsDisplayed(String locator)
	  { boolean
	  elementIsDisplayed= false; 
	  try { 
		  waitForElementVisible(locator);
	  elementIsDisplayed= getElement(locator).isDisplayed(); return
	  elementIsDisplayed; } 
	  catch (NoSuchElementException e) { 
		  e.printStackTrace();
	  System.out.println("Element is not Displayed"); 
	  return elementIsDisplayed; 
	  }
	  
	 }
	  
	  public static void clearElement(String locator) {
		  waitForElementClickable(locator);
		  getElement(locator).clear();
	  }
	  
	  public static void handlePrintDialog() {
		 WebElement printButton= getElement("printButton");
		 driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
		 ((JavascriptExecutor)driver).executeScript(
				    "var callback = arguments[1];" +
				    "window.print = function(){callback();};" +
				    "arguments[0].click();"
				    , printButton);
	  }
	  
	  public void waitForPageGetsLoad() throws InterruptedException {
			Thread.sleep(2000);
	  }
	  
	  public void waitForPageGetsLoad2() throws InterruptedException {
			Thread.sleep(3000);
		}

	  public void waitForPageGetsLoad1() throws InterruptedException {
			Thread.sleep(7000);
		}

		public static void clickElementJs(String locator) {
		try {
			JavascriptExecutor jse= (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click();", getElement(locator));
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("Element is not enabled");
		}
	}

	public static void until(WebDriver diver, Function<WebDriver, Boolean>function, Long timeUnit){

			WebDriverWait wait= new WebDriverWait(driver, timeUnit);
			wait.withTimeout(Duration.ofSeconds(timeUnit));
			wait.ignoring(NoSuchElementException.class);
			try {
				wait.until(function);
			}catch (Exception e) {

			}
	}

	public static void waitUntilElementGetsDisplayed(String locator) {
		until(driver, (d)->{
			boolean flag= getElements(locator).size()>0;
			return flag;
		}, 20L);
	}

	public static List<WebElement> getElements(String locator, String xpath) {
		String repolocator = ObjRepo.getProperty(locator).trim();
		String[] tokens = repolocator.split(";");
		String locatorType = tokens[0];
		String strlocator = tokens[1]+xpath.trim()+tokens[2];
		List<WebElement> webElement = null;

		try {

			if (locatorType.equalsIgnoreCase("XPATH")) {

				webElement = driver.findElements(By.xpath(strlocator));

			} else if (locatorType.equalsIgnoreCase("ID")) {

				webElement = driver.findElements(By.id(strlocator));

			} else if (locatorType.equalsIgnoreCase("NAME")) {

				webElement = driver.findElements(By.name(strlocator));

			} else if (locatorType.equalsIgnoreCase("CSS")) {

				webElement = driver.findElements(By.cssSelector(strlocator));

			} else if (locatorType.equalsIgnoreCase("LINKTEXT")) {

				webElement = driver.findElements(By.linkText(strlocator));
			}

		} catch (NoSuchElementException e) {

			e.printStackTrace();
			System.out.println(strlocator + " Element not found");
			// Assert.fail(strlocator + " Element not found");

		}

		return webElement;
	}

	public static void close_Popup() throws AWTException {
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

}
