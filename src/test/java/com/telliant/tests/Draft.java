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

	@Test(testName = "TC_UC20_09", description =  "Verify User views Edit & Save FI details Flow", priority = 9)
	public void TC_09_Verify_User_views_EditAndSave_FI_details_Flow() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 1), ExcelMethods.getData("Sheet1", "Password", 1));
        waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyHomePageTitle(), true, "Title is not displaying as Lendovative Home Page");
		homePage.clickUserAdmin();
		homePage.getAccountManagerList();
		homePage.clickFiAdmin();
		homePage.click_EditButtonOnFiAdminUser();
        waitForPageGetsLoad();
        softAssert.assertEquals(homePage.verifyCardHeadersOnAddFiPage(), true, "All Card Headers section don't get displayed on the Add FI page");
        homePage.enterDataOn_FinancialInstiution();
        homePage.enterDataOn_ServiceLevelFeeStrucure1();
        homePage.enterDataOn_ServiceLevelFeeStrucure2();
        homePage.enterDataOn_ServiceLevelFeeStrucure3();

        homePage.enterDataOn_LicensedDate();
        homePage.enterDataOn_LicenseRateCommission();
        homePage.enterDataOn_LicensedMaturityDate();
        softAssert.assertEquals(homePage.verifyLvAccountManagerDropDownList(), true, "LV Account Manager dropdown list don't get matches with the expected list");
        homePage.selectFromLvAccountManagerDropDown();
        homePage.selectFromFiStatusDropDown();
        homePage.selectFromFiCoreSyncDropDown();
        softAssert.assertEquals(homePage.verifyFiStatusDropDownList(), true, "FI Status dropdown list don't get matches with the expected list");
        softAssert.assertEquals(homePage.verifyFiCoreSyncDropDownList(), true, "FI Core Sync dropdown list don't get matches with the expected list");
        homePage.selectFromFiType();
        softAssert.assertEquals(homePage.verifyFiTypeDropDownList(), true, "FI Type dropdown list don't get matches with the expected list");
        homePage.enterDataOn_OtherDetaislAssestSize();
        homePage.clickSaveButton();
        softAssert.assertEquals(homePage.verifyUserCreationMessageIsDisplayed(), true, "Add Fi User creation message don't get displayed upon creatin new FI user");
        softAssert.assertEquals(homePage.verify_FiName(), true, "Updated name don't get displayed on FI-Name field");
        homePage.click_ToastMessage();
        loginPage.logout();
        softAssert.assertAll();
        softAssert.assertAll();
	}
}