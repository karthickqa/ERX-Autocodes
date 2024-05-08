package selenium;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.telliant.core.mobile.Reporting;
import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.LoginPage;

//@Listeners(com.telliant.tests.SmokeTestCases.class)
@Listeners({Reporting.class})

public class Loginpage extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	
	
	 @Test(testName = "TC_01", description =  "Login with Lab admin", priority = 1)
	public void Verify_Login_with_Lab_Admin() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//Login as Lab Admin User 
	 homePage.Lab_Login();
		homePage.Practice_Creation();
		waitForPageGetsLoad2();
		homePage.Practice_Editing();
		
		waitForPageGetsLoad();

	 }
	 

	 @Test(testName = "TC_02", description =  "Create RX", priority = 2)
	public void Create_RX_empty_prescription() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		 homePage.create_empty_RX();
		 Assert.assertEquals(homePage.isPaitentValidationDisplayed(), true, "Patient Enter warning dialog do not get displayed on RX creation page");
		 homePage.patient_details_entry();
		 homePage.Sign_submit_with_empty_prescription();
		 Assert.assertEquals(homePage.isDateneedPopupDisplayed(), true, "Date needed alert popup do not get displayed");
		 Assert.assertEquals(homePage.isBlankprescriptionPopupDisplayed(), true, "Blank Prescription alert popup do not get displayed");
		 Assert.assertEquals(homePage.isModelSourcePopupDisplayed(), true, "Model source alert popup do not get displayed");
		 homePage.Add_Parts_Comments_and_Modelsource();
		 homePage.Fileupload();
		 
	 }
}