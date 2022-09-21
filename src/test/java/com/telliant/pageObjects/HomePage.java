package com.telliant.pageObjects;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.telliant.core.web.BaseClass;
import com.telliant.core.web.ExcelMethods;
import com.telliant.core.web.WebDriverRoot;
import java.util.logging.Level; 
import java.util.logging.Logger;

public class HomePage extends WebDriverRoot {
	 JavascriptExecutor js = (JavascriptExecutor)driver;
	 Logger logger = Logger.getLogger(HomePage.class.getSimpleName());
	 ArrayList<String> arrayList= new ArrayList<String>();
	 static String getVal=null;
		
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
	
	public void clickDropdown_HP() throws InterruptedException, AWTException {
		waitForElementVisible("LV_FilterBy_dropdown");
		clickElement("LV_FilterBy_dropdown");
		Thread.sleep(2000);
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(\"#mat-select-0-panel > div.mat-option.ng-tns-c57-1 > input\").click()");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	
	}
	public void click_Active_HP() throws InterruptedException, AWTException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		Robot robot = new Robot();
		clickElement("LV_FilterBy_dropdown");
		js.executeScript("document.querySelector(\"#mat-select-0-panel > div.mat-option.ng-tns-c57-1 > input\").click()");
		js.executeScript("document.querySelector(\"#mat-option-0 > mat-pseudo-checkbox\").click()");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	public void click_Pending_Active_HP() throws InterruptedException, AWTException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		Robot robot = new Robot();
		clickElement("LV_FilterBy_dropdown");
		js.executeScript("document.querySelector(\"#mat-option-0 > mat-pseudo-checkbox\").click()");
		js.executeScript("document.querySelector(\"#mat-option-1 > mat-pseudo-checkbox\").click()");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	public void click_License_Matured() throws InterruptedException, AWTException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		Robot robot = new Robot();
		clickElement("LV_FilterBy_dropdown");
		js.executeScript("document.querySelector(\"#mat-option-1 > mat-pseudo-checkbox\").click()");
		js.executeScript("document.querySelector(\"#mat-option-2 > mat-pseudo-checkbox\").click()");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	public void click_Terminated() throws InterruptedException, AWTException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		Robot robot = new Robot();
		clickElement("LV_FilterBy_dropdown");
		js.executeScript("document.querySelector(\"#mat-option-2 > mat-pseudo-checkbox\").click()");
		js.executeScript("document.querySelector(\"#mat-option-3 > mat-pseudo-checkbox\").click()");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	
	public boolean verify_Active_Status() {
		 return verifyDialogHeader(getElement("Active_Status"), config.getProperty("Active_Status"));
	}
	public boolean verify_Pending_Active_Status() {
		 return verifyDialogHeader(getElement("PendingActive_Status"), config.getProperty("PendingActive_Status"));
	}
	public boolean verify_License_Matured_Status() {
		 return verifyDialogHeader(getElement("License_Matured_Status"), config.getProperty("License_Matured_Status"));
	}
	public boolean verify_Terminated_Status() {
		 return verifyDialogHeader(getElement("Terminated_Status"), config.getProperty("Terminated_Status"));
	}
	//```````````````````````````````````````````````````````````````````
	public boolean verify_Active_Status_Grid() {
		 return verifyDialogHeader(getElement("Grid_Status"), config.getProperty("Active_Status"));
	}
	public boolean verify_Pending_Active_Status_Grid() {
		 return verifyDialogHeader(getElement("Grid_Status"), config.getProperty("PendingActive_Status"));
	}
	public boolean verify_License_Matured_Status_Grid() {
		 return verifyDialogHeader(getElement("Grid_Status"), config.getProperty("License_Matured_Status"));
	}
	public boolean verify_Terminated_Status_Grid() {
		 return verifyDialogHeader(getElement("Grid_Status"), config.getProperty("Terminated_Status"));
	}
	public void clickSidemenu_FI_Admin() {
		clickElement("Sidemenu_FI_Admin");
	}
	public void click_Edit_FI() {
		type("Search_FIname",config.getProperty("Search_FIname"));
		clickElement("Edit_FI");
	}
	public void click_Add_Admin_User() {
		clickElement("AddUser_Tab_FI");
		type("First_Name_FI_TB",config.getProperty("First_Name_FI_TB"));
		type("Last_Name_FI_TB", config.getProperty("Last_Name_FI_TB"));
		type("Contact_Name_FI_TB", config.getProperty("Contact_Name_FI_TB"));
		type("Contact_Number_FI_TB", config.getProperty("Contact_Number_FI_TB"));
		type("Email_Address_FI_TB", ExcelMethods.getData("Sheet1", "emailID", 1));
		type("Contact_Title_FI_TB", config.getProperty("Contact_Title_FI_TB"));
		clickElement("Role_FI_TB");
		clickElement("Administrator_FI_TB");
		clickElement("isPrimaryAdmin_Dwn_FI_TB");
		try {
			clickElement("isPrimaryAdmin_Dwn_Yes_FI_TB");
			
		} catch (Exception e) {
			// TODO: handle exception
			clickElement("isPrimaryAdmin_Dwn_No_FI_TB");
		}
		//clickElement("isPrimaryAdmin_Dwn_Yes_FI_TB");
		//clickElement("isPrimaryAdmin_Dwn_No_FI_TB");
		clickElement("User_Status_FI_TB");
		clickElement("Restricted_dwn_value_FI_TB");
		clickElement("Save_Btn_FI_TB");
		clickElement("Toast_Container");
		clickElement("Toast_Container");
	}
	public void click_Add_Portfolio_Manager_User() {
		clickElement("AddUser_Tab_FI");
		type("First_Name_FI_TB",config.getProperty("First_Name_FI_TB"));
		type("Last_Name_FI_TB", config.getProperty("Last_Name_FI_TB"));
		type("Contact_Name_FI_TB", config.getProperty("Contact_Name_FI_TB"));
		type("Contact_Number_FI_TB", config.getProperty("Contact_Number_FI_TB"));
		type("Email_Address_FI_TB",ExcelMethods.getData("Sheet1", "emailID", 1));
		type("Contact_Title_FI_TB", config.getProperty("Contact_Title_FI_TB"));
		clickElement("Role_FI_TB");
		clickElement("Portfolio_Manager_FI_TB");
		clickElement("Fi_portfolio_dwn_FI_TB");
		clickElement("Fi_portfolio_dwn_Val_FI_TB");
		clickElement("User_Status_FI_TB");
		clickElement("Restricted_dwn_value_FI_TB");
		clickElement("Save_Btn_FI_TB");
		clickElement("Toast_Container");
		clickElement("Toast_Container");
		clickElement("Toast_Container");
	}
	public void updateEmpmailId() throws IOException { 
		 
		// Initializing String
        String Str = new String(config.getProperty("Email_Address_FI_TB"));
        System.out.println(Str);
        String S =Str.substring(9, 12) ;
        System.out.println(S);
        //convert string to integer
        int i = Integer.parseInt(S); 	
        int j=i+1; 	
        //convert integer to String 
        String sj=String.valueOf(j);
        String si=String.valueOf(i);
        String s1=new String(config.getProperty("Email_Address_FI_TB")); 
        String replaceString=s1.replaceAll(si,sj);  
        System.out.println("Updated Employee mailid is :" +replaceString); 
  
         FileOutputStream out = new FileOutputStream("config.properties");
	     FileInputStream in = new FileInputStream("config.properties");
	     Properties props = new Properties();
	     props.load(in);
	     in.close();
         props.setProperty("Email_Address_FI_TB",replaceString);
         props.store(out,null);
         out.close();
       
	}
	public void updateEmpmailIdInExcel() { 
        // Initializing String
        String Str = new String(ExcelMethods.getData("Sheet1", "emailID", 1));
        String S =Str.substring(10, 13) ;
        //System.out.println(S);
        logger.log(Level.INFO, S); 
        //convert string to integer
        int i = Integer.parseInt(S); 	
        int j=i+1; 	
        //convert integer to String 
        String sj=String.valueOf(j);
        String si=String.valueOf(i);
        String s1=new String(ExcelMethods.getData("Sheet1", "emailID", 1)); 
        String replaceString=s1.replaceAll(si,sj);  
        System.out.println("Updated Employee mailid is :" +replaceString); 
        ExcelMethods.putData("Sheet1", "emailID", 1, replaceString);    
}
	public void click_Delete_flow_FI_User() {
		clickElement("AddUser_Tab_FI");
		//type("Search_FIname",config.getProperty("User_delete"));
		clickElement("Delete_icon_FI_User_TB");
		clickElement("Toast_Container");
		
	}
	public boolean verifyDeleted_FI_Notification() {
		return verifyDialogHeader(getElement("Toast_Container"), config.getProperty("Toast_Container_delete"));
	}
	
