	package com.telliant.core.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class BaseClass extends WebDriverRoot {
	public static String methodName;
	public static String packageName;
	public static String testname;
	public SoftAssert softAssert;
	
	DesiredCapabilities cap = new DesiredCapabilities();
	
	// public String browserName;

	@BeforeSuite
	@Parameters("Env")
	public void testSetup(@Optional String env) throws FileNotFoundException, IOException {
		System.out.println("Suite Before");

		
		ObjRepo = GeneralMethods.loadProperty("./objectRepo/webObject.properties");
		config = GeneralMethods.loadProperty("./objectRepo/config.properties");
		Reporter.log("Current Env: " + config.getProperty("Env"));
		Reporter.log("Platform: " + config.get("mobile.platform"));
		launchBrowser();
		driver.manage().deleteAllCookies();

		//driver.get("chrome://settings/clearBrowserData");
		//driver.findElement(By.xpath("//*[@id='clearBrowsingDataConfirm']")).click();

	}
	

	@Parameters("testName")
	@BeforeMethod(alwaysRun = true)
	public void testSetup(@Optional String testName, @Optional Method method) throws Exception {
		softAssert= new SoftAssert();
		String testCaseName = method.getAnnotation(Test.class).testName();
		methodName = method.getName();
		packageName = getClass().getPackage().getName();
		if (testCaseName.isEmpty()) {
			testname = testName;
		} else {
			testname = method.getAnnotation(Test.class).testName();
		}

	}

//	@AfterMethod
//	public void tearDowns() {
//	   Reporting.testReport.get().info(MarkupHelper.createLabel("Test ID: " + testname, ExtentColor.BLUE));
//	}
	
	
	
	
	
	
	
	@AfterSuite
	public void tearDown() {
		Reporting.testReport.get().info(MarkupHelper.createLabel("Test ID: " + testname, ExtentColor.BLUE));
		driver.close();
	}
}