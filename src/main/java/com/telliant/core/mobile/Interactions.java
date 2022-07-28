// 
// Decompiled by Procyon v0.5.36
// 

package com.telliant.core.mobile;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.touch.TouchActions;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.Reporter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import io.appium.java_client.MobileElement;

public class Interactions extends MobileDriverRoot
{
    public static MobileElement getElement(final String locator) {
        final String repolocator = Interactions.ObjRepo.getProperty(locator).trim();
        final String[] tokens = repolocator.split(";");
        final String locatorType = tokens[tokens.length - 2];
        final String strlocator = tokens[tokens.length - 1];
        MobileElement mobElement = null;
        try {
            if (locatorType.equalsIgnoreCase("XPATH")) {
                mobElement = (MobileElement)Interactions.driver.findElement(By.xpath(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("ID")) {
                mobElement = (MobileElement)Interactions.driver.findElement(By.id(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("CLASS")) {
                mobElement = (MobileElement)Interactions.driver.findElement(By.className(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("NAME")) {
                mobElement = (MobileElement)Interactions.driver.findElement(By.name(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("CSS")) {
                mobElement = (MobileElement)Interactions.driver.findElement(By.cssSelector(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("LINKTEXT")) {
                mobElement = (MobileElement)Interactions.driver.findElement(By.linkText(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("accessibilityid")) {
                mobElement = (MobileElement)Interactions.driver.findElementByAccessibilityId(strlocator);
            }
            else if (locatorType.equalsIgnoreCase("TagName")) {
                mobElement = (MobileElement)Interactions.driver.findElementByTagName(strlocator);
            }
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println(String.valueOf(strlocator) + " Element not found");
        }
        return mobElement;
    }
    
    public static boolean clickElement(final String locator) {
        try {
            final boolean elementIsClickable = getElement(locator).isEnabled();
            if (elementIsClickable) {
                getElement(locator).click();
                return true;
            }
        }
        catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Element is not enabled");
        }
        return false;
    }
    
    public static void type(final String locator, final String textToType) {
        final MobileElement ele2 = getElement(locator);
        try {
            if (ele2.isEnabled()) {
                ele2.clear();
                ele2.sendKeys(new CharSequence[] { textToType });
                Reporter.log("Entered Text: " + textToType);
            }
        }
        catch (ElementNotVisibleException e) {
            e.printStackTrace();
        }
    }
    
    public static void waitInSeconds(final int seconds) {
        Interactions.driver.manage().timeouts().implicitlyWait((long)seconds, TimeUnit.SECONDS);
    }
    
    public static String getText(final String locator) {
        return getElement(locator).getText();
    }
    
    public static void singleTap(final String locator) {
        final MobileElement element = getElement(locator);
        final TouchActions action = new TouchActions((WebDriver)Interactions.driver);
        action.singleTap((WebElement)element);
        action.perform();
    }
    
    public static void doubleTap(final String locator) {
        final MobileElement element = getElement(locator);
        final TouchActions action = new TouchActions((WebDriver)Interactions.driver);
        action.doubleTap((WebElement)element);
        action.perform();
    }
    
    public static void moveTo(final int a, final int b, final int c, final int d) {
        final TouchActions action = new TouchActions((WebDriver)Interactions.driver);
        action.down(a, b);
        action.move(c, d);
        action.perform();
    }
    
    public static void longPress(final String locator) {
        final TouchActions action = new TouchActions((WebDriver)Interactions.driver);
        action.longPress((WebElement)getElement(locator));
        action.perform();
    }
    
    public static void scrollToElement(final String locator) {
        final TouchActions action = new TouchActions((WebDriver)Interactions.driver);
        action.scroll((WebElement)getElement(locator), 10, 100);
        action.perform();
    }
    
    public static boolean isElementPresent(final String locator) {
        Interactions.element = getElement(locator);
        final Boolean checkIfElementPresent = Interactions.element.isDisplayed();
        return checkIfElementPresent;
    }
}
