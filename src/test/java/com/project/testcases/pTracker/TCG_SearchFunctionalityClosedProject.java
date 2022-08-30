package com.project.testcases.pTracker;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ClosedProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.testbase.TestBase;
import com.project.utilities.ControlActions;

public class TCG_SearchFunctionalityClosedProject extends TestBase{
	
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	PL_ClosedProjectsPage closedProject;
	ControlActions controlActions;
	Operations actions ;
	private String uName = "abc";
	private String uPassword = "xyz";
	private String eName = "Mahajan, Milind";
	private static final int DELAY = 10;
	String expectedFileName = "Closed_Projects.csv";
	String projectNumber;
	String invalidText;
	String partialText;
	String noElementFoundMessage = "No project has been found.";
    String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	String inputDataFilePath = workspace+"\\test-data-files\\UI-TestData\\TC_SearchFunctionalityPTrackerPageData.xls";
	String sheetName = "Automation";
	
  @BeforeClass
	public void groupInit() throws Exception {
	  	driver = launchbrowser();
		driver.manage().timeouts().implicitlyWait(DELAY, TimeUnit.SECONDS);		
		driver.manage().timeouts().pageLoadTimeout(DELAY, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(DELAY, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, DELAY);
	  	actions = new Operations(driver);
		controlActions = new ControlActions(driver);
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		closedProject = new PL_ClosedProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		int tcRowNum = ExcelUtils.getRowNum(inputDataFilePath,"inputData","TestName","TCG_SearchFunctionalityClosedProject");
		HashMap<String, String> inputData = ExcelUtils.getTestDataXls(inputDataFilePath, "inputData", 0, tcRowNum-1);
		projectNumber = inputData.get("ProjectNumber");
		partialText = inputData.get("PartialSearchText");
		invalidText = inputData.get("InvalidSearchText");
		
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}
  
  @Test(priority = 1,description = "Validate Search Functionalites for Valid Input")
  public void verifySearchFeatureValidInputforClosedProjects(){
	  	int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_VALID_INPUT_FOR_CLOSED_PROJECTS");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			
			IsTrue(newProject.ClosedProjectTabLink.isDisplayed(),"New Project Tab is Displayed","New Project Tab not displayed");
			logInfo("Navigate to New Project Tab");
			actions.clickElement(newProject.ClosedProjectTabLink);
			wait.until(ExpectedConditions.visibilityOf(closedProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(closedProject.DownloadBtn));
			IsTrue(closedProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");
			
			actions.waitForElementToBeDisplayed(closedProject.SearchProjectTxt);
			actions.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.sendKeys(projectNumber);
			actions.clickElement(closedProject.GoBtn);
			
			//wait.until(ExpectedConditions.invisibilityOf(newProject.TableSpinner));
			threadsleep(3000);

			String projectNumberActual = closedProject.ProjectTableRows.get(0).findElement(By.tagName("td")).getText().trim();
			Assert.assertEquals(closedProject.ProjectTableRows.size(), 1);
			Assert.assertEquals(projectNumberActual, projectNumber,"Searched element is not found at the top of the table");
			closedProject.SearchProjectTxt.clear();	
			threadsleep(3000);
			IsTrue(closedProject.ProjectTableRows.size() == 5,"Row size is 5","Row size is not 5");
			
			logInfo("Project Successfully Submited with Request ID: " + projectName);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		}
		 
  }
  
  @Test(priority = 2,description = "Validate Search Functionalites for Partial Input")
  public void verifySearchFeaturePartialInputForClosedProjects(){
	  	int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_PARTIAL_INPUT_FOR_CLOSED_PROJECTS");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			
			actions.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.sendKeys(partialText);
			actions.clickElement(closedProject.GoBtn);
			threadsleep(3000);

			IsTrue(closedProject.ProjectTableRows.size() > 1,"More than 1 Project Reports is displayed","More than 1 Project Reports should be displayed");
			//Assert.assertTrue(closedProject.NewProjectPagination.size() >= 1,"Pagination should be displayed");
			
			actions.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.clear();	
			threadsleep(3000);
			
			IsTrue(closedProject.ProjectTableRows.size() == 5,"Row size is 5","Row size is not 5");
			
			logInfo("Project Successfully Submited with Request ID: " + projectName);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		}
		 
  }
  
  @Test(priority = 3,description= "Validate Search Functionalites for Invalid Input")
  public void verifySearchFeatureInvalidInputForClosedProjects(){
	  	int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_INVALID_INPUT_FOR_CLOSED_PROJECTS");
			HashMap<String, String> rowData = ExcelUtils.getTestDataXls(datapoolPath, sheetName, 0, tcRowNum-1);
			String projectName = rowData.get("ProjectName");
			
			actions.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.clear();
			closedProject.SearchProjectTxt.sendKeys(invalidText);
			actions.clickElement(closedProject.GoBtn);
			threadsleep(3000);
			
			IsTrue(closedProject.ProjectTableRows.size() == 0,"No Project Reports are displayed for Invalid entry","No Project Reports should be displayed for Invalid entry");
			IsTrue(newProject.NoProjectMessage.isDisplayed(),"No Project Reports are displayed for Invalid entry","No Project Reports should be displayed for Invalid entry");
			Assert.assertEquals(newProject.NoProjectMessage.getText().trim(),noElementFoundMessage ,"'No project has been found.' should be displayed for invalid entry");
			//Assert.assertTrue(newProject.NewProjectPagination.size() == 0,"Pagination should not be displayed");
			
			actions.clickElement(closedProject.SearchProjectTxt);
			closedProject.SearchProjectTxt.clear();	
			threadsleep(3000);
			
			IsTrue(closedProject.ProjectTableRows.size() == 5,"Row size is 5","Row size is not 5");
			
			logInfo("Project Successfully Submited with Request ID: " + projectName);
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		}
		 
  }

}
