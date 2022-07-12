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
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

import com.project.utilities.ExcelReader;


public class TC_CreateSupportProjectFromExiting extends TestBase {
	
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
	  
	@Test 
	public void CreatCreateSupportProjectFromExiting() throws Exception {
		
		// Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateSupportNewProject.xls";
		logInfo("Reading Excel:   "+datapoolPath);
		String projectName = "Test319";
		  if(NewProject.createProjectFromExiting(projectName)) { 
			     NewProject.NewProjectPageSaveBtn.click();
			     threadsleep(2000);
				 String RequestID = NewProject.getRequestID(); 
				 if (RequestID != null &&
				 !RequestID.trim().isEmpty()) {
				 logInfo("Project Successfully Saved/Submited with Request ID: " + RequestID);
				 ExcelUtils.setCellData(datapoolPath, "Status", 3, "PASS", "GREEN");
				 ExcelUtils.setCellData(datapoolPath, "RequestID", 3, RequestID, "");
				 //ExcelUtils.updateCellData(datapoolPath,2,2,RequestID);
				 threadsleep(1000);
			 
			 } else { 
				 logError("Fail to Saved/Submited Project."); 
				 ExcelUtils.setCellData(datapoolPath, "Status", 2, "FAIL", "RED");
				 } 
		}
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

	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
