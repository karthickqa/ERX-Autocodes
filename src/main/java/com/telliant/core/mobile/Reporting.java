
package com.telliant.core.mobile;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting  implements ITestListener {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onTestStart(final ITestResult result) {
		String timeStamp = new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//reports//" + repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Telliant User");

		htmlReporter.config().setDocumentTitle("Telliant Framework Execution | Result");
		htmlReporter.config().setReportName("Functional Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(final ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
		System.out.println(String.valueOf(result.getMethod().getMethodName()) + " passed");
	}

	public void onTestFailure(final ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		System.out.println(String.valueOf(result.getMethod().getMethodName()) + " failed");
	}

	public void onTestSkipped(final ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
		System.out.println(String.valueOf(result.getMethod().getMethodName()) + " skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
	}

	public void onStart(final ITestContext context) {
	}

	public void onFinish(final ITestContext context) {
		extent.flush();
	}
}
