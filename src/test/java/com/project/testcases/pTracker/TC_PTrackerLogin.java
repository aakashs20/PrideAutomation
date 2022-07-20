package com.project.testcases.pTracker;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;



public class TC_PTrackerLogin extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage LoginPage;
	NewProjectsPage NewProject;
	ControlActions controlActions;
	public String uName = "abc";
	public String uPassword = "xyz";

	@BeforeClass
	public void launchBrowser() throws InterruptedException {
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		LoginPage = new PTrackerLoginPage(driver);
		NewProject = new NewProjectsPage(driver);
		LoginPage.waitForPageLoaded(driver);
	}

	@Test(priority = 1,groups = {"sanity","regression"}, description="Perfrom Login")
	public void TC_Login() throws Exception {
		String expectedTitle = "PRIDE - Sign In";
		String title = LoginPage.TitleOfPage();
		Assert.assertEquals(title, expectedTitle);
		boolean isUserNameSet = LoginPage.enterTextToUserName(uName);
		Assert.assertTrue(isUserNameSet, "Failed to add text to Google search as '" + uName + "'");
		boolean isPasswordSet = LoginPage.enterTextToPassword(uPassword);
		Assert.assertTrue(isPasswordSet, "Failed to add text to Google search as '" + uPassword + "'");
		boolean isLoginButtonClicked = LoginPage.clkLoginButton();
		Assert.assertTrue(isLoginButtonClicked, "Failed to clicked on Login Button.");
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		boolean isDashboardIconVisibleOnPage = LoginPage.isDashboardIconVisibleOnPage();
		Assert.assertTrue(isDashboardIconVisibleOnPage, "Login Failed and Dashboard ICON not visible.");
	}

	@Test(priority = 2,groups = {"sanity","regression"}, description="Perfrom Change User")
	public void TC_ChangeUser() throws Exception {
		boolean isclkNavigationBarIcon = LoginPage.clkNavigationBarIcon();
		Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
		boolean isCancelButtonClicked = LoginPage.clkCancelButton();
		Assert.assertTrue(isCancelButtonClicked, "Failed to click Cancel Button.");
		isclkNavigationBarIcon = LoginPage.clkNavigationBarIcon();
		Assert.assertTrue(isclkNavigationBarIcon, "Failed to click Navigation Icon.");
		String eName = "Mahajan, Milind";
		boolean isEmployeeSelected = LoginPage.selectEmployee(eName);
		Assert.assertTrue(isEmployeeSelected, "Failed to add text to slect Employee search as '" + eName + "'");
		boolean isSubmitButtonClicked = LoginPage.clkSubmitButton();
		Assert.assertTrue(isSubmitButtonClicked, "Failed to click Submit Button on login page");
		threadsleep(2000);
	}

	@Test(priority = 3,groups = {"sanity","regression"}, description="Nevigate to pTrack home page")
	public void TC_OpenPTrackPage() throws Exception {
		boolean ispTrackPageOpend = LoginPage.goToPTrack();
		Assert.assertTrue(ispTrackPageOpend, "Failed to open P-Tracker Home page");
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
