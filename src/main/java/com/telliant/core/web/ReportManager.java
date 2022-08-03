package com.telliant.core.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.telliant.core.web.WebDriverRoot;


public class ReportManager extends WebDriverRoot {

	private static ExtentReports extent;

	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Execution | Result");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Project: Lendovative");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Organization", "Telliant QA");
		//extent.setSystemInfo("Build No.", "v2.0");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Java Version",System.getProperty("java.runtime.version" ));
		return extent;
	}

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot(String name) {

		File scrFile = ((TakesScreenshot) WebDriverRoot.driver).getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		screenshotName = date.toString().replace(":", "_").replace(" ", "_") + " " + name + ".png";

		try {
			FileUtils.copyFile(scrFile, new File("./" + "/reports/" + screenshotName));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
