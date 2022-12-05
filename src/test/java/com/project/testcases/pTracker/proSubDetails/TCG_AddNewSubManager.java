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
import org.testng.SkipException;
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

// Ganesh

public class TCG_AddNewSubManager extends TestBase {

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
	boolean isSubManagerReallocated ;

	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		prop = new Properties();
		prop.load(new FileInputStream(Constants.configFile));
		searchProjectName = prop.getProperty("projectName");
		driver = launchbrowser();
        driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		controlActions = new ControlActions(driver);
		op = new Operations(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		cp = new CommonPages(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver,uName, uPassword);
		threadsleep(2000);
		loginPage.TC_ChangeUser(driver,eName);
	}

	
	@Test(priority=1, groups = { "sanity","UI" }, description = "Add Reallocat Sub Manager")
	public void addNewSubManager() throws Exception {
		String tcID = "TC_CANCEL_NEW_SUB_MANAGER_AFTER_ADDING_DETAILS";
		String tcID_1 = "TC_ADD_NEW_SUB_MANAGER";
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum; 
		int tc_Row_Num; 
		int tc_Row_No; 
		
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		tc_Row_Num = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
		HashMap<String, String> row_Data = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc_Row_Num-1);	
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID_1);
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		String subMangerName=rowData.get("SubManager").trim();
		String SubMangerStarDate=rowData.get("ProjectStartDate").trim();
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
		element.click();
		boolean isProjectOpened = controlActions.isElementDisplayedOnPage(cp.ManagerDetailsTab);
		IsTrue(isProjectOpened,"Selected Project Opened Successfully", "Failed to Open Selected Project");
		//cp.ManagerDetailsTab.click();
		op.clickElement(cp.ManagerDetailsTab, driver);
		boolean isManagerDetailsTabOpened = controlActions.isElementDisplayedOnPage(cp.AddSubManagerBtn);
		IsTrue(isManagerDetailsTabOpened,"Manager Details Tab Opened Successfully", "Failed to Open Manager Details Tab");
		logInfo("Manager Details Tab Opened Successfully");
		String readonly = cp.AddSubManager.getAttribute("class");
		logInfo("IS Add Sub Manager Button is ReadOnly? " + readonly.contains("disable_button"));
		if (!readonly.contains("disable_button")) 
		{ 
			logInfo("Starting of Test Case : " + tcID );
			op.clickElement(cp.AddSubManagerBtn, driver);
			//cp.AddSubManagerBtn.click();
			threadsleep(4000);
			boolean subManagerCancel = cp.cancelSubManager(driver, subMangerName,tc_Row_Num);
			IsTrue(subManagerCancel, "Cancel Opretion Perfromed Successfully on Add SubManager Popup", "Failed to Perfrom Cancel on Add SubManager Popup");
			if(subManagerCancel )
			{
				logInfo("Cancel Opretion Perfromed Successfully on Add SubManager Popup"); 
				ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "PASS", "GREEN");
			}
			else
			{
				 logError("Failed to Perfrom Cancel on Add SubManager Popup"); 
				 ExcelUtils.setCellData(datapoolPath, "Status", tc_Row_Num, "FAIL", "RED");
			}
			logInfo("End of Test Case : " + tcID);
			
			logInfo("Starting of Test Case : " + tcID_1);
			op.clickElement(cp.AddSubManagerBtn, driver);
			threadsleep(2000);
			boolean subManagerAdded = cp.addSubManager(driver, subMangerName, tcRowNum);
			IsTrue(subManagerAdded, "add Manager Details", "Failed to add Manager Details");
			threadsleep(2000);
			op.switchToDefaultContent(driver);
			cp.waitSwitch();
			String roleVerify = cp.subMangerRoleVerify();
			String nameVerify = cp.subMangerNameVerify();
			String dateVerify = cp.subMangerDateVerify();
			Equals(roleVerify, "Sub Manager", "Sub Manager Role is verified", "Sub Manager Role is not verified");
			Equals(nameVerify, subMangerName, "Sub Manager Name is  verified", "Sub Manager Name is not verified");
			Equals(dateVerify, SubMangerStarDate, "Sub Manager Name is verified", "Sub Manager Date is not verified");
			if(cp.subMangerRoleDisplay() && cp.subMangerNameDisplay() && cp.subMangerDateDisplay() )
			{
				logInfo("Sub Manager added Successfully"); 
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}
			else
			{
				 logError("Fail to Saved/Submited Project."); 
				 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
			logInfo("End of Test Case : " + tcID_1);
			
			String tcID_2 = "TC_REALLOCATE_SUB_MANAGER";
			tc_Row_No = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID_2);
			HashMap<String, String> rowDatas = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tc_Row_No-1);
			logInfo("Starting of Test Case : " + tcID_2);
			emplyoeeName= rowDatas.get ("EmplyoeeName");
			endDate= rowDatas.get ("EndDate");
			logInfo("Test Case Row No Is: " + tcRowNum);
			logInfo("Reading Excel:   "+datapoolPath);
			isManagerDetailsTabOpened = controlActions.isElementDisplayedOnPage(cp.AddSubManagerBtn);
			IsTrue(isManagerDetailsTabOpened,"Manager Details Tab Opend Successfully", "Failed to Open Manager Details Tab");
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
			logInfo("End of Test Case : " + tcID_2);
		}
		else
		{
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "SKIP", "YELLOW");
			throw new SkipException("Skipping Test Exception");
		}
		
	}