	public void click_Edit_flow_FI_User() {
		clickElement("AddUser_Tab_FI");
		clickElement("Edit_User_FI");
		type("First_Name_FI_TB",config.getProperty("First_Name_FI_TB_Edit"));
		type("Last_Name_FI_TB", config.getProperty("Last_Name_FI_TB_Edit"));
		type("Contact_Name_FI_TB", config.getProperty("Contact_Name_FI_TB_Edit"));
		type("Contact_Number_FI_TB", config.getProperty("Contact_Number_FI_TB_Edit"));
		type("Contact_Title_FI_TB", config.getProperty("Contact_Title_FI_TB_Edit"));
		clickElement("Save_Btn_FI_TB");
		clickElement("Toast_Container");
	}

	public void Verify_Edit_FI_User() {
		clickElement("Edit_User_FI");
	}
	public void Cancel_btn() {
		clickElement("Cancel_btn_Edit_User_FI");
	}
	public boolean verify_First_Name_FI_TB() {	
		boolean flag = false;
		String s1= getElement("First_Name_Check").getAttribute("value");
		if(s1.equalsIgnoreCase(config.getProperty("First_Name_FI_TB_Edit"))){
			flag =true;
		}
	return flag;
	}
	public boolean verify_Last_Name_FI_TB() {
		
		boolean flag = false;
		String s1= getElement("Last_Name_FI_TB").getAttribute("value");
		if(s1.equalsIgnoreCase(config.getProperty("Last_Name_FI_TB_Edit"))){
			flag =true;
		}
	return flag;
	}
	public boolean verify_Contact_Name_FI_TB() {
		boolean flag = false;
		String s1= getElement("Contact_Name_FI_TB").getAttribute("value");
		if(s1.equalsIgnoreCase(config.getProperty("Contact_Name_FI_TB_Edit"))){
			flag =true;
		}
	return flag;
		
	}
	public boolean verify_Contact_Number_FI_TB() {
		
		boolean flag = false;
		String s1= getElement("Contact_Number_FI_TB").getAttribute("value");
		if(s1.equalsIgnoreCase(config.getProperty("Contact_Number_FI_TB_Edit"))){
			flag =true;
		}
	return flag;
	}
	public boolean verify_Contact_Title_FI_TB() {
			
		boolean flag = false;
		String s1= getElement("Contact_Title_FI_TB").getAttribute("value");
		if(s1.equalsIgnoreCase(config.getProperty("Contact_Title_FI_TB_Edit"))){
			flag =true;
		}
	return flag;
	}
	public void clickFiAdmin() {
		waitForElementClickable("toogleButton_FiAdmin");
		clickElement("toogleButton_FiAdmin");
		waitForElementClickable("sideMenu_FIAdmin");
		clickElement("sideMenu_FIAdmin");
	}
	
