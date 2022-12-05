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
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import com.project.utilities.ExcelReader;

public class TCG_DraftNewStaffingProject extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage LoginPage;
	NewProjectsPage NewProject;
	ControlActions controlActions;
	Operations op ;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";

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
		op = new Operations(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		LoginPage = new PTrackerLoginPage(driver);
		NewProject = new NewProjectsPage(driver);
		LoginPage.waitForPageLoaded(driver);
		LoginPage.TC_Login(driver,uName, uPassword);
		LoginPage.TC_ChangeUser(driver,eName);
	}

	@Test(groups = { "sanity", "regression" }, description = "Create Staffing New Project")
	public void CreateStaffingNewProject() throws Exception {
		String tcID = "TC_DRAFT_NEW_STAFFING_PROJECT";
		logInfo("Starting of Test Case : " + tcID );
		String sheetName = "Automation";
		int tcRowNum; 
		String projectState = "DRAFT"; //SUBMIT
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		//tcRowNum = 1;
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
		 if (NewProject.createNewProject()) 
		 {
			 NewProject.fillProjectCreation(datapoolPath,sheetName,projectState,tcRowNum); 
			 String RequestID = NewProject.getRequestID(); 
			 if (RequestID != null &&
			 !RequestID.trim().isEmpty()) {
			 logInfo("Project Successfully Saved/Submited with Request ID: " + RequestID);
			 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			 ExcelUtils.setCellData(datapoolPath, "RequestID", tcRowNum, RequestID, "YELLOW");
			 //ExcelUtils.updateCellData(datapoolPath,1,2,RequestID);
		 
		 } else { 
			 logError("Fail to Saved/Submited Project."); 
			 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			 } 
		 }
		 logInfo("End of Test Case : " + tcID );
	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException, IOException {
		op.closeBrowser(driver);
	}
}
