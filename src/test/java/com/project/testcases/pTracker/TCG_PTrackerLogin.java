package com.project.testcases.pTracker;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;



public class TCG_PTrackerLogin extends TestBase {
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	ExcelUtils ExcelUtils;
	private String uName = "abc";
	private String uPassword = "xyz";
	private static final int DELAY = 20;
    String tcStatus;

	@BeforeClass
	public void launchBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver = launchbrowser();
        driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);		
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		try 
		{
			controlActions = new ControlActions(driver);
		}catch(Exception e) 
		{
			logError("Message " + e.getMessage());
		}
		ExcelUtils = new ExcelUtils();
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		
	}

	@Test(priority = 1,groups = {"sanity","regression"}, description="Perfrom Login Operation")
	public void TC_Login() throws Exception {
		String expectedTitle = "PRIDE - Sign In";
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
			ExcelUtils.logTestResult("TC_LOGIN",tcStatus);
		}
		else 
		{ 
			logError("Testcase is failed"); 
			tcStatus = "FAIL";
			ExcelUtils.logTestResult("TC_LOGIN",tcStatus);
		}
	}

	@Test(priority = 2,groups = {"sanity","regression"}, description="Perfrom Change User Operation")
	public void TC_ChangeUser() throws Exception {
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
		boolean isSubmitButtonClicked = loginPage.clkSubmitButtonNew(driver);
		IsTrue(isSubmitButtonClicked, "Submit Button on login page clicked successfully", "Failed to click Submit Button on login page");
		threadsleep(1000);
	    if(isSubmitButtonClicked)
        {
    		logInfo("Testcase is pass");
			tcStatus = "PASS";
			ExcelUtils.logTestResult("TC_CHANGE_USER",tcStatus);
		}
		else 
		{ 
			logError("Testcase is failed"); 
			tcStatus = "FAIL";
			ExcelUtils.logTestResult("TC_CHANGE_USER",tcStatus);
		}
	}

	@Test(priority = 3,groups = {"sanity","regression"}, description="Navigate to pTrack home page")
	public void TC_OpenPTrackPage() throws Exception {
		boolean ispTrackPageOpend = loginPage.goToPTrack();
		IsTrue(ispTrackPageOpend,"Successfully Opened P-Tracker Home page","Failed to open P-Tracker Home page");
		threadsleep(1000);
	    if(ispTrackPageOpend)
        {
    		logInfo("Testcase is pass");
			tcStatus = "PASS";
			ExcelUtils.logTestResult("TC_OPEN_PTRACK_HOME_PAGE",tcStatus);
		}
		else 
		{ 
			logError("Testcase is failed"); 
			tcStatus = "FAIL";
			ExcelUtils.logTestResult("TC_OPEN_PTRACK_HOME_PAGE",tcStatus);
		}
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

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
