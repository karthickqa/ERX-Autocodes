package com.telliant.core.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class GeneralMethods extends WebDriverRoot {

	public String takeScreenshot() throws IOException {
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filepath = System.getProperty("user.dir");
		String localpath = "\\testdata\\screenshot.png";
		String jenkinspath = "/testdata/screenshot.png";
		if (filepath.contains("/data/jenkins/workspace")) {
		FileUtils.copyFile(scrFile, new File(filepath + jenkinspath));
			System.out.println("Jenkins Path: " + filepath + jenkinspath);
			return filepath + jenkinspath;
		} else {
			FileUtils.copyFile(scrFile, new File(filepath + localpath));
		}
		System.out.println((filepath + localpath));
		return filepath + localpath;
	}
	public static Properties loadProperty(String path) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(path));

		} catch (Exception e) {
			System.err.println("Properties file cannot be handled");
		}
		return properties;
	}
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	/**
	 * Function to return the current time
	 * 
	 * @return The current time
	 * @see #getCurrentFormattedTime(String)
	 */
	public static Date getCurrentTime() {
		final Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * Function to return the current time, formatted as per the DateFormatString
	 * setting
	 * 
	 * @param dateFormatString The date format string to be applied
	 * @return The current time, formatted as per the date format string specified
	 * @see #getCurrentTime()
	 * @see #getFormattedTime(Date, String)
	 */
	public static String getCurrentFormattedTime(String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}
	/**
	 * Function to format the given time variable as specified by the
	 * DateFormatString setting
	 * 
	 * @param time             The date/time variable to be formatted
	 * @param dateFormatString The date format string to be applied
	 * @return The specified date/time, formatted as per the date format string
	 *         specified
	 * @see #getCurrentFormattedTime(String)
	 */
	public static String getFormattedTime(Date time, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.format(time);
	}
	
	public static String getTimeStamp(){
		DateFormat DF=DateFormat.getDateTimeInstance();
		Date dte=new Date();
		String DateValue=DF.format(dte);
		DateValue=DateValue.replaceAll(":", "_");
		DateValue=DateValue.replaceAll(",", "");
		return DateValue;
		}
	
	
	
}
