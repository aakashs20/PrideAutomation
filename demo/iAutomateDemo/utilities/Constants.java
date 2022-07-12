package com.project.utilities;

import java.util.Properties;

import com.project.testbase.TestBase;

public class Constants extends TestBase {

	Properties prop = new Properties();

	final public static String RESULT_TAB = "Execution_Result";
	public static String Execution_REPORT_PATH, SCREENSHOT_PATH_FAIL, SCREENSHOT_PATH_PASS, Execution_REPORT_PATH_API,
			Execution_REPORT_PATH_RULESAPI_CSV;

	// ***********************All generic Path set
	// up*************************************************************

	final public static String CONFIG_FILE = System.getProperty("user.dir")
			+ "//src//test//resources//config.properties";
//	inal public static String CONFIG_FILE = System.getProperty("user.dir")
//			+ SetPathForIOS("\\src\\test\\resources\\config.properties");
	final public static String LOG_CONFIG_FILE = System.getProperty("user.dir")
			+ SetPathForIOS("\\src\\test\\resources\\log4j.properties");

	/************************************** Browser Drivers **************************************************/ 
	final public static String CHROME_DRIVER_PATH = System.getProperty("user.dir")
			+ "//src//test//resources//chromedriver//chromedriver.exe";
	final public static String GECKO_DRIVER_PATH = System.getProperty("user.dir")
			+ SetPathForIOS("\\src\\test\\resources\\geckodriver\\geckodriver.exe");
	final public static String IE_DRIVER_PATH = System.getProperty("user.dir")
			+ SetPathForIOS("\\src\\test\\resources\\IEdriver\\IEDriverServer.exe");
	final public static String iOSChrome_DRIVER_PATH = System.getProperty("user.dir")
			+ SetPathForIOS("\\src\\test\\resources\\chromedriver\\chromedriver");
	
	
	
//	/************************************** Safety Chain JAR **************************************************/  
//	final public static String CONFIG_FILE = System.getProperty("user.dir")
//			+ "\\resources\\config.properties";
//	final public static String LOG_CONFIG_FILE = System.getProperty("user.dir")
//			+ "\\resources\\log4j.properties";
//	final public static String CHROME_DRIVER_PATH = System.getProperty("user.dir")
//			+ "\\resources\\chromedriver\\chromedriver.exe";
//	final public static String GECKO_DRIVER_PATH = System.getProperty("user.dir") 
//			+ "\\resources\\geckodriver\\geckodriver.exe";
//	final public static String IE_DRIVER_PATH = System.getProperty("user.dir") 
//			+ "\\resources\\IEdriver\\IEDriverServer.exe";

	final public static String emailpath = System.getProperty("user.dir")
			+ SetPathForIOS("\\test-output\\emailable-report.html");
	final public static String projectpath = System.getProperty("user.dir") + SetPathForIOS("\\test-output\\reports");

	// ***********************Docker Path setup**********************
	final public static String DOCKER_CONFIG_FILE = SetPathForIOS("./config.properties");
	final public static String DOCKER_LOG_CONFIG_FILE = SetPathForIOS("./log4j.properties");
	final public static String DOCKER_CHROME_DRIVER_EXE = SetPathForIOS("./chromedriver.exe");

	// *******Excel Coulmn Name

	final public static String config = "Configuration";

	/**************************************
	 * API
	 **************************************************/
	final public static String api_file_path = System.getProperty("user.dir")
			+ SetPathForIOS("\\test-data-files\\APITest-Data\\");
	final public static String large_form_api_file_path = System.getProperty("user.dir")
			+ SetPathForIOS("\\test-data-files\\APIData\\largeForm\\");

}
