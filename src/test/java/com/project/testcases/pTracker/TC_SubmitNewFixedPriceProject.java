package com.project.testcases.pTracker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

public class TC_SubmitNewFixedPriceProject extends TestBase {

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
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		//controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		LoginPage = new PTrackerLoginPage(driver);
		NewProject = new NewProjectsPage(driver);
		LoginPage.waitForPageLoaded(driver);
		LoginPage.TC_Login(uName, uPassword);
		LoginPage.TC_ChangeUser();
	}

	@Test(groups = { "sanity", "regression" }, description = "Create Fixed Price New Project")
	public void SubmitNewFixedPriceProject() throws Exception {
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tc=4;
		String projectState = "SUBMIT"; //SUBMIT, DRAFT
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		
		
		logInfo("Reading Excel:   "+datapoolPath);
		 if (NewProject.createNewProject()) 
		 {
			 NewProject.fillProjectCreation(datapoolPath,projectState); 
			 op.sleepInMiliSeconds(2000);
			 HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc);
			 String projectName = rowData.get("ProjectName");
			 logInfo("Searching for the Project : " + projectName);
			 //driver.findElement(By.xpath(SEARCH_INPUT_XPATH)).clear();
			 driver.findElement(By.xpath("//*[@id='P30_NP_FC_SEARCH']")).sendKeys("");
			 driver.findElement(By.xpath("//*[@id='P30_NP_FC_SEARCH']")).sendKeys(projectName);
			 driver.findElement(By.xpath("//*[@id='B604615927023884937' and contains(text(),'GO')]")).click();
			 op.sleepInMiliSeconds(2000);
			 String xpathLocator = "//*[@id='report_table_New-Project-Request']/tbody/tr/td";
			 op.searchReportTable(driver, xpathLocator, projectName);
			 op.sleepInMiliSeconds(2000);
//			 String RequestID = NewProject.getRequestID(); 
//			 if (RequestID != null &&
//			 !RequestID.trim().isEmpty()) {
//			 logInfo("Project Successfully Submited with Request ID: " + RequestID);
//			 ExcelUtils.setCellData(datapoolPath, "Status", 4, "PASS", "GREEN");
//			 ExcelUtils.setCellData(datapoolPath, "RequestID", 4, RequestID, "");
//		 
//		 } else { 
//			 logError("Fail to Saved/Submited Project."); 
//			 ExcelUtils.setCellData(datapoolPath, "Status", 3, "FAIL", "RED");
//			 } 
		 }
	}


	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
