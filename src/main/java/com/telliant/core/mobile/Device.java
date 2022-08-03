// 
// Decompiled by Procyon v0.5.36
// 

package com.telliant.core.mobile;

import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.DeviceRotation;
import java.io.IOException;
import io.appium.java_client.android.PowerACState;
import java.time.Duration;
import java.io.FileNotFoundException;
import java.io.File;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

public class Device extends MobileDriverRoot
{
    public static void startActivity(final String packageName, final String activityName) {
        ((AndroidDriver)Device.driver).startActivity(new Activity(packageName, activityName));
    }
    
    public static String getCurrentActivity() {
        final String currentActivity = ((AndroidDriver)Device.driver).currentActivity();
        return currentActivity;
    }
    
    public static String getCurrentPackage() {
        final String currentPackage = ((AndroidDriver)Device.driver).getCurrentPackage();
        return currentPackage;
    }
    
    public static void installApp(final String apkPath) throws FileNotFoundException {
        final File tmp = new File(apkPath);
        if (tmp.exists()) {
            Device.driver.installApp(apkPath);
        }
    }
    
    public static boolean isAppInstalled(final String packageName) {
        return Device.driver.isAppInstalled(packageName);
    }
    
    public static void launchApp() {
        Device.driver.launchApp();
    }
    
    public static void runAppInBackground(final int seconds) {
        Device.driver.runAppInBackground(Duration.ofSeconds(seconds));
    }
    
    public static void closeApp() {
        Device.driver.closeApp();
    }
    
    public static void resetApp() {
        Device.driver.resetApp();
    }
    
    public static void removeApp(final String packageName) {
        Device.driver.removeApp(packageName);
    }
    
    public static void activateApp(final String packageName) {
        Device.driver.activateApp(packageName);
    }
    
    public static void terminateApp(final String packageName) {
        Device.driver.terminateApp(packageName);
    }
    
    public static void getAppState(final String packageName) {
        Device.driver.queryAppState(packageName);
    }
    
    public static void setPowerState(final String powerState) {
        if (powerState.equalsIgnoreCase("OFF")) {
            ((AndroidDriver)Device.driver).setPowerAC(PowerACState.OFF);
        }
        else if (powerState.equalsIgnoreCase("ON")) {
            ((AndroidDriver)Device.driver).setPowerAC(PowerACState.ON);
        }
    }
    
    public static void setPowerCapacity(final int powerCapacity) {
        ((AndroidDriver)Device.driver).setPowerCapacity(powerCapacity);
    }
    
    public static void pushFile(final String sourcePath, final String destPath) {
        try {
            ((AndroidDriver)Device.driver).pushFile(sourcePath, new File(destPath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void lockDevice() {
        ((AndroidDriver)Device.driver).lockDevice();
    }
    
    public static void unlockDevice() {
        ((AndroidDriver)Device.driver).unlockDevice();
    }
    
    public static boolean isDeviceLocked() {
        final boolean isLocked = ((AndroidDriver)Device.driver).isDeviceLocked();
        return isLocked;
    }
    
    public static void rotateDevice(final int x, final int y, final int z) {
        ((AndroidDriver)Device.driver).rotate(new DeviceRotation(x, y, z));
    }
    
    public static void pressKey(final AndroidKey keyValue) {
        ((AndroidDriver)Device.driver).pressKey(new KeyEvent(keyValue));
    }
    
    public static void longPressKey(final AndroidKey keyValue) {
        ((AndroidDriver)Device.driver).longPressKey(new KeyEvent(keyValue));
    }
    
    public static void hideKeyboard() {
        ((AndroidDriver)Device.driver).hideKeyboard();
    }
    
    public static boolean isKeyboardShown() {
        final boolean isKeyboardShown = ((AndroidDriver)Device.driver).isKeyboardShown();
        return isKeyboardShown;
    }
    
    public static void toggleAirplaneMode() {
        ((AndroidDriver)Device.driver).toggleAirplaneMode();
    }
    
    public static void toggleData() {
        ((AndroidDriver)Device.driver).toggleData();
    }
    
    public static void toggleLocation() {
        ((AndroidDriver)Device.driver).toggleLocationServices();
    }
    
    public static void toggleWifi() {
        ((AndroidDriver)Device.driver).toggleWifi();
    }
    
    public static void screenRecording() {
        ((AndroidDriver)Device.driver).startRecordingScreen();
    }
}
