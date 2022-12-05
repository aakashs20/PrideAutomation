package com.project.testcases.pTracker;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;



public class TCG_PTrackerLogin extends TestBase {
	//WebDriver driver;
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op ;
	ExcelUtils ExcelUtils;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
    String tcStatus;

	@BeforeClass(alwaysRun = true)
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver = launchbrowser();
        String currentWindow = driver.getWindowHandle();
        driver.switchTo().window(currentWindow);
        driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);		
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		try 
		{
			controlActions = new ControlActions(driver);
			ExcelUtils = new ExcelUtils();
			op = new Operations(driver);
			loginPage = new PTrackerLoginPage(driver);
			newProject = new NewProjectsPage(driver);
		}catch(Exception e) 
		{
			logError("Message " + e.getMessage());
		}
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage.waitForPageLoaded(driver);
		threadsleep(9000);
	}

	@Test(priority = 1,groups = {"sanity","UI"}, description="Perfrom Login Operation")
	public void TC_Login() throws Exception {
		String tcID = "TC_LOGIN";
		logInfo("Starting of Test Case : " + tcID );
		String expectedTitle = "PRIDE - Sign In";
		prop.getProperty("executeOnUAT");
		if(prop.getProperty("executeOnUAT").trim().equalsIgnoreCase("true"))
		{
			loginPage.handleSSLError(driver);
		}
		String title = loginPage.TitleOfPage(driver);
		Equals(title, expectedTitle,"Title of the page Matched Successfully","Fail to Match the Title of the page");
		boolean isUserNameSet = loginPage.enterTextToUserName(uName);
		IsTrue(isUserNameSet, "Successfully added user id '" + uName + "'", "Failed to add user id '" + uName + "'");
		boolean isPasswordSet = loginPage.enterTextToPassword(uPassword);
		IsTrue(isPasswordSet, "Password  '" + uPassword + "' Added Successfully", "Failed to add password '" + uPassword + "'");
		boolean isLoginButtonClicked = loginPage.clkLoginButton();
		IsTrue(isLoginButtonClicked, "Successfully clicked on Login Button", "Failed to clicked on Login Button");
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		boolean isDashboardIconVisibleOnPage = loginPage.isDashboardIconVisibleOnPage();
		IsTrue(isDashboardIconVisibleOnPage, "Login Success and Dashboard ICON is visible", "Login Failed and Dashboard ICON not visible");
	    if(isDashboardIconVisibleOnPage)
        {
    		logInfo("Testcase is pass");
			tcStatus = "PASS";
			ExcelUtils.logTestResult(tcID,tcStatus);
		}
		else 
		{ 
			logError("Testcase is failed"); 
			tcStatus = "FAIL";
			ExcelUtils.logTestResult(tcID,tcStatus);
		}
		logInfo("End of Test Case : " + tcID );
	}

	@Test(priority = 2,dependsOnMethods = { "TC_Login" }, groups = {"sanity","regression"},testName = "TC_CHANGE_USER",  description="Perfrom Change User Operation")
	public void TC_ChangeUser() throws Exception {
		String tcID = "TC_CHANGE_USER";
		logInfo("Starting of Test Case : " + tcID );
		boolean isclkNavigationBarIcon = loginPage.clkNavigationBarIcon();
		IsTrue(isclkNavigationBarIcon, "Successfully clicked on Navigation Icon", "Failed to click Navigation Icon");
		boolean isCancelButtonClicked = loginPage.clkCancelButton();
		IsTrue(isCancelButtonClicked, "Successfully clicked on Cancel Button", "Failed to click Cancel Button");
		isclkNavigationBarIcon = loginPage.clkNavigationBarIcon();
		IsTrue(isclkNavigationBarIcon, "Successfully clicked on Navigation Icon", "Failed to click Navigation Icon");
		String eName = "Mahajan, Milind";
		boolean isEmployeeSelected = loginPage.selectEmployee(eName);
		IsTrue(isEmployeeSelected, "Employee " + eName + "Successfully selected from List", "Failed to add text to select Employee search as '" + eName + "'");
		threadsleep(2000);
		controlActions.waitForAnElementToBeClickable(loginPage.SubmitBtn);
		boolean isSubmitButtonClicked = loginPage.clkSubmitButtonNew(driver,eName);
		IsTrue(isSubmitButtonClicked, "Submit Button on login page clicked successfully", "Failed to click Submit Button on login page");
		threadsleep(1000);
	    if(isSubmitButtonClicked)
        {
    		logInfo("Testcase is pass");
			tcStatus = "PASS";
			ExcelUtils.logTestResult(tcID,tcStatus);
		}
		else 
		{ 
			logError("Testcase is failed"); 
			tcStatus = "FAIL";
			ExcelUtils.logTestResult(tcID,tcStatus);
		}
		logInfo("End of Test Case : " + tcID );
	}

	@Test(priority = 3,dependsOnMethods = { "TC_ChangeUser" }, groups = {"sanity","regression"}, description="Navigate to pTrack home page")
	public void TC_OpenPTrackPage() throws Exception {
		String tcID = "TC_OPEN_PTRACK_HOME_PAGE";
		logInfo("Starting of Test Case : " + tcID );
		boolean ispTrackPageOpend = loginPage.goToPTrack();
		IsTrue(ispTrackPageOpend,"Successfully Opened P-Tracker Home page","Failed to open P-Tracker Home page");
		threadsleep(1000);
	    if(ispTrackPageOpend)
        {
    		logInfo("Testcase is pass");
			tcStatus = "PASS";
			ExcelUtils.logTestResult(tcID,tcStatus);
		}
		else 
		{ 
			logError("Testcase is failed"); 
			tcStatus = "FAIL";
			ExcelUtils.logTestResult(tcID,tcStatus);
		}
		logInfo("End of Test Case : " + tcID );
	}

	/*
	 * @Test (priority=4) public void CreateNewProject() throws Exception {
	 * NewProject.createProjectFromExiting();
	 * 
	 * 
	 * if(NewProject.createNewProject()) { //NewProject.validateAlert();
	 * NewProject.fillProjectCreation(); }
	 * 
	 * }
	 */

	@AfterClass (alwaysRun = true)
	public void tearDown() {
		try {
			op.closeBrowser(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
