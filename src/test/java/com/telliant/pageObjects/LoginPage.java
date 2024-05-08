package com.telliant.pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.telliant.core.web.BaseClass;
public class LoginPage extends BaseClass {
	
	JavascriptExecutor executor = (JavascriptExecutor)driver;

	public void login(String username, String pwd) throws InterruptedException {
		driver.navigate().refresh();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);	
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
			scrollToElement("logoutDropdown");
			waitForElementVisible("logoutDropdown");
			waitForElementClickable("logoutDropdown");
			clickElement("logoutDropdown");
			waitForElementClickable("logoutButton");
			clickElement("logoutButton");
		}catch(ElementClickInterceptedException e) {
			scrollToElement("logoutDropdown");
			waitForElementVisible("logoutDropdown");
			waitForElementClickable("logoutDropdown");
			WebElement logoutdropdown = getElement("logoutDropdown");
            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", logoutdropdown);
			//clickElement("logoutDropdown");
			waitForElementClickable("logoutButton");
			WebElement logoutbutton = getElement("logoutButton");
            JavascriptExecutor js1 = (JavascriptExecutor)driver;
            js1.executeScript("arguments[0].click();", logoutbutton);
			//clickElement("logoutButton");
		}
		
	}
	
}
	
	