//    //  @Test(priority=1,groups = { "sanity","UI" }, description ="Perfrom SubManager Cancel")
//	  public void cancelSubManager() throws IOException, Exception {
//		String tcID = "TC_CANCEL_NEW_SUB_MANAGER_AFTER_ADDING_DETAILS";
//		logInfo("Starting of Test Case : " + tcID );
//	    String sheetName = "Automation";
//		int header=0; //Excel first row is 0
//		int tcRowNum; 
//		// Prepare the path of excel file.
//	    String workspace = System.getProperty("user.dir");
//		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
//		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase",tcID);
//		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
//		//String projectName = rowData.get("ProjectName").trim();
//		String projectName = searchProjectName;
//		boolean bb = controlActions.waitUntilElementIsClickable(cp.ActiveProjectGoBtn);
//		IsTrue(bb, "Go Button on Active Project Tab is visible", "Go Button on Active Project Tab is Not visible");
//		threadsleep(1000);
//		String tableXpath = "//*[@id='report_table_projects-active-report']/tbody/tr[1]/td";
//		cp.ActiveProjectSearchTxt.clear();
//		cp.ActiveProjectSearchTxt.sendKeys("");
//		cp.ActiveProjectSearchTxt.sendKeys(projectName);
//		cp.ActiveProjectGoBtn.click();
//		threadsleep(5000); 
//		String subMangerName=rowData.get("SubManager").trim();
//	    List<String> searchResult = op.searchReportTable(driver, tableXpath, projectName);
//		Assert.assertNotNull(searchResult, "Active Project Search failed");
//		Equals(searchResult.get(9), "Active", searchResult.get(1) + "is an Active Project", searchResult.get(1) + "is not an Active Project");
//		String projectID = "//*[@id='report_table_projects-active-report']/tbody/tr[1]//a[contains(text(),'"+searchResult.get(0)+"')]";
//		WebElement element  = driver.findElement(By.xpath(projectID));
//		element.click();
//		threadsleep(10000);
//		boolean isProjectOpened = controlActions.isElementDisplayedOnPage(cp.ManagerDetailsTab);
//		IsTrue(isProjectOpened, "Selected Project Opend Successfully", "Failed to Open Selected Project");
//		cp.ManagerDetailsTab.click();
//		boolean isManagerDetailsTabOpened = controlActions.isElementDisplayedOnPage(cp.AddSubManagerBtn);
//		IsTrue(isManagerDetailsTabOpened, "Manager Details Tab Opended Successfully", "Failed to Open Manager Details Tab");
//		cp.AddSubManagerBtn.click();
//		threadsleep(4000);
//		boolean subManagerCancel = cp.cancelSubManager(driver, subMangerName,tcRowNum);
//		IsTrue(subManagerCancel, "Cancel SubManager", "Failed to Cancel SubManager");
//		threadsleep(4000);
//		op.switchToDefaultContent(driver);
//		String roleVerify = cp.projectManagerRoleVerify();
//		Equals(roleVerify, "Project Manager", "Project Manager text  is present","Project Manager text not is present");
//		if(cp.projectManagerRoleDisplay() )
//		{
//			logInfo("Sub Manager Cancel and Add Submanager Scenarios Verified Successfully"); 
//			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
//		}
//		else
//		{
//			logInfo("Failed to Verify Sub Manager Cancel and Add Submanager Scenarios"); 
//			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
//		}
//		logInfo("End of Test Case : " + tcID );
//   }
	
	@AfterClass (alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
