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
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;
import com.project.utilities.ExcelReader;
import com.project.pTracker.Utils.Constants;

public class TCG_NewProjectSubmitByPM extends TestBase {

	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	ControlActions controlActions;
	ExcelReader reader;
	Operations op ;
	private String uName = "admin";
	private String uPassword = "admin";
	private static final int DELAY = 100;
	//String eName = "Mahajan, Milind";
	String eName = "Maydeo, Parag Prakash";
	int tcRowNum; 
    String workspace = Constants.workspace;
	String datapoolPath = Constants.datapoolPath;
	String sheetName = Constants.sheetName;
		
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
		op = new Operations(driver);
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}

	@Test(groups = {"sanity", "regression"}, description = "Create Fixed Price New Project")
	public void submitNewFixedPriceProjectByPM() throws Exception {
		try
		{
			String sheetName = "Automation";
			reader = new ExcelReader();
			String projectState = "SUBMIT"; //DRAFT
			int header=0; //Excel first row is 0
			tcRowNum = 3; // Test Case Row No Is: 4
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_NEW_FIXED_PRICE_PROJECT_SUBMIT_BY_PM");
			logInfo("Test Case Row No Is: " + tcRowNum);
			logInfo("Reading Excel:   "+datapoolPath);
			 if (newProject.createNewProject()) 
			 {
				 	 newProject.fillProjectCreation(datapoolPath,sheetName,projectState,tcRowNum); 
				 	 threadsleep(2000);
					 HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, header, tcRowNum-1);
					 String projectName = rowData.get("ActualProjectName");
					 logInfo("Searching for the Project : " + projectName);
					 //driver.findElement(By.xpath(SEARCH_INPUT_XPATH)).clear();
					 driver.findElement(By.xpath("//*[@id='P30_NP_FC_SEARCH']")).sendKeys("");
					 driver.findElement(By.xpath("//*[@id='P30_NP_FC_SEARCH']")).sendKeys(projectName);
					 driver.findElement(By.xpath("//*[@id='B604615927023884937' and contains(text(),'GO')]")).click();
					 threadsleep(3000);
					 String xpathLocator = "//*[@id='report_table_New-Project-Request']/tbody/tr/td";
					 List<String> ElementsList = op.searchReportTable(driver, xpathLocator, projectName);

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
			
		}
		catch(Exception e) 
		{
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			logError("Message " + e.getMessage());
		}

	}


	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException, IOException {
		op.closeBrowser(driver);
	}
}
