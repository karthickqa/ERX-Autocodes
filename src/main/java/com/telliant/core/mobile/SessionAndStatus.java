// 
// Decompiled by Procyon v0.5.36
// 

package com.telliant.core.mobile;

import java.util.Set;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.ScreenOrientation;
import java.util.Map;

public class SessionAndStatus extends MobileDriverRoot
{
    public static String getSessionID() {
        final String sessionID = SessionAndStatus.driver.getSessionId().toString();
        return sessionID;
    }
    
    public static Map<String, Object> getSessionDetails() {
        final Map<String, Object> sessionDetails = (Map<String, Object>)SessionAndStatus.driver.getSessionDetails();
        return sessionDetails;
    }
    
    public static void closeSession() {
        SessionAndStatus.driver.quit();
    }
    
    public static ScreenOrientation getOrientation() {
        final ScreenOrientation orientation = SessionAndStatus.driver.getOrientation();
        return orientation;
    }
    
    public static void setOrientation(final String orientationVal) {
        switch (orientationVal) {
            case "portrait": {
                SessionAndStatus.driver.rotate(ScreenOrientation.PORTRAIT);
                break;
            }
            case "landscape": {
                SessionAndStatus.driver.rotate(ScreenOrientation.LANDSCAPE);
                break;
            }
            default:
                break;
        }
    }
    
    public static Location getLocation() {
        final Location location = SessionAndStatus.driver.location();
        return location;
    }
    
    public static void setLocation() {
        SessionAndStatus.driver.setLocation(new Location(49.0, 123.0, 10.0));
    }
    
    public static String getContext() {
        final String context = SessionAndStatus.driver.getContext();
        return context;
    }
    
    public static String getAllContext() {
        final Set<String> contextNames = (Set<String>)SessionAndStatus.driver.getContextHandles();
        return contextNames.toString();
    }
}
