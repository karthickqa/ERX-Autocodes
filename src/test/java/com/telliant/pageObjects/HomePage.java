package com.telliant.pageObjects;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;
import java.util.logging.Level; 
import java.util.logging.Logger;

public class HomePage extends WebDriverRoot {
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 Logger logger = Logger.getLogger(HomePage.class.getSimpleName());
		
 /*#################################################################################################################*/	
	public boolean verifyHomePageTitle() {
		boolean flag= false;
		String header= getElement("homePage_Title").getText();
		if(header.equalsIgnoreCase("Lendovative Home Page"))
			flag= true;
		return flag;
	}
	
	public void verifySideBarMenu() {
		getElements("homePage_SideBarMenuList");
	}
	
	public ArrayList<String> getSideBarMenuList_FiUser() {
		ArrayList<String> sideMenu= new ArrayList<String>();
		sideMenu.add(config.getProperty("home"));
		sideMenu.add(config.getProperty("supportResources"));
		sideMenu.add(config.getProperty("contactAdmin"));
		sideMenu.add(config.getProperty("coreFileUploads"));
		sideMenu.add(config.getProperty("communicationCenter"));
		return sideMenu;
	}
	
	public ArrayList<String> getSideBarMenuList_LvUser() {
		ArrayList<String> sideMenu= new ArrayList<String>();
		sideMenu.add(config.getProperty("home"));
		sideMenu.add(config.getProperty("userAdmin"));
		sideMenu.add(config.getProperty("financialInstitutionAdmin"));
		sideMenu.add(config.getProperty("businessAdmin"));
		sideMenu.add(config.getProperty("accountDebtorAdmin"));
		sideMenu.add(config.getProperty("lvBusinessFee"));
		sideMenu.add(config.getProperty("lvReports"));
		sideMenu.add(config.getProperty("addSupportResources"));
		sideMenu.add(config.getProperty("businessFileUploads"));
		sideMenu.add(config.getProperty("communicationCenter"));
		return sideMenu;
	}
	
	public ArrayList<String> getSideBarMenuList_BiUser() {
		ArrayList<String> sideMenu= new ArrayList<String>();
		sideMenu.add(config.getProperty("home"));
		sideMenu.add(config.getProperty("supportResources"));
		sideMenu.add(config.getProperty("contactAdmin"));
		sideMenu.add(config.getProperty("newFileUploads"));
		sideMenu.add(config.getProperty("communicationCenter"));
		sideMenu.add(config.getProperty("addAccountDebtor"));
		return sideMenu;
	}
	
	public void click_SideMenuToggleButton() {
		clickElement("homePage_SideMenuToggleButton");
	}
	
	public void click_LvUserSideMenuToggleButton() {
		clickElement("homePage_LvUserSideMenuToggleButton");
	}
	
	public void click_BiUserSideMenuToggleButton() {
		clickElement("homePage_BiUserSideMenuToggleButton");
	}
	
	public boolean verifySideBarMenulist_FiUser() {
		boolean flag= true;
		ArrayList<String> menuList= new ArrayList<>();
		for(WebElement element: getElements("homePage_SideBarMenuList")) {
			menuList.add(element.getText());
		}
		for(int i=0; i<menuList.size(); i++) {
			if(!(menuList.get(i).equalsIgnoreCase(getSideBarMenuList_FiUser().get(i)))) {
				flag=false;
				break;
			}
		}
		return flag;
	}
	
	public boolean verifySideBarMenulist_LvUser() {
		boolean flag= true;
		ArrayList<String> menuList= new ArrayList<>();
		for(WebElement element: getElements("homePage_LvUserSideBarMenuList")) {
			menuList.add(element.getText());
		}
		for(int i=0; i<menuList.size(); i++) {
			if(!(menuList.get(i).equalsIgnoreCase(getSideBarMenuList_LvUser().get(i)))) {
				flag=false;
				break;
			}
		}
		return flag;
	}
	