	public void clickUserAdmin() {
		//waitForElementClickable("toogleButton_FiAdmin");
		//clickElement("toogleButton_FiAdmin");
		waitForElementClickable("sideMenu_UserAdmin");
		clickElement("sideMenu_UserAdmin");
	}
	
	public boolean verifyAddFinancialInstiutionButtonIsDisplayed() {
		return getElement("button_AddFinancialInstiution").isDisplayed();
	}
	
	public void click_AddFinancialInstiutionButton() {
		waitForElementClickable("button_AddFinancialInstiution");
		clickElement("button_AddFinancialInstiution");
	}
	
	public boolean verifyAddFinancialInstitutionPageTitle() {
		return verifyDialogHeader(getElement("homePage_Title"), config.getProperty("addFinancialInstitutePageTitle"));
	}
	
	public ArrayList<String> getCardHeadersOnAddFiPage(){
		ArrayList<String> headers= new ArrayList<>();
		headers.add(config.getProperty("financialInstitutionInformation"));
		headers.add(config.getProperty("serviceLevel"));
		headers.add(config.getProperty("licensed"));
		headers.add(config.getProperty("otherDetails"));
		return headers;
	}
	
	public boolean verifyCardHeadersOnAddFiPage() {
		boolean flag= true;
		ArrayList<String> headers= new ArrayList<>();
		for(WebElement element: getElements("card_Header")) {
			headers.add(element.getText());
		}
		for(int i=0; i<getCardHeadersOnAddFiPage().size(); i++) {
			if(!(getCardHeadersOnAddFiPage().get(i).equalsIgnoreCase(headers.get(i)))){
				System.out.println("Expected: "+getCardHeadersOnAddFiPage().get(i));
				System.out.println("Actual: "+headers.get(i));
				flag= false;
				break;
			}
		}
		return flag;	
	}
	
