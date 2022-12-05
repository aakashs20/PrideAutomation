package com.project.testcases.pTracker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class TC_EndToEndFixedPriceProject extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage LoginPage;
	NewProjectsPage NewProject;
	ControlActions controlActions;
	Operations op ;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";

	
	@BeforeClass
	public void groupInit() throws Exception {
		driver = launchbrowser();
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		//controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		LoginPage = new PTrackerLoginPage(driver);
		NewProject = new NewProjectsPage(driver);
		LoginPage.waitForPageLoaded(driver);
		LoginPage.TC_Login(driver,uName, uPassword);
		LoginPage.TC_ChangeUser(driver,eName);
	}

	@Test(groups = { "endtoend" }, description = "Create Fixed Price New Project")
	public void SubmitNewFixedPriceProject() throws Exception {
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum=3; 
		String projectState = "SUBMIT"; //SUBMIT, DRAFT
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_END_TO_END_FIXED_PRICE_PROJECT.xls";
			
		logInfo("Reading Excel:   "+datapoolPath);
		 if (NewProject.createNewProject()) 
		 {
			 NewProject.fillProjectCreation(datapoolPath,sheetName,projectState,tcRowNum); 
			 op.sleepInMiliSeconds(2000);
			 HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum);
			 String projectName = rowData.get("ProjectName");
			 logInfo("Searching for the Project : " + projectName);
			 //driver.findElement(By.xpath(SEARCH_INPUT_XPATH)).clear();
			 driver.findElement(By.xpath("//*[@id='P30_NP_FC_SEARCH']")).sendKeys("");
			 driver.findElement(By.xpath("//*[@id='P30_NP_FC_SEARCH']")).sendKeys(projectName);
			 driver.findElement(By.xpath("//*[@id='B604615927023884937' and contains(text(),'GO')]")).click();
			 op.sleepInMiliSeconds(2000);
			 String xpathLocator = "//*[@id='report_table_New-Project-Request']/tbody/tr/td";
			 List<String> ElementsList = op.searchReportTable(driver, xpathLocator, projectName);
//			 int size = ElementsList.size();
			 logInfo("List Size is: " + ElementsList.size());
			 logInfo("Project ID : " + ElementsList.get(0));
			 logInfo("Project Name : " + ElementsList.get(1));
			 logInfo("Project State : " + ElementsList.get(9));
//				for (int i = 0; i < size; i++)
//				{
//					logInfo( i + " -----> " + ElementsList.get(i));
//				}
			 op.sleepInMiliSeconds(2000);
		     String RequestID = ElementsList.get(0); 
		     String exp_projectName = ElementsList.get(1); 
		     String TestAction = ElementsList.get(9); 
			 if (projectName.equals(exp_projectName)) {
			 logInfo("Project Successfully Submited with Request ID: " + RequestID);
			 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			 ExcelUtils.setCellData(datapoolPath, "RequestID", tcRowNum, RequestID, "YELLOW");
			 ExcelUtils.setCellData(datapoolPath, "TestAction", tcRowNum, TestAction, "YELLOW");
		 
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
