package com.project.testcases.pTracker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

// Ganesh

public class TC_AddNewSubManager_old extends TestBase {

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

	
	@BeforeClass
	public void groupInit() throws Exception {
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

	
	  @Test(priority=1,groups = { "endtoend" }, description ="Perfrom SubManager Cancel")
	  public void cancelSubManager() throws IOException, Exception {
	    String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum; 
		// Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","TC_CANCEL_NEW_SUB_MANAGER_AFTER_ADDING_DETAILS");
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
		String projectName = rowData.get("ProjectName").trim();
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
		boolean bb = controlActions.waitUntilElementIsClickable(cp.ActiveProjectGoBtn);
		IsTrue(bb, "Go Button on Active Project Tab is visible", "Go Button on Active Project Tab is Not visible");
		threadsleep(1000);
		logInfo("Searching for the Project : " + projectName);
		String tableXpath = "//*[@id='report_table_projects-active-report']/tbody/tr[1]/td";
		cp.ActiveProjectSearchTxt.clear();
		cp.ActiveProjectSearchTxt.sendKeys("");
		cp.ActiveProjectSearchTxt.sendKeys(projectName);
		cp.ActiveProjectGoBtn.click();
		threadsleep(5000); 
		String subMangerName=rowData.get("SubManager").trim();
	    List<String> searchResult = op.searchReportTable(driver, tableXpath, projectName);
		Assert.assertNotNull(searchResult, "Active Project Search failed");
		//List<String> ElementsList = op.searchReportTable(driver, tableXpath, projectName);
		 
		logInfo("Table Rows Count is: " + searchResult.size());
		logInfo("Project Number : " + searchResult.get(0));
		logInfo("Project Name : " + searchResult.get(1));
		logInfo("Project State : " + searchResult.get(10));
		Equals(searchResult.get(10), "Active", searchResult.get(1) + "is an Active Project", searchResult.get(1) + "is not an Active Project");
//		Assert.assertEquals(searchResult.get(10), "Active", searchResult.get(1) + "is not an Active Project" );
		String projectID = "//*[@id='report_table_projects-active-report']/tbody/tr[1]//a[contains(text(),'"+searchResult.get(0)+"')]";
		WebElement element  = driver.findElement(By.xpath(projectID));
		element.click();
		threadsleep(10000);
		boolean isProjectOpened = controlActions.isElementDisplayedOnPage(cp.ManagerDetailsTab);
		IsTrue(isProjectOpened, "Selected Project Opend Successfully", "Failed to Open Selected Project");
		cp.ManagerDetailsTab.click();
		boolean isManagerDetailsTabOpened = controlActions.isElementDisplayedOnPage(cp.AddSubManagerBtn);
		IsTrue(isManagerDetailsTabOpened, "Manager Details Tab Opended Successfully", "Failed to Open Manager Details Tab");
		cp.AddSubManagerBtn.click();
		threadsleep(4000);
		boolean subManagerCancel = cp.cancelSubManager(driver, subMangerName,tcRowNum);
		IsTrue(subManagerCancel, "Cancel SubManager", "Failed to Cancel SubManager");
		threadsleep(4000);
		op.switchToDefaultContent(driver);
		logInfo("Sun Manager cancel and return add submanager page" + cp.projectManagerRoleVerify());
		String roleVerify = cp.projectManagerRoleVerify();
		Equals(roleVerify, "Project Manager", "Project Manager text  is present","Project Manager text not is present");
		if(cp.projectManagerRoleDisplay() )
		{
			logInfo("Sun Manager cancel and return add submanager page Successfully"); 
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		}
		else
		{
			 logError("Fail to Saved/Submited Project."); 
			 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
		}
	 }
	 

	@Test(priority=2, groups = { "endtoend" }, description = "Add New Sub Manager")
	public void addNewSubManager() throws Exception {
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		int tcRowNum; 
		
	    // Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","TC_ADD_NEW_SUB_MANAGER");
		HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
//		String projectName = rowData.get("ProjectName").trim();
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
		
//		boolean bb = controlActions.waitUntilElementIsClickable(cp.ActiveProjectGoBtn);
//		IsTrue(bb, "Go Button on Active Project Tab is visible", "Go Button on Active Project Tab is Not visible");
//		threadsleep(1000);
//		logInfo("Searching for the Project : " + projectName);
//		String tableXpath = "//*[@id='report_table_projects-active-report']/tbody/tr[1]/td";
		
//		cp.ActiveProjectSearchTxt.clear();
//		cp.ActiveProjectSearchTxt.sendKeys("");
//		cp.ActiveProjectSearchTxt.sendKeys(projectName);
//		cp.ActiveProjectGoBtn.click();
//		threadsleep(5000);        
//		 
//		 List<String> searchResult = op.searchReportTable(driver, tableXpath, projectName);
//		 Assert.assertNotNull(searchResult, "Active Project Search failed");
//		 //List<String> ElementsList = op.searchReportTable(driver, tableXpath, projectName);
//		 
//		logInfo("Table Rows Count is: " + searchResult.size());
//		logInfo("Project Number : " + searchResult.get(0));
//		logInfo("Project Name : " + searchResult.get(1));
//		logInfo("Project State : " + searchResult.get(10));
		
//		Assert.assertEquals(searchResult.get(10), "Active", searchResult.get(1) + "is not an Active Project" );
//		String projectID = "//*[@id='report_table_projects-active-report']/tbody/tr[1]//a[contains(text(),'"+searchResult.get(0)+"')]";
//		WebElement element  = driver.findElement(By.xpath(projectID));
//		element.click();
//		threadsleep(10000);
//		boolean isProjectOpened = controlActions.isElementDisplayedOnPage(cp.ManagerDetailsTab);
//		IsTrue(isProjectOpened, "Selected Project Opened Successfully", "Failed to Open Selected Project");
//		cp.ManagerDetailsTab.click();
//		boolean isManagerDetailsTabOpened = controlActions.isElementDisplayedOnPage(cp.AddSubManagerBtn);
//		IsTrue(isManagerDetailsTabOpened, "Manager Details Tab Opend Successfully", "Failed to Open Manager Details Tab");
		
		String subMangerName=rowData.get("SubManager").trim();
		String SubMangerStarDate=rowData.get("ProjectStartDate").trim();
		cp.AddSubManagerBtn.click();
		threadsleep(4000);
		boolean subManagerAdded = cp.addSubManager(driver, subMangerName, tcRowNum);
		IsTrue(subManagerAdded, "add Manager Details", "Failed to add Manager Details");
		threadsleep(4000);
		op.switchToDefaultContent(driver);
		cp.waitSwitch();
		logInfo("Sub Manager added" + cp.subMangerRoleVerify());
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
	}

	
	@AfterClass
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}