	public void enterDataOn_FinancialInstiution() {
		
		//type(("addFi_FinancialInstiutionName"), config.getProperty("addFi_FinancialInstiutionName")+randStrGen("N", 3));
		String S1= config.getProperty("addFi_FinancialInstiutionName")+randStrGen("N", 3);
		type(("addFi_FinancialInstiutionName"), S1);
		type(("addFi_Address1"), config.getProperty("addFi_Address1"));
		type(("addFi_Address2"), config.getProperty("addFi_Address2"));
		type(("addFi_City"), config.getProperty("addFi_City"));
		type(("addFi_State"), config.getProperty("addFi_State"));
		type(("addFi_ZipCode"), config.getProperty("addFi_ZipCode"));
		getVal= getElement("addFi_FinancialInstiutionName").getAttribute("value");
		System.out.println(getVal);
		ExcelMethods.putData("Sheet1", "Finame", 1, S1); 
		System.out.println("the string value is  " +S1);
	}
	
	public void enterDataOn_ServiceLevelFeeStrucure1() {
		type("addFi_SeriveLevel1_FIFeeDefault", config.getProperty("addFi_SeriveLevel1_FIFeeDefault")+randStrGen("N", 3));
		type("addFi_SeriveLevel1_StartDate", config.getProperty("addFi_SeriveLevel1_StartDate"));
		type("addFi_SeriveLevel1_EndDate", config.getProperty("addFi_SeriveLevel1_EndDate"));
		type("addFi_SeriveLevel1_FieldOverrideApprovedDate", config.getProperty("addFi_SeriveLevel1_FieldOverrideApprovedDate"));
	}
	
	public void enterDataOn_ServiceLevelFeeStrucure2() {
		type("addFi_SeriveLevel2_FIFeeDefault", config.getProperty("addFi_SeriveLevel2_FIFeeDefault")+randStrGen("N", 3));
		type("addFi_SeriveLevel2_StartDate", config.getProperty("addFi_SeriveLevel2_StartDate"));
		type("addFi_SeriveLevel2_EndDate", config.getProperty("addFi_SeriveLevel2_EndDate"));
		type("addFi_SeriveLevel2_FieldOverrideApprovedDate", config.getProperty("addFi_SeriveLevel2_FieldOverrideApprovedDate"));
	}
	
	public void enterDataOn_ServiceLevelFeeStrucure3() {
		type("addFi_SeriveLevel3_FIFeeDefault", config.getProperty("addFi_SeriveLevel3_FIFeeDefault")+randStrGen("N", 3));
		type("addFi_SeriveLevel3_StartDate", config.getProperty("addFi_SeriveLevel3_StartDate"));
		type("addFi_SeriveLevel3_EndDate", config.getProperty("addFi_SeriveLevel3_EndDate"));
		type("addFi_SeriveLevel3_FieldOverrideApprovedDate", config.getProperty("addFi_SeriveLevel3_FieldOverrideApprovedDate"));
	}
	
