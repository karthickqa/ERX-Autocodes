package com.telliant.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import com.telliant.core.web.BaseClass;
public class LoginPage extends BaseClass {
	
	JavascriptExecutor executor = (JavascriptExecutor)driver;

	public void login(String username, String pwd) throws InterruptedException {
		try {
			clickElement("username");
			}catch(StaleElementReferenceException | NullPointerException e){
				try {
				executor.executeScript("arguments[0].click();", getElement("username"));
				}catch( NullPointerException e1){
					clickElement("logout");
					Thread.sleep(700);
					executor.executeScript("arguments[0].click();", getElement("username"));
				}
			}
			
			try {
				type("username", username);
				}catch(StaleElementReferenceException e){
					 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);				
					executor.executeScript("arguments[0].value='"+ username +"';", getElement("username"));
				}
			
			try {
				type("password", pwd);
				}catch(StaleElementReferenceException e){
					
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].value='"+ pwd +"';", getElement("password"));
				}
			
			clickElement("loginBtn");
		}


	public void logout() {
		try {
			waitForElementClickable("logoutDropdown");
			clickElement("logoutDropdown");
			waitForElementClickable("logoutButton");
			clickElement("logoutButton");
		}catch(ElementClickInterceptedException e) {
			waitForElementClickable("logoutDropdown");
			clickElement("logoutDropdown");
			waitForElementClickable("logoutButton");
			clickElement("logoutButton");
		}
		
	}
	
	public boolean verifyLogoIsDisplayed() {
		return verifyElementIsDisplayed("signInPage_Logo");
	}
	
	public boolean verifyInvalidCredentialsAlertMessageIsDispplayed() {
		return verifyElementIsDisplayed("signInPage_AlertMessage");
	}
	
	public void clickRemeberMeCheckBox() {
		clickElement("checkBox_RememberMe");
	}
	
	public String getTextUsername() {
		return getElement("username").getText();
	}
	
	public String getTextPassword() {
		return getElement("password").getText();
	}
	
	public void clickLoginButton() {
		clickElement("loginBtn");
	}
	
	public void clickErrorToast() {
		clickElement("toastError");
	}
	
	public void clickForgotPasswordLink() {
		clickElement("forgotPassword");
	}
	
	public boolean verifyForgotPasswrodPageIsDisplayed() {
		boolean flag= false;
		if(driver.getCurrentUrl().contains(config.getProperty("forgotPasswordUrl")))
			flag= true;
		return flag;
	}
	
	public void clickSendResetLink() {
		clickElement("resetLinkButton");
	}
	
	public boolean verifyPleaseEnterEmailAlertMessageIsDisplayed() {
		boolean flag= false;
		String alertMessage= getElement("pleaseEnterEmailAlertMessage").getText();
		if(alertMessage.equalsIgnoreCase(config.getProperty("pleaseEnterYourEmail")))
			flag= true;
		return flag;
	}
	
	public void enterUnRegisteredEmail() {
		getElement("emailTextBox").clear();
		type("emailTextBox", config.getProperty("unregisteredEmail"));
	}
	
	public void enterRegisteredEmail() {
		getElement("emailTextBox").clear();
		type("emailTextBox", config.getProperty("registeredEmail"));
	}
	
	public boolean verifyErrorMessageIsDisplayedForUnregisteredEmail() throws InterruptedException {
		waitForPageGetsLoad();
		boolean flag= false;
		String toastMessage= getElement("forgotPasswordResponseMessage").getText();
		if(toastMessage.equalsIgnoreCase(config.getProperty("unregisteredEmailAlertMessage"))) {
			flag= true;
		}
		return flag;
	}
	
	public boolean verifySuccessrMessageIsDisplayedForRegisteredEmail() throws InterruptedException {
		waitForPageGetsLoad();
		boolean flag= false;
		String toastMessage= getElement("forgotPasswordResponseMessage").getText();
		if(toastMessage.equalsIgnoreCase(config.getProperty("registeredEmailAlertMessage"))) {
			flag= true;
		}
		return flag;
	}
	
	public void clickMessageToastContainer() {
		clickElement("forgotPasswordResponseMessage");
	}
	
	public void clickBackToLogin() {
		clickElement("backToLoginLink");
	}
	
	public boolean verifyHomePageIsDisplayed() {
		boolean flag= false;
		if(driver.getCurrentUrl().contains(config.getProperty("loginPageUrl")))
			flag= true;
		return flag;
	}
}
