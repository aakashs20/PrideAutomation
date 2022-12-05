package com.project.testcases.pTracker.proSubDetails;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.Constants;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;



public class TCG_AddNewSow  extends TestBase {

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
		threadsleep(2000);
		loginPage.TC_ChangeUser(driver,eName);
	}

	@Test(groups = { "endtoend" }, description = "Validate Connect SOW")
	public void addNewSow() throws Exception {
		String tcID = "TC_ADD_NEW_SOW";
		logInfo("Starting of Test Case : " + tcID );
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum; 
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		//String projectName = rowData.get("ProjectName").trim();
		String projectName = searchProjectName;
		String sowName = rowData.get("SowName");
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
		boolean bb = controlActions.waitUntilElementIsClickable(cp.ActiveProjectGoBtn);
		Assert.assertTrue(bb, "Go Button on Active Project Tab is Not visible");
		threadsleep(1000);
		logInfo("Searching for the Project : " + projectName);
		String tableXpath = "//*[@id='report_table_projects-active-report']/tbody/tr/td";

		cp.ActiveProjectSearchTxt.clear();
		cp.ActiveProjectSearchTxt.sendKeys("");
		cp.ActiveProjectSearchTxt.sendKeys(projectName);
		cp.ActiveProjectGoBtn.click();
		threadsleep(5000);        
		 List<String> searchResult = op.searchReportTable(driver, tableXpath, projectName);
		 Assert.assertNotNull(searchResult, "Active Project Search failed");
		 int size = searchResult.size();
		 for (int i = 0; i < size; i++)
		 {
			logInfo( i + " -----> " + searchResult.get(i));
		 }

		 logInfo("Table Rows Count is: " + searchResult.size());
		 logInfo("Project Number : " + searchResult.get(0));
		 logInfo("Project Name : " + searchResult.get(1));
		 logInfo("Project State : " + searchResult.get(9));
		 Assert.assertEquals(searchResult.get(9), "Active", searchResult.get(1) + "is not an Active Project" );
		 String projectID = "//*[@id='report_table_projects-active-report']/tbody/tr[1]//a[contains(text(),'"+searchResult.get(0)+"')]";
		 WebElement element  = driver.findElement(By.xpath(projectID));
		 element.click();
		 boolean isProjectOpened = controlActions.isElementDisplayedOnPage(cp.ManagerDetailsTab);
		 Assert.assertTrue(isProjectOpened, "Failed to Open Selected Project");
		 cp.ManagerDetailsTab.click();
		 boolean isManagerDetailsTabOpened = controlActions.isElementDisplayedOnPage(cp.AddSubManagerBtn);
		 Assert.assertTrue(isManagerDetailsTabOpened, "Failed to Open Manager Details Tab");
		 cp.SowDetailsTab.click();
		 boolean isProjectOpened2 = controlActions.isElementDisplayedOnPage(cp.SowDetailsTab);
		 Assert.assertTrue(isProjectOpened2, "Failed to Open SOW Tab");
		 //String sowName="SOW_Banking    [FP]";
		boolean  AddSowToProject=cp.ConnectSow(driver,sowName);
		IsTrue(AddSowToProject,sowName+ " is succesfully Added", "Failed to Add "+sowName);
		if (AddSowToProject)
		{
			 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		}
		else
		{
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
		} 
		logInfo("End of Test Case : " + tcID );
	}
	
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}





