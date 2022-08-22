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

public class SmokeTestCases extends BaseClass implements ITestListener{
	LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
	ExcelMethods excel = PageFactory.initElements(driver, ExcelMethods.class);
	HomePage homePage = PageFactory.initElements(driver, HomePage.class);
	
	
	@Test(testName = "TC_UC1_01", description = "Veriyf Login Page UI",priority = 1)
	public void TC_01_Verify_Login_Page_UI() throws IOException, InterruptedException {
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		softAssert.assertEquals(loginPage.verifyLogoIsDisplayed(), true, "BB-360 Logo Is Not Displayed");
		loginPage.clickRemeberMeCheckBox();
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 2), ExcelMethods.getData("Sheet1", "Password", 2));
		waitForPageGetsLoad();
		Thread.sleep(2000);
		softAssert.assertEquals(homePage.verifyHomePageTitle(), true, "Title is not displaying as Lendovative Home Page");
		loginPage.logout();
		loginPage.clickLoginButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyHomePageTitle(), true, "Title is not displaying as Lendovative Home Page during RememberMe Checkbox");
		loginPage.logout();
		waitForPageGetsLoad();
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 24), ExcelMethods.getData("Sheet1", "Password", 24));
		softAssert.assertEquals(loginPage.verifyInvalidCredentialsAlertMessageIsDispplayed(), true, "Invalid Credentials Alert message is not displayed");
		loginPage.clickErrorToast();
		softAssert.assertAll();
	}
	
	
	  @Test(testName = "TC_UC1_02", description="Verify User views Reset Password flow", priority = 2) 
	  public void TC_02_Verify_User_Views_Reset_Password_flow() throws InterruptedException{
	  loginPage.clickForgotPasswordLink();
	  softAssert.assertEquals(loginPage.verifyForgotPasswrodPageIsDisplayed(),true, "Forgot Password page don't get displayed");
	  loginPage.clickSendResetLink(); 
	  softAssert.assertEquals(loginPage.verifyPleaseEnterEmailAlertMessageIsDisplayed(), true, "Please Enter Your Email alert message don't get displayed");
	  loginPage.enterUnRegisteredEmail(); 
	  loginPage.clickSendResetLink();
	  waitForPageGetsLoad(); 
	  softAssert.assertEquals(loginPage.verifyErrorMessageIsDisplayedForUnregisteredEmail(), true, "Error message don't get displayed for unregistered email account");
	  loginPage.clickMessageToastContainer(); 
	  loginPage.enterRegisteredEmail();
	  loginPage.clickSendResetLink(); waitForPageGetsLoad();
	  softAssert.assertEquals(loginPage.verifySuccessrMessageIsDisplayedForRegisteredEmail(), true, "Success message don't get dispalyed for registered email account after raise request for password reset"); 
	  loginPage.clickMessageToastContainer(); 
	  loginPage.clickBackToLogin();
	  softAssert.assertEquals(loginPage.verifyHomePageIsDisplayed(), true, "HomePage don't get dispalyed");
	  softAssert.assertAll(); }
	 
	  
	@Test(testName="TC_UC2_01", description="Verify FI-User view Sidebar Menu", priority = 3)
	public void TC_03_Verify_FI_User_views_SideBarMenu() throws InterruptedException, AWTException, IOException {
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.click_SideMenuToggleButton();
		softAssert.assertEquals(homePage.verifySideBarMenulist_FiUser(), true, "Side bar doesn't display all the menu for FI-User-Admin");
		homePage.click_SideMenuToggleButton();
		softAssert.assertEquals(homePage.verifyToggleButtonIsCollapsed(), true, "Toggle button doesn't collapsed");
		loginPage.logout();
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 10), ExcelMethods.getData("Sheet1", "Password", 10));
		waitForPageGetsLoad();
		homePage.click_SideMenuToggleButton();
		softAssert.assertEquals(homePage.verifySideBarMenulist_FiUser(), true, "Side bar doesn't display all the menu for FI-User-Viewer");
		homePage.click_SideMenuToggleButton();
		softAssert.assertEquals(homePage.verifyToggleButtonIsCollapsed(), true, "Toggle button doesn't collapsed");
		loginPage.logout();
		//loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 4), ExcelMethods.getData("Sheet1", "Password", 4));
		//waitForPageGetsLoad();
		//homePage.click_SideMenuToggleButton();
		//softAssert.assertEquals(homePage.verifySideBarMenulist_FiUser(), true, "Side bar doesn't display all the menu for FI-User-PortfolioManager");
		//homePage.click_SideMenuToggleButton();
		//softAssert.assertEquals(homePage.verifyToggleButtonIsCollapsed(), true, "Toggle button doesn't collapsed");
		//loginPage.logout();
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 5), ExcelMethods.getData("Sheet1", "Password", 5));
		waitForPageGetsLoad();
		homePage.click_SideMenuToggleButton();
		softAssert.assertEquals(homePage.verifySideBarMenulist_FiUser(), true, "Side bar doesn't display all the menu for FI-User-Lender");
		homePage.click_SideMenuToggleButton();
		softAssert.assertEquals(homePage.verifyToggleButtonIsCollapsed(), true, "Toggle button doesn't collapsed");
		loginPage.logout();
		softAssert.assertAll();
	}
	
	
	@Test(testName = "TC_UC2_02", description = "Verify Business User views Sidebar Menu", priority=4)
	public void TC_04_Verify_BusinessUser_views_SidebarMenu() throws InterruptedException {
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 17), ExcelMethods.getData("Sheet1", "Password", 17));
		//waitForPageGetsLoad();
		homePage.click_BiUserSideMenuToggleButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifySideBarMenulist_BIUser(), true, "Side bar doesn't display all the menu for Business-User-Admin");
		homePage.click_BiUserSideMenuToggleButton();
		softAssert.assertEquals(homePage.verifyBiUserSideMenuToggleButtonIsCollapsed(), true, "Bi_User Side Menu Toggle button doesn't collapsed");
		loginPage.logout();
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 18), ExcelMethods.getData("Sheet1", "Password", 18));
		//waitForPageGetsLoad();
		homePage.click_BiUserSideMenuToggleButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifySideBarMenulist_BIUser(), true, "Side bar doesn't display all the menu for Business-User-Viewer");
		homePage.click_BiUserSideMenuToggleButton();
		softAssert.assertEquals(homePage.verifyBiUserSideMenuToggleButtonIsCollapsed(), true, "Bi_User Side Menu Toggle button doesn't collapsed");
		loginPage.logout();
		softAssert.assertAll();
	}
	
	@Test(testName = "TC_UC2_03", description = "Verify LV User views Sidebar Menu", priority=5)
	public void TC_05_Verify_LV_User_Views_SidebarMenu() throws InterruptedException {
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 25), ExcelMethods.getData("Sheet1", "Password", 25));
		//waitForPageGetsLoad();
		homePage.click_LvUserSideMenuToggleButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifySideBarMenulist_LvUser(), true, "Side bar doesn't display all the menu for LV-User-Admin");
		homePage.click_LvUserSideMenuToggleButton();
		softAssert.assertEquals(homePage.verifyLvUserSideMenuToggleButtonIsCollapsed(), true, "Bi_User Side Menu Toggle button doesn't collapsed");
		loginPage.logout();
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 2), ExcelMethods.getData("Sheet1", "Password", 2));
		//waitForPageGetsLoad();
		homePage.click_LvUserSideMenuToggleButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifySideBarMenulist_LvUser(), true, "Side bar doesn't display all the menu for LV-User-Viewer");
		homePage.click_LvUserSideMenuToggleButton();
		softAssert.assertEquals(homePage.verifyLvUserSideMenuToggleButtonIsCollapsed(), true, "Bi_User Side Menu Toggle button doesn't collapsed");
		loginPage.logout();
		softAssert.assertAll();
	}
	
	
	@Test(testName = "TC_UC3_01", description = "Verify User views FI Home Page", priority = 6)
	public void TC_06_Verify_User_Views_FI_HomePage() throws InterruptedException, AWTException {
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyHomePageIsDisplayed(), true, "HomePage do not get displayed");
		homePage.enterSearchText();
		softAssert.assertEquals(homePage.verifyBusinessNameListContainsSearchText(), true, "Result doesn't match with the search text");
		homePage.clickHelpLink();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyHelpModalIsDiplayed(), true, "Help dialog do not get displayed on FI Home Page");
		homePage.closeHelpDialog();
		homePage.clickDownloadIcon();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyBusinessListFileIsDownladed(), true, "Business List file do not get downladed");
		homePage.clickOnBusinessName();
		softAssert.assertEquals(homePage.verifyBusinessInfoPageTitle(), true, "Selected Business Profile info page title do not displayed");
		loginPage.logout();
		softAssert.assertAll();
		
		/*
		 * homePage.clickPrintButtton(); loginPage.waitForPageGetsLoad();
		 * homePage.closePrintDialog(); loginPage.waitForPageGetsLoad();
		 * homePage.clickUploadIcon(); homePage.clickFileUploadButton();
		 * loginPage.waitForPageGetsLoad();
		 * Runtime.getRuntime().exec("C:\\Users\\umani\\Desktop\\Udhay\\Upload.exe");
		 * Thread.sleep(5000);
		 */
	}
	
	@Test(testName = "TC_UC4_01",description = "Verify FI User views Borrowing Base Page 1", priority = 7)
	public void TC_07_Verify_FI_User_Views_BorrowingBasePage1() throws InterruptedException, AWTException {
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnBusinessName();
		softAssert.assertEquals(homePage.verifyBusinessInfoPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickHelpLink();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyHelpModalIsDiplayed(), true, "Help dialog do not get displayed on Borrowing Base Page");
		homePage.closeHelpDialog();
		homePage.clickDownloadIcon();
		softAssert.assertEquals(homePage.verifyBorrowingBaseListFileIsDownladed(), true, "Borrowing Base page file do not get downloaded");
		loginPage.logout();
		softAssert.assertAll();
	}
	
	@Test(testName = "TC_UC4_07", description =  "Verify User views BB Review FI, BB Review Date, Comments", priority = 8)
	public void TC_08_Verify_User_Views_BBReviewFI_BBReviewDate_Comments() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnBusinessName();
		softAssert.assertEquals(homePage.verifyBusinessInfoPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyCommentBoxIsDisplayed(), true, "BB's Comment box don't get displayed");
		homePage.enterTextonCommentBox();
		homePage.clickCancelButton();
		softAssert.assertEquals(homePage.verifyCommentBoxDoesNotDisplayed(), true, "Comment Box is still dispalying even after click Cancel button");	
		homePage.clickEditButton();
		homePage.enterTextonCommentBox();
		homePage.clickApproveButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyCommentTextIsDisplayeAfterApproved(), true, "Given comment don't get displayed next to the Comment label");
		homePage.clickEditHistoryIcon();
		softAssert.assertEquals(homePage.verifyCommentHistoryDialogIsDisplayed(), true, "Comment History dialog don't get displayed");
		softAssert.assertEquals(homePage.verifyCommentIsDisplayedOnHistoryDialog(), true, "Given Comment don't get displayed on the history dialog");
		homePage.closeHelpDialog();
		loginPage.logout();
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 10), ExcelMethods.getData("Sheet1", "Password", 10));
		waitForPageGetsLoad();
		homePage.clickOnBusinessName();
		softAssert.assertEquals(homePage.verifyEditButtonDoesNotDisplayed(), true, "Edit Button gets displayed");
		loginPage.logout();
		softAssert.assertAll();
	}
	
	@Test(testName = "TC_UC5_01", description =  "Verify User views FI Line Structure Page", priority = 9)
	public void TC_09_Verify_User_Views_FI_LineStructurePage() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnBusinessName();
		softAssert.assertEquals(homePage.verifyBusinessInfoPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		homePage.clickHelpLink();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyFiLineStructureHelpDialogHeader(), true, "Help dialog header is not displaying as FI Line Structure Administration");
		softAssert.assertEquals(homePage.verifyHelpModalIsDiplayed(), true, "Help dialog do not get displayed on Borrowing Base Page");
		homePage.closeHelpDialog();
		homePage.clickDownloadIcon();
		softAssert.assertEquals(homePage.verifyLineStructureFileIsDownladed(), true, "Borrowing Base page file do not get downloaded");
		loginPage.logout();
		softAssert.assertAll();
	}
	
	@Test(testName = "TC_UC5_02", description =  "Verify User views Accounts Receivable Structure", priority = 10)
	public void TC_10_Verify_User_Views_Accounts_Receivable_Structure() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnServiceLevel1BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel1BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get dispalyed on the LineStructures page");
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyServiceLevel1AccountReceivableTableEditableFields(), true, "Service Level1 Account Receivable Table fields are non-editable");
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyCancelModalDialogIsDisplayed(), true, "Cancel Modal dialog don't get displayed");
		softAssert.assertEquals(homePage.verifyCancelModalDialogContent(), true, "Cancel Modal dialog content is not dispalyed as Expected");
		homePage.clickYesButton();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get displayed after cancelling the edit process");
		homePage.clickEditButton();
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		homePage.clickServiceLevle1NoButton();
		homePage.enterDataOnServiceLevel1AccountReceivableTableColumnTotal();
		homePage.enterDataOnServiceLevel1AccountReceivableTableColumnExclusions();
		homePage.enterDataOnServiceLevel1AccountReceivableTableColumnAdvanceRate();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyAccountReceivableUpdateSavedSuccessfullyToastMessage(), true, "Success toast is not dispalying as AccountReceivable values saved successfully");
		homePage.clickToastContainer();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnSL1ColumnTotal(), true, "Given Data don't get dispalyed on the column TOTAL");
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnSL1ColumnExclusions(), true, "Given Data don't get dispalyed on the column EXCLUSIONS");
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnSL1ColumnAdvanceRate(), true, "Given Data don't get dispalyed on the column ADVANCE RATE");
		homePage.clickAddInventoryIcon();
		waitForPageGetsLoad();
		homePage.clickInventoryNoButton();
		softAssert.assertEquals(homePage.verifyCapLimitsDefaultText(), true, "CapLimits default text is not displaying as NA");
		waitForPageGetsLoad();
		homePage.enterDataOnInventoryDescription();
		homePage.enterDataOnInventoryTotal();
		Thread.sleep(1000);
		waitForPageGetsLoad();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		homePage.enterDataOnCapsLimit();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnInventoryCapslimit(), true, "Given data don't get displayed on the Inventory Caplimit field");
		loginPage.logout();
		softAssert.assertAll();
	}
	
	@Test(testName = "TC_UC5_03", description =  "Verify User views Accounts Receivable Structure", priority = 11)
	public void TC_11_Verify_User_Views_Accounts_Receivable_Structure() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnServiceLevel2BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel2BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get dispalyed on the LineStructures page");
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyTotalAndExclusionsFieldsAreNonEditable(), true, "Total and Exclusion feilds are editable for Service Level2");
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyCancelModalDialogIsDisplayed(), true, "Cancel Modal dialog don't get displayed");
		softAssert.assertEquals(homePage.verifyCancelModalDialogContent(), true, "Cancel Modal dialog content is not dispalyed as Expected");
		homePage.clickYesButton();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get displayed after cancelling the edit process");
		homePage.clickEditButton();
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		homePage.clickServiceLevle1NoButton();
		homePage.enterDataOnServiceLevel1AccountReceivableTableColumnAdvanceRate();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyAccountReceivableUpdateSavedSuccessfullyToastMessage(), true, "Success toast is not dispalying as AccountReceivable values saved successfully");
		homePage.clickToastContainer();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnSL1ColumnAdvanceRate(), true, "Given Data don't get dispalyed on the column ADVANCE RATE");
		homePage.clickAddInventoryIcon();
		waitForPageGetsLoad();
		homePage.clickInventoryNoButton();
		softAssert.assertEquals(homePage.verifyCapLimitsDefaultText(), true, "CapLimits default text is not displaying as NA");
		waitForPageGetsLoad();
		homePage.enterDataOnInventoryDescription();
		homePage.enterDataOnInventoryTotal();
		waitForPageGetsLoad();
		ScrollUp();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		Thread.sleep(1000);
		homePage.clickToastContainer();
		homePage.clickEditButton();
		homePage.enterDataOnCapsLimit();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnInventoryCapslimit(), true, "Given data don't get displayed on the Inventory Caplimit field");
		loginPage.logout();
		softAssert.assertAll();
	}
	
	@Test(testName = "TC_UC5_03", description =  "Verify User Views Inventory & Collateral Structure", priority = 12)
	public void TC_12_Verify_User_Views_InventoryCollateral_Structure() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnServiceLevel1BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel1BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
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
//-----------------------------         ------------------------------------------//
	@Test(testName = "TC_UC5_06", description =  "Verify_User_views_Manual_Borrowing_Base_Adjustment", priority = 13)
	public void TC_13_Verify_User_views_Manual_Borrowing_Base_Adjustment() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnServiceLevel1BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel1BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		waitForPageGetsLoad();
		homePage.clickEditonLineStructure();
		homePage.click_Manual_Borrowing_Base_Adjustments();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments();
		homePage.click_Manual_Borrowing_Base_Adjustments_val();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments_val();
		homePage.click_Open_credits_field();
		homePage.enterDataOn_Open_credits_field();
		homePage.clickSaveButton();
		homePage.Navigate_to_HP();
		//Verify for service level 2 Business
		
		homePage.clickOnServiceLevel2BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel2BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		waitForPageGetsLoad();
		homePage.clickEditonLineStructure();
		homePage.click_Manual_Borrowing_Base_Adjustments();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments();
		homePage.click_Manual_Borrowing_Base_Adjustments_val();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments_val();
		homePage.click_Open_credits_field();
		homePage.enterDataOn_Open_credits_field();
		homePage.clickSaveButton();
		homePage.Navigate_to_HP();

		//Verify for service level 3 Business
		homePage.clickOnServiceLevel3BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel3BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		waitForPageGetsLoad();
		homePage.clickEditonLineStructure();
		homePage.click_Manual_Borrowing_Base_Adjustments();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments();
		homePage.click_Manual_Borrowing_Base_Adjustments_val();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments_val();
		homePage.click_Open_credits_field();
		homePage.enterDataOn_Open_credits_field();
		homePage.clickSaveButton();
		waitForPageGetsLoad1();
		loginPage.logout();
	}
		
	@Test(testName = "TC_UC5_08", description =  "Verify_User_views_Edit_Button", priority = 14)
	public void TC_14_Verify_User_views_Edit_Button() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnServiceLevel1BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel1BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get dispalyed on the LineStructures page");
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyServiceLevel1AccountReceivableTableEditableFields(), true, "Service Level1 Account Receivable Table fields are non-editable");
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyCancelModalDialogIsDisplayed(), true, "Cancel Modal dialog don't get displayed");
		softAssert.assertEquals(homePage.verifyCancelModalDialogContent(), true, "Cancel Modal dialog content is not dispalyed as Expected");
		homePage.clickYesButton();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get displayed after cancelling the edit process");
		homePage.clickEditButton();
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		homePage.clickServiceLevle1NoButton();
		homePage.enterDataOnServiceLevel1AccountReceivableTableColumnTotal();
		homePage.enterDataOnServiceLevel1AccountReceivableTableColumnExclusions();
		homePage.enterDataOnServiceLevel1AccountReceivableTableColumnAdvanceRate();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyAccountReceivableUpdateSavedSuccessfullyToastMessage(), true, "Success toast is not dispalying as AccountReceivable values saved successfully");
		homePage.clickToastContainer();
		waitForPageGetsLoad();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnSL1ColumnTotal(), true, "Given Data don't get dispalyed on the column TOTAL");
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnSL1ColumnExclusions(), true, "Given Data don't get dispalyed on the column EXCLUSIONS");
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnSL1ColumnAdvanceRate(), true, "Given Data don't get dispalyed on the column ADVANCE RATE");
		waitForPageGetsLoad();
		homePage.clickAddInventoryIcon();
		waitForPageGetsLoad();
		homePage.clickInventoryNoButton();
		softAssert.assertEquals(homePage.verifyCapLimitsDefaultText(), true, "CapLimits default text is not displaying as NA");
		waitForPageGetsLoad();
		homePage.enterDataOnInventoryDescription();
		homePage.enterDataOnInventoryTotal();
		Thread.sleep(1000);
		waitForPageGetsLoad();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		homePage.enterDataOnCapsLimit();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnInventoryCapslimit(), true, "Given data don't get displayed on the Inventory Caplimit field");
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
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditonLineStructure();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralCapslimit(), true, "Given data don't get displayed on the OtherCollateral Caplimit field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralAdvanceRates(), true, "Given data don't get displayd on the OtherCollateral Advancec Rates field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralDescription(), true, "Given data don't get displayd on the OtherCollateral Description field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralTotal(), true, "Given data don't get displayd on the OtherCollateral Total field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnOtherCollateralSublimitText(), true, "Given data don't get displayd on the OtherCollateral Sublimit Text");
		waitForPageGetsLoad();
		homePage.click_Manual_Borrowing_Base_Adjustments();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments();
		homePage.click_Manual_Borrowing_Base_Adjustments_val();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments_val();
		homePage.click_Open_credits_field();
		homePage.enterDataOn_Open_credits_field();
		homePage.clickSaveButton();
		homePage.clickToastContainer();
		loginPage.logout();
		softAssert.assertAll();

	}
	
	@Test(testName = "TC_UC5_09", description =  "Verify_User_views_Edit_Button", priority = 15)
	public void TC_15_Verify_User_views_Edit_Button() throws InterruptedException, AWTException{
		launchURL(config.getProperty("url"));
		String ValidateUrl=driver.getCurrentUrl();
		ValidateUrl.equalsIgnoreCase(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		loginPage.login(ExcelMethods.getData("Sheet1", "UserName", 8), ExcelMethods.getData("Sheet1", "Password", 8));
		waitForPageGetsLoad();
		homePage.clickOnServiceLevel2BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel2BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get dispalyed on the LineStructures page");
		homePage.clickEditButton();
		//softAssert.assertEquals(homePage.verifyServiceLevel1AccountReceivableTableEditableFields(), true, "Service Level1 Account Receivable Table fields are non-editable");
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyCancelModalDialogIsDisplayed(), true, "Cancel Modal dialog don't get displayed");
		softAssert.assertEquals(homePage.verifyCancelModalDialogContent(), true, "Cancel Modal dialog content is not dispalyed as Expected");
		homePage.clickYesButton();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get displayed after cancelling the edit process");
		homePage.clickEditButton();
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		homePage.clickServiceLevle1NoButton();
		waitForPageGetsLoad();
		homePage.clickAddInventoryIcon();
		waitForPageGetsLoad();
		homePage.clickInventoryNoButton();
		softAssert.assertEquals(homePage.verifyCapLimitsDefaultText(), true, "CapLimits default text is not displaying as NA");
		waitForPageGetsLoad();
		homePage.enterDataOnInventoryDescription();
		homePage.enterDataOnInventoryTotal();
		Thread.sleep(1000);
		waitForPageGetsLoad();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		homePage.enterDataOnCapsLimit();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnInventoryCapslimit(), true, "Given data don't get displayed on the Inventory Caplimit field");
		
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
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditonLineStructure();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralCapslimit(), true, "Given data don't get displayed on the OtherCollateral Caplimit field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralAdvanceRates(), true, "Given data don't get displayd on the OtherCollateral Advancec Rates field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralDescription(), true, "Given data don't get displayd on the OtherCollateral Description field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralTotal(), true, "Given data don't get displayd on the OtherCollateral Total field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnOtherCollateralSublimitText(), true, "Given data don't get displayd on the OtherCollateral Sublimit Text");
		waitForPageGetsLoad();
		
		//homePage.clickEditonLineStructure();
		homePage.click_Manual_Borrowing_Base_Adjustments();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments();
		homePage.click_Manual_Borrowing_Base_Adjustments_val();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments_val();
		homePage.click_Open_credits_field();
		homePage.enterDataOn_Open_credits_field();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.Navigate_to_HP();
