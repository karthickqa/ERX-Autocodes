package com.telliant.mobile.PageObjects;

import com.telliant.core.web.WebDriverRoot;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MainPage extends WebDriverRoot{
	
	public void accceptAgreement() {
	
		clickElement(ObjRepo.getProperty("main_screen_activity"));
	}

}
