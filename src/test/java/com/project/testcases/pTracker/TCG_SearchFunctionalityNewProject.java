package com.project.testcases.pTracker;

import org.testng.annotations.Test;

import com.project.pTracker.Utils.ExcelUtils;
import com.project.pTracker.Utils.Operations;
import com.project.pageobjects.pTracker.ActionProjectsPage;
import com.project.pageobjects.pTracker.NewProjectsPage;
import com.project.pageobjects.pTracker.PL_ActiveProjectsPage;
import com.project.pageobjects.pTracker.PL_ClosedProjectsPage;
import com.project.pageobjects.pTracker.PTrackerLoginPage;
import com.project.utilities.ControlActions;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.Assert;
import com.project.testbase.TestBase;


public class TCG_SearchFunctionalityNewProject extends TestBase{
	WebDriverWait wait;
	PTrackerLoginPage loginPage;
	NewProjectsPage newProject;
	PL_ClosedProjectsPage closedProject;
	ControlActions controlActions;
	Operations op ;
	private String uName = "admin";
	private String uPassword = "admin";
	private String eName = "Mahajan, Milind";
	private static final int DELAY = 10;
	String expectedFileName = "New_Projects.csv";
	String projectNumber;
	String invalidText;
	String partialText;
	String noElementFoundMessage = "No project has been found.";
    String workspace = System.getProperty("user.dir");
	String datapoolPath = workspace+"\\test-data-files\\UI-TestData\\TC_CreateFixedPriceNewProject.xls";
	String inputDataFilePath = workspace+"\\test-data-files\\UI-TestData\\TC_SearchFunctionalityPTrackerPageData.xls";
	String sheetName = "Automation";
	
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
		wait = new WebDriverWait(driver, DELAY);
	  	op = new Operations(driver);
		controlActions = new ControlActions(driver);
		loginPage = new PTrackerLoginPage(driver);
		newProject = new NewProjectsPage(driver);
		loginPage.waitForPageLoaded(driver);
		int tcRowNum = ExcelUtils.getRowNum(inputDataFilePath,"inputData","TestName","TCG_SearchFunctionalityNewProject");
		HashMap<String, String> inputData = ExcelUtils.getTestDataXls(inputDataFilePath, "inputData", 0, tcRowNum-1);
		projectNumber = inputData.get("ProjectNumber");
		partialText = inputData.get("PartialSearchText");
		invalidText = inputData.get("InvalidSearchText");
		controlActions.getUrl(prop.getProperty("appl_url_dev"));
		loginPage.TC_Login(driver,uName, uPassword);
		loginPage.TC_ChangeUser(driver,eName);
	}
  
  @Test(priority = 1,description = "Validate Search Functionalites for Valid Input")
  public void verifySearchFeatureValidInputforNewProjects(){
	  	int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_VALID_INPUT_FOR_NEW_PROJECTS");
			IsTrue(newProject.ActiveProjectTabLink.isDisplayed(),"New Project Tab is Displayed","New Project Tab not displayed");
			logInfo("Navigate to New Project Tab");
			op.clickElement(newProject.NewProjectLink);
			wait.until(ExpectedConditions.visibilityOf(newProject.DownloadBtn));
			wait.until(ExpectedConditions.elementToBeClickable(newProject.DownloadBtn));
			IsTrue(newProject.DownloadBtn.isDisplayed(),"Download button is displayed","Download button not displayed");

			op.waitForElementToBeDisplayed(newProject.SearchProjectTxt);
			op.clickElement(newProject.SearchProjectTxt);
			newProject.SearchProjectTxt.sendKeys(projectNumber);
			op.clickElement(newProject.GoBtn);

			threadsleep(3000);

			String projectNumberActual = newProject.ProjectTableRows.get(0).findElement(By.tagName("td")).getText().trim();
			Boolean isElementCountOne = newProject.ProjectTableRows.size() == 1 ;
			IsTrue(isElementCountOne ,"Only one element is displayed in the table","Only one element should be displayed in the table");
			Boolean isElementFound = projectNumberActual.equalsIgnoreCase(projectNumber);
			IsTrue(isElementFound,"Searched element is found at the top of the table","Searched element is not found at the top of the table");
			newProject.SearchProjectTxt.clear();	
			threadsleep(3000);

			if(isElementCountOne && isElementFound) {
				logInfo("Test was Successfully ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");
			}else {
				logInfo("Test Failed ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		}
		 
  }
  
 @Test(priority = 2,description = "Validate Search Functionalites for Partial Input")
  public void verifySearchFeaturePartialInputForNewProjects(){
	  	int tcRowNum = 0;
		Boolean areFiveProjectsDiplayed = false;
		Boolean areMoreThanOneProjectsDiplayed = false;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_PARTIAL_INPUT_FOR_NEW_PROJECTS");
			
			op.clickElement(newProject.SearchProjectTxt);
			newProject.SearchProjectTxt.sendKeys(partialText);
			op.clickElement(newProject.GoBtn);
			threadsleep(3000);

			IsTrue(newProject.ProjectTableRows.size() > 1,"More than 1 Project Reports is displayed","More than 1 Project Reports should be displayed");
			if (newProject.NewProjectPagination.size() > 0) {
				areFiveProjectsDiplayed = newProject.ProjectTableRows.size() == 5;
				IsTrue(areFiveProjectsDiplayed,"Row size is 5","Row size is not 5");
			}else {
				areMoreThanOneProjectsDiplayed = newProject.ProjectTableRows.size() >= 1;
				IsTrue(areMoreThanOneProjectsDiplayed,"Row size more than 1","Row size is not more than 1");
			}

			op.clickElement(newProject.SearchProjectTxt);
			newProject.SearchProjectTxt.clear();	
			threadsleep(1000);
			IsTrue(newProject.ProjectTableRows.size() == 5,"Row size is 5","Row size is not 5");

			if(areFiveProjectsDiplayed || areMoreThanOneProjectsDiplayed) {
				logInfo("Test was Successfully ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");

			}else {
				logInfo("Test Failed ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			Assert.fail();
		}
		 
  }
  
  @Test(priority = 3,description= "Validate Search Functionalites for Invalid Input")
  public void verifySearchFeatureInvalidInputForNewProjects(){
	  	int tcRowNum = 0;
		try {
			tcRowNum = ExcelUtils.getRowNum(datapoolPath,sheetName,"testCase","TC_SEARCH_FEATURE_INVALID_INPUT_FOR_NEW_PROJECTS");
			
			op.clickElement(newProject.SearchProjectTxt);
			newProject.SearchProjectTxt.clear();
			newProject.SearchProjectTxt.sendKeys(invalidText);
			op.clickElement(newProject.GoBtn);
			op.waitForElementToBeClickable(newProject.GoBtn);
			//op.waitUntilElementPresent(newProject.NoProjectMessage);
			Boolean isNoProjectTextDisplayed= driver.findElement(By.xpath("//span[contains(text(),'No project has been found')]")).isDisplayed();
			logInfo("Is no project found message is displayed: "+ isNoProjectTextDisplayed);
			logInfo("No project found message is: "+ driver.findElement(By.xpath("//span[contains(text(),'No project has been found')]")).getText());
			//op.waiForTextToBePresentInElementValue(newProject.NoProjectMessage, "No project has been found");
			// Validate for No Row in Search Result
			Boolean areNoElementsDisplayed = newProject.ProjectTableRows.size() == 0;
			IsTrue(areNoElementsDisplayed,"No Project Reports is displayed for Invalid entry","No Project Reports should be displayed for Invalid entry");
			// Validate for "No project has been found" Message
			Boolean isNoElementFoundMessageDisplayed = newProject.NoProjectMessage.isDisplayed();
			IsTrue(isNoElementFoundMessageDisplayed,"No Project Reports is displayed for Invalid entry","No Project Reports should be displayed for Invalid entry");
			
			Boolean isNoElementFoundTextCorrect = newProject.NoProjectMessage.getText().trim().equalsIgnoreCase(noElementFoundMessage);
			IsTrue(isNoElementFoundTextCorrect,"'No project has been found.' is displayed for invalid entry","'No project has been found.' should be displayed for invalid entry");
			
			Boolean areNoPaginationsDisplayed = newProject.NewProjectPagination.size() == 0;
			IsTrue(areNoPaginationsDisplayed,"Pagination is not displayed","Pagination should not be displayed");

			op.clickElement(newProject.SearchProjectTxt);
			newProject.SearchProjectTxt.clear();	
			threadsleep(3000);
			

			if(areNoElementsDisplayed && isNoElementFoundTextCorrect && areNoPaginationsDisplayed) {
				logInfo("Test Case was Successfully ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "PASS", "GREEN");

			}else {
				logInfo("Test Case Failed ");
				ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logError("Error "+e.getMessage());
			ExcelUtils.setCellData(datapoolPath, "Status", tcRowNum, "FAIL", "RED");
			//Assert.fail();
		}  
  }
  
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			op.closeBrowser(driver);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
