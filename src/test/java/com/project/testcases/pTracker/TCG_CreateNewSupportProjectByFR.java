package com.project.testcases.pTracker;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import com.project.pageobjects.pTracker.CommonPages;
import com.project.pageobjects.pTracker.FinanceRepresentativePage;

import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.utilities.ExcelReader;

public class TCG_CreateNewSupportProjectByFR extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	FinanceRepresentativePage frp;
	ControlActions controlActions;
	Operations op ;
	CommonPages cp;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 100;
	String eName = "Bhujbal, Sameer";
	String roleFR = "Finance"; 
	ExcelReader reader;
		
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		// setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver = launchbrowser();
        String currentWindow = driver.getWindowHandle();
        driver.switchTo().window(currentWindow);
	   // setting up property to suppress the warning
		System.setProperty("webdriver.chrome.silentOutput","true");
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
		controlActions = new ControlActions(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		frp = new FinanceRepresentativePage(driver);
		op = new Operations(driver);
		cp = new CommonPages(driver);
		loginPage.waitForPageLoaded(driver);
		//loginPage.TC_Login(driver,uName, uPassword);
		loginPage.login(driver,uName, uPassword);
		loginPage.userChange(driver, eName);
		//loginPage.openPride(roleFR);
		loginPage.selectRole(roleFR);
		//loginPage.TC_ChangeUser(driver,eName);
	}

	@Test(groups = {"sanity", "regression"}, description = "Create Fixed Price New Project By Finance Representative")
	public void createNewSupportProjectByFR() throws Exception {
		reader = new ExcelReader();
		int tcRowNum; 
		String projectState = "SUBMIT"; //DRAFT
		// Prepare the path of excel file
	    String workspace = System.getProperty("user.dir");
		String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
		String sheetName = "Automation";
		int header=0; //Excel first row is 0
		tcRowNum = 3; // Test Case Row No Is: 4
		tcRowNum = ExcelUtils.getRowNum(datapoolPath,"Automation","testCase","TC_CREATE_NEW_SUPPORT_PROJECT_BY_FR");
		logInfo("Test Case Row No Is: " + tcRowNum);
		logInfo("Reading Excel:   "+datapoolPath);
		boolean isCreateNewProjOpened = frp.createNewProject();
		IsTrue(isCreateNewProjOpened, "Create New Project Page Opened Successfully", "Failed to Open Create New Projecct Page");
		 if (isCreateNewProjOpened) 
		 {
			 	 frp.fillProjectCreation(datapoolPath,sheetName,projectState,tcRowNum); 
			 	 threadsleep(5000);
				 HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
				 String projectName = rowData.get("ActualProjectName");
				 logInfo("Searching for the Project : " + projectName);
		         op.waitForAnElementToBeClickable(cp.ActiveProjectGoBtn);
				 op.clickElement(cp.ActiveProjectSearchTxt,driver);
				 op.sendKeys(cp.ActiveProjectSearchTxt, projectName);
				 op.clickElement(cp.ActiveProjectGoBtn,driver);
				 threadsleep(2000);
				 String xpathLocator = "//*[@id='report_table_projects-active-report']/tbody/tr/td";
				 List<String> ElementsList = op.searchReportTable(driver, xpathLocator, projectName);
				 if(ElementsList.size() > 0 )
                 {
					 logInfo("List Size is: " + ElementsList.size());
					 logInfo("Project ID : " + ElementsList.get(0));
					 logInfo("Project Name : " + ElementsList.get(1));
					 logInfo("Project State : " + ElementsList.get(9));
//					 int size = ElementsList.size();
//						for (int i = 0; i < size; i++)
//						{
//							logInfo( i + " -----> " + ElementsList.get(i));
//						}
					 threadsleep(2000);
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
				 else { 
					 logError("Fail to Saved/Submited Project."); 
					 ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
					 } 
			}
	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException, IOException {
		op.closeBrowser(driver);
	}
}
