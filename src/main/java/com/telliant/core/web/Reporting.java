package com.telliant.core.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.telliant.core.web.ReportManager;

public class Reporting implements ITestListener  {

	//static Date date = new Date();
	//static String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	static String date = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
	static String fileName = "Report_"+ date+".html";

	private static ExtentReports extent = ReportManager.createInstance("./" + "/reports/" +fileName);

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {

		ExtentTest test = extent.createTest("Test Method: " + result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		//String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		String logText = "TEST CASE:- " + methodName.toUpperCase() + " PASSED";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(markup);
		System.out.println(methodName + " passed");
		
	}

	public void onTestFailure(ITestResult result) {
		String exception = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + exception.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		try {

			ReportManager.captureScreenshot(result.getName());
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ReportManager.screenshotName).build());
		} catch (IOException e) {

		}

		String failureLogg = "TEST CASE FAILED";
		Markup markup = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, markup);
		System.out.println(result.getMethod().getMethodName() + " failed");
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup markup = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(markup);
		System.out.println(methodName + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		
		if (extent != null) {
			
			testReport.get().info(MarkupHelper.createLabel("Package Path: "+ BaseClass.packageName, ExtentColor.BLUE));
			testReport.get().info(MarkupHelper.createLabel("Current class Name: " + getClass().getSimpleName(), ExtentColor.BLUE));
			//testReport.get().info("Browser Quit: Success");
			extent.flush();
		}

	}

}


	