	public boolean verifySideBarMenulist_BIUser() {
		boolean flag= true;
		ArrayList<String> menuList= new ArrayList<>();
		for(WebElement element: getElements("homePage_BiUserSideBarMenuList")) {
			menuList.add(element.getText());
		}
		for(int i=0; i<menuList.size(); i++) {
			if(!(menuList.get(i).equalsIgnoreCase(getSideBarMenuList_BiUser().get(i)))) {
				flag=false;
				break;
			}
		}
		return flag;
	}
	
	public boolean verifyToggleButtonIsCollapsed() {
		  boolean flag= false;
		  List<WebElement>elements= driver.findElements(By.xpath("(//*[@id='toggleImage'])[1]"));
		  if(elements.size()>0)
			  flag= true;
		  return flag;	  
	  }
	
	public boolean verifyLvUserSideMenuToggleButtonIsCollapsed() {
		  boolean flag= false;
		  List<WebElement>elements= driver.findElements(By.xpath("(//*[@id='toggleImage'])[2]"));
		  if(elements.size()>0)
			  flag= true;
		  return flag;	  
	  }
	
	public boolean verifyBiUserSideMenuToggleButtonIsCollapsed() {
		  boolean flag= false;
		  List<WebElement>elements= driver.findElements(By.xpath("(//*[@id='toggleImage'])[3]"));
		  if(elements.size()>0)
			  flag= true;
		  return flag;	  
	  }
	
	public void clickPrintButtton() {
		clickElement("printButton");
	}
	
	public void closePrintDialog() throws AWTException, InterruptedException {
		 Robot robot= new Robot();
		 Thread.sleep(2000);
		 try {
			 robot.keyPress(KeyEvent.VK_ESCAPE);
			 robot.keyRelease(KeyEvent.VK_ESCAPE); 
		 }catch(ElementClickInterceptedException e){
			 Thread.sleep(2000);
			 robot.keyPress(KeyEvent.VK_ESCAPE);
			 robot.keyRelease(KeyEvent.VK_ESCAPE); 
		 }finally {
			 Thread.sleep(2000);
			 robot.keyPress(KeyEvent.VK_ESCAPE);
			 robot.keyRelease(KeyEvent.VK_ESCAPE); 
		}
		 
	}
	
	public void clickDownloadIcon() {
		clickElement("downloadButton");
	}
	
	public void clickUploadIcon() {
		clickElement("uploadButton");
	}
	
	public void clickFileUploadButton() {
		clickElement("fileUploadButton");
	}
	