	public ArrayList<String> getFiStatusDropdownList(){
		ArrayList<String> fiStatus= new ArrayList<String>();
		fiStatus.add(config.getProperty("fiStatus_Active"));
		fiStatus.add(config.getProperty("fiStatus_PendingActive"));
		fiStatus.add(config.getProperty("fiStatus_LicenseMatured"));
		fiStatus.add(config.getProperty("fiStatus_Terminated"));
		return fiStatus;
	}
	
	public ArrayList<String> getFiCoreSyncDropdownList(){
		ArrayList<String> fiStatus= new ArrayList<String>();
		fiStatus.add(config.getProperty("fiCoreSync_Manual"));
		fiStatus.add(config.getProperty("fiCoreSync_Automated"));
		return fiStatus;
	}
	
	public ArrayList<String> getFiTypeyDropdownList(){
		ArrayList<String> fiStatus= new ArrayList<String>();
		fiStatus.add(config.getProperty("fiType_Bank"));
		fiStatus.add(config.getProperty("fiType_CreditUnion"));
		fiStatus.add(config.getProperty("fiType_Other"));
		return fiStatus;
	}
	
	public boolean verifyDropDownList(ArrayList<String> expectedList, String elementLocator) {
		boolean flag= true;
		List<WebElement>elementList= getElements(elementLocator);
		ArrayList<String> dropdownText= new ArrayList<>();
		for(WebElement element:elementList) {
			dropdownText.add(element.getText());
		}
		for(int i=0; i<expectedList.size(); i++) {
			if(!(expectedList.get(i).equalsIgnoreCase(dropdownText.get(i)))) {
				flag= false;
				break;
			}
		}
		return flag;
	}
	
	public boolean verifyFiStatusDropDownList() {
		return verifyDropDownList(getFiStatusDropdownList(), "addFi_Licensed_FiStatusList");
	}
	
	public boolean verifyFiCoreSyncDropDownList() {
		return verifyDropDownList(getFiCoreSyncDropdownList(), "addFi_Licensed_FiCoreSyncList");
	}
	
	public boolean verifyFiTypeDropDownList() {
		return verifyDropDownList(getFiTypeyDropdownList(), "addFi_OtherDetails_FiTypeList");
	}
	
	public boolean verifyLvAccountManagerDropDownList() {
		boolean flag= true;
		ArrayList<String> dropDownList= new ArrayList<>();
		List<WebElement> elementList= getElements("addFi_Licensed_LvAccountManagerlist");
		for(WebElement element: elementList) {
			String user= element.getText();
			dropDownList.add(user);
		}
		if(!(dropDownList.containsAll(arrayList)))
			flag= false;
		if(!(arrayList.containsAll(dropDownList)))
			flag= false;
		return flag;
	}
	
	public void selectFromLvAccountManagerDropDown() {
		selectDropdownByIndex("addFi_Licensed_LvAccountManager", 1);
	}
	
	public void selectFromFiStatusDropDown() {
		selectDropdownByVisibleTxt("addFi_Licensed_FiStatus", config.getProperty("fiStatus_PendingActive"));
	}
	
	public void selectFromFiCoreSyncDropDown() {
		selectDropdownByVisibleTxt("addFi_Licensed_FiCoreSync", config.getProperty("fiCoreSync_Manual"));
	}
	
	public void selectFromFiType() {
		selectDropdownByIndex("addFi_OtherDetails_FiType", 1);
	}
	
	public void enterDataOn_OtherDetaislAssestSize() {
		type("addFi_OtherDetails_AssetSize", config.getProperty("addFi_OtherDetails_AssestSize"));
	}
	
	public void enterDataOn_LicensedDate() {
		type("addFi_Licensed_LicensedDate", config.getProperty("addFi_LicensedDate"));
	}
	
