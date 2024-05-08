package com.telliant.tests;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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

public class HomePage extends BaseClass {
	 public static final String User_Add_New_FI_Notes = null;
	private static final String Add_User_Save_Button = null;
	private static final String Edit_button_bu = null;
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
	public void Lab_Login() throws InterruptedException, AWTException {
		clickElement("Email_field");
		type("Email_field",config.getProperty("Email"));
		clickElement("Next_button");
		clickElement("Password_field");
		type("Password_field",config.getProperty("Password"));
		clickElement("Login_button");
		
	}
	
	public void Practice_Creation() throws InterruptedException, AWTException {
	clickElement("Practice_menu");
	waitForPageGetsLoad2();
	clickElement("Create_Practice_button");
	waitForPageGetsLoad2();
	scrollToElement("Practice_Name_field");
	clickElement("Practice_Name_field");
	type("Practice_Name_field",config.getProperty("Practice_name"));
	clickElement("Email_enter_field");
	config.setProperty("Email_Address",randStrGen("LN",8)+"@gmail.com");
	type("Email_enter_field",config.getProperty("Email_Address"));
	//type("Email_enter_field",randStrGen("LN",8)+"@gmail.com");
	waitForPageGetsLoad2();
	clickElement("Doctor_Name");
	type("Doctor_Name",config.getProperty("Doctor_first_name"));
	clickElement("Doctor_Last_Name");
	type("Doctor_Last_Name",config.getProperty("Doctor_last_name"));
	clickElement("License_Number");
	type("License_Number",config.getProperty("License_number"));
	
	clickElement("Best_contact_first_name");
	type("Best_contact_first_name",config.getProperty("Bestcontact_first_name"));
	clickElement("Best_contact_last_name");
	type("Best_contact_last_name",config.getProperty("Bestcontact_last_name"));
	clickElement("Best_contact_email_address");
	type("Best_contact_email_address",config.getProperty("Bestcontact_email_address"));
	waitForPageGetsLoad2();
	
	clickElement("Office_phone_number");
	type("Office_phone_number",config.getProperty("Offic_number"));
	clickElement("Fax_number");
	type("Fax_number",config.getProperty("Faxnumber"));
	clickElement("Address_line1");
	type("Address_line1",config.getProperty("Address_line1"));
	clickElement("Address_line2");
	type("Address_line2",config.getProperty("Address_line2"));
	scrollToElement("Country_dropdown");
	clickElement("Country_dropdown");
	clickElement("country_value_selection");
	clickElement("State_Province_dropdown");
	clickElement("State_Province_value");
	waitForPageGetsLoad2();
	clickElement("City1");
	type("City1",config.getProperty("City_value"));
	clickElement("Zip");
	type("Zip",config.getProperty("Zip_value"));
	clickElement("Create_button");
    //clickElement("CreateandInvite_button");
	}

	
	public void Practice_Editing() throws InterruptedException, AWTException {
		scrollToElement("Practice_menu");
		clickElement("Practice_menu");
		waitForPageGetsLoad2();
		clickElement("Practice_view_text_link");
		clickElement("Edit_Name_Email_Doctors_link");
		waitForPageGetsLoad2();
		clickElement("Edit_PracticeName");
		type("Edit_PracticeName",config.getProperty("practice_name_edit"));
		clickElement("Edit_Emailaddress");
		type("Edit_Emailaddress",config.getProperty("Email_edit"));
		clickElement("Note_comment_enter_field");
		type("Note_comment_enter_field",config.getProperty("Note_or_comment"));
		clickElement("Message_invoice");
		type("Message_invoice",config.getProperty("type_message"));
		clickElement("Inactive_Icon");
		clickElement("Active_Icon");
		clickElement("Edit_contact_first_name");
		type("Edit_contact_first_name",config.getProperty("type_frist_name"));
		clickElement("Edit_contact_last_name");
		type("Edit_contact_last_name",config.getProperty("type_last_name"));
		clickElement("Edit_contact_Email");
		type("Edit_contact_Email",config.getProperty("type_email"));
		scrollToElement("Edit_Doctor_first_name");
		clickElement("Edit_Doctor_first_name");
		type("Edit_Doctor_first_name",config.getProperty("type_Dr_first_name"));
		clickElement("Edit_Doctor_Last_name");
		type("Edit_Doctor_Last_name",config.getProperty("type_Dr_last_name"));
		clickElement("Edit_license_number");
		type("Edit_license_number",config.getProperty("type_license_name"));
		clickElement("Edit_Phone_number");
		type("Edit_Phone_number",config.getProperty("type_Ph_number"));
		clickElement("Edit_Email_Address");
		config.setProperty("Email_Address",randStrGen("LN",8)+"@gmail.com");
		type("Edit_Email_Address",config.getProperty("Email_Address"));
		clickElement("Lab_Doctor_id");
		type("Lab_Doctor_id",config.getProperty("type_Dr_ID"));
		clickElement("Save_button");
}
	
