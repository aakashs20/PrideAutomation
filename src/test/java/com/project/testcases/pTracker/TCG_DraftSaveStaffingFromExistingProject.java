package com.project.testcases.pTracker;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
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


public class TCG_DraftSaveStaffingFromExistingProject extends TestBase {
	
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
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
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver,uName,uPassword);
		loginPage.TC_ChangeUser(driver,eName);
		//LoginPage.TC_OpenPTrackPage();
	}	
	  
	@Test 
	public void CreatCreateStaffingProjectFromExiting() throws Exception {
		String tcID = "TC_DRAFT_SAVE_FROM_STAFFING_PROJECT";
		logInfo("Starting of Test Case : " + tcID );
		int tcRowNum; 
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		//tcRowNum = 1;
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
    	String projectName = "Demo new";
		  if(newProject.createProjectFromExiting(projectName)) { 
			  	 threadsleep(3000);
			  	 op.waitUntilElementIsClickable(newProject.NewProjectPageSaveBtn);
			     //newProject.NewProjectPageSaveBtn.click();
				 JavascriptExecutor executor = (JavascriptExecutor)driver; 
				 executor.executeScript("arguments[0].click();",newProject.NewProjectPageSaveBtn);
			     threadsleep(3000);
			     op.scrollPageTo(newProject.NewProjectPageHeadings, driver);
			     op.waitUntilElementIsClickable(newProject.NewProjectPageSaveBtn);
				 String RequestID = newProject.getRequestID(); 
				 if (RequestID != null &&
				 !RequestID.trim().isEmpty()) {
				 logInfo("Project Successfully Saved/Submited with Request ID: " + RequestID);
				 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
				 ExcelUtils.setCellData(datapoolPath, "RequestID",tcRowNum, RequestID, "YELLOW");
				 //ExcelUtils.updateCellData(datapoolPath,2,2,RequestID);
				 threadsleep(2000);
			 } else { 
				 logError("Fail to Saved/Submited Project."); 
				 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
				 } 
			logInfo("End of Test Case : " + tcID );
		}
		/*
		 * if(NewProject.createNewProject()) { //NewProject.validateAlert();
		 * NewProject.fillProjectCreation(); }
		 */
		
		
	}
	
	@DataProvider
	public Object[][] dataexcel() throws IOException
	{
		Object[][] exceldata = null;
		try
		{
		ExcelReader e=new ExcelReader();
		String excel=prop.getProperty("excel_file_path");
		System.out.println(excel);
	    exceldata=e.readDataFromExcel(System.getProperty("user.dir")+File.separator+"test-data-files"+File.separator+"UI-TestData"+File.separator+excel,"Sheet1");
	
		return exceldata;
		}
		catch (Exception e)
		{
			log4jError("Error while reading from excel check the excel path or sheet name: " + e.toString());
			return exceldata;
		}
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException, IOException {
		op.closeBrowser(driver);
	}
}
