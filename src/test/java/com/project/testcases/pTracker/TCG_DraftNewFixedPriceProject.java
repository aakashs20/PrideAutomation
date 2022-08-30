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

import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_DraftNewFixedPriceProject extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	private String uName = "abc";
	private String uPassword = "xyz";
	private static final int DELAY = 2000;
	String eName = "Mahajan, Milind";
	
	@BeforeClass
	public void groupInit() throws Exception {
		driver = launchbrowser();
	   // setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput","true");
        driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);		
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		//controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}

	@Test(groups = { "sanity", "regression" }, description = "Create Fixed Price New Project")
	public void DraftFixedPriceNewProject() throws Exception {
		int tcRowNum; 
		String projectState = "DRAFT"; //SUBMIT
		// Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		//tcRowNum = 1;
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","TC_DRAFT_NEW_FIXED_PRICE_PROJECT");
		//ExcelUtils.getTestDataXlsx(projectState, workspace, tcRowNum, datapoolPath);
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
		 if (newProject.createNewProject()) 
		 {
			 newProject.fillProjectCreation(datapoolPath,projectState,tcRowNum); 
			 String RequestID = newProject.getRequestID(); 
			 if (RequestID != null &&
			 !RequestID.trim().isEmpty()) {
			 logInfo("Project Successfully Saved/Submited with Request ID: " + RequestID);
			 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			 ExcelUtils.setCellData(datapoolPath, "RequestID", tcRowNum, RequestID, "YELLOW");
			 
			 //ExcelUtils.updateCellData(datapoolPath,1,3,RequestID);
		 
		 } else { 
			 logError("Fail to Saved/Submited Project."); 
			 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			 } 
		 }
	}


	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
