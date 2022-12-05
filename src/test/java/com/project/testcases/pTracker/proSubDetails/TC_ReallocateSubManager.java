package com.project.testcases.pTracker.proSubDetails;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
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
import com.project.pTracker.Utils.Constants;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import com.project.utilities.ExcelReader;

public class TC_ReallocateSubManager extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	Operations op ;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 20;
	String eName = "Mahajan, Milind";
	private String searchProjectName;
	boolean  isSubManagerReallocated ;

	
	@BeforeClass
	public void groupInit() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));
		searchProjectName = prop.getProperty("projectName");
		driver = launchbrowser();
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		//controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		cp = new CommonPages(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}

	@Test(groups = { "endtoend" }, description = "Validate Reallocate Sub Manager")
	public void reallocateSubManager() throws Exception {
		String tcID = "TC_REALLOCATE_SUB_MANAGER";
		logInfo("Starting of Test Case : " + tcID );
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum; 
		
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		String projectName = searchProjectName;
		//String projectName = rowData.get("ProjectName").trim();
		String emplyoeeName= rowData.get ("EmplyoeeName");
		String endDate= rowData.get ("EndDate");
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
		boolean bb = controlActions.waitUntilElementIsClickable(cp.ActiveProjectGoBtn);
		IsTrue(bb,"Go Button on Active Project Tab is Visible", "Go Button on Active Project Tab is Not Visible");
		threadsleep(1000);
		logInfo("Searching for the Project : " + projectName);
		String tableXpath = "//*[@id='report_table_projects-active-report']/tbody/tr[1]/td";
		
		cp.ActiveProjectSearchTxt.clear();
		cp.ActiveProjectSearchTxt.sendKeys("");
		cp.ActiveProjectSearchTxt.sendKeys(projectName);
		cp.ActiveProjectGoBtn.click();
		threadsleep(5000);        
		 
		List<String> searchResult = op.searchReportTable(driver, tableXpath, projectName);
		Assert.assertNotNull(searchResult, "Active Project Search failed");
		logInfo("Table Rows Count is: " + searchResult.size());
		logInfo("Project Number : " + searchResult.get(0));
		logInfo("Project Name : " + searchResult.get(1));
		logInfo("Project State : " + searchResult.get(9));
		Assert.assertEquals(searchResult.get(9), "Active", searchResult.get(1) + "is not an Active Project" );
		String projectID = "//*[@id='report_table_projects-active-report']/tbody/tr[1]//a[contains(text(),'"+searchResult.get(0)+"')]";
		WebElement element  = driver.findElement(By.xpath(projectID));
		op.clickElement(element, driver);
		//element.click();
		boolean isProjectOpened = controlActions.isElementDisplayedOnPage(cp.ManagerDetailsTab);
		IsTrue(isProjectOpened,"Selected Project Opened Successfully", "Failed to Open Selected Project");
		op.clickElement(cp.ManagerDetailsTab, driver);
		//cp.ManagerDetailsTab.click();
		boolean isManagerDetailsTabOpened = controlActions.isElementDisplayedOnPage(cp.AddSubManagerBtn);
		Assert.assertTrue(isManagerDetailsTabOpened, "Failed to Open Manager Details Tab");
		logInfo("Manager Details Tab Opened Successfully ");
		boolean isRoleSelected = cp.selectProjectRole(driver,"Sub Manager",emplyoeeName);
		if(isRoleSelected)
		{
			isSubManagerReallocated =cp.reallocateSubManager(driver, endDate , emplyoeeName);
			IsTrue(isSubManagerReallocated,emplyoeeName+ " is succesfully Reallocated", "Failed to Reallocate "+emplyoeeName);
		}
		if (isSubManagerReallocated)
		{
			logInfo("Succesfully Reallocated - > " +  emplyoeeName);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		}
		else
		{
			logInfo("Failed to Reallocate - > "+ emplyoeeName);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
		} 
		logInfo("End of Test Case : " + tcID );
		}


	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
