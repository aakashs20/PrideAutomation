package com.project.testcases.pTracker;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import com.project.utilities.ExcelReader;


public class TCG_ValidatePTrackerPage extends TestBase {
	
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	ExcelUtils ExcelUtils;
	Operations op ;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
    String tcStatus;
    boolean isAlertPresent;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		// setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver = launchbrowser();
        String currentWindow = driver.getWindowHandle();
        driver.switchTo().window(currentWindow);
        driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);		
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		wait = new WebDriverWait(driver, DELAY);
		op = new Operations(driver);
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		ExcelUtils = new ExcelUtils();
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);

		//LoginPage.TC_OpenPTrackPage();
	}

	@Test (priority=1, groups = { "sanity", "regression" }, description = "For New Project Creation an Alert Message is shown if mandatory fields are not filled") 
	 public void ValidateAlertONCreateNewProject() throws Exception {
	 String tcID = "TC_VALIDATE_ALERT_ON_NEW_PROJECT_CREATION";
	 logInfo("Starting of Test Case : " + tcID );
	  //NewProject.createProjectFromExisting();
	  if(newProject.createNewProject()) { 
		  isAlertPresent = newProject.validateAlert();
	  // NewProject.fillProjectCreation(); 
			    if(isAlertPresent)
                {
		    		logInfo("Testcase is pass");
					tcStatus = "PASS";
					ExcelUtils.logTestResult(tcID,tcStatus);
					controlActions.clickElement(newProject.NewProjectPageCancelBtn);
				}
				else 
				{ 
					logError("Testcase is failed"); 
					tcStatus = "FAIL";
					ExcelUtils.logTestResult(tcID,tcStatus);
				}
		  }
	  logInfo("End of Test Case : " + tcID );
	 }
	 
	 @Test (priority=2, groups = { "sanity", "regression" }, description = "For Creating a Project by Copy from Existing Project an Alert Message is shown if mandatory fields are not filled") 
	 public void ValidateAlertONCreateProjectFromExisting() throws Exception {
	 String projectName = "Test0006";
	 String tcID = "TC_VALIDATE_ALERT_ON_COPY_PROJECT_FROM_FROM_EXISTING_CREATION";
	 logInfo("Starting of Test Case : " + tcID );
	 if(newProject.createProjectFromExiting(projectName)) { 
		  isAlertPresent = newProject.validateAlert();
			 // NewProject.fillProjectCreation(); 
		    if(isAlertPresent)
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
		  }
	 logInfo("End of Test Case : " + tcID );
	 }


		@AfterClass (alwaysRun = true)
		public void tearDown() {
			try {
				op.closeBrowser(driver);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
}