	public ArrayList<String> getListOfDownloadedFile() {
		boolean flag= false;
		File folder = new File(System.getProperty("user.dir")+"\\Downloads\\");
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> fileName= new ArrayList<String>();
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    //System.out.println("File " + fileName.add(listOfFiles[i].getName()) );
			  fileName.add(listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    //System.out.println("Directory " + fileName.add(listOfFiles[i].getName()) );
			  fileName.add(listOfFiles[i].getName());
		  }
		}
		return fileName;
	}
	
	public boolean verifyBusinessListFileIsDownladed() {
		boolean flag= false;
		for(int i=0; i<getListOfDownloadedFile().size(); i++) {
			if(getListOfDownloadedFile().get(i).contains(config.getProperty("businessListFileName"))) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public boolean verifyBorrowingBaseListFileIsDownladed() {
		boolean flag= false;
		for(int i=0; i<getListOfDownloadedFile().size(); i++) {
			if(getListOfDownloadedFile().get(i).contains(config.getProperty("borrowingBaseListFileName"))) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public boolean verifyLineStructureFileIsDownladed() {
		boolean flag= false;
		for(int i=0; i<getListOfDownloadedFile().size(); i++) {
			if(getListOfDownloadedFile().get(i).contains(config.getProperty("lineStructureFileName"))) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public boolean verifyHomePageLogoIsDisplayed() {
		return getElement("homePageLogo").isDisplayed();
	}
	
	public void navigateToHomePage() {
		clickElement("sideMenu_HomePage");
	}
	
	public boolean verifyHomePageIsDisplayed() {
		boolean flag= false;
		if(driver.getCurrentUrl().contains(config.getProperty("homePageUrl")))
			flag= true;
		return flag;
	}
	
	public void enterSearchText() {
		clickElement("searchTextBox");
		type("searchTextBox", config.getProperty("searchText"));
		pressKeyEnter("searchTextBox");
	}
	
	public boolean verifyBusinessNameListContainsSearchText() {
		getElements("businessNameList");
		ArrayList<String> businessName= new ArrayList<String>();
		for(WebElement element: getElements("businessNameList")) {
			businessName.add(element.getText());
		}
		//System.out.println(businessName);
		boolean flag= true;
		for(int i=0; i<businessName.size(); i++) {
			if(!(businessName.get(i).contains(config.getProperty("searchText")))) {
				flag= false;
				break;
			}
		}
		return flag;
	}
	
	public void clickHelpLink() {
		clickElement("helpLink");
	}
	
	public boolean verifyHelpModalIsDiplayed() {
		return getElement("helpDialog").isDisplayed();
	}
	
	public void closeHelpDialog() throws AWTException, InterruptedException {
		Thread.sleep(500);
		Robot robot= new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);			
	}
	
	public void clickOnBusinessName() {
		clickElement("businessNameList");
	}
	
	public void clickOnServiceLevel1BusinessName() {
		type("searchTextBox", config.getProperty("serviceLevel1BusinessName"));
		clickElement("businessNameList");
	}
	
	public void clickOnServiceLevel2BusinessName() {
		type("searchTextBox", config.getProperty("serviceLevel2BusinessName"));
		clickElement("businessNameList");
	}
	
	public void clickOnServiceLevel3BusinessName() {
		type("searchTextBox", config.getProperty("serviceLevel3BusinessName"));
		clickElement("businessNameList");
	}
	
	public boolean verifyBusinessInfoPageTitle() {
		boolean flag= false;
		String title= getElement("businessInfoPageTitle").getText();
		if(title.equalsIgnoreCase(config.getProperty("searchText"))) {
			flag= true;
		}
		return flag;
	}
	
	public void clickEditButton() throws InterruptedException {
		scrollToElement("bbEditButton");
		waitForPageGetsLoad();
		clickElement("bbEditButton");
	}
	
	public boolean verifyCommentBoxIsDisplayed() {
		boolean flag= false;
		int count= getElements("bbCommentBox").size();
		if(count>0)
			flag= true;
		return flag;
	}
	
	public void enterTextonCommentBox() {
		type("bbCommentBox", config.getProperty("testComment"));
	}
	
	public void clickCancelButton() {
		try {
			clickElement("bbCancelButton");
		}catch(ElementClickInterceptedException e) {
			clickElement("bbCancelButton");
		}
		
	}
	
	public void clickApproveButton() {
		try {
			clickElement("bbApproveButton");
		}catch(ElementClickInterceptedException e) {
			clickElement("bbApproveButton");
		}
	}
	
	public void clickEditHistoryIcon() {
		clickElement("bbHistoryIcon");
	}
	
	public boolean verifyCommentTextIsDisplayeAfterApproved() {
		boolean flag= false;
		String commentText= getElement("bbCommentText").getText();
		if(commentText.equalsIgnoreCase(config.getProperty("testComment")))
			flag= true;
		return flag;
	}
	
	public boolean verifyCommentHistoryDialogIsDisplayed() {
		boolean flag= false;
		int count= getElements("bbHistoryDialog").size();
		if(count>0)
			flag= true;
		return flag;
	}
	
	public boolean verifyCommentIsDisplayedOnHistoryDialog() {
		boolean flag= false;
		ArrayList<String> comments= new ArrayList<>();
		for(WebElement element: getElements("bbHistoryDialogComments")) {
			comments.add(element.getText());
		}
		for(int i=0; i<comments.size(); i++) {
			if(comments.get(i).equalsIgnoreCase(config.getProperty("testComment"))) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	public boolean verifyEditButtonDoesNotDisplayed() {
		boolean flag= false;
		if(!(getElement("bbEditButton").isDisplayed()))
			flag= true;
		return flag;
				
	}
	
	public boolean verifyCommentBoxDoesNotDisplayed() {
		boolean flag= false;
		if(getElements("bbCommentBox").size()==0)
			flag=true;
		return flag;
	}
	
	public void clickTabLineStructure() {
		clickElement("tabLineStructure");
	}
	
	public boolean verifyDialogHeader(WebElement element, String dialogHeader) {
		boolean flag= false;
		String headerTitle= element.getText();
		if(headerTitle.equalsIgnoreCase(dialogHeader))
			flag= true;
		return flag;
	}
	
	public boolean verifyFiBorrowingBaseHelpDialogHeader() {
		return verifyDialogHeader(getElement("fiUserBorrowingBasePageHelpDialogTitle"), config.getProperty("fiUserBorrowingBasePageHelpDialogTitle"));
	}
	
	public boolean verifyFiLineStructureHelpDialogHeader() {
		return verifyDialogHeader(getElement("fiUserLineStructurePageHelpDialogTitle"), config.getProperty("fiUserLineStructurePageHelpDialogTitle"));
	}
	
	public boolean verifyEditButtonIsDisplayed() {
		boolean flag= false;
		if(getElement("bbEditButton").isDisplayed())
			flag= true;
		return flag;
	}
	
	public boolean verifyFieldsAreEditable(List<WebElement> webElement) {
		boolean flag= true;
		for(WebElement element: webElement){
			if(!(element.isDisplayed()))
				flag= false;
		}
		return flag;
	}
	
	public boolean verifyServiceLevel1AccountReceivableTableEditableFields() {
		return verifyFieldsAreEditable(getElements("LineStructureSL1AccountReceivableTableEditableFields"));
	}
	
	public boolean verifyCancelModalDialogIsDisplayed() {
		return getElement("cancelModalDialog").isDisplayed();
	}
	
	public boolean verifyCancelModalDialogContent() {
		boolean flag= false;
		if(getElement("cancelModalDialogContent").getText().equalsIgnoreCase(config.getProperty("cancelModalDialogContent")))
			flag= true;
		return flag;
	}
	
	public void clickServiceLevle1NoButton() {
		clickElement("serviceLevel1NoButton");
	}
	
	public void clickServiceLevle1yesButton() {
		clickElement("serviceLevel1YesButton");
	}
	
	public void enterDataOnServiceLevel1AccountReceivableTableColumnTotal() {
		type("lineStructureSL1AccountReceivableEditTableColumnTotal", config.getProperty("lineStructureSL1AccountReceivableTableColumnTotal"));
	}
	
	public void enterDataOnServiceLevel1AccountReceivableTableColumnExclusions() {
		type("lineStructureSL1AccountReceivableEditTableColumnExclusions", config.getProperty("lineStructureSL1AccountReceivableTableColumnExclusions"));
	}
	
	public void enterDataOnServiceLevel1AccountReceivableTableColumnAdvanceRate() {
		type("lineStructureSL1AccountReceivableEditTableColumnAdvanceRate", config.getProperty("lineStructureSL1AccountReceivableTableColumnAdvanceRate"));
	}
	
	public void clickSaveButton() {
		scrollToElement("lsSaveButton");
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(\"div.heading-elements>button.btn.btn-primary\").click()");
	}
	
	public boolean verifyServiceLevel1BusinessPageTitle() {
		return verifyDialogHeader(getElement("businessInfoPageTitle"), config.getProperty("serviceLevel1BusinessName"));
	}
	
	public boolean verifyServiceLevel2BusinessPageTitle() {
		return verifyDialogHeader(getElement("businessInfoPageTitle"), config.getProperty("serviceLevel2BusinessName"));
	}
	
	public boolean verifyServiceLevel3BusinessPageTitle() {
		return verifyDialogHeader(getElement("businessInfoPageTitle"), config.getProperty("serviceLevel3BusinessName"));
	}
	
	public boolean verifyTextContains(String elementText, String text) {
		boolean flag= false;
		if(elementText.equalsIgnoreCase(text))
			flag= true;
		return flag;
	}
	
	public boolean verifyGivenDataIsDisplayedOnSL1ColumnTotal() {
		clickElement("lineStructureSL1AccountReceivableEditTableColumnTotal");
		return verifyTextContains(getElement("lineStructureSL1AccountReceivableEditTableColumnTotal").getAttribute("value"), config.getProperty("lineStructureSL1AccountReceivableTableColumnTotal"));
	}
	
	public boolean verifyGivenDataIsDisplayedOnSL1ColumnExclusions() {
		clickElement("lineStructureSL1AccountReceivableEditTableColumnExclusions");
		return verifyTextContains(getElement("lineStructureSL1AccountReceivableEditTableColumnExclusions").getAttribute("value"), config.getProperty("lineStructureSL1AccountReceivableTableColumnExclusions"));
	}
	
	public boolean verifyGivenDataIsDisplayedOnSL1ColumnAdvanceRate() {
		clickElement("lineStructureSL1AccountReceivableEditTableColumnAdvanceRate");
		return verifyTextContains(getElement("lineStructureSL1AccountReceivableEditTableColumnAdvanceRate").getAttribute("value"), config.getProperty("lineStructureSL1AccountReceivableTableColumnAdvanceRate"));
	}
	
	public void clickToastContainer() {
		clickElement("lsAccountReceivableUpdateSavedSuccessfullyToastMessage");
	}
	
	public boolean verifyAccountReceivableUpdateSavedSuccessfullyToastMessage() {
		return verifyDialogHeader(getElement("lsAccountReceivableUpdateSavedSuccessfullyToastMessage"), config.getProperty("AccountReceivableUpdateSavedSuccessfullyToastMessageContent"));
	}
	
	public void clickYesButton() {
		clickElement("serviceLevel1YesButton");
	}
	
	public void clickAddInventoryIcon() {
		scrollToElement("addNewInventoryIcon");
		try {
			JavascriptExecutor js= (JavascriptExecutor) driver;
			js.executeScript("document.querySelector(\"table#InvenLSCollater>thead>tr>th:nth-of-type(6)>a\").click()");
			
		} catch (Exception e) {
			// TODO: handle exception
			clickElement("addNewInventoryIcon");
		}
		//JavascriptExecutor js= (JavascriptExecutor) driver;
		//js.executeScript("document.querySelector(\"table#InvenLSCollater>thead>tr>th:nth-of-type(6)>a\").click()");
		}
	
	public void clickInventoryNoButton() {
		clickElement("inventoryNoButton");
	}
	
	public boolean verifyCapLimitsDefaultText() {
		return verifyTextContains(getElement("inventoryCapLimits").getAttribute("value"), config.getProperty("inventoryCapLimitsDefaultText"));
	}
	
	public boolean verifyCapLimitsText() {
		return verifyTextContains(getElement("inventoryCapLimits").getAttribute("value"), config.getProperty("inventoryCapLimitsText"));
	}
	
	public void enterDataOnInventoryDescription() {
		type(("inventoryDescription"), config.getProperty("inventoryDescription"));
	}
	
	public void enterDataOnInventoryTotal() {
		type(("inventoryTotal"), config.getProperty("inventoryTotal"));
	}
	
	public void enterDataOnCapsLimit() {
		scrollToElement("inventoryCapLimits");
		type(("inventoryCapLimits"), config.getProperty("inventoryCapsLimit"));
	}
	
	public void enterDataOnAdvanceRate() {
		type("inventoryAdvanceRate", config.getProperty("inventoryAdvanceRate"));
	}
	
	public boolean verifyGivenDataIsDispalyedOnInventoryAdvanceRates() {
		return verifyTextContains(getElement("inventoryAdvanceRate").getAttribute("value"), config.getProperty("inventoryAdvanceRate"));
	}
	
	public boolean verifyGivenDataIsDispalyedOnInventoryCapslimit() {
		scrollToElement("inventoryCapLimits");
		return verifyTextContains(getElement("inventoryCapLimits").getAttribute("value"), config.getProperty("inventoryCapsLimit"));
	}
	
	public boolean verifyGivenDataIsDispalyedOnInventoryDescription() {
		return verifyTextContains(getElement("inventoryDescription").getAttribute("value"), config.getProperty("inventoryDescription"));
	}
	
	public boolean verifyGivenDataIsDispalyedOnInventoryTotal() {
		return verifyTextContains(getElement("inventoryTotal").getAttribute("value"), config.getProperty("inventoryTotal"));
	}
	
	public boolean verifyTotalAndExclusionsFieldsAreNonEditable(){
		boolean flag= false;
		int count= getElements("accountReceivableSummaryNonEditableFields").size();
		if(count==1) {
			flag= true;
		}
		return flag;
	}
	
	public boolean verifyAddInventoryIconIsDisplayed() {
		boolean flag= false;
		int size= getElements("addNewInventoryIcon").size();
		if(size>0)
			flag= true;
		return flag;
	}
	
	public boolean verifyYesButtonOnInventoryDialog() {
		return getElement("inventoryYesButton").isDisplayed();
	}
	
	public boolean verifyNoButtonOnInventoryDialog() {
		return getElement("inventoryNoButton").isDisplayed();
	}
	
	public void clickInventroyYesButton() {
		clickElement("inventoryYesButton");
	}
	
	public void clickOtherCollateralAddButon() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(150, 49)");
		//clickElement("otherColllateralAddNewButton");
		js.executeScript("document.querySelector(\"table#Line-Structure-OtherSummaryEdit>thead>tr>th:nth-of-type(6)>a\").click()");
	}
	
	public void enterDataOnInventorySublimitTextBox() {
		type("inventorySubLimitTextBox", config.getProperty("inventorySublimitText"));
	}
	
	public boolean verifyGivenDataIsDisplayedOnInventorySublimitText() {
		return verifyTextContains(getElement("inventorySubLimitTextBox").getAttribute("value"), config.getProperty("inventorySublimitText"));
	}
	
	public void click_OtherCollateralYesButton() {
		clickElement("otherCollateralYesButton");
	}
	
	public void enterDataOnOtherCollateralDescription() {
		type(("otherCollateralDescription"), config.getProperty("otherCollateralDescription"));
	}
	
	public void enterDataOnOtherCollateralTotal() {
		type(("otherCollateralTotal"), config.getProperty("otherCollateralTotal"));
	}
	
	public void enterDataOnOtherCollateralCapsLimit() {
		scrollToElement("otherCollateralCapLimits");
		type(("otherCollateralCapLimits"), config.getProperty("otherCollateralCapsLimit"));
	}
	
	public void enterDataOnOtherCollateralAdvanceRate() {
		type("otherCollateralAdvanceRate", config.getProperty("otherCollateralAdvanceRate"));
	}
	
	public void enterDataOnOtherCollateralSublimitTextBox() {
		type("otherCollateralSubLimitTextBox", config.getProperty("otherCollateralSublimitText"));
	}
	
	public boolean verifyOtherCollateralYesButton() {
		return getElement("otherCollateralYesButton").isDisplayed();
	}
	
	public boolean verifyOtherCollateralNoButton() {
		return getElement("otherCollateralNoButton").isDisplayed();
	}
	
	public void clickOtherCollateralYesButton() {
		clickElement("otherCollateralYesButton");
	}
	
	public boolean verifyGivenDataIsDispalyedOnOtherCollateralAdvanceRates() {
		return verifyTextContains(getElement("otherCollateralAdvanceRate").getAttribute("value"), config.getProperty("otherCollateralAdvanceRate"));
	}
	
	public boolean verifyGivenDataIsDispalyedOnOtherCollateralCapslimit() {
		scrollToElement("otherCollateralCapLimits");
		return verifyTextContains(getElement("otherCollateralCapLimits").getAttribute("value"), config.getProperty("otherCollateralCapsLimit"));
	}
	
	public boolean verifyGivenDataIsDispalyedOnOtherCollateralDescription() {
		return verifyTextContains(getElement("otherCollateralDescription").getAttribute("value"), config.getProperty("otherCollateralDescription"));
	}
	
	public boolean verifyGivenDataIsDispalyedOnOtherCollateralTotal() {
		return verifyTextContains(getElement("otherCollateralTotal").getAttribute("value"), config.getProperty("otherCollateralTotal"));
	}
	
	public boolean verifyGivenDataIsDisplayedOnOtherCollateralSublimitText() {
		return verifyTextContains(getElement("otherCollateralSubLimitTextBox").getAttribute("value"), config.getProperty("otherCollateralSublimitText"));
	}
	public void clickEditonLineStructure() throws InterruptedException {
		try {
			scrollToElement("Linestructure_Edit_Button");
			waitForElementVisible("Linestructure_Edit_Button");
			waitForElementClickable("Linestructure_Edit_Button");
			clickElement("Linestructure_Edit_Button");
		} catch (Exception e) {
			// TODO: handle exception
			ScrollUp();
			ScrollUp();
			scrollToElement("Linestructure_Edit_Button");
			ScrollUp();
			waitForElementClickable("Linestructure_Edit_Button");
			clickElement("Linestructure_Edit_Button");
		}
		//waitForElementVisible("Linestructure_Edit_Button");
		//waitForElementClickable("Linestructure_Edit_Button");
		//clickElement("Linestructure_Edit_Button");
	}
	
	public void click_Manual_Borrowing_Base_Adjustments() throws InterruptedException {
		Thread.sleep(5000);
		
		scrollToElement("Manual_Borrowing_Base_Adjustments_lable");
		Thread.sleep(2000);
		clickElement("Manual_Borrowing_Base_Adjustments");
		waitForElementClickable("Manual_Borrowing_Base_Adjustments");
		clearElement("Manual_Borrowing_Base_Adjustments");
	}
	
	public void enterDataOn_Manual_Borrowing_Base_Adjustments() {
		type(("Manual_Borrowing_Base_Adjustments"), config.getProperty("Manual_Borrowing_Base_Adjustments"));
	}
	public void click_Manual_Borrowing_Base_Adjustments_val() throws InterruptedException {
		
		Thread.sleep(2000);
		clickElement("Manual_Borrowing_Base_Adjustments_Value");
		waitForElementClickable("Manual_Borrowing_Base_Adjustments_Value");
		clearElement("Manual_Borrowing_Base_Adjustments_Value");
	}
	
	public void enterDataOn_Manual_Borrowing_Base_Adjustments_val() {
		type(("Manual_Borrowing_Base_Adjustments_Value"), config.getProperty("Manual_Borrowing_Base_Adjustments_Value"));
	}
public void click_Open_credits_field() throws InterruptedException {
		
		Thread.sleep(2000);
		clickElement("Open_credits_field");
		waitForElementClickable("Open_credits_field");
		clearElement("Open_credits_field");
	}
	
	public void enterDataOn_Open_credits_field() {
		type(("Open_credits_field"), config.getProperty("Open_credits_field"));
	}
	public boolean verifyGivenDataIsDispalyedOn_Manual_Borrowing_Base_Adjustments() {
		scrollToElement("Manual_Borrowing_Base_Adjustments_lable");
		return verifyTextContains(getElement("Manual_Borrowing_Base_Adjustments").getAttribute("value"), config.getProperty("Manual_Borrowing_Base_Adjustments"));
	}
	public void Navigate_to_HP() {
		clickElement("Homepage_Link");
	}
	
	public void clickTabAgingDetail() {
		clickElement("Aging_Detail_Tab");
	}
	public boolean verifyLV_HP_Lable() {
		return verifyDialogHeader(getElement("LV_Homepage_Text"), config.getProperty("LV_Homepage_Text"));
	}
	public void clickOnFI_Name_1() {
		type("searchTextBox", config.getProperty("FI_Name_1"));
		clickElement("businessNameList");
	}
	public boolean verifyLV_Business1_Lable() {
		return verifyDialogHeader(getElement("LV_bus_Title_1"), config.getProperty("FI_Name_1"));
		
	}
}

