package com.telliant.core.mobile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.telliant.core.web.GeneralMethods;

public class MobileBaseClas extends MobileDriverRoot {
	
	
	public static String methodName;
	public static String packageName;
	public static String testname;
	
	//public String browserName;

	@BeforeSuite
	@Parameters("Env")
	public void testSetup(@Optional String env) throws FileNotFoundException, IOException{
		System.out.println("Suite Before");
		
		config = GeneralMethods.loadProperty("./objectRepo/config.properties");
		
	}
	
	
	@Parameters("testName")
	@BeforeMethod(alwaysRun = true)
	public void testSetup(@Optional String testName, @Optional Method method) throws Exception {
		String testCaseName = method.getAnnotation(Test.class).testName();
		methodName = method.getName();
		packageName = getClass().getPackage().getName();
		if (testCaseName.isEmpty()) {
			testname = testName;
		} else {
			testname =   method.getAnnotation(Test.class).testName();
		}
		

	}

	/*@AfterMethod
	public void tearDown() {
		Reporting.testReport.get().info(MarkupHelper.createLabel("Test ID: " + testname, ExtentColor.BLUE));
		//driver.quit();
	}*/

}
