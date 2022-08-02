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

public class Draft extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	
	@Test(testName = "TC_UC5_06", description =  "Verify User views Manual Borrowing Base Adjustment", priority = 13)
	public void TC_13_Verify_User_Views_Manual_BorrowingBaseAdjustment() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnServiceLevel3BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel3BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyAddInventoryIconIsDisplayed(), true, "Add Inventory Icon doesn't get displayed");
		homePage.clickAddInventoryIcon();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyYesButtonOnInventoryDialog(), true, "Yes button don't get dispalyed on Inventory dialog");
		softAssert.assertEquals(homePage.verifyNoButtonOnInventoryDialog(), true, "No button don't get dispalyed on Inventory dialog");
		homePage.clickInventroyYesButton();
		softAssert.assertEquals(homePage.verifyCapLimitsText(), true, "Cap limits text is dispalying as NA");
		homePage.enterDataOnInventoryDescription();
		homePage.enterDataOnInventoryTotal();
		homePage.enterDataOnAdvanceRate();
		homePage.enterDataOnCapsLimit();
		homePage.enterDataOnInventorySublimitTextBox();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnInventoryCapslimit(), true, "Given data don't get displayed on the Inventory Caplimit field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnInventoryAdvanceRates(), true, "Given data don't get displayd on the Inventory Advancec Rates field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnInventoryDescription(), true, "Given data don't get displayd on the Inventory Description field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnInventoryTotal(), true, "Given data don't get displayd on the Inventory Total field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnInventorySublimitText(), true, "Given data don't get displayd on the Inventory Sublimit Text");
		waitForPageGetsLoad();	
		
		homePage.clickOtherCollateralAddButon();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyOtherCollateralYesButton(), true, "Yes button don't get dispalyed on OtherCollateral dialog");
		softAssert.assertEquals(homePage.verifyOtherCollateralNoButton(), true, "No button don't get dispalyed on OtherCollateral dialog");
		homePage.click_OtherCollateralYesButton();
		waitForPageGetsLoad();
		homePage.enterDataOnOtherCollateralDescription();
		homePage.enterDataOnOtherCollateralTotal();
		homePage.enterDataOnOtherCollateralAdvanceRate();
		homePage.enterDataOnOtherCollateralCapsLimit();
		homePage.enterDataOnOtherCollateralSublimitTextBox();
		homePage.clickSaveButton();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralCapslimit(), true, "Given data don't get displayed on the OtherCollateral Caplimit field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralAdvanceRates(), true, "Given data don't get displayd on the OtherCollateral Advancec Rates field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralDescription(), true, "Given data don't get displayd on the OtherCollateral Description field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralTotal(), true, "Given data don't get displayd on the OtherCollateral Total field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnOtherCollateralSublimitText(), true, "Given data don't get displayd on the OtherCollateral Sublimit Text");
		waitForPageGetsLoad();
		
		loginPage.logout();
		softAssert.assertAll();
	}
		
}