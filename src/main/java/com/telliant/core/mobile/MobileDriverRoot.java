
package com.telliant.core.mobile;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.touch.TouchActions;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.Reporter;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import java.nio.file.Path;
import java.nio.file.LinkOption;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import org.apache.commons.codec.binary.Base64;
import io.appium.java_client.screenrecording.BaseStartScreenRecordingOptions;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import java.util.function.Function;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.openqa.selenium.OutputType;
import java.io.File;
import org.openqa.selenium.WebElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import org.openqa.selenium.Capabilities;
import io.appium.java_client.ios.IOSDriver;
import com.telliant.core.web.GeneralMethods;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Properties;
import io.appium.java_client.MobileElement;
import io.appium.java_client.AppiumDriver;

public class MobileDriverRoot
{
    protected static AppiumDriver<?> driver;
    public static MobileElement element;
    public static Properties config;
    public static Properties mobile;
    protected static DesiredCapabilities capabilities;
    public static Properties ObjRepo;
    public static String screenshotName;
    public static URL videoURL;
    public static String x;
    
    static {
        MobileDriverRoot.capabilities = new DesiredCapabilities();
        MobileDriverRoot.ObjRepo = GeneralMethods.loadProperty("./objectRepo/webObject.properties");
    }
    
    public static void launchDriver() {
        setCapabilities();
        if (MobileDriverRoot.mobile.getProperty("platformName").equalsIgnoreCase("ios")) {
            try {
                MobileDriverRoot.driver = (AppiumDriver<?>)new IOSDriver(new URL(MobileDriverRoot.mobile.getProperty("appiumURL")), (Capabilities)MobileDriverRoot.capabilities);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if (MobileDriverRoot.mobile.getProperty("platformName").equalsIgnoreCase("android")) {
            try {
                final AndroidDriver<WebElement> driver2 = (AndroidDriver<WebElement>)MobileDriverRoot.driver;
                MobileDriverRoot.driver = (AppiumDriver<?>)new AndroidDriver(new URL(MobileDriverRoot.mobile.getProperty("appiumURL")), (Capabilities)MobileDriverRoot.capabilities);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void setCapabilities() {
        MobileDriverRoot.mobile = GeneralMethods.loadProperty("./objectRepo/webObject.properties");
        MobileDriverRoot.capabilities.setCapability("version", MobileDriverRoot.mobile.getProperty("androidVersion"));
        MobileDriverRoot.capabilities.setCapability("deviceName", MobileDriverRoot.mobile.getProperty("deviceName"));
        MobileDriverRoot.capabilities.setCapability("app", MobileDriverRoot.mobile.getProperty("appPath"));
        MobileDriverRoot.capabilities.setCapability("platformName", MobileDriverRoot.mobile.getProperty("platformName"));
        MobileDriverRoot.capabilities.setCapability("skipDeviceInitialization", MobileDriverRoot.mobile.getProperty("skipDeviceInitialization"));
        MobileDriverRoot.capabilities.setCapability("ignoreUnimportantViews ", MobileDriverRoot.mobile.getProperty("ignoreUnimportantViews"));
        MobileDriverRoot.capabilities.setCapability("skipServerInstallation", MobileDriverRoot.mobile.getProperty("skipServerInstallation"));
        MobileDriverRoot.capabilities.setCapability("automationName", MobileDriverRoot.mobile.getProperty("automationName"));
        MobileDriverRoot.capabilities.setCapability("appPackage", MobileDriverRoot.mobile.getProperty("appPackage"));
        MobileDriverRoot.capabilities.setCapability("appActivity", MobileDriverRoot.mobile.getProperty("appActivity"));
        MobileDriverRoot.capabilities.setCapability("fullReset", MobileDriverRoot.mobile.getProperty("fullReset"));
        MobileDriverRoot.capabilities.setCapability("noReset", MobileDriverRoot.mobile.getProperty("noReset"));
    }
    
    public static void captureScreenshot(final String name) {
        final File scrFile = (File)((TakesScreenshot)MobileDriverRoot.driver).getScreenshotAs(OutputType.FILE);
        final String date = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        MobileDriverRoot.screenshotName = "capture_" + date.toString().replace(":", "_").replace(" ", "_") + ".png";
        try {
            FileUtils.copyFile(scrFile, new File(".//reports/" + MobileDriverRoot.screenshotName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean waitForElement(final String locator) {
        try {
            final WebDriverWait wait = new WebDriverWait((WebDriver)MobileDriverRoot.driver, 15L);
            wait.until((Function)ExpectedConditions.visibilityOf((WebElement)getElement(locator)));
            final boolean isElementPresent = getElement(locator).isDisplayed();
            return isElementPresent;
        }
        catch (Exception e) {
            final boolean isElementPresent = false;
            System.out.println(e.getMessage());
            return isElementPresent;
        }
    }
    
    public static void startRecording() {
        ((AndroidDriver)MobileDriverRoot.driver).startRecordingScreen();
        ((AndroidDriver)MobileDriverRoot.driver).startRecordingScreen((BaseStartScreenRecordingOptions)new AndroidStartScreenRecordingOptions());
    }
    
    public static void stopRecording() throws IOException {
        final String date = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        final String recordedVideo = ((AndroidDriver)MobileDriverRoot.driver).stopRecordingScreen();
        final byte[] data = Base64.decodeBase64(recordedVideo);
        final String videoName = "video_" + date.toString().replace(":", "_").replace(" ", "_") + ".mp4";
        final String destPath = "D:\\workspace\\Silver\\reports\\" + videoName;
        final Path path = Paths.get(destPath, new String[0]);
        MobileDriverRoot.x = "<html> <a href=" + destPath + "></a> </html>";
        Files.write(path, data, new OpenOption[0]);
        MobileDriverRoot.videoURL = Paths.get(destPath, new String[0]).toRealPath(new LinkOption[0]).toUri().toURL();
    }
    
    public static MobileElement getElement(final String locator) {
        final String repolocator = MobileDriverRoot.ObjRepo.getProperty(locator).trim();
        final String[] tokens = repolocator.split(";");
        final String locatorType = tokens[tokens.length - 2];
        final String strlocator = tokens[tokens.length - 1];
        MobileElement mobElement = null;
        try {
            if (locatorType.equalsIgnoreCase("XPATH")) {
                mobElement = (MobileElement)MobileDriverRoot.driver.findElement(By.xpath(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("ID")) {
                mobElement = (MobileElement)MobileDriverRoot.driver.findElement(By.id(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("CLASS")) {
                mobElement = (MobileElement)MobileDriverRoot.driver.findElement(By.className(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("NAME")) {
                mobElement = (MobileElement)MobileDriverRoot.driver.findElement(By.name(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("CSS")) {
                mobElement = (MobileElement)MobileDriverRoot.driver.findElement(By.cssSelector(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("LINKTEXT")) {
                mobElement = (MobileElement)MobileDriverRoot.driver.findElement(By.linkText(strlocator));
            }
            else if (locatorType.equalsIgnoreCase("accessibilityid")) {
                mobElement = (MobileElement)MobileDriverRoot.driver.findElementByAccessibilityId(strlocator);
            }
            else if (locatorType.equalsIgnoreCase("TagName")) {
                mobElement = (MobileElement)MobileDriverRoot.driver.findElementByTagName(strlocator);
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
        MobileDriverRoot.driver.manage().timeouts().implicitlyWait((long)seconds, TimeUnit.SECONDS);
    }
    
    public static String getText(final String locator) {
        return getElement(locator).getText();
    }
    
    public static void singleTap(final String locator) {
        final MobileElement element = getElement(locator);
        final TouchActions action = new TouchActions((WebDriver)MobileDriverRoot.driver);
        action.singleTap((WebElement)element);
        action.perform();
    }
    
    public static void doubleTap(final String locator) {
        final MobileElement element = getElement(locator);
        final TouchActions action = new TouchActions((WebDriver)MobileDriverRoot.driver);
        action.doubleTap((WebElement)element);
        action.perform();
    }
    
    public static void moveTo(final int a, final int b, final int c, final int d) {
        final TouchActions action = new TouchActions((WebDriver)MobileDriverRoot.driver);
        action.down(a, b);
        action.move(c, d);
        action.perform();
    }
    
    public static void longPress(final String locator) {
        final TouchActions action = new TouchActions((WebDriver)MobileDriverRoot.driver);
        action.longPress((WebElement)getElement(locator));
        action.perform();
    }
    
    public static void scrollToElement(final String locator) {
        final TouchActions action = new TouchActions((WebDriver)MobileDriverRoot.driver);
        action.scroll((WebElement)getElement(locator), 10, 100);
        action.perform();
    }
    
    public static boolean isElementPresent(final String locator) {
        MobileDriverRoot.element = getElement(locator);
        final Boolean checkIfElementPresent = MobileDriverRoot.element.isDisplayed();
        return checkIfElementPresent;
    }
}
