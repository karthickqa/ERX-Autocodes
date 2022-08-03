// 
// Decompiled by Procyon v0.5.36
// 

package com.telliant.core.mobile;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.ExtentReports;

public class ReportManager extends MobileDriverRoot
{
    private static ExtentReports extent;
    public static String screenshotPath;
    public static String screenshotName;
    
    public static ExtentReports createInstance(final String fileName) {
        final ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Telliant Framework Execution | Result");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Project: Mobile Automation Demo");
        (ReportManager.extent = new ExtentReports()).attachReporter(new ExtentReporter[] { (ExtentReporter)htmlReporter });
        ReportManager.extent.setSystemInfo("Organization", "Telliant QA Framework");
        ReportManager.extent.setSystemInfo("Build No.", "v2.0");
        ReportManager.extent.setSystemInfo("Operating System", System.getProperty("os.name"));
        ReportManager.extent.setSystemInfo("Java Version", System.getProperty("java.runtime.version"));
        return ReportManager.extent;
    }
}
