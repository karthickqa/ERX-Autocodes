package com.telliant.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;
import com.telliant.pageObjects.HomePage;
import com.telliant.pageObjects.LoginPage;

@Listeners(com.telliant.tests.SmokeTestCases.class)

public class RegressionCases extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	
	
	@Test(testName = "TC_UC17_01", description =  "Verify User views Titile, Portfolio Summary in Lendovative Home Page", priority = 1)
	public void TC_17_Verify_User_views_Titile_Portfolio_Summary_in_Lendovative_Home_Page() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//Login as LV Admin User 
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 1), ExcelMethods.getData("Sheet1", "Password", 1));
		waitForPageGetsLoad();
		homePage.clickOnFI_Name_1();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyLV_Business1_Lable(), true, "Selected LV Profile info page title do not displayed" );
		homePage.clickHelpLink();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyHelpModalIsDiplayed(), true, "Help dialog do not get displayed on FI Home Page");
		homePage.closeHelpDialog();
		homePage.clickDownloadIcon();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyBusinessListFileIsDownladed(), true, "Business List file do not get downladed");
		loginPage.logout();
		softAssert.assertAll();
		
		
		//Login as LV viewer User
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 2), ExcelMethods.getData("Sheet1", "Password", 2));
		waitForPageGetsLoad();
		homePage.clickOnFI_Name_1();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyLV_Business1_Lable(), true, "Selected LV Profile info page title do not displayed" );
		homePage.clickHelpLink();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyHelpModalIsDiplayed(), true, "Help dialog do not get displayed on FI Home Page");
		homePage.closeHelpDialog();
		homePage.clickDownloadIcon();
		loginPage.logout();
		softAssert.assertAll();
		
		//Login as LV Manager User 
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 26), ExcelMethods.getData("Sheet1", "Password", 26));
		waitForPageGetsLoad();
		homePage.clickOnFI_Name_1();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyLV_Business1_Lable(), true, "Selected LV Profile info page title do not displayed" );
		homePage.clickHelpLink();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyHelpModalIsDiplayed(), true, "Help dialog do not get displayed on FI Home Page");
		homePage.closeHelpDialog();
		homePage.clickDownloadIcon();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyBusinessListFileIsDownladed(), true, "Business List file do not get downladed");
		loginPage.logout();
		softAssert.assertAll();
		
	}
	@Test(testName = "TC_UC17_05", description =  "Verify User views Titile, Portfolio Summary in Lendovative Home Page", priority = 02)
	public void TC_18_Verify_User_views_Titile_Portfolio_Summary_in_Lendovative_Home_Page() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 2), ExcelMethods.getData("Sheet1", "Password", 2));
		waitForPageGetsLoad1();
		homePage.clickDropdown_HP();
		softAssert.assertEquals(homePage.verify_Active_Status(), true, "Active Status is not Displayed" );
		softAssert.assertEquals(homePage.verify_Pending_Active_Status(), true, "Pending_Active_Status is not Displayed" );
		softAssert.assertEquals(homePage.verify_License_Matured_Status(), true, "License_Matured_Status is not Displayed" );
		softAssert.assertEquals(homePage.verify_Terminated_Status(), true, "Terminated_Status is not Displayed" );
		homePage.click_Active_HP();
		softAssert.assertEquals(homePage.verify_Active_Status(), true, "Active Status is not Displayed" );
		softAssert.assertEquals(homePage.verify_Active_Status_Grid(), true, "Active Status is not Displayed" );
		homePage.click_Pending_Active_HP();
		softAssert.assertEquals(homePage.verify_Pending_Active_Status(), true, "Pending_Active_Status is not Displayed" );
		softAssert.assertEquals(homePage.verify_Pending_Active_Status_Grid(), true, "Pending_Active_Status is not Displayed" );
		homePage.click_License_Matured();
		softAssert.assertEquals(homePage.verify_License_Matured_Status(), true, "License_Matured_Status is not Displayed" );
		softAssert.assertEquals(homePage.verify_License_Matured_Status_Grid(), true, "License_Matured_Status is not Displayed" );
		homePage.click_Terminated();
		softAssert.assertEquals(homePage.verify_Terminated_Status(), true, "Terminated_Status is not Displayed" );
		softAssert.assertEquals(homePage.verify_Terminated_Status_Grid(), true, "Terminated_Status is not Displayed" );
		loginPage.logout();
		softAssert.assertAll();	
	}
	@Test(testName = "TC_UC20_07", description =  "Verify User views Add New User Flow ", priority = 03)
	public void TC_20_Verify_User_views_Add_New_User_Flow() throws InterruptedException, AWTException,IOException {
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//Add_Admin_User
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 1), ExcelMethods.getData("Sheet1", "Password", 1));
		waitForPageGetsLoad();
		homePage.clickSidemenu_FI_Admin();
		homePage.click_Edit_FI();
		homePage.click_Add_Admin_User();
		homePage.updateEmpmailIdInExcel();	
		loginPage.logout();
		BaseClass.refresh();
		//Add_Portfolio_Manager_User
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 1), ExcelMethods.getData("Sheet1", "Password", 1));
		waitForPageGetsLoad();
		homePage.clickSidemenu_FI_Admin();
		homePage.click_Edit_FI();
		homePage.click_Add_Portfolio_Manager_User();
		homePage.updateEmpmailIdInExcel();	
		loginPage.logout(); 
		BaseClass.refresh();
		//Login as Restricted User
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 27), ExcelMethods.getData("Sheet1", "Password", 27));
		softAssert.assertEquals(loginPage.verifyErrorMessageIsDisplayedFor_Restricted_User(), true, "Error message don't get displayed for unregistered email account");
		softAssert.assertAll();
		//Login as unRestricted User
		BaseClass.refresh();
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 1), ExcelMethods.getData("Sheet1", "Password", 1));
		loginPage.logout(); 
		
	}
		
}