	public void enterDataOn_LicenseRateCommission() {
		type("addFi_Licensed_LicensedRate", config.getProperty("addFi_LicensedRateCommission"));
	}
	
	public void enterDataOn_LicensedMaturityDate() {
		type("addFi_Licensed_LicensedMaturityDate", config.getProperty("addFi_LicensedMaturityDate"));
	}
	
	public ArrayList<String> getAccountManagerList() {
		arrayList.clear();
		int pageCount= driver.findElements(By.cssSelector("div.page-count>ul>li")).size();
		for(int i=2; i<pageCount; i++)
		{
			JavascriptExecutor jsp= (JavascriptExecutor) driver;
			jsp.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.page-count>ul>li:nth-of-type("+i+")>a")));
			int rowCount= driver.findElements(By.cssSelector("table#userlist>tbody>tr")).size();
			for(int j=1; j<=rowCount; j++) {
				jsp.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.cssSelector("table#userlist>tbody>tr:nth-of-type("+j+")>td:nth-of-type(8)")));
				String roleName= driver.findElement(By.cssSelector("table#userlist>tbody>tr:nth-of-type("+j+")>td:nth-of-type(8)")).getText();
				if(roleName.equalsIgnoreCase("Account Manager")) {
					jsp.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector("table#userlist>tbody>tr:nth-of-type("+j+")>td:nth-of-type(4)")));
					String userName= driver.findElement(By.cssSelector("table#userlist>tbody>tr:nth-of-type("+j+")>td:nth-of-type(4)")).getText();
					arrayList.add(userName);
				}
			}
		}
		return arrayList;
	}
	
	public boolean verifyUserCreationMessageIsDisplayed() {
		return verifyDialogHeader(getElement("toast_UserUpdatedSuccessfully"), config.getProperty("fiUserCreationMessage"));
	}
	
	public boolean verifyFiAdminPageTitle() {
		boolean flag= false;
		String pageTitle=getElement("homePage_Title").getText();
		if(pageTitle.equalsIgnoreCase(config.getProperty("lendovativeAdminPageTitle")))
			flag= true;
		return flag;
	}

	public void clickDropdown_FI() throws InterruptedException, AWTException {
		waitForElementVisible("LV_FilterBy_dropdown_FI");
		clickElement("LV_FilterBy_dropdown_FI");
		Thread.sleep(2000);
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(\"#mat-select-4-panel > div.mat-option.ng-tns-c57-5 > input\").click()");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	
	}
	public void click_Active_FI() throws InterruptedException, AWTException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		Robot robot = new Robot();
		clickElement("LV_FilterBy_dropdown_FI");
		js.executeScript("document.querySelector(\"#mat-select-4-panel > div.mat-option.ng-tns-c57-5 > input\").click()");
		js.executeScript("document.querySelector(\"#mat-option-4 > mat-pseudo-checkbox\").click()");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	public void click_Pending_Active_FI() throws InterruptedException, AWTException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		Robot robot = new Robot();
		clickElement("LV_FilterBy_dropdown_FI");
		js.executeScript("document.querySelector(\"#mat-option-4 > mat-pseudo-checkbox\").click()");
		js.executeScript("document.querySelector(\"#mat-option-5 > mat-pseudo-checkbox\").click()");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	public void click_License_Matured_FI() throws InterruptedException, AWTException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		Robot robot = new Robot();
		clickElement("LV_FilterBy_dropdown_FI");
		js.executeScript("document.querySelector(\"#mat-option-5 > mat-pseudo-checkbox\").click()");
		js.executeScript("document.querySelector(\"#mat-option-6 > mat-pseudo-checkbox\").click()");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	public void click_Terminated_FI() throws InterruptedException, AWTException {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		Robot robot = new Robot();
		clickElement("LV_FilterBy_dropdown_FI");
		js.executeScript("document.querySelector(\"#mat-option-6 > mat-pseudo-checkbox\").click()");
		js.executeScript("document.querySelector(\"#mat-option-7 > mat-pseudo-checkbox\").click()");
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	public boolean verify_Active_Status_Grid_FI() {
		 return verifyDialogHeader(getElement("Grid_Status_FI"), config.getProperty("Active_Status"));
	}
	public boolean verify_Pending_Active_Status_Grid_FI() {
		 return verifyDialogHeader(getElement("Grid_Status_FI"), config.getProperty("PendingActive_Status"));
	}
	public boolean verify_License_Matured_Status_Grid_FI() {
		 return verifyDialogHeader(getElement("Grid_Status_FI"), config.getProperty("License_Matured_Status"));
	}
	public boolean verify_Terminated_Status_Grid_FI() {
		 return verifyDialogHeader(getElement("Grid_Status_FI"), config.getProperty("Terminated_Status"));
	}
	public void Add_User_Flow_FI() {
		clickElement("Toast_Container");
		clickElement("toogleButton_FiAdmin");
		
	}
        public void Select_new_Protfolio() {
        	clickElement("Toast_Container");
        	clickElement("add_Protfolio_Tab");
        	BaseClass.refresh();
        	String s1 = ExcelMethods.getData("Sheet1", "Finame", 1);
        	  Select select = new Select(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/app-lending-watchdog/lv-add-portfolio/app-add-portfolio-utils/section/div[1]/div/div[1]/div[1]/div/select")));
            List<WebElement> optionElements = driver.findElement(By.cssSelector("#mainContent > div > app-lending-watchdog > lv-add-portfolio > app-add-portfolio-utils > section > div.CustomTab > div > div.card-header > div:nth-child(1) > div > select")).findElements(By.tagName("option"));

            for (WebElement optionElement: optionElements) {
                if (optionElement.getText().contains(s1)) {
                    String optionIndex = optionElement.getAttribute("index");
                    select.selectByIndex(Integer.parseInt(optionIndex));
                    break;   
                }
            }
        }
            public void Create_new_Protfolio() {
            	waitForElementClickable("Add_Icon_Protfolio");
            clickElement("Add_Icon_Protfolio");
            waitForElementClickable("Protfolio_Name_Field");
            type("Protfolio_Name_Field",config.getProperty("Protfolio_name"));
            clickElement("Create_Protfolio_Icon");
            clickElement("Toast_Container");
            }
            
            public void Navigate_To_FI() {
            	clickElement("Sidemenu_FI_Admin");
            	scrollToElement("ScrollTo_Dropdown");
            	clickElement("Click_FI_RowSelection_Dropdown");
            	clickElement("Click_FI_RowSelection_Dropdown_Value");
            	scrollToElement("Search_FIname");
        		type("Search_FIname",ExcelMethods.getData("Sheet1", "Finame", 1));
        		clickElement("Edit_FI");
            }
            public void click_Sidebar_Icon() {
                clickElement("Sidebar_Icon");  
        }
                                                         
         public void click_Add_User_Admin_Menu() {  
             clickElement("Sidebar_Icon");
             clickElement("User_Admin_Menu");
             clickElement("Sidebar_Icon");
             type("First_Name", config.getProperty("First_Name"));
             type("Last_Name", config.getProperty("Last_Name"));
             type("Contact_Name", config.getProperty("Contact_Name"));
             type("Contact_Number", config.getProperty("Contact_Number"));
             type("Contact_Title", config.getProperty("Contact_Title"));
             type("Email_Address", config.getProperty("Email_Address"));
             clickElement("ContactSecurityRole_Dropdown");
             clickElement("Administrator_Dropdown");
             clickElement("Account_Manager_Dropdown");
             clickElement("Viewer_Dropdown");
             clickElement("Save_Button");   
       }
	        
          public boolean verifyLV_User_Admin_Page_Title() {
           return verifyDialogHeader(getElement("LV_User_Admin_Page_Title"), config.getProperty("Lendovative User Admin"));
          }
          public void click_User_Admin_Menu() {   
           clickElement("User_Admin_Menu");
          }
       
      public void click_User_Edit_Save() {  
         clickElement("Sidebar_Icon");
       clickElement("User_Admin_Menu");
       clickElement("Sidebar_Icon");
       scrollToElement("Edit_Button");
       waitForElementClickable("Edit_Button");
           clickElement("Edit_Button");
       type("First_Name", config.getProperty("First_Name"));
         type("Last_Name", config.getProperty("Last_Name"));
         type("Contact_Name", config.getProperty("Contact_Name"));
         type("Contact_Number", config.getProperty("Contact_Number"));
         type("Contact_Title", config.getProperty("Contact_Title"));
         type("Email_Address", config.getProperty("Email_Address"));
         clickElement("ContactSecurityRole_Dropdown");
         clickElement("Administrator_Dropdown");
         scrollToElement("Save_Button");
           waitForElementClickable("Save_Button");
         clickElement("Save_Button");  
         clickElement("Toast_Container");
	public void click_EditButtonOnFiAdminUser() throws InterruptedException {
		List<WebElement> elements= getElements("fi_Table");
		boolean flag= false;
		JavascriptExecutor js= (JavascriptExecutor) driver;
		int j=0;
		int pageCount= driver.findElements(By.cssSelector("div.page-count>ul>li")).size();
		for(int i=2; i<pageCount; i++)
		{
			js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div.page-count>ul>li:nth-of-type("+i+")>a")));
			for(WebElement element: elements) {
			j++;
			String fiName= element.getText();
			if(fiName.contains(config.getProperty("addFi_FinancialInstiutionName"))) {
				flag= true;
				String num= String.valueOf(j);
				scrollToElement(("fi_Table"));
				js.executeScript("arguments[0].click();", getElement("fi_Table_Parametrized",num));
				break;
			}
		}
			if(flag) {
				break;
			}
	     	
      }
      public void click_User_Cancel_Delete() {  
             clickElement("Sidebar_Icon");
             clickElement("User_Admin_Menu");  
             clickElement("Sidebar_Icon");
             scrollToElement("Edit_Button");
               waitForElementClickable("Edit_Button");
             clickElement("Edit_Button");
             type("First_Name", config.getProperty("First_Name"));
             type("Last_Name", config.getProperty("Last_Name"));
             type("Contact_Name", config.getProperty("Contact_Name"));
             type("Contact_Number", config.getProperty("Contact_Number"));
             type("Contact_Title", config.getProperty("Contact_Title"));
             type("Email_Address", config.getProperty("Email_Address"));
             clickElement("ContactSecurityRole_Dropdown");
             clickElement("Administrator_Dropdown");
               scrollToElement("Cancel_Button");
               waitForElementClickable("Cancel_Button");
             clickElement("Cancel_Button");
             scrollToElement("Page_2");
               waitForElementClickable("Page_2");
             clickElement("Page_2");
             clickElement("Delete_Button");  
             clickElement("Toast_Container");
      }
      public void Create_New_Business() {  
             clickElement("Sidebar_Icon");
             scrollToElement("Business_Admin_Menu");
               waitForElementClickable("Business_Admin_Menu");
             clickElement("Business_Admin_Menu");
             clickElement("Sidebar_Icon");
             clickElement("Add_Business_Button");
             type("Business_Name", config.getProperty("Business_Name"));
             type("Business_Phone", config.getProperty("Business_Phone"));
             type("Business_Email", config.getProperty("Business_Email"));
             type("Business_Address_Line1", config.getProperty("Business_Address_Line1"));
             type("Business_Address_Line2", config.getProperty("Business_Address_Line2"));
             type("Business_city", config.getProperty("Business_city"));
             type("Business_State", config.getProperty("Business_State"));
             type("Zipcode", config.getProperty("Zipcode"));
             type("NAICS_Industry_Code", config.getProperty("NAICS_Industry_Code"));
      
      	
   }
    }
        	}
	
       		public boolean verify_FiName() {
		boolean flag= false;
		scrollToElement("addFi_FinancialInstiutionName");
		String inputVal= getElement("addFi_FinancialInstiutionName").getAttribute("value");
		System.out.println(inputVal);
		if(inputVal.equalsIgnoreCase(getVal))
			flag= true;
		return flag;
	}

}