//---------------------------------------------------------------------------------------------------
		homePage.clickOnServiceLevel3BusinessName();
		softAssert.assertEquals(homePage.verifyServiceLevel3BusinessPageTitle(), true, "Selected Business Profile info page title do not displayed" );
		homePage.clickTabLineStructure();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get dispalyed on the LineStructures page");
		homePage.clickEditButton();
		//softAssert.assertEquals(homePage.verifyServiceLevel1AccountReceivableTableEditableFields(), true, "Service Level1 Account Receivable Table fields are non-editable");
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		softAssert.assertEquals(homePage.verifyCancelModalDialogIsDisplayed(), true, "Cancel Modal dialog don't get displayed");
		softAssert.assertEquals(homePage.verifyCancelModalDialogContent(), true, "Cancel Modal dialog content is not dispalyed as Expected");
		homePage.clickYesButton();
		softAssert.assertEquals(homePage.verifyEditButtonIsDisplayed(), true, "Edit button don't get displayed after cancelling the edit process");
		homePage.clickEditButton();
		homePage.clickCancelButton();
		waitForPageGetsLoad();
		homePage.clickServiceLevle1NoButton();
		waitForPageGetsLoad();
		homePage.clickAddInventoryIcon();
		waitForPageGetsLoad();
		homePage.clickInventoryNoButton();
		softAssert.assertEquals(homePage.verifyCapLimitsDefaultText(), true, "CapLimits default text is not displaying as NA");
		waitForPageGetsLoad();
		homePage.enterDataOnInventoryDescription();
		homePage.enterDataOnInventoryTotal();
		Thread.sleep(1000);
		waitForPageGetsLoad();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		homePage.enterDataOnCapsLimit();
		homePage.clickSaveButton();
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditButton();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnInventoryCapslimit(), true, "Given data don't get displayed on the Inventory Caplimit field");
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
		waitForPageGetsLoad();
		homePage.clickToastContainer();
		homePage.clickEditonLineStructure();
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralCapslimit(), true, "Given data don't get displayed on the OtherCollateral Caplimit field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralAdvanceRates(), true, "Given data don't get displayd on the OtherCollateral Advancec Rates field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralDescription(), true, "Given data don't get displayd on the OtherCollateral Description field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDispalyedOnOtherCollateralTotal(), true, "Given data don't get displayd on the OtherCollateral Total field");
		softAssert.assertEquals(homePage.verifyGivenDataIsDisplayedOnOtherCollateralSublimitText(), true, "Given data don't get displayd on the OtherCollateral Sublimit Text");
		waitForPageGetsLoad();
		homePage.click_Manual_Borrowing_Base_Adjustments();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments();
		homePage.click_Manual_Borrowing_Base_Adjustments_val();
		homePage.enterDataOn_Manual_Borrowing_Base_Adjustments_val();
		homePage.click_Open_credits_field();
		homePage.enterDataOn_Open_credits_field();
		homePage.clickSaveButton();
		homePage.clickToastContainer();
		loginPage.logout();
		softAssert.assertAll();
			
	}	
	
}