	public void create_empty_RX() throws InterruptedException, AWTException {
		clickElement("Email_field");
		type("Email_field",config.getProperty("Email2"));
		clickElement("Next_button");
		waitForPageGetsLoad2();
		clickElement("Password_field");
		type("Password_field",config.getProperty("Password"));
		clickElement("Login_button");
		waitForPageGetsLoad2();
		clickElement("Ask_me_later");
		waitForPageGetsLoad2();
		clickElement("Create_RX_button");
		waitForPageGetsLoad2();
		clickElement("Prescription_create_button");
	
	}
	public boolean isPaitentValidationDisplayed() throws InterruptedException {
	    try {
	        WebElement PatientvalidatePopup = getElement("Warnig_popup_dialog");
	        waitForPageGetsLoad2();
	        if (PatientvalidatePopup.isDisplayed()) {
	            System.out.println("Patient Validate alert popup is displayed");
	            WebElement modelSourceOKButton = driver.findElement(By.xpath("//span[@class=\"fa fa-check\"]"));
	            modelSourceOKButton.click();
	            return true;
	        } else {
	            System.out.println("Patient Validate alert popup not displayed");
	            return false;
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("Patient Validate alert popup not found");
	        return false;
	    }
	}
	    public void patient_details_entry() throws InterruptedException, AWTException {
		waitForPageGetsLoad2();
		clickElement("Patient_firstname_field");
		waitForPageGetsLoad2();
        type("Patient_firstname_field",config.getProperty("Patient_firstname"));
		clickElement("Patient_lastname_field");
		type("Patient_lastname_field",config.getProperty("Patient_lastname"));
		clickElement("App't_date_field");
		waitForPageGetsLoad2();
		type("App't_date_field",config.getProperty("Appn't_date"));
		WebElement datetime = getElement("App't_time_field");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", datetime);
		type("App't_time_field",config.getProperty("Appn't_time"));
		WebElement codefield = getElement("App't_code_field");
        JavascriptExecutor js2 = (JavascriptExecutor)driver;
        js2.executeScript("arguments[0].click();", codefield);
        waitForPageGetsLoad2();
		type("App't_code_field",config.getProperty("Code"));
		//clickElement("Dateneeded_field");
		//type("Dateneeded_field",config.getProperty("Date_needed"));
		waitForPageGetsLoad2();
		clickElement("Month_dropdown");
		clickElement("may_month");
		clickElement("Days_dropdown");
		clickElement("Day_value");
		waitForPageGetsLoad2();
		clickElement("Year_dropdown");
		clickElement("year_value");
		clickElement("Patient_ID");
		type("Patient_ID",config.getProperty("Patient_ID"));
		clickElement("Gender_dropdown");
		clickElement("Male");
		waitForPageGetsLoad2();
		clickElement("Doctor_dropdown");
		clickElement("doctor_value");
		clickElement("Office_dropdown");
		clickElement("Office_value");
		clickElement("Shipto_dropdown");
		clickElement("shipto_value");
		clickElement("scan_imp_field");
		clickElement("scan_imp_value");
		clickElement("submittedby_field");
		clickElement("submittedby_Value");
		clickElement("Billto_field");
		clickElement("Billto_value");
		clickElement("Prescription_create_button");
	}	
		public void Sign_submit_with_empty_prescription() throws InterruptedException, AWTException {
			scrollToElement("Sign_Submit_button");
			waitForPageGetsLoad2();
			WebElement signsubmitbutton = getElement("Sign_Submit_button");
	        JavascriptExecutor js = (JavascriptExecutor)driver;
	        js.executeScript("arguments[0].click();", signsubmitbutton);
	        waitForPageGetsLoad2();
		}
		
		public boolean isDateneedPopupDisplayed() throws InterruptedException {
		    try {
		        WebElement DateneedPopup = getElement("Date_needed_popup");
		        waitForPageGetsLoad2();
		        if (DateneedPopup.isDisplayed()) {
		            System.out.println("Date needed alert popup is displayed");
		            WebElement modelSourceOKButton = driver.findElement(By.xpath("//a[contains(text(),'Ok')]"));
		            modelSourceOKButton.click();
		            return true;
		        } else {
		            System.out.println("Date needed alert popup not displayed");
		            return false;
		        }
		    } catch (NoSuchElementException e) {
		        System.out.println("Date needed alert popup not found");
		        return false;
		    }
		}
		
	 	public boolean isBlankprescriptionPopupDisplayed() throws InterruptedException {
			    try {
			    	WebElement signsubmitbutton = getElement("Sign_Submit_button");
			    	waitForPageGetsLoad2();
			        JavascriptExecutor js = (JavascriptExecutor)driver;
			        js.executeScript("arguments[0].click();", signsubmitbutton);
			        WebElement blankprescrition = getElement("Blank_prescription_popup");
			        if (blankprescrition.isDisplayed()) {
			            System.out.println("Blank Prescription alert popup is displayed");
			            WebElement modelSourceOKButton = driver.findElement(By.xpath("//a[contains(text(),'Ok')]"));
			            modelSourceOKButton.click();
			            return true;
			        } else {
			            System.out.println("Blank Prescription alert popup not displayed");
			            return false;
			        }
			    } catch (NoSuchElementException e) {
			        System.out.println("Blank Prescription alert popup not found");
			        return false;
			    }
			}
	 	
	 	  public boolean isModelSourcePopupDisplayed() throws InterruptedException {
			    try {
			        WebElement modelSourcePopup = getElement("Model_source_popup");
			        waitForPageGetsLoad2();
			        if (modelSourcePopup.isDisplayed()) {
			            System.out.println("Model source alert popup displayed");
			            WebElement modelSourceOKButton = driver.findElement(By.xpath("//a[contains(text(),'Ok')]"));
			            modelSourceOKButton.click();
			            return true;
			        } else {
			            System.out.println("Model source alert popup not is displayed");
			            return false;
			        }
			    } catch (NoSuchElementException e) {
			        System.out.println("Model source alert popup not found");
			        return false;
			    }
			}
	                public void Add_Parts_Comments_and_Modelsource() throws InterruptedException, AWTException {
					waitForPageGetsLoad2();
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyRelease(KeyEvent.VK_PAGE_UP);
					waitForPageGetsLoad2();
				    WebElement partbutton = getElement("Left_Part_textlink");
			        JavascriptExecutor js = (JavascriptExecutor)driver;
			        js.executeScript("arguments[0].click();", partbutton);
			        scrollToElement("Denture_Part");
					WebElement denturepart = getElement("Denture_Part");
			        JavascriptExecutor js2 = (JavascriptExecutor)driver;
			        js2.executeScript("arguments[0].click();", denturepart);
			        waitForPageGetsLoad2();
			        robot.keyPress(KeyEvent.VK_PAGE_UP);
			        robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyRelease(KeyEvent.VK_PAGE_UP);
				    scrollToElement("Overdenture_part_selection");
				    clickElement("Overdenture_part_selection");
			        		       
					//Comment adding
			        waitForPageGetsLoad2();
			        scrollToElement("Comment_Enter_field");
			        WebElement commentfiled = getElement("Comment_Enter_field");
			        JavascriptExecutor js4 = (JavascriptExecutor)driver;
			        js4.executeScript("arguments[0].click();", commentfiled);
			        type("Comment_Enter_field",config.getProperty("Comment_text"));
			        robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyRelease(KeyEvent.VK_PAGE_UP);
			        scrollToElement("Save_commentbutton");
			        WebElement savebutton = getElement("Save_commentbutton");
			        JavascriptExecutor js5 = (JavascriptExecutor)driver;
			        js5.executeScript("arguments[0].click();", savebutton);
			        robot.keyPress(KeyEvent.VK_PAGE_UP);
			        robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyRelease(KeyEvent.VK_PAGE_UP);
			        scrollToElement("Use_Digital_Model_Attached_Script_Radioicon");
			        WebElement modelsource = getElement("Use_Digital_Model_Attached_Script_Radioicon");
			        JavascriptExecutor js6 = (JavascriptExecutor)driver;
			        js6.executeScript("arguments[0].click();", modelsource);
			   
			        
					//Actions actions = new actions(driver);
					//actions.clickAndHold('Overdenture_part_selection').moveToElement().release(element).build.perform();
					
                    }
	                public void Fileupload() throws InterruptedException, AWTException {
	                Robot robot = new Robot();
	               	robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyPress(KeyEvent.VK_PAGE_UP);
					robot.keyRelease(KeyEvent.VK_PAGE_UP);
	               	scrollToElement("files_from_your_computer");
	            	WebElement fileupload = getElement("files_from_your_computer");
			        JavascriptExecutor js1 = (JavascriptExecutor)driver;
	 			    js1.executeScript("arguments[0].click();", fileupload);
	                waitForPageGetsLoad2();
					String file= "D:\\AutoIt\\A_Doubleday_REGULAR.stl";
					StringSelection selection = new StringSelection(file);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);  
					
					Robot robot1 = new Robot();
					robot1.keyPress(KeyEvent.VK_CONTROL);
					robot1.keyPress(KeyEvent.VK_V);
					robot1.keyRelease(KeyEvent.VK_V);
					robot1.keyRelease(KeyEvent.VK_CONTROL);
					robot1.keyPress(KeyEvent.VK_ENTER);
					robot1.keyRelease(KeyEvent.VK_ENTER);
					waitForPageGetsLoad3();
					//clickElement("Sign_Submit_button");
}   
}
