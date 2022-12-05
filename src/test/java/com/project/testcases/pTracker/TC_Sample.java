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
import com.project.utilities.ExcelReader;

public class TC_Sample extends TestBase {

	WebDriverWait wait;
	ControlActions controlActions;
	private static final int DELAY = 100;
	ExcelReader reader;
		
	@BeforeClass
	public void groupInit() throws Exception {
		driver = launchbrowser();
	   // setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput","true");
//		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
//		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
//		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));

	}

	@Test(groups = {"sanity", "regression"}, description = "Create Fixed Price New Project")
	public void Sample() throws Exception {
		reader = new ExcelReader();
		int tcRowNum; 
		String eFlag;
		
		String projectState = "SUBMIT"; //DRAFT
		// Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateStaffingNewProject.xls";
		//tcRowNum = 3;
		//tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","TC_DRAFT_NEW_FIXED_PRICE_PROJECT");
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","TC_DRAFT_NEW_STAFFING_PROJECT");
		
		//tcRowNum = reader.getCellRowNum("Automation","testCase","TC_DRAFT_NEW_FIXED_PRICE_PROJECT",datapoolPath);
		//eFlag = reader.getCellDataUsingColumnName("Automation", "Flag", tcRowNum,  datapoolPath);
		logInfo("Test Case Row No Is: " + tcRowNum);
		//logInfo("Execution Flag Is: " + eFlag);
		
		//ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		//reader.setCellDataAndChangeCellColor( "Automation",  "Status", tcRowNum, "PASS", "YELLOW",  datapoolPath);
	}


	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
