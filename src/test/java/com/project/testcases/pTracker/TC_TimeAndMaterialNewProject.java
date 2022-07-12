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

import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import com.project.utilities.ExcelReader;

public class TC_TimeAndMaterialNewProject extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage LoginPage;
	NewProjectsPage NewProject;
	ControlActions controlActions;
	public String uName = "abc";
	public String uPassword = "xyz";

	@BeforeClass
	public void groupInit() throws Exception {
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		LoginPage = new PTrackerLoginPage(driver);
		NewProject = new NewProjectsPage(driver);
		LoginPage.waitForPageLoaded(driver);
		LoginPage.TC_Login(uName, uPassword);
		LoginPage.TC_ChangeUser();

	}

	@Test(groups = { "sanity", "regression" }, description = "Create Time and Material New Project")
	public void CreateTimeNMaterialNewProject() throws Exception {
		
		String datapoolPath = "D:\\Ketan Tank\\iautomate-ref-impl-main\\test-data-files\\UI-TestData\\TC_CreateTimeAndMaterialNewProject.xls";
		
		 if (NewProject.createNewProject()) 
		 {
			 NewProject.fillProjectCreation(datapoolPath); 
			 String RequestID = NewProject.getRequestID(); 
			 if (RequestID != null &&
			 !RequestID.trim().isEmpty()) {
			 logInfo("Project Successfully Saved/Submited with Request ID: " + RequestID);
			 ExcelUtils.setCellData(datapoolPath, "Status", 2, "PASS", "GREEN");
			 ExcelUtils.updateCellData(datapoolPath,1,2,RequestID);
		 
		 } else { 
			 logError("Fail to Saved/Submited Project."); 
			 ExcelUtils.setCellData(datapoolPath, "Status", 2, "FAIL", "RED");
			 } 
		 }
	}


	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}