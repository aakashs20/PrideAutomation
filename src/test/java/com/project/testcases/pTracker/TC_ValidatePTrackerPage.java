package com.project.testcases.pTracker;


import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import com.project.utilities.ExcelReader;


public class TC_ValidatePTrackerPage extends TestBase {
	
	WebDriverWait wait;
	PTrackerLoginPage LoginPage;
	NewProjectsPage NewProject;
	ControlActions controlActions;
	Operations op ;
	public String uName = "abc";
	public String uPassword = "xyz";

	@BeforeClass
	public void groupInit() throws Exception {

		driver = launchbrowser();
		op = new Operations(driver);
		controlActions = new ControlActions(driver);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		LoginPage = new PTrackerLoginPage(driver);
		NewProject = new NewProjectsPage(driver);
		LoginPage.waitForPageLoaded(driver);
		LoginPage.TC_Login(uName, uPassword);
		LoginPage.TC_ChangeUser();
		//LoginPage.TC_OpenPTrackPage();
	}

	@Test (priority=1, groups = { "sanity", "regression" }, description = "Validate Alert on New Project Creation") 
	 public void ValidateAlertONCreateNewProject() throws Exception {
	  //NewProject.createProjectFromExiting();
	  if(NewProject.createNewProject()) { 
			 NewProject.validateAlert();
			 controlActions.clickElement(NewProject.NewProjectPageCancelBtn);
			 // NewProject.fillProjectCreation(); 
		  }
	 }
	 
	 @Test (priority=2, groups = { "sanity", "regression" }, description = "Validate Alert on Project Creation Page From Copy from Exiting Project") 
	 public void ValidateAlertONCreateProjectFromExiting() throws Exception {
	 String projectName = "Test0006";
	  if(NewProject.createProjectFromExiting(projectName)) { 
			 NewProject.validateAlert();
			 // NewProject.fillProjectCreation(); 
		  }
	 }


	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

