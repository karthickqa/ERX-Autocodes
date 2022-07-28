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
	
	public void navigateToEmpCreation() throws InterruptedException {
		//Thread.sleep(5000);
		clickElement("sideMenuBtn_HP");
		
		try {
			clickElement("employeeHubSideMenuBtn_HP");
		}catch(TimeoutException | ElementNotInteractableException e) {
			getElement("sideMenuBtn_HP").click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			clickElement("employeeHubSideMenuBtn_HP");
		}
		//Thread.sleep(5000);
		clickElement("addEmpBtn_EH");
	}
	
	public void navigateBackToHomepage() {
		
		clickElement("sideMenuBtn_HP");
		clickElement("homePageSideMenu_HP");		
	}
	
	public void navigateToDuetimecard() throws InterruptedException {
		//Thread.sleep(6000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		clickElement("duetab_HP");
		 // ele3 =getElement("DueWeekSep5-11").getText();	
		//System.out.println(ele3);		
	    clickElement("Due_value_HP");
	}
	
	public void navigateToCusCreation() throws InterruptedException, ElementClickInterceptedException {
		//Thread.sleep(5000);
		try {
		clickElement("sideMenuBtn_HP");
		clickElement("customerHubSideMenuBtn_HP");
		}catch(ElementNotInteractableException| TimeoutException e) {
			driver.navigate().refresh();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			Thread.sleep(1000);
			getElement("sideMenuBtn_HP").click();
			 getElement("customerHubSideMenuBtn_HP").click();
			/*js.executeScript("arguments[0].click();", getElement("sideMenuBtn_HP"));
			js.executeScript("arguments[0].click();", getElement("customerHubSideMenuBtn_HP"));*/
		}finally {
			 getElement("sideMenuBtn_HP").click();
			 getElement("customerHubSideMenuBtn_HP").click();
		}
		//Thread.sleep(5000);
		clickElement("addbtn_CH");
	}
	public void navigateChangePwd() throws InterruptedException {
		 
		waitForElementClickable("Changepwd_icon");
		try {
		clickElement("Changepwd_icon");
		}catch(ElementClickInterceptedException e) {
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			js.executeScript("arguments[0].click();", getElement("Changepwd_icon"));
		}
		}
	public void navigaterejectedtimecard() throws InterruptedException {
		waitForElementVisible("rejectedtab_HP");
		clickElement("rejectedtab_HP");
		waitForElementVisible("rejectedvalue_HP");
		clickElement("rejectedvalue_HP");
		}
	public void TimeCardSucc_Notification() throws InterruptedException {
		waitForElementVisible("notification");
		//clickElement("notification");
		Assert.assertEquals( getText("notification"), "Notification"+ '\n' +"TimeCard Submitted Successfully");
		clickElement("noti_cls");
	}
	
	public void navigateToMytimecardPg() throws InterruptedException {
		
		clickElement("sideMenuBtn_HP");
		
		clickElement("MyTimecardPage_HP");
		
	}
	public void navigateToSubmittedtimecardpage() throws InterruptedException {
		clickElement("submitTab_HP");
		clickElement("submittedvalue_HP");
		clickElement("submittedweek_HP");
		String actualSubmittedWeekrange = getText("submittedweek_HP");
		//System.out.println(actualSubmittedWeekrange);
		logger.log(Level.INFO, actualSubmittedWeekrange); 
		clickElement("submittedHours_HP");
		String actualSubmittedHours = getText("submittedHours_HP");
		//System.out.println(actualSubmittedHours);	
		logger.log(Level.INFO, actualSubmittedHours); 
	}
	
	public void EmpHubnavigation() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			//Thread.sleep(1000);
			getElement("sideMenuBtn_HP").click();
			//Thread.sleep(1000);
			
			try {
				clickElement("employeeHubSideMenuBtn_HP");
			}catch(TimeoutException | ElementNotInteractableException e) {
				getElement("sideMenuBtn_HP").click();
				driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				clickElement("employeeHubSideMenuBtn_HP");
			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			
		}
	
public void navigateToMytimecard(int rowNo) throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		try {
			clickElement("duetab_HP");
		}catch(NoSuchElementException e){
			
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", getElement("duetab_HP"));
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		ExcelMethods.putData("Sheet2", "Week", rowNo, getElement("FrstDueTimecards_HP").getText());
		System.out.println(getElement("FrstDueTimecards_HP").getText());
		
	   try { clickElement("FrstDueTimecards_HP");
	}catch(NullPointerException e){
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", getElement("FrstDueTimecards_HP"));
	}
	   
	}

public void navigateToRolesAndTask() throws InterruptedException, ElementClickInterceptedException {
	//Thread.sleep(5000);
	try {
	clickElement("sideMenuBtn_HP");
	clickElement("RolesAndTaskSideMenuBtn_HP");
	}catch(ElementNotInteractableException| TimeoutException e) {
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		Thread.sleep(1000);
		getElement("sideMenuBtn_HP").click();
		 getElement("RolesAndTaskSideMenuBtn_HP").click();
		/*js.executeScript("arguments[0].click();", getElement("sideMenuBtn_HP"));
		js.executeScript("arguments[0].click();", getElement("customerHubSideMenuBtn_HP"));*/
	}finally {
		 getElement("sideMenuBtn_HP").click();
		 getElement("RolesAndTaskSideMenuBtn_HP").click();
		}
	//clickElement("addbtn_CH");
	}
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
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("document.querySelector(\"table#InvenLSCollater>thead>tr>th:nth-of-type(6)>a\").click()");
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
	public void clickEditonLineStructure() {
		clickElement("Linestructure_Edit_Button");
	}
	
	